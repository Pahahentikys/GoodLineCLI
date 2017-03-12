import javax.annotation.Resource;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

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

    /**
     * Коллекция объектов объектов ресурсов пользователя
     */
    private static ArrayList<UserResources> usersResources = new ArrayList<UserResources>();

    /**
     * Получить информацию о ресурсе и его пользователе
     *
     * @return - коллекция объектов с ресурами и пользователями
     */
    public static List<UserResources> getUsersResources() {
        return usersResources;
    }

    /**
     * Коллекция объектов вех ресурсов
     */
    private static List<UserResources> allResources = new ArrayList<UserResources>();

    /**
     * Получить все ресурсы
     * @return - коллекция объектов с набором всех ресурсов
     */
    public static List<UserResources> getAllResources(){
        return allResources;
    }
}
