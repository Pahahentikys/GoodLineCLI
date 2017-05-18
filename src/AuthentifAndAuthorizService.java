/**
 * Created by Pavel on 09.03.2017.
 */

import org.apache.commons.codec.digest.DigestUtils;

import java.sql.SQLException;
import java.util.ArrayList;

public class AuthentifAndAuthorizService {


    /**
     * Получение хэша пароля
     *
     * @param userNoHashPassword - пароль пользователя без хэша
     * @param salt               - соль
     * @return - "посоленный" хэш пароля
     */

    public String generHashUserPassword(String userNoHashPassword, String salt) {

        return DigestUtils.md5Hex(DigestUtils.md5Hex(userNoHashPassword) + salt);

    }

    /**
     * Сравнить хэши на совпадение
     *
     * @param user         - информация о пользователе, который задан, из коллекции
     * @param hashUserPass - хэш пароля, который ввёл пользователь
     * @return true, если хэши равны
     */
    public boolean isUserHashesEqual(UserInfo user, String hashUserPass) {
        return user.getUserHashPassword().equals(hashUserPass);
    }

    /**
     * Проверка на то, аутентифицирован ли пользователь
     * @param userInfoDAO - слой данных, который берёт по запросу из БД нужного пользователя
     * @param userInputData - объект, хранящий в себе входные параметры
     * @return
     * @throws SQLException
     */
    public boolean isUserAuthentification(UserInfoDAO userInfoDAO, UserInputData userInputData) throws SQLException {
        DataBaseContext dataBaseContext = new DataBaseContext();
        if (!dataBaseContext.isGetUserLoginDAO(userInfoDAO, userInputData)) {
            System.exit(1);
        }

        if (!dataBaseContext.isGetUserPasswordDAO(userInfoDAO, userInputData)) {

            System.exit(2);

        }
        return true;
    }

    /**
     * Проверка на то, авторизован ли пользователь
     * @param userResourceDAO - перечень ресурсов, который получен выборкой из БД
     * @param userInputData - входные параметры
     * @param isUserAuthentification - проверка на то, аутентифицирован ли пользователь
     * @return - код: 3, если неправильная роль, код: 4, если нет доступа
     * @throws SQLException
     */
    public boolean isUserAuthorization(UserResourceDAO userResourceDAO, UserInputData userInputData, boolean isUserAuthentification) throws SQLException {
       DataValidator dataValidator = new DataValidator();
        DataBaseContext dataBaseContext = new DataBaseContext();
        if ((isUserAuthentification) && (userInputData.getUserInputRole() != null) && (userInputData.getUserInputPathResource() != null)) {
            if (!dataValidator.isUserRoleValid(userInputData)) {
                System.exit(3);
            }
            if (!dataBaseContext.isResUserAccessDAO(userResourceDAO, userInputData)) {
                System.exit(4);
            }
            return true;
        }
        return false;
    }

    /**
     * Метод для создания сеанса доступа к ресурсу для юзера, который фиксирует даты: начала доступа, окончания и объем, потребдённых ресурсов.
     *
     * @param accountingList - коллекция сенсов пользователя.
     * @param userInputData  - входные данные.
     */
    public void createUserSeans(ArrayList<Accounting> accountingList, UserInputData userInputData) {
        Accounting userSeans = new Accounting()
                .setResourceUserId(userInputData.getUserInputId())
                .setStartAccountingDate(userInputData.getUserInputDs())
                .setEndAccountingDate(userInputData.getUserInputDe())
                .setVolumeOfUseRes(userInputData.getUserInputVol());

        accountingList.add(userSeans);
    }

    /**
     * Проверка на то, был ли пользовательский сеанс
     *
     * @param accountingList      - коллекция сеансов пользователя
     * @param userInputData       - входные данные
     * @param isUserAuthorization - проверка на то, что пользователь авторизован
     * @return - код: 5, если некорректная дата
     */
    public boolean isUserAccounting(ArrayList<Accounting> accountingList, UserInputData userInputData, DataValidator dataValidator, boolean isUserAuthorization) {
        if (isUserAuthorization && userInputData.getUserInputDs() != null) {
            if (!dataValidator.isDateDsAndDeValid(userInputData) || !dataValidator.isVolumeValid(userInputData)) {
                System.exit(5);
            }
            createUserSeans(accountingList, userInputData);
            return true;
        }
        return false;
    }


}


