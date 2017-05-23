package general;

import java.sql.Connection;
import java.sql.SQLException;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.flywaydb.core.Flyway;
import general.dao.*;
import general.dom.*;
import general.serv.*;

public class Main {

    private static final Logger logger = LogManager.getLogger(Main.class.getName());

    public static void main(String[] args) {

        DataContextDAO dataContextDAO = new DataContextDAO();

        dataContextDAO.setDataBaseDriver("org.h2.Driver")
                .setDataBaseUrl("jdbc:h2:file:./resources/db/GoodLineCLI")
                .setDataBaseUserName("Pavel")
                .setDataBasePassword("1234");

        logger.debug("Процесс миграции");
        Flyway flyway = new Flyway();
        flyway.setDataSource(dataContextDAO.getDataBaseUrl(), dataContextDAO.getDataBaseUserName(), dataContextDAO.getDataBasePassword());
        flyway.migrate();

        try (Connection connection = dataContextDAO.getConnection()) {
            logger.debug("Подключение к базе данных установлено");
            UserInputData userInputData = new UserInputData();
            DataValidator dataValidator = new DataValidator();
            AuthentifAndAuthorizService authentifAndAuthorServ = new AuthentifAndAuthorizService();
            UserInfoDAO userInfoDAO = new UserInfoDAO(connection);
            UserResourceDAO userResourceDAO = new UserResourceDAO(connection);
            AccountingDAO accountingDAO = new AccountingDAO(connection);
            dataValidator.getUserInputData(userInputData, args);
            logger.debug("Запускается аутентификация");

            boolean isAuthentification = authentifAndAuthorServ.isUserAuthentification(userInfoDAO, userInputData);
            if (isAuthentification) {
                System.out.println("Authentification success!");
            }

            logger.debug("Запускается авторизация");
            boolean isAuthorization = authentifAndAuthorServ.isUserAuthorization(userResourceDAO, userInputData, isAuthentification);
            if (isAuthorization) {
                System.out.println("Authorization success!");
            }

            logger.debug("Запускается аккаунтинг");
            Accounting accounting = new Accounting();
            if (authentifAndAuthorServ.isUserAccounting(accounting, userResourceDAO, userInputData, dataValidator, isAuthorization)) {

                accountingDAO.addUserSeans(accounting);
                System.out.println("Accounting success!");
            }

        } catch (SQLException | ClassNotFoundException e) {

            logger.debug("При подключении к БД произошла ошибка");
            e.printStackTrace();

        }
    }
}