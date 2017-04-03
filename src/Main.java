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
                .setDataBaseUrl("jdbc:h2:./GoodLineCLI")
                .setDataBaseUserName("Pavel")
                .setDataBasePassword("1234");
        try (Connection connection = dataContextDAO.getConnection()) {
            System.out.println("DB connect!");

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


        UserInputData userInputData = new UserInputData();
        DataValidator dataValidator = new DataValidator();
        AuthentifAndAuthorizService authentifAndAuthorServ = new AuthentifAndAuthorizService();
        DataBaseContext dataBaseContext = new DataBaseContext();


        UserInfo johnDoe = new UserInfo();
        UserInfo janeRow = new UserInfo();
        UserResources userResourceOne = new UserResources();
        UserResources userResourceTwo = new UserResources();
        UserResources userResourceThree = new UserResources();
        UserResources userResourceFour = new UserResources();


        johnDoe.setUserId(1)
                .setUserLogin("jdoe")
                .setUserSalt(RandomStringUtils.randomAscii(8))
                .setUserHashPassword(authentifAndAuthorServ.generHashUserPassword("sup3rpaZZ",
                        johnDoe.getUserSalt()));

        janeRow.setUserId(2)
                .setUserLogin("jrow")
                .setUserSalt(RandomStringUtils.randomAscii(8))
                .setUserHashPassword(authentifAndAuthorServ.generHashUserPassword("Qweqrty12",
                        janeRow.getUserSalt()));

        ArrayList<UserInfo> usersInfo = new ArrayList<>();
        usersInfo.add(johnDoe);
        usersInfo.add(janeRow);

        userResourceOne.setUserResId(1)
                .setUserResUserId(johnDoe.getUserId())
                .setUserResResId(userResourceOne.getUserResResId())
                .setResourcePath("a")
                .setUserRole(UserRoles.READ);

        userResourceTwo.setUserResId(2)
                .setUserResUserId(johnDoe.getUserId())
                .setUserResResId(userResourceTwo.getUserResResId())
                .setResourcePath("a.b")
                .setUserRole(UserRoles.WRITE);
        userResourceThree.setUserResId(3)
                .setUserResUserId(janeRow.getUserId())
                .setUserResResId(userResourceThree.getUserResResId())
                .setResourcePath("a.b.c")
                .setUserRole(UserRoles.EXECUTE);
        userResourceFour.setUserResId(4)
                .setUserResUserId(johnDoe.getUserId())
                .setResourcePath("a.bc")
                .setUserRole(UserRoles.EXECUTE);


        ArrayList<UserResources> usersResources = new ArrayList<>();
        usersResources.add(userResourceOne);
        usersResources.add(userResourceTwo);
        usersResources.add(userResourceThree);
        usersResources.add(userResourceFour);

        ArrayList<Accounting> accountingList = new ArrayList<>();

        dataValidator.getUserInputData(userInputData, args);

        boolean isAuthentification = authentifAndAuthorServ.isUserAuthentification(usersInfo, userInputData);
        if (isAuthentification) {
            System.out.println("Authentification success!");
        }

        boolean isAuth = authentifAndAuthorServ.isUserAuthorization(usersResources, userInputData, dataBaseContext, dataValidator, isAuthentification);
        if (isAuth) {
            System.out.println("Authorization success!");
        }

        if (authentifAndAuthorServ.isUserAccounting(accountingList, userInputData, dataValidator, isAuth)) {
            System.out.println("Accounting!");
        }

        boolean isAccount = authentifAndAuthorServ.isUserAccounting(accountingList, userInputData, dataValidator, isAuth);
        if (isAccount) {
            System.out.println("Seance save!");
        }
    }
}