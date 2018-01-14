package general;

import com.google.inject.Guice;
import com.google.inject.Injector;
import general.dao.DataContextDAO;
import general.dao.UserInfoDAO;
import general.dom.UserInputData;
import general.serv.AuthenticationService;
import general.serv.AuthorizationService;
import general.serv.DataBaseContext;
import general.serv.DataValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.flywaydb.core.Flyway;

import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    private static final Logger logger = LogManager.getLogger(Main.class.getName());

    public static void main(String[] args) {

        Injector injector = Guice.createInjector(new GuiceClientConfig());

        DataBaseContext instance = injector.getInstance(DataBaseContext.class);

//        DataBaseContext dataBaseContext = injector.getInstance(DataBaseContext.class);
//
//        AuthenticationService authenticationService = injector.getInstance(AuthenticationService.class);
//
//        MyApplication app = injector.getInstance(MyApplication.class);

        DataContextDAO dataContextDAO = DataContextDAO.builder()
                .dataBaseDriver("org.h2.Driver")
                .dataBaseUrl("jdbc:h2:file:./src/main/resources/db/GoodLineCLI")
                .dataBaseUserName("Pavel")
                .dataBasePassword("1234")
                .build();

        logger.debug("Процесс миграции");
        Flyway flyway = new Flyway();
        flyway.setDataSource(dataContextDAO.getDataBaseUrl(), dataContextDAO.getDataBaseUserName(), dataContextDAO.getDataBasePassword());
        flyway.migrate();
//Connection connection = dataContextDAO.getConnection()
        try (Connection connection = dataContextDAO.getConnection()) {
            logger.debug("Подключение к базе данных установлено");
//            AuthenticationService authenticationService = new AuthenticationService();
            AuthorizationService authorizationService = new AuthorizationService();
            AuthenticationService authenticationService = injector.getInstance(AuthenticationService.class);
            UserInputData userInputData = new UserInputData();
            DataValidator dataValidator = new DataValidator();
            UserInfoDAO userInfoDAO = new UserInfoDAO();
//            authenticationService.setUserInfoDAO(userInfoDAO);
            DataBaseContext dataBaseContext = new DataBaseContext();
//            dataBaseContext.setUserInfoDAO(userInfoDAO);
//            authenticationService.setDataBaseContext(dataBaseContext);
//            UserResourceDAO userResourceDAO = new UserResourceDAO(connection);
//            AccountingDAO accountingDAO = new AccountingDAO(connection);
            dataValidator.getUserInputData(userInputData, args);
            String userLogin = userInputData.getUserInputLogin();
            String userPassword = userInputData.getUserInputPassword();
            String userResourcePath = userInputData.getUserInputPathResource();
            String userResourceRole = userInputData.getUserInputRole();
            logger.debug("Запускается аутентификация");

//            int isAuthentification = authenticationService.isUserAuthentification(userInfoDAO, userLogin, userPassword);
            int isAuthentification = authenticationService.isUserAuthentification(userLogin, userPassword);
            if (isAuthentification == ExitCodeType.SUCCESS.getExitCode()) {
                logger.info("Authentification success!");
            }

            logger.debug("Запускается авторизация");
//
//            int isAuthorization = authorizationService.isUserAuthorization(userResourceDAO, userResourcePath, userResourceRole, isAuthentification);
//            if (isAuthorization == ExitCodeType.SUCCESS.getExitCode()) {
//                logger.info("Authorization success!");
//            }
//
//            logger.debug("Запускается аккаунтинг");
//
//            Accounting accounting = new Accounting();
//
//            int isAccounting = authorizationService.isUserAccounting(accounting, userResourceDAO, userInputData, dataValidator, isAuthorization);
//            if (isAccounting == ExitCodeType.SUCCESS.getExitCode()) {
//                accountingDAO.addUserSeans(accounting);
//                logger.info("Accounting success!");
//            }

        } catch (SQLException | ClassNotFoundException e) {

            logger.debug("При подключении к БД произошла ошибка", e);
        }
    }
}