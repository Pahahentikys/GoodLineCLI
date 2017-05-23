package general;

import general.dao.AccountingDAO;
import general.dao.DataContextDAO;
import general.dao.UserInfoDAO;
import general.dao.UserResourceDAO;
import general.dom.Accounting;
import general.dom.UserInputData;
import general.serv.AuthenticationService;
import general.serv.AuthorizationService;
import general.serv.DataValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.flywaydb.core.Flyway;

import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    private static final Logger logger = LogManager.getLogger(Main.class.getName());
    public static void main(String[] args) {

        DataContextDAO dataContextDAO = new DataContextDAO();

        dataContextDAO.withDataBaseDriver("org.h2.Driver")
                .withDataBaseUrl("jdbc:h2:file:./src/main/resources/db/GoodLineCLI")
                .withDataBaseUserName("Pavel")
                .withDataBasePassword("1234");

        logger.debug("Процесс миграции");
        Flyway flyway = new Flyway();
        flyway.setDataSource(dataContextDAO.getDataBaseUrl(), dataContextDAO.getDataBaseUserName(), dataContextDAO.getDataBasePassword());
        flyway.migrate();

        try (Connection connection = dataContextDAO.getConnection()) {
            logger.debug("Подключение к базе данных установлено");
            AuthenticationService authenticationService = new AuthenticationService();
            AuthorizationService authorizationService = new AuthorizationService();
            UserInputData userInputData = new UserInputData();
            DataValidator dataValidator = new DataValidator();
            UserInfoDAO userInfoDAO = new UserInfoDAO(connection);
            UserResourceDAO userResourceDAO = new UserResourceDAO(connection);
            AccountingDAO accountingDAO = new AccountingDAO(connection);
            dataValidator.getUserInputData(userInputData, args);
            String userLogin = userInputData.getUserInputLogin();
            String userPassword = userInputData.getUserInputPassword();
            String userResourcePath = userInputData.getUserInputPathResource();
            String userResourceRole = userInputData.getUserInputRole();
            logger.debug("Запускается аутентификация");

            boolean isAuthentification = authenticationService.isUserAuthentification(userInfoDAO, userLogin, userPassword);
            if (isAuthentification) {
                logger.info("Authentification success!");
            }

            logger.debug("Запускается авторизация");

            boolean isAuthorization = authorizationService.isUserAuthorization(userResourceDAO, userResourcePath, userResourceRole, isAuthentification);
            if (isAuthorization) {
                logger.info("Authorization success!");
            }

            logger.debug("Запускается аккаунтинг");

            Accounting accounting = new Accounting();

            if (authorizationService.isUserAccounting(accounting, userResourceDAO, userInputData, dataValidator, isAuthorization)) {

                accountingDAO.addUserSeans(accounting);
                logger.info("Accounting success!");
            }

        } catch (SQLException | ClassNotFoundException e) {

            logger.debug("При подключении к БД произошла ошибка", e);
        }
    }
}