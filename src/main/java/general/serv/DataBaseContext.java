package general.serv;

import general.dao.DataContextDAO;
import general.dao.UserInfoDAO;
import general.dao.UserResourceDAO;
import general.dom.UserInfo;
import general.dom.UserInputData;
import general.dom.UserResources;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;

public class DataBaseContext {
    AuthenticationService authenticationService = new AuthenticationService();
    private static final Logger logger = LogManager.getLogger(DataContextDAO.class.getName());

    /**
     * Поиск пользователя по логину
     *
     * @param userInfoDAO   - слой доступа к БД
     * @param userInputData - входящие параметры
     * @return - true, если пользователь с таким же логином найден
     * @throws SQLException
     */
    public boolean hasGetUserLoginDAO(UserInfoDAO userInfoDAO, UserInputData userInputData) throws SQLException {
        UserInfo userInfo = userInfoDAO.searchUserLogin(userInputData.getUserInputLogin());
        if (userInfo == null) {
            logger.error("Пользователь не найден по логину {}", userInputData.getUserInputLogin());
            return false;
        }
        return true;
    }

    /**
     * Поиск пользователя по паролю
     *
     * @param userInfoDAO   - слой доступа к БД
     * @param userInputData - входящие параметры
     * @return - true, если хэши паролей совпадают
     * @throws SQLException
     */
    public boolean hasGetUserPasswordDAO(UserInfoDAO userInfoDAO, UserInputData userInputData) throws SQLException {
        UserInfo userInfo = userInfoDAO.searchUserLogin(userInputData.getUserInputLogin());
        System.out.println(userInfo.getUserLogin());
        String hashUserPass = authenticationService.generHashUserPassword(userInputData.getUserInputPassword(), userInfo.getUserSalt());
        if (authenticationService.isUserHashesEqual(userInfo, hashUserPass)) {
            return true;
        }

        logger.error("Хэши паролей не совпадают!");
        return false;
    }

    /**
     * Метод, который проверяет доступность юзеру к ресурсу
     *
     * @param userResourceDAO - слой доступа к БД, который наполняет объект UserResource данными из БД
     * @param userInputData   - входные данные
     * @return - true, если доступ к ресурсу есть, false - если доступ к ресурсу отсутствует
     * @throws SQLException
     */
    public boolean hasResUserAccessDAO(UserResourceDAO userResourceDAO, UserInputData userInputData) throws SQLException {
        UserResources userResources = userResourceDAO.getPathUserResource(userInputData.getUserInputPathResource(), userInputData.getUserInputRole());
        if (userResources == null) {
            logger.error("Пути к ресурсу {} не существует!", userInputData.getUserInputPathResource());
            return false;
        }
        return true;
    }
}
