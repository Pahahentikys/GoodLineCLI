/**
 * Created by Pavel on 09.03.2017.
 */
package general.serv;
import org.apache.commons.codec.digest.DigestUtils;

import java.sql.SQLException;
import general.dom.*;
import general.dao.*;
import general.dom.UserInputData;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class AuthentifAndAuthorizService {

    private static final Logger logger = LogManager.getLogger(AuthentifAndAuthorizService.class.getName());

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
     *
     * @param userInfoDAO   - слой данных, который берёт по запросу из БД нужного пользователя
     * @param userInputData - объект, хранящий в себе входные параметры
     * @return
     * @throws SQLException
     */
    public boolean isUserAuthentification(UserInfoDAO userInfoDAO, UserInputData userInputData) throws SQLException {
        logger.debug("Проверка на то, аутентифицирован ли пользователь");
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
     *
     * @param userResourceDAO        - перечень ресурсов, который получен выборкой из БД
     * @param userInputData          - входные параметры
     * @param isUserAuthentification - проверка на то, аутентифицирован ли пользователь
     * @return - код: 3, если неправильная роль, код: 4, если нет доступа
     * @throws SQLException
     */
    public boolean isUserAuthorization(UserResourceDAO userResourceDAO, UserInputData userInputData, boolean isUserAuthentification) throws SQLException {
        logger.debug("Проверка на то, авторизован ли пользователь");
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
     * Метод для создания сеанса доступа к ресурсу для юзера, который фиксирует даты: начала доступа, окончания, а также объем потреблённых ресурсов и id ресурса.
     *
     * @param accounting      - объект для наполнения параметрами, который после будет помещаться в БД
     * @param userInputData   - входные параметры
     * @param userResourceDAO - слой доступа к БД
     * @throws SQLException
     */
    public void addAccounting(Accounting accounting, UserInputData userInputData, UserResourceDAO userResourceDAO) throws SQLException {
        logger.debug("Добавление пользовательского сеанса в БД");
        UserResources userResources = userResourceDAO.findIdRes(userInputData.getUserInputPathResource());

        accounting.setResourceId(userResources.getUserResResId());
        accounting.setStartAccountingDate(userInputData.getUserInputDs());
        accounting.setEndAccountingDate(userInputData.getUserInputDe());
        accounting.setVolumeOfUseRes(userInputData.getUserInputVol());
    }

    /**
     * Проверка на то, был ли пользовательский сеанс
     *
     * @param accounting          - сеанс пользователя
     * @param userResourceDAO     - слой доступа к БД
     * @param userInputData       - входные данные
     * @param dataValidator       - объект для валидации данных
     * @param isUserAuthorization - проверка на то, авторизован ли юзер
     * @return код: 5, если некорректная дата
     * @throws SQLException
     */
    public boolean isUserAccounting(Accounting accounting, UserResourceDAO userResourceDAO, UserInputData userInputData, DataValidator dataValidator, boolean isUserAuthorization) throws SQLException {
        logger.debug("Проверка на то, выполнен ли процесс аккаунтинга");
        if (isUserAuthorization && userInputData.getUserInputDs() != null) {
            if (!dataValidator.isDateDsAndDeValid(userInputData) || !dataValidator.isVolumeValid(userInputData)) {
                System.exit(5);
            }
            addAccounting(accounting, userInputData, userResourceDAO);
            return true;
        }
        return false;
    }

}


