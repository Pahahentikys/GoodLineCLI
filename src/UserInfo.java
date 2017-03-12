import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Pavel on 09.03.2017.
 */
public class UserInfo {
    private int userId;
    private String userLogin,
            userHashPassword,
            userSalt;

    public int getUserId() {
        return userId;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public String getUserHashPassword() {
        return userHashPassword;
    }

    public String getUserSalt() {
        return userSalt;
    }

    public UserInfo setUserId(int userId) {
        this.userId = userId;
        return this;
    }

    public UserInfo setUserLogin(String userLogin) {
        this.userLogin = userLogin;
        return this;
    }

    public UserInfo setUserHashPassword(String userNoHashPassword) {
        this.userHashPassword = AuthentifAndAuthorizService.getHashUserPassword(userNoHashPassword, getUserSalt());
        return this;
    }

    public UserInfo setUserSalt(String userSalt) {
        this.userSalt = userSalt;
        return this;
    }

    /**
     * Коллеция объектов с пользователями, которая нужна
     * для формирования тестовых данных
     */
    private static ArrayList<UserInfo> usersInfo = new ArrayList<UserInfo>();

    /**
     * Получить всех пользователей
     *
     * @return - коллекция объектов с пользователями
     */
    public static List<UserInfo> getUsersinfo() {
        return usersInfo;
    }


}
