import org.apache.commons.lang3.RandomStringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Pavel on 07.03.2017.
 */
public class Main {
    public static void main(String[] args) {

        UserInfo userOne = new UserInfo();
        UserResources userResourceOne = new UserResources();
        UserInputData userInputData = new UserInputData();

        userOne.setUserId(1)
                .setUserLogin("userOne")
                .setUserSalt(RandomStringUtils.randomAscii(8))
                .setUserHashPassword("111");

        ArrayList<UserInfo> usersInfo = new ArrayList<>();
        usersInfo.add(userOne);

        userResourceOne.setUserResId(1)
                .setUserResUserId(1)
                .setUserResResId(1)
                .setResourcePath("A.B")
                .setUserRole(UserRoles.EXECUTE);

        ArrayList<UserResources> usersResources = new ArrayList<>();
        usersResources.add(userResourceOne);

        DataValidator.getUserInputData(userInputData, args);

        boolean isAuthentification = AuthentifAndAuthorizService.isUserAuthentification(usersInfo, userInputData);
        if (isAuthentification) {
            System.out.println("Аутентификация прошла успешно!");
        }

        boolean isAuth = AuthentifAndAuthorizService.isUserAuthorization(usersResources, userInputData, isAuthentification);
        if (isAuth) {
            System.out.println("Авторизация прошла успешно!");
        }
    }
}





