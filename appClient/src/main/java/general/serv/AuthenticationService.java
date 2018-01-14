package general.serv;

import general.ExitCodeType;
import general.dao.UserInfoDAO;
import general.dom.UserInfo;
import lombok.*;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.sql.SQLException;

@Log4j2
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class AuthenticationService {

   @Inject
   private DataBaseContext dataBaseContext;

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
        log.debug("Проверка на то, аутентифицирован ли пользователь");

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
