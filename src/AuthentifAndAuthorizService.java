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
    private boolean isGetUserLogin(ArrayList<UserInfo> usersList, UserInputData userInpdata) {
        for (UserInfo user : usersList) {
            if (userInpdata.getUserInputLogin().equals(user.getUserLogin())) {
                userInpdata.userInputId = user.getUserId();
                return true;
            } else {
                return false;
            }
        }
    }

    /**
     * Сравнить хэши на совпадение
     *
     * @param user         - информация о пользователе, который задан, из коллекции
     * @param hashUserPass - хэш пароля, который ввёл пользователь
     * @return
     */
    private boolean isUserHashesEqual(UserInfo user, String hashUserPass) {
        return user.getUserHashPassword().equals(hashUserPass);
    }

    /**
     * Получить пароль пользователяя
     *
     * @param usersList   - коллекция пользователей
     * @param userInpdata - введённые пользователем данные
     * @return - true, если парольные хэши совпадают
     */
    private boolean isGetUserPassword(ArrayList<UserInfo> usersList, UserInputData userInpdata) {

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
