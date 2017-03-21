import org.apache.commons.codec.digest.DigestUtils;

import java.util.ArrayList;

/**
 * Created by Pavel on 21.03.2017.
 */
public class DataBaseContext {

    /**
     * Получить пользователя по логину
     *
     * @param usersList   - коллекция пользователей
     * @param userInpdata - входные данные
     * @return - true, если логины совпадают
     */
    public static boolean isGetUserLogin(ArrayList<UserInfo> usersList, UserInputData userInpdata) {
        for (UserInfo user : usersList) {
            if (userInpdata.getUserInputLogin().equals(user.getUserLogin())) {
                userInpdata.userInputId = user.getUserId();
                return true;
            }

        }
        return false;
    }


    /**
     * Получить пароль пользователяя
     *
     * @param usersList   - коллекция пользователей
     * @param userInpdata - введённые пользователем данные
     * @return - true, если парольные хэши совпадают
     */
    public static boolean isGetUserPassword(ArrayList<UserInfo> usersList, UserInputData userInpdata) {

        for (UserInfo user : usersList) {
            if (userInpdata.getUserInputId() == user.getUserId()) {
                String hashUserPass = AuthentifAndAuthorizService.generHashUserPassword(userInpdata.getUserInputPassword(), user.getUserSalt());
                if (AuthentifAndAuthorizService.isUserHashesEqual(user, hashUserPass)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Метод, который проверяет доступность к ресурсу
     *
     * @param userResourcesList - коллекция ресурсов, заданных в программе
     * @param userInputData     - объект, который хранит входные данные
     */
    public static boolean isResUserAccess(ArrayList<UserResources> userResourcesList, UserInputData userInputData) {
        String[] nodeResInputPath = userInputData.getUserInputPathResource().split("\\.");
        for (UserResources anUserResourcesList : userResourcesList) {
            boolean isResEqual = false;
            String[] userResPath = anUserResourcesList.getResourcePath().split("\\.");
            for (int i = 0; i < userResPath.length; i++) {
                isResEqual = nodeResInputPath[i].equals(userResPath[i]);
                if (isResEqual) {
                    if (userInputData.getUserInputId() == anUserResourcesList.getUserResUserId()) {
                        if (UserRoles.valueOf(userInputData.getUserInputRole()).equals((anUserResourcesList.getUserRole()))) {
                            return true;
                        }
                    }
                }
                return false;
            }
        }
        return false;
    }
}
