package general.serv;

import general.ExitCodeType;
import general.dom.UserInfo;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;
import java.sql.SQLException;

public class AuthenticationService {

    private static final Logger logger = LogManager.getLogger(AuthenticationService.class.getName());

//    @Inject
//    private DataBaseContext dataBaseContext;

//    private DataBaseContext dataBaseContext;
//
//    public AuthenticationService setDataBaseContext(DataBaseContext dataBaseContext){
//        this.dataBaseContext = dataBaseContext;
//        return this;
//    }

    /**
     * Проверка на то, аутентифицирован ли пользователь
     *
     * @param userInfoDAO  - слой данных, который берёт по запросу из БД нужного пользователя
     * @param userLogin    - пользовательский логин, который считывается с входных аргументов
     * @param userPassword - пользовательская пароль, которая считывается с входных аргументов
     * @return exit-1, если неверный лоигн, а exit-2, если неверный пароль
     * @throws SQLException
     */
    public int isUserAuthentification(String userLogin, String userPassword) throws SQLException {

        DataBaseContext dataBaseContext = new DataBaseContext();

        logger.debug("Проверка на то, аутентифицирован ли пользователь");

        if (dataBaseContext.hasGetUserLoginDAO(userLogin) == ExitCodeType.INVALID_LOGIN.getExitCode()) {
            return ExitCodeType.INVALID_LOGIN.getExitCode();

        }

        if (dataBaseContext.hasGetUserPasswordDAO(userLogin, userPassword) == ExitCodeType.INVALID_PASSWORD.getExitCode()) {
            return ExitCodeType.INVALID_PASSWORD.getExitCode();
        }
        return ExitCodeType.SUCCESS.getExitCode();
    }

    /**
     * Получение хэша пароля
     *
     * @param userNoHashPassword - пароль пользователя без хэша
     * @param salt               - соль
     * @return - "посоленный" хэш пароля
     */
    String generHashUserPassword(String userNoHashPassword, String salt) {
        return DigestUtils.md5Hex(DigestUtils.md5Hex(userNoHashPassword) + salt);
    }

    /**
     * Сравнить хэши на совпадение
     *
     * @param user         - информация о пользователе, который задан, из коллекции
     * @param hashUserPass - хэш пароля, который ввёл пользователь
     * @return true, если хэши равны
     */
    boolean isUserHashesEqual(UserInfo user, String hashUserPass) {
        return user.getUserHashPassword().equals(hashUserPass);
    }

}
