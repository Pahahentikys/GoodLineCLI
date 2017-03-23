import org.apache.commons.lang3.RandomStringUtils;

import java.util.ArrayList;


/**
 * Created by Pavel on 07.03.2017.
 */
public class Main {
    public static void main(String[] args) {
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
            System.out.println("Аутентификация прошла успешно!");
        }

        boolean isAuth = authentifAndAuthorServ.isUserAuthorization(usersResources, userInputData,dataBaseContext, dataValidator, isAuthentification);
        if (isAuth) {
            System.out.println("Авторизация прошла успешно!");
        }

        if (authentifAndAuthorServ.isUserAccounting(accountingList, userInputData, dataValidator, isAuth)) {
            System.out.println("Аккаунтинг!");
        }

        boolean isAccount = authentifAndAuthorServ.isUserAccounting(accountingList, userInputData, dataValidator, isAuth);
        if (isAccount) {
            System.out.println("Сеанс записан!");
        }
    }
}