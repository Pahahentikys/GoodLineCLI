/**
 * Created by Pavel on 09.03.2017.
 */

import org.apache.commons.codec.digest.DigestUtils;

import java.util.ArrayList;
import java.util.List;

public class AuthentifAndAuthorizService {

    /**
     * Получить пользователя по логину
     *
     * @param usersList   - коллекция пользователей
     * @param userInpdata - входные данные
     * @return - true, если логины совпадают
     */
    private static boolean isGetUserLogin(ArrayList<UserInfo> usersList, UserInputData userInpdata) {
        for (UserInfo user : usersList) {
            if (userInpdata.getUserInputLogin().equals(user.getUserLogin())) {
                userInpdata.userInputId = user.getUserId();
                return true;
            }

        }
        return false;
    }

    /**
     * Сравнить хэши на совпадение
     *
     * @param user         - информация о пользователе, который задан, из коллекции
     * @param hashUserPass - хэш пароля, который ввёл пользователь
     * @return
     */
    private static boolean isUserHashesEqual(UserInfo user, String hashUserPass) {
        return user.getUserHashPassword().equals(hashUserPass);
    }

    /**
     * Получить пароль пользователяя
     *
     * @param usersList   - коллекция пользователей
     * @param userInpdata - введённые пользователем данные
     * @return - true, если парольные хэши совпадают
     */
    private static boolean isGetUserPassword(ArrayList<UserInfo> usersList, UserInputData userInpdata) {

        for (UserInfo user : usersList) {
            if (userInpdata.getUserInputId() == user.getUserId()) {
                String hashUserPass = generHashUserPassword(userInpdata.getUserInputPassword(), user.getUserSalt());
                if (isUserHashesEqual(user, hashUserPass)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Проверка на то, аутентифицирован ли пользователь
     *
     * @param usersList     - коллекция пользователей
     * @param userInputData - объект, хранящий в себе входные параметры
     * @return - true, если верный логин и пароль
     */
    boolean isUserAuthentification(ArrayList<UserInfo> usersList, UserInputData userInputData) {
        if (!AuthentifAndAuthorizService.isGetUserLogin(usersList, userInputData)) {
            System.exit(1);
        }

        if (!AuthentifAndAuthorizService.isGetUserPassword(usersList, userInputData)) {

            System.exit(2);

        }
        return true;
    }

    /**
     * Проверка на то, авторизован ли пользователь
     *
     * @param resourcesList          - коллекция ресурсов
     * @param userInpData            - входные данные
     * @param isUserAuthentification - имеет значение true, если пользователь аутентифицирован
     * @return - код: 3, если неправильная роль,
     */

    boolean isUserAuthorization(ArrayList<UserResources> resourcesList, UserInputData userInpData, boolean isUserAuthentification) {
        if (isUserAuthentification & !userInpData.getUserInputRole().isEmpty() & !userInpData.getUserInputPathResource().isEmpty()) {
            if (!DataValidator.isUserRoleValid(userInpData)) {
                System.exit(3);
            }
            return true;
        }
    }

    /**
     * Получение хэша пароля
     *
     * @param userNoHashPassword - пароль пользователя без хэша
     * @param salt               - соль
     * @return - "посоленный" хэш пароля
     */

    public static String generHashUserPassword(String userNoHashPassword, String salt) {

        return DigestUtils.md5Hex(DigestUtils.md5Hex(userNoHashPassword) + salt);

    }


}

