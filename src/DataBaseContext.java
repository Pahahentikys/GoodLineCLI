import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Pavel on 21.03.2017.
 */
public class DataBaseContext {

    AuthentifAndAuthorizService authAndAuthorServ = new AuthentifAndAuthorizService();

    /**
     * Поиск пользователя по логину
     * @param userInfoDAO - слой доступа к БД
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
     * @param userInfoDAO - слой доступа к БД
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
     * Метод, который проверяет доступность к ресурсу
     *
     * @param userResourcesList - коллекция ресурсов, заданных в программе
     * @param userInputData     - объект, который хранит входные данные
     */
    public boolean isResUserAccess(ArrayList<UserResources> userResourcesList, UserInputData userInputData) {
        String[] nodeResInputPath = userInputData.getUserInputPathResource().split("\\.");
        for (UserResources anUserResourcesList : userResourcesList) {
            boolean isResEqual = false;
            String[] userResPath = anUserResourcesList.getResourcePath().split("\\.");
            for (int i = 0; i < userResPath.length; i++) {
                isResEqual = nodeResInputPath[i].equals(userResPath[i]);
                if (isResEqual) {
                    if (userInputData.getUserInputId() == anUserResourcesList.getUserResUserId()) {
                        if (UserRoles.valueOf(userInputData.getUserInputRole()).equals((anUserResourcesList.getUserRole()))) {
                            return true;
                        }
                    }
                }
                return false;
            }
        }
        return false;
    }
}
