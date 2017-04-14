import org.apache.commons.lang3.RandomStringUtils;

import java.util.ArrayList;
import java.sql.*;


/**
 * Created by Pavel on 07.03.2017.
 */
public class Main {
    public static void main(String[] args) {

        DataContextDAO dataContextDAO = new DataContextDAO();

        dataContextDAO.setDataBaseDriver("org.h2.Driver")
                .setDataBaseUrl("jdbc:h2:~/GoodLineCLI")
                .setDataBaseUserName("Pavel")
                .setDataBasePassword("1234");

        try (Connection connection = dataContextDAO.getConnection()) {

            System.out.println("DB connect!");

            UserInputData userInputData = new UserInputData();

            DataValidator dataValidator = new DataValidator();

            AuthentifAndAuthorizService authentifAndAuthorServ = new AuthentifAndAuthorizService();

            UserInfoDAO userInfoDAO = new UserInfoDAO(connection);

            dataValidator.getUserInputData(userInputData, args);

            boolean isAuthentification = authentifAndAuthorServ.isUserAuthentification(userInfoDAO, userInputData);

            if (isAuthentification) {
                System.out.println("Authentification success!");
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();

        }
    }
}