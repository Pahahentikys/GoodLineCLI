import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Pavel on 09.03.2017.
 */
public class UserResources {
    private UserRoles userRole;
    private String resourcePath;
    private int userResId,
            userResUserId,
            userResResId;

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

//    /**
//     * Коллекция объектов ресурсов, на которые может переходить пользователь
//     */
//    private static List<UserResources> usersResources = Arrays.asList(
//            new UserResources()
//                    .setUserResId(1)
//                    .setUserResUserId(1)
//                    .setUserResResId(1)
//                    .setResourcePath("A.B")
//                    .setUserRole(UserRoles.EXECUTE)
//            ,
//            new UserResources()
//                    .setUserResId(2)
//                    .setUserResUserId(2)
//                    .setUserResResId(2)
//                    .setResourcePath("A.B.C")
//                    .setUserRole(UserRoles.EXECUTE)
//
//    );
//
//    /**
//     * Получить информацию о ресурсе и его пользователе
//     *
//     * @return - коллекция объектов с ресурами и пользователями
//     */
//    public static List<UserResources> getUsersResources() {
//        return usersResources;
//    }


}
