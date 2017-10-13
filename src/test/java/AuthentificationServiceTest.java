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

    @Before
    public void setDataAboutUser() {

        authenticationService = new AuthenticationService();
    }

    @Test
    public void testLoginValid() throws SQLException {

        // Дефолтный юзер, чтобы его мокнуть
        UserInfo userInfo = new UserInfo()
                .withUserLogin("jdoe")
                .withUserHashPassword("d824656e5bb0902512e1e8a7f1a099cb")
                .withUserSalt("+NSw]7");

        String userLogin = userInfo.getUserLogin();
        String userPass = userInfo.getUserHashPassword();

        UserInputData userInputData = new UserInputData("jdoe", "sup3rpaZZ");

        // Класс для доступа к данным в БД
        UserInfoDAO userInfoDAO = Mockito.mock(UserInfoDAO.class);

        // Mock на ситуацию, когда пользователь с таким логином найден в БД
        when(userInfoDAO.searchUserLogin("jdoe")).thenReturn(userInfo);

        // Mock на ситуацию, когда пользователь с таким логином найден в БД
        when(userInfoDAO.searchUserLogin("xxx")).thenReturn(null);

        assertEquals(true, authenticationService.isUserAuthentification(userInfoDAO, userLogin, userPass));
    }

}
