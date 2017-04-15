
import com.sun.org.apache.xpath.internal.SourceTree;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Pavel on 21.03.2017.
 */
public class DataBaseContext {

    AuthentifAndAuthorizService authAndAuthorServ = new AuthentifAndAuthorizService();

    public boolean isGetUserLoginDAO(UserInfoDAO userInfoDAO, UserInputData userInputData) throws SQLException {
        UserInfo userInfo = userInfoDAO.searchUserLogin(userInputData.getUserInputLogin());
//        if (userInfo.equals(userInputData.getUserInputLogin())) {
//            System.out.println("Пользователя с таким логином есть!");
//            return true;
//        }
//        System.out.println("Пользователя с таким логином нет!");
//        return false;

        if (userInfo == null) {
            System.out.println("Пользователя с таким логином нет!");
            return false;
        }
        return true;

    }



    /**
     * Получить пароль пользователяя
     *
     * @param usersList   - коллекция пользователей
     * @param userInpdata - введённые пользователем данные
     * @return - true, если парольные хэши совпадают
     */
    public boolean isGetUserPassword(ArrayList<UserInfo> usersList, UserInputData userInpdata) {

        for (UserInfo user : usersList) {
            if (userInpdata.getUserInputId() == user.getUserId()) {
                String hashUserPass = authAndAuthorServ.generHashUserPassword(userInpdata.getUserInputPassword(), user.getUserSalt());
                if (authAndAuthorServ.isUserHashesEqual(user, hashUserPass)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean isGetUserPasswordDAO(UserInfoDAO userInfoDAO, UserInputData userInputData) throws SQLException {
        //UserInfo userInfo = userInfoDAO.searchUserLogin(userInputData.getUserInputLogin());
        UserInfo userInfo = new UserInfo();
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
