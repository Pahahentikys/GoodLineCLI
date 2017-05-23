package general.dom;


public class UserInfo {
    private int userId;
    private String userLogin;
    private String userHashPassword;
    private String userSalt;

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

    public UserInfo setUserHashPassword(String userHashPassword) {
        this.userHashPassword = userHashPassword;
        return this;
    }

    public UserInfo setUserSalt(String userSalt) {
        this.userSalt = userSalt;
        return this;
    }

}
