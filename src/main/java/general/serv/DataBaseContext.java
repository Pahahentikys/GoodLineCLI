package general.serv;

import general.dao.DataContextDAO;
import general.dao.UserInfoDAO;
import general.dao.UserResourceDAO;
import general.dom.UserInfo;
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
     * @param userLogin - пользовательский логин, который считывается с входных аргументов
     * @return - true, если пользователь с таким же логином найден
     * @throws SQLException
     */
    public boolean hasGetUserLoginDAO(UserInfoDAO userInfoDAO, String userLogin) throws SQLException {
        UserInfo userInfo = userInfoDAO.searchUserLogin(userLogin);
        if (userInfo == null) {
            logger.error("Пользователь не найден по логину {}", userLogin);
            return false;
        }
        return true;
    }

    /**
     * Поиск пользователя по паролю
     *
     * @param userInfoDAO   - слой доступа к БД
     * @param userLogin - пользовательский логин, который считывается с входных аргументов
     * @param userPassword - пользовательская пароль, которая считывается с входных аргументов
     * @return - true, если хэши паролей совпадают
     * @throws SQLException
     */
    public boolean hasGetUserPasswordDAO(UserInfoDAO userInfoDAO, String userLogin, String userPassword) throws SQLException {
        UserInfo userInfo = userInfoDAO.searchUserLogin(userLogin);
        logger.info("Запращивается пользователь с плогином {} и паролем {}", userLogin, userPassword);
        String hashUserPass = authenticationService.generHashUserPassword(userPassword, userInfo.getUserSalt());
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
     * @param userResourcePath - пользовательский путь, который считывается с входных аргументов
     * @param userResourceRole - пользовательская роль, которая считывается с входных аргументов
     * @return - true, если доступ к ресурсу есть, false - если доступ к ресурсу отсутствует
     * @throws SQLException
     */
    public boolean hasResUserAccessDAO(UserResourceDAO userResourceDAO, String userResourcePath, String userResourceRole) throws SQLException {
        UserResources userResources = userResourceDAO.getPathUserResource(userResourcePath, userResourceRole);
            if (userResources == null) {
                logger.error("Пути к ресурсу {} не существует!", userResourcePath);
                return false;
            }
        return true;
    }
}
