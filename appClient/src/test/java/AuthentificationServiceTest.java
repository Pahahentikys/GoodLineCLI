import general.ExitCodeType;
import general.dao.UserInfoDAO;
import general.dom.UserInfo;
import general.dom.UserInputData;
import general.serv.DataBaseContext;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.sql.SQLException;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Mockito.when;


public class AuthentificationServiceTest {

    // Класс, обеспечивающий доступ к слоям с данными.
    private DataBaseContext dataBaseContext;

    // Слой данных, содержащийи информацию о пользователе.
    private UserInfoDAO userInfoDAO;

    @Before
    public void setDefaultParamsForTests() throws SQLException {

        dataBaseContext = new DataBaseContext();

        // Дефолтный юзер, чтобы его мокнуть
        UserInfo userInfo = UserInfo.builder()
                .userLogin("jdoe")
                .userHashPassword("d824656e5bb0902512e1e8a7f1a099cb")
                .userSalt("+NSw]7")
                .build();

        userInfoDAO = Mockito.mock(UserInfoDAO.class);

        // Mock на ситуацию, когда пользователь с таким логином найден в БД
        when(userInfoDAO.searchUserLogin("jdoe")).thenReturn(userInfo);

        // Mock на ситуацию, когда пользователь с таким логином найден в БД
        when(userInfoDAO.searchUserLogin("xxx")).thenReturn(null);
    }

    @Test
    public void testInvalidLogin() throws SQLException {

        UserInputData userInputData = UserInputData.builder()
                .userInputLogin("xxx")
                .build();

        String userLogin = userInputData.getUserInputLogin();

        assertEquals(ExitCodeType.INVALID_LOGIN.getExitCode(), dataBaseContext.hasGetUserLoginDAO(userInfoDAO, userLogin));
    }

    @Test
    public void testInvalidPassword() throws SQLException {

        UserInputData userInputData =  UserInputData.builder()
                .userInputLogin("jdoe")
                .userInputPassword("xxx")
                .build();

        String userLogin = userInputData.getUserInputLogin();
        String userPass = userInputData.getUserInputPassword();

        assertEquals(ExitCodeType.INVALID_PASSWORD.getExitCode(), dataBaseContext.hasGetUserPasswordDAO(userInfoDAO, userLogin, userPass));

    }

    @Test
    public void testValidLoginAndPassword() throws SQLException {

        UserInputData userInputData = UserInputData.builder()
                .userInputLogin("jdoe")
                .userInputPassword("sup3rpaZZ")
                .build();

        String userLogin = userInputData.getUserInputLogin();
        String userPass = userInputData.getUserInputPassword();

        assertEquals(ExitCodeType.SUCCESS.getExitCode(), dataBaseContext.hasGetUserPasswordDAO(userInfoDAO, userLogin, userPass));

    }

}
