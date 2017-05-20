import java.sql.SQLException;

/**
 * Created by Pavel on 21.03.2017.
 */
public class DataBaseContext {

    AuthentifAndAuthorizService authAndAuthorServ = new AuthentifAndAuthorizService();

    /**
     * Поиск пользователя по логину
     *
     * @param userInfoDAO   - слой доступа к БД
     * @param userInputData - входящие параметры
     * @return - true, если пользователь с таким же логином найден
     * @throws SQLException
     */
    public boolean isGetUserLoginDAO(UserInfoDAO userInfoDAO, UserInputData userInputData) throws SQLException {
        UserInfo userInfo = userInfoDAO.searchUserLogin(userInputData.getUserInputLogin());
        if (userInfo == null) {
            System.out.println("Пользователя с таким логином нет!");
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
    public boolean isGetUserPasswordDAO(UserInfoDAO userInfoDAO, UserInputData userInputData) throws SQLException {
        UserInfo userInfo = userInfoDAO.searchUserLogin(userInputData.getUserInputLogin());
        System.out.println(userInfo.getUserLogin());
        String hashUserPass = authAndAuthorServ.generHashUserPassword(userInputData.getUserInputPassword(), userInfo.getUserSalt());
        if (authAndAuthorServ.isUserHashesEqual(userInfo, hashUserPass)) {
            return true;
        }

        System.out.println("Хэши паролей не совпадают!");
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
    public boolean isResUserAccessDAO(UserResourceDAO userResourceDAO, UserInputData userInputData) throws SQLException {
        UserResources userResources = userResourceDAO.getPathUserResource(userInputData.getUserInputPathResource(), userInputData.getUserInputRole());
        System.out.println(userResources.getUserResResId());
        if (userResources == null) {
            System.out.println("Такого пути нет!");
            return false;
        }
        return true;
    }

}
