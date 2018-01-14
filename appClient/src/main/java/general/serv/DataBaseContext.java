package general.serv;

import general.ExitCodeType;
import general.dao.UserInfoDAO;
import general.dao.UserResourceDAO;
import general.dom.UserInfo;
import general.dom.UserResources;
import lombok.*;
import lombok.extern.log4j.Log4j2;

import javax.inject.Inject;
import java.sql.SQLException;

@Log4j2
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class DataBaseContext {

    private AuthenticationService authenticationService = new AuthenticationService();

    @Inject
    private UserInfoDAO userInfoDAO;

    /**
     * Поиск пользователя по логину
     *
     * @param userInfoDAO - слой доступа к БД
     * @param userLogin   - пользовательский логин, который считывается с входных аргументов
     * @return - true, если пользователь с таким же логином найден
     * @throws SQLException
     */
    public int hasGetUserLoginDAO(String userLogin) throws SQLException {
        UserInfo userInfo = userInfoDAO.searchUserLogin(userLogin);

        if (userInfo == null) {

            log.error("Пользователь не найден по логину {}", userLogin);
            return ExitCodeType.INVALID_LOGIN.getExitCode();
        }

        return ExitCodeType.SUCCESS.getExitCode();
    }

    /**
     * Поиск пользователя по паролю
     *
     * @param userInfoDAO  - слой доступа к БД
     * @param userLogin    - пользовательский логин, который считывается с входных аргументов
     * @param userPassword - пользовательская пароль, которая считывается с входных аргументов
     * @return - true, если хэши паролей совпадают
     * @throws SQLException
     */
    public int hasGetUserPasswordDAO(String userLogin, String userPassword) throws SQLException {
        UserInfo userInfo = userInfoDAO.searchUserLogin(userLogin);
        String hashUserPass = authenticationService.generHashUserPassword(userPassword, userInfo.getUserSalt());
        if (authenticationService.isUserHashesEqual(userInfo, hashUserPass)) {

            log.info("Пользователь по логину: {} и паролю: {} найден", userLogin, userPassword);
            return ExitCodeType.SUCCESS.getExitCode();
        }

        log.error("Хэши паролей не совпадают!");
        return ExitCodeType.INVALID_PASSWORD.getExitCode();
    }

    /**
     * Метод, который проверяет доступность юзеру к ресурсу
     *
     * @param userResourceDAO  - слой доступа к БД, который наполняет объект UserResource данными из БД
     * @param userResourcePath - пользовательский путь, который считывается с входных аргументов
     * @param userResourceRole - пользовательская роль, которая считывается с входных аргументов
     * @return - true, если доступ к ресурсу есть, false - если доступ к ресурсу отсутствует
     * @throws SQLException
     */
    public int hasResUserAccessDAO(UserResourceDAO userResourceDAO, String userResourcePath, String userResourceRole) throws SQLException {
        UserResources userResources = userResourceDAO.getPathUserResource(userResourcePath, userResourceRole);
        if (userResources == null) {

            log.error("Пути к ресурсу {} не существует! Либо не существует данного пути: {} с ролью: {}", userResourcePath, userResourcePath, userResourceRole);
            return ExitCodeType.INVALID_ACCESS.getExitCode();
        }

        log.info("Роль: {} соответствует ресурсу: {}", userResourceRole, userResourcePath);
        return ExitCodeType.SUCCESS.getExitCode();
    }
}
