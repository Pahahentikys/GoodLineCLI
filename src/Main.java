import java.sql.Connection;
import java.sql.SQLException;


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

            UserResourceDAO userResourceDAO = new UserResourceDAO(connection);


            dataValidator.getUserInputData(userInputData, args);

            boolean isAuthentification = authentifAndAuthorServ.isUserAuthentification(userInfoDAO, userInputData);

            if (isAuthentification) {
                System.out.println("Authentification success!");
            }

            boolean isAuthorization = authentifAndAuthorServ.isUserAuthorization(userResourceDAO, userInputData, isAuthentification);
            if (isAuthorization) {
                System.out.println("Authorization success!");
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();

        }
    }
}