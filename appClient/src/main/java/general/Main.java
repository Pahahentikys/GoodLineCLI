package general;

import com.google.inject.Guice;
import com.google.inject.Injector;
import general.dao.AccountingDAO;
import general.dao.DataContextDAO;
import general.dao.UserInfoDAO;
import general.dao.UserResourceDAO;
import general.dom.Accounting;
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

        try (Connection connection = dataContextDAO.getConnection()) {
            logger.debug("Подключение к базе данных установлено");

            AuthorizationService authorizationService = injector.getInstance(AuthorizationService.class);
            AuthenticationService authenticationService = injector.getInstance(AuthenticationService.class);

            UserInputData userInputData = new UserInputData();
            DataValidator dataValidator = new DataValidator();
            Accounting accounting = new Accounting();

            UserInfoDAO userInfoDAO = new UserInfoDAO();
            UserResourceDAO userResourceDAO = new UserResourceDAO();
            AccountingDAO accountingDAO = new AccountingDAO();

            dataValidator.getUserInputData(userInputData, args);
            String userLogin = userInputData.getUserInputLogin();
            String userPassword = userInputData.getUserInputPassword();
            String userResourcePath = userInputData.getUserInputPathResource();
            String userResourceRole = userInputData.getUserInputRole();

            logger.debug("Запускается аутентификация");

            int isAuthentification = authenticationService.isUserAuthentification(userLogin, userPassword);
            if (isAuthentification == ExitCodeType.SUCCESS.getExitCode()) {
                logger.info("Authentification success!");
            }

            logger.debug("Запускается авторизация");

            int isAuthorization = authorizationService.isUserAuthorization(userResourcePath, userResourceRole, isAuthentification);
            if (isAuthorization == ExitCodeType.SUCCESS.getExitCode()) {
                logger.info("Authorization success!");
            }

            logger.debug("Запускается аккаунтинг");

            int isAccounting = authorizationService.isUserAccounting(accounting, userInputData, dataValidator, isAuthorization);
            if (isAccounting == ExitCodeType.SUCCESS.getExitCode()) {
                accountingDAO.addUserSeans(accounting);
                logger.info("Accounting success!");
            }

        } catch (SQLException | ClassNotFoundException e) {

            logger.debug("При подключении к БД произошла ошибка", e);
        }
    }
}