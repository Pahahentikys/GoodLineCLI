import general.ExitCodeType;
import general.dao.UserInfoDAO;
import general.dao.UserResourceDAO;
import general.dom.UserInfo;
import general.dom.UserInputData;
import general.dom.UserResources;
import general.serv.DataBaseContext;
import general.serv.DataValidator;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.sql.SQLException;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Mockito.when;

public class AuthorizationServiceTest {

    // Слой, обеспечивающий доступ к данным.
    private DataBaseContext dataBaseContext;

    // Слой данных, содержащий ресурсы пользователя.
    private UserResourceDAO userResourceDAO;

    @Before
    public void setDefaultParamsForTests() throws SQLException {

        // Дефолтный юзер, чтобы его мокнуть
        UserInfo userInfo = UserInfo.builder()
                .userLogin("jdoe")
                .userHashPassword("d824656e5bb0902512e1e8a7f1a099cb")
                .userSalt("+NSw]7")
                .build();

        // Дефолтный ресурсы для дальнейшего мока.
        UserResources userResourceA = UserResources.builder()
                .resourcePath("A")
                .build();

        // Дефолтный ресурсы для дальнейшего мока.
        UserResources userResourceAAndB = UserResources.builder()
                .resourcePath("A.B")
                .build();

        // Мокаем группу слоёв доступа к данным.
        UserInfoDAO userInfoDAO = Mockito.mock(UserInfoDAO.class);
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

        dataBaseContext = new DataBaseContext();

        UserInputData userInputData = UserInputData.builder()
                .userInputRole("READ")
                .userInputPathResource("a")
                .build();
        String userRole = userInputData.getUserInputRole();
        String userPath = userInputData.getUserInputPathResource();

        assertEquals(ExitCodeType.SUCCESS.getExitCode(), dataBaseContext.hasResUserAccessDAO(userResourceDAO, userPath, userRole));
    }

    @Test
    public void testWithInvalidAccessAuthorizationToResA() throws SQLException {

        dataBaseContext = new DataBaseContext();

        UserInputData userInputData = UserInputData.builder()
                .userInputRole("WRITE")
                .userInputPathResource("a")
                .build();

        String userRole = userInputData.getUserInputRole();
        String userPath = userInputData.getUserInputPathResource();

        assertEquals(ExitCodeType.INVALID_ACCESS.getExitCode(), dataBaseContext.hasResUserAccessDAO(userResourceDAO, userPath, userRole));
    }


    @Test
    public void testValidAuthorizationToResAB() throws SQLException {

        dataBaseContext = new DataBaseContext();

        UserInputData userInputData = UserInputData.builder()
                .userInputRole("READ")
                .userInputPathResource("a.b")
                .build();
        String userRole = userInputData.getUserInputRole();
        String userPath = userInputData.getUserInputPathResource();

        assertEquals(ExitCodeType.SUCCESS.getExitCode(), dataBaseContext.hasResUserAccessDAO(userResourceDAO, userPath, userRole));
    }

    @Test
    public void testAuthorizationInvalidRole() throws SQLException {

        // Класс, отвечающий за валидацию некоторых входных данных.
        DataValidator dataValidator = new DataValidator();

        UserInputData userInputData = UserInputData.builder()
                .userInputRole("xxx")
                .build();

        String userRole = userInputData.getUserInputRole();

        assertEquals(ExitCodeType.INVALID_ROLE.getExitCode(), dataValidator.isUserRoleValid(userRole));
    }

    @Test
    public void testAuthorizationInvalidResPath() throws SQLException {

        dataBaseContext = new DataBaseContext();

        UserInputData userInputData = UserInputData.builder()
                .userInputRole("READ")
                .userInputPathResource("xxx")
                .build();
        String userRole = userInputData.getUserInputRole();
        String userPath = userInputData.getUserInputPathResource();

        assertEquals(ExitCodeType.INVALID_ACCESS.getExitCode(), dataBaseContext.hasResUserAccessDAO(userResourceDAO, userPath, userRole));
    }

    @Test
    public void testAuthorizationConnectToResourceWithOtherRole() throws SQLException {

        dataBaseContext = new DataBaseContext();

        UserInputData userInputData = UserInputData.builder()
                .userInputRole("EXECUTE")
                .userInputPathResource("a.b")
                .build();
        String userRole = userInputData.getUserInputRole();
        String userPath = userInputData.getUserInputPathResource();

        assertEquals(ExitCodeType.INVALID_ACCESS.getExitCode(), dataBaseContext.hasResUserAccessDAO(userResourceDAO, userPath, userRole));
    }
}