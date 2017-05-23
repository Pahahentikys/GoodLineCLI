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

    public UserInfo withUserId(int userId) {
        this.userId = userId;
        return this;
    }
    public UserInfo withUserLogin(String userLogin) {
        this.userLogin = userLogin;
        return this;
    }
    public UserInfo withUserHashPassword(String userHashPassword) {
        this.userHashPassword = userHashPassword;
        return this;
    }
    public UserInfo withUserSalt(String userSalt) {
        this.userSalt = userSalt;
        return this;
    }

}
