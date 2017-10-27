import general.ExitCodeType;
import general.dao.AccountingDAO;
import general.dao.UserInfoDAO;
import general.dao.UserResourceDAO;
import general.dom.*;
import general.serv.AuthenticationService;
import general.serv.AuthorizationService;
import general.serv.DataValidator;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.sql.SQLException;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Mockito.when;

public class AccountingServiceTest {

    // Класс, отвечающий за валидацию дат и объёма.
    DataValidator dataValidator = new DataValidator();

    // Класс, фиксирующий состояние сеанса юзера.
    Accounting accounting = new Accounting();

    // Сервис, ответственный за аутентификацию пользователя.
    private AuthenticationService authenticationService;

    // Сервис, ответственный за авторизацию пользователя.
    private AuthorizationService authorizationService;

    // Слой данных, содержащий данные о пользователе.
    private UserInfoDAO userInfoDAO;

    // Слой данных, содержащий ресурсы пользователя.
    private UserResourceDAO userResourceDAO;

    // Слой данных, фиксирующий пользовательский сеанс.
    private AccountingDAO accountingDAO;

    // Стоп-код при выолнении программы.
    private int exitCode;

    // Стоп-код успешности/неуспешности выполнения аутентификации.
    private int userAuthCode;

    // Стоп-код успешности/неуспешности выполнения авторизации.
    private int userAuthorizationCode;


    @Before
    public void setDefaultParamsForTests() throws SQLException {

        accounting = new Accounting();

        authorizationService = new AuthorizationService();

        authenticationService = new AuthenticationService();

        // Дефолтный юзер, чтобы его мокнуть
        UserInfo userInfo = new UserInfo()
                .withUserLogin("jdoe")
                .withUserHashPassword("d824656e5bb0902512e1e8a7f1a099cb")
                .withUserSalt("+NSw]7");

        // Дефолтный ресурс для дальнейшего мока.
        UserResources userResourceAAndB = new UserResources()
                .withUserRole(UserRoles.READ)
                .withUserResResId(1)
                .setResourcePath("a.b");

        // Мокаем группу слоёв доступа к данным.
        userInfoDAO = Mockito.mock(UserInfoDAO.class);
        userResourceDAO = Mockito.mock(UserResourceDAO.class);
        accountingDAO = Mockito.mock(AccountingDAO.class);


        // Mock на ситуацию, когда пользователь с таким логином найден в БД
        when(userInfoDAO.searchUserLogin("jdoe")).thenReturn(userInfo);

        when(userResourceDAO.findIdRes(userResourceAAndB.getResourcePath())).thenReturn(userResourceAAndB);

        when(userResourceDAO.getPathUserResource("a.b", "READ")).thenReturn(userResourceAAndB);
    }

    @Test
    public void accountinTestValid() throws SQLException {

        UserInputData userInputData = new UserInputData("jdoe", "sup3rpaZZ",
                "a.b", "READ", "2017-10-19", "2017-10-19", "100");
        String userLogin = userInputData.getUserInputLogin();
        String userPass = userInputData.getUserInputPassword();
        String userRole = userInputData.getUserInputRole();
        String userPath = userInputData.getUserInputPathResource();

        userAuthCode = authenticationService.isUserAuthentification(userInfoDAO, userLogin,
                userPass);

        userAuthorizationCode = authorizationService.isUserAuthorization(userResourceDAO, userPath, userRole, userAuthCode);

        exitCode = authorizationService.isUserAccounting(accounting, userResourceDAO, userInputData,
                dataValidator, userAuthorizationCode);

        assertEquals(exitCode, ExitCodeType.SUCCESS.getExitCode());
    }

    @Test
    public void accountinTestInvalidDate() throws SQLException {

        UserInputData userInputData = new UserInputData("jdoe", "sup3rpaZZ",
                "a.b", "READ", "xxxx-10-19", "2017-10-19", "100");
        String userLogin = userInputData.getUserInputLogin();
        String userPass = userInputData.getUserInputPassword();
        String userRole = userInputData.getUserInputRole();
        String userPath = userInputData.getUserInputPathResource();

        userAuthCode = authenticationService.isUserAuthentification(userInfoDAO, userLogin,
                userPass);

        userAuthorizationCode = authorizationService.isUserAuthorization(userResourceDAO, userPath, userRole, userAuthCode);

        exitCode = authorizationService.isUserAccounting(accounting, userResourceDAO, userInputData,
                dataValidator, userAuthorizationCode);

        assertEquals(exitCode, ExitCodeType.INVALID_ACTION.getExitCode());
    }

    @Test
    public void accountinTestInvalidVolume() throws SQLException {

        UserInputData userInputData = new UserInputData("jdoe", "sup3rpaZZ",
                "a.b", "READ", "2017-10-19", "2017-10-19", "xxx");
        String userLogin = userInputData.getUserInputLogin();
        String userPass = userInputData.getUserInputPassword();
        String userRole = userInputData.getUserInputRole();
        String userPath = userInputData.getUserInputPathResource();

        userAuthCode = authenticationService.isUserAuthentification(userInfoDAO, userLogin,
                userPass);

        userAuthorizationCode = authorizationService.isUserAuthorization(userResourceDAO, userPath, userRole, userAuthCode);

        exitCode = authorizationService.isUserAccounting(accounting, userResourceDAO, userInputData,
                dataValidator, userAuthorizationCode);

        assertEquals(exitCode, ExitCodeType.INVALID_ACTION.getExitCode());
    }
}
