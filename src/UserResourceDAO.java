import javax.management.relation.Role;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Pavel on 23.04.2017.
 */
public class UserResourceDAO {

    Connection connection;

    public UserResourceDAO(Connection connection) {
        this.connection = connection;
    }

    static final String selectUserResourcesWherePath = "SELECT * FROM GOOD_LINE_CLI_SCHEME.USER_RESOURCES WHERE" +
            "(USER_RESOURCES.USER_RESOURCE_PATH = userResourcePath) OR (USER_RESOURCES.USER_RESOURCE_PATH LIKE ?) AND " +
            "(USER_RESOURCES.USER_RESOURCE_ROLE LIKE ?)";

    UserResources getPathUserResource(int userInfoId, String userResourcePath, String userRole) throws SQLException {

        try {
            PreparedStatement statement = connection.prepareStatement(selectUserResourcesWherePath);
            System.out.println("Ресурс: " + userResourcePath);
            statement.setInt(1, userInfoId);
            statement.setString(2, userResourcePath);
            statement.setString(3, userRole);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {

                System.out.println("ID: " + resultSet.getInt("USER_ID") + ", " + resultSet.getString("USER_RESOURCE_PATH")
                        + ", " + resultSet.getString("USER_RESOURCE_ROLE"));
            }

            } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
