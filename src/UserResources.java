/**
 * Created by Pavel on 09.03.2017.
 */
public class UserResources {
    private UserRoles userRole;
    private int userResUserId,
            userResResId;

    public UserRoles getUserRole() {
        return userRole;
    }

    public int getUserResUserId() {
        return userResUserId;
    }

    public int getUserResResId() {
        return userResResId;
    }

    public UserResources setUserRole(UserRoles userRole) {
        this.userRole = userRole;
        return this;
    }

    public UserResources setUserResUserId(int userResUserId) {
        this.userResUserId = userResUserId;
        return this;
    }

    public UserResources setUserResResId(int userResResId) {
        this.userResResId = userResResId;
        return this;
    }
}
