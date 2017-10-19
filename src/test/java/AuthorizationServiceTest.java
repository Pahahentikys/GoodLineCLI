import general.ExitCodeType;
import general.dao.UserInfoDAO;
import general.dao.UserResourceDAO;
import general.dom.UserInfo;
import general.dom.UserInputData;
import general.dom.UserResources;
import general.serv.AuthenticationService;
import general.serv.AuthorizationService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.sql.SQLException;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Mockito.when;

/**
 * Created by Pavel on 17.10.2017.
 */
public class AuthorizationServiceTest {

    // Сервис, ответственный за аутентификацию пользователя.
    private AuthenticationService authenticationService;

    // Сервис, ответственный за авторизацию пользователя.
    private AuthorizationService authorizationService;

    // Слой данных, содержащий данные о пользователе.
    private UserInfoDAO userInfoDAO;

    // Слой данных, содержащий ресурсы пользователя.
    private UserResourceDAO userResourceDAO;

    // Стоп-код при выолнении программы.
    private int exitCode;

    // Стоп-код успешности/неуспешности выполнения аутентификации.
    private int userAuthCode;


    @Before
    public void setDefaultParamsForTests() throws SQLException {

        authorizationService = new AuthorizationService();

        authenticationService = new AuthenticationService();

        // Дефолтный юзер, чтобы его мокнуть
        UserInfo userInfo = new UserInfo()
                .withUserLogin("jdoe")
                .withUserHashPassword("d824656e5bb0902512e1e8a7f1a099cb")
                .withUserSalt("+NSw]7");

        // Дефолтный ресурсы для дальнейшего мока.
        UserResources userResourceA = new UserResources()
                .setResourcePath("A");

        // Дефолтный ресурсы для дальнейшего мока.
        UserResources userResourceAAndB = new UserResources()
                .setResourcePath("A.B");

        // Мокаем группу слоёв доступа к данным.
        userInfoDAO = Mockito.mock(UserInfoDAO.class);
        userResourceDAO = Mockito.mock(UserResourceDAO.class);

        // Mock на ситуацию, когда пользователь с таким логином найден в БД
        when(userInfoDAO.searchUserLogin("jdoe")).thenReturn(userInfo);

        // Mock на ситуацию, когда пользователь с правами доступа на ресурс найден в БД.
        when(userResourceDAO.getPathUserResource("a", "READ")).thenReturn(userResourceA);

        // Mock на ситуацию, когда пользователь с правами доступа на ресурс найден в БД.
        when(userResourceDAO.getPathUserResource("a.b", "READ")).thenReturn(userResourceAAndB);

        // Mock на ситуацию, когда задан некорректный ресурс для доступа пользователя.
        when(userResourceDAO.getPathUserResource("a.b", "xxx")).thenReturn(userResourceAAndB);
    }

    @Test
    public void testValidAuthorizationToResA() throws SQLException {

        UserInputData userInputData = new UserInputData("jdoe", "sup3rpaZZ", "READ", "a");
        String userLogin = userInputData.getUserInputLogin();
        String userPass = userInputData.getUserInputPassword();
        String userRole = userInputData.getUserInputRole();
        String userPath = userInputData.getUserInputPathResource();

        userAuthCode = authenticationService.isUserAuthentification(userInfoDAO, userLogin,
                userPass);

        exitCode = authorizationService.isUserAuthorization(userResourceDAO, userPath, userRole, userAuthCode);


        assertEquals(exitCode, ExitCodeType.SUCCESS.getExitCode());
    }
}