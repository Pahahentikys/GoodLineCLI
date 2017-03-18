import org.apache.commons.lang3.RandomStringUtils;

import java.util.ArrayList;


/**
 * Created by Pavel on 07.03.2017.
 */
public class Main {
    public static void main(String[] args) {

        UserInfo johnDoe = new UserInfo();
        UserInfo janeRow = new UserInfo();
        UserResources userResourceOne = new UserResources();
        UserResources userResourceTwo = new UserResources();
        UserResources userResourceThree = new UserResources();
        UserResources userResourceFour = new UserResources();

        UserInputData userInputData = new UserInputData();

        johnDoe.setUserId(1)
                .setUserLogin("jdoe")
                .setUserSalt(RandomStringUtils.randomAscii(8))
                .setUserHashPassword("sup3rpaZZ");

        janeRow.setUserId(2)
                .setUserLogin("jrow")
                .setUserSalt(RandomStringUtils.randomAscii(8))
                .setUserHashPassword("Qweqrty12");

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

        DataValidator.getUserInputData(userInputData, args);

        boolean isAuthentification = AuthentifAndAuthorizService.isUserAuthentification(usersInfo, userInputData);
        if (isAuthentification) {
            System.out.println("Аутентификация прошла успешно!");
        }

        boolean isAuth = AuthentifAndAuthorizService.isUserAuthorization(usersResources, userInputData, isAuthentification);
        if (isAuth) {
            System.out.println("Авторизация прошла успешно!");
        }

        if (AuthentifAndAuthorizService.isUserAccounting(accountingList, userInputData, isAuth)) {
            System.out.println("Аккаунтинг!");
        }

        boolean isAccount = AuthentifAndAuthorizService.isUserAccounting(accountingList, userInputData, isAuth);
        if (isAccount) {
            System.out.println("Сеанс записан!");
        }
    }
}






