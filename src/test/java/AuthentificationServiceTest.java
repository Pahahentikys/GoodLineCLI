import general.dao.UserInfoDAO;
import general.dom.UserInfo;
import general.serv.AuthenticationService;
import org.junit.Before;
import org.mockito.Mockito;


public class AuthentificationServiceTest {

    // Сервис, ответственный за аутентификацию юзера.
    private AuthenticationService authenticationService;

    @Before
    public void setDataAboutUser() {

        // Класс для доступа к данным в БД
        UserInfoDAO userInfoDAO = Mockito.mock(UserInfoDAO.class);

        // Дефолтный юзер, чтобы его мокнуть
        UserInfo userInfo = new UserInfo()
                .withUserLogin("jdoe")
                .withUserHashPassword("d824656e5bb0902512e1e8a7f1a099cb")
                .withUserSalt("+NSw]7");

        // Сюда будет подставляеться юзер через метод в классе AuthentificationService
        authenticationService = new AuthenticationService();

    }

}
