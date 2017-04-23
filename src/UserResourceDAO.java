import java.sql.Connection;

/**
 * Created by Pavel on 23.04.2017.
 */
public class UserResourceDAO {

    Connection connection;

    public UserResourceDAO(Connection connection) {
        this.connection = connection;
    }
}
