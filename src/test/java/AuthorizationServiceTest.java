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

public class AuthorizationServiceTest {

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

        // Mock на ситуацию, когда пользователь пытается зайти на ресурс с отсутствием необходимых прав доступа.
        when(userResourceDAO.getPathUserResource("a", "WRITE")).thenReturn(null);

        // Mock на ситуацию, когда пользователь с правами доступа на ресурс найден в БД.
        when(userResourceDAO.getPathUserResource("a.b", "READ")).thenReturn(userResourceAAndB);

        // Mock на ситуацию, когда задана некорректная роль у пользователя.
        when(userResourceDAO.getPathUserResource("a.b", "xxx")).thenReturn(userResourceAAndB);

        when(userResourceDAO.getPathUserResource("a", "xxx")).thenReturn(userResourceA);

        //Mock на ситуацию, когда юзер с корректной ролью пытается получить доступ к некорректному ресурсу.
        when(userResourceDAO.getPathUserResource("xxx", "READ")).thenReturn(null);

        // Mock на ситуацию, когда к корректному ресурсу юзер пытается получить доступ с отсутствующей у него ролью.
        when(userResourceDAO.getPathUserResource("a.b", "EXECUTE")).thenReturn(null);
    }

    @Test
    public void testValidAuthorizationToResA() throws SQLException {

        UserInputData userInputData = new UserInputData()
                .withUserInputRole("READ")
                .withUserInputPathResource("a");
        String userRole = userInputData.getUserInputRole();
        String userPath = userInputData.getUserInputPathResource();

        authentificateUser();

        exitCode = authorizationService.isUserAuthorization(userResourceDAO, userPath, userRole, userAuthCode);

        assertEquals(exitCode, ExitCodeType.SUCCESS.getExitCode());
    }

    @Test
    public void testWithInvalidAccessAuthorizationToResA() throws SQLException {

        UserInputData userInputData = new UserInputData()
                .withUserInputRole("WRITE")
                .withUserInputPathResource("a");
        String userRole = userInputData.getUserInputRole();
        String userPath = userInputData.getUserInputPathResource();

        authentificateUser();

        exitCode = authorizationService.isUserAuthorization(userResourceDAO, userPath, userRole, userAuthCode);

        assertEquals(exitCode, ExitCodeType.INVALID_ACCESS.getExitCode());
    }

    @Test
    public void testValidAuthorizationToResAB() throws SQLException {

        UserInputData userInputData = new UserInputData()
                .withUserInputRole("READ")
                .withUserInputPathResource("a.b");
        String userRole = userInputData.getUserInputRole();
        String userPath = userInputData.getUserInputPathResource();

        authentificateUser();

        exitCode = authorizationService.isUserAuthorization(userResourceDAO, userPath, userRole, userAuthCode);

        assertEquals(exitCode, ExitCodeType.SUCCESS.getExitCode());
    }

    @Test
    public void testAuthorizationInvalidRole() throws SQLException {

        UserInputData userInputData = new UserInputData()
                .withUserInputRole("xxx")
                .withUserInputPathResource("a");
        String userRole = userInputData.getUserInputRole();
        String userPath = userInputData.getUserInputPathResource();

        authentificateUser();

        exitCode = authorizationService.isUserAuthorization(userResourceDAO, userPath, userRole, userAuthCode);

        assertEquals(exitCode, ExitCodeType.INVALID_ROLE.getExitCode());
    }

    @Test
    public void testAuthorizationInvalidResPath() throws SQLException {

        UserInputData userInputData = new UserInputData()
                .withUserInputRole("READ")
                .withUserInputPathResource("xxx");
        String userRole = userInputData.getUserInputRole();
        String userPath = userInputData.getUserInputPathResource();

        authentificateUser();

        exitCode = authorizationService.isUserAuthorization(userResourceDAO, userPath, userRole, userAuthCode);

        assertEquals(exitCode, ExitCodeType.INVALID_ACCESS.getExitCode());
    }

    @Test
    public void testAuthorizationConnectToResourceWithOtherRole() throws SQLException {

        UserInputData userInputData = new UserInputData()
                .withUserInputRole("EXECUTE")
                .withUserInputPathResource("a.b");
        String userRole = userInputData.getUserInputRole();
        String userPath = userInputData.getUserInputPathResource();

        authentificateUser();

        exitCode = authorizationService.isUserAuthorization(userResourceDAO, userPath, userRole, userAuthCode);

        assertEquals(exitCode, ExitCodeType.INVALID_ACCESS.getExitCode());
    }

    private int authentificateUser() throws SQLException {

        AuthenticationService authenticationService = new AuthenticationService();

        UserInputData userInputData = new UserInputData()
                .withUserInputLogin("jdoe")
                .withUserInputPassword("sup3rpaZZ");
        String userLogin = userInputData.getUserInputLogin();
        String userPass = userInputData.getUserInputPassword();


        if (authenticationService.isUserAuthentification(userInfoDAO, userLogin,
                userPass) == ExitCodeType.SUCCESS.getExitCode()) {

            return userAuthCode = ExitCodeType.SUCCESS.getExitCode();
        }

        return userAuthCode = ExitCodeType.INVALID_ACCESS.getExitCode();
    }
}