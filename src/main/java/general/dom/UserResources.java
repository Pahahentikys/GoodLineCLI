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

    public UserResources withUserResId(int userResId) {
        this.userResId = userResId;
        return this;
    }

    public UserResources withUserRole(UserRoles userRole) {
        this.userRole = userRole;
        return this;
    }

    public UserResources withUserResUserId(int userResUserId) {
        this.userResUserId = userResUserId;
        return this;
    }

    public UserResources withUserResResId(int userResResId) {
        this.userResResId = userResResId;
        return this;
    }


}
