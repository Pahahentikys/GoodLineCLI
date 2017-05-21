
/**
 * Created by Pavel on 09.03.2017.
 */
package general.dom;
public class UserResources {
    private UserRoles userRole;
    private String resourcePath;
    private int userResId;
    private int userResUserId;
    private int userResResId;

    public String getResourcePath() {
        return resourcePath;
    }

    public UserResources setResourcePath(String resourcePath) {
        this.resourcePath = resourcePath;
        return this;
    }

    public UserRoles getUserRole() {
        return userRole;
    }

    public int getUserResUserId() {
        return userResUserId;
    }

    public int getUserResResId() {
        return userResResId;
    }

    public int getUserResId() {
        return userResId;
    }

    public UserResources setUserResId(int userResId) {
        this.userResId = userResId;
        return this;
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