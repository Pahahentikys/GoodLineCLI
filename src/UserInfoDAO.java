import java.sql.Connection;

/**
 * Created by Pavel on 06.04.2017.
 */
public class UserInfoDAO {
    private Connection connection;

    public UserInfoDAO(Connection connection)
    {
        this.connection = connection;
    }
}
