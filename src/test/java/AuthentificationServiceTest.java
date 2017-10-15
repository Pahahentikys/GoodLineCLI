import general.ExitCodeType;
import general.dao.UserInfoDAO;
import general.dom.UserInfo;
import general.dom.UserInputData;
import general.serv.AuthenticationService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.sql.SQLException;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Mockito.when;


public class AuthentificationServiceTest {

    // Сервис, ответственный за аутентификацию юзера.
    private AuthenticationService authenticationService;

    private UserInfoDAO userInfoDAO;

    // Стоп-код при выолнении программы
    private int exitCode;

    @Before
    public void setDefaultParamsForTests() throws SQLException {
        authenticationService = new AuthenticationService();

        // Дефолтный юзер, чтобы его мокнуть
        UserInfo userInfo = new UserInfo()
                .withUserLogin("jdoe")
                .withUserHashPassword("d824656e5bb0902512e1e8a7f1a099cb")
                .withUserSalt("+NSw]7");

        userInfoDAO = Mockito.mock(UserInfoDAO.class);

        // Mock на ситуацию, когда пользователь с таким логином найден в БД
        when(userInfoDAO.searchUserLogin("jdoe")).thenReturn(userInfo);

        // Mock на ситуацию, когда пользователь с таким логином найден в БД
        when(userInfoDAO.searchUserLogin("xxx")).thenReturn(null);
    }

    @Test
    public void testInvalidLogin() throws SQLException {

        UserInputData userInputData = new UserInputData("xxx", "sup3rpaZZ");
        String userLogin = userInputData.getUserInputLogin();
        String userPass = userInputData.getUserInputPassword();

        exitCode = authenticationService.isUserAuthentification(userInfoDAO, userLogin,
                userPass);

        assertEquals(exitCode, ExitCodeType.INVALID_LOGIN.getExitCode());
    }

    @Test
    public void testInvalidPassword() throws SQLException {

        UserInputData userInputData = new UserInputData("jdoe", "xxx");
        String userLogin = userInputData.getUserInputLogin();
        String userPass = userInputData.getUserInputPassword();

        exitCode = authenticationService.isUserAuthentification(userInfoDAO, userLogin,
                userPass);

        assertEquals(exitCode, ExitCodeType.INVALID_PASSWORD.getExitCode());
    }

    @Test
    public void testValidLoginAndPassword() throws SQLException {
        UserInputData userInputData = new UserInputData("jdoe", "sup3rpaZZ");
        String userLogin = userInputData.getUserInputLogin();
        String userPass = userInputData.getUserInputPassword();

        exitCode = authenticationService.isUserAuthentification(userInfoDAO, userLogin,
                userPass);

        assertEquals(exitCode, ExitCodeType.SUCCESS.getExitCode());
    }
}
