package general;

import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.LoggerContext;
import org.flywaydb.core.Flyway;
import general.dao.*;
import general.dom.*;
import general.serv.*;

public class Main {
    private static final Logger logger = LogManager.getLogger(Main.class.getName());
    public static void main(String[] args) {

        LoggerContext context = (LoggerContext) LogManager.getContext(false);
        File file = new File("./src/main/resources/log4j2.xml");
        context.setConfigLocation(file.toURI());

        DataContextDAO dataContextDAO = new DataContextDAO();

        dataContextDAO.setDataBaseDriver("org.h2.Driver")
                .setDataBaseUrl("jdbc:h2:file:./src/main/resources/db/GoodLineCLI")
                .setDataBaseUserName("Pavel")
                .setDataBasePassword("1234");

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
            logger.debug("Запускается аутентификация");

            boolean isAuthentification = authenticationService.isUserAuthentification(userInfoDAO, userInputData);
            if (isAuthentification) {
                logger.info("Authentification success!");
            }

            logger.debug("Запускается авторизация");

            boolean isAuthorization = authorizationService.isUserAuthorization(userResourceDAO, userInputData, isAuthentification);
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