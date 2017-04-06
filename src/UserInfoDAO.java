import java.sql.*;

/**
 * Created by Pavel on 06.04.2017.
 */
public class UserInfoDAO {
    public static final String selectUsersWhereLogin = "SELECT FROM GOOD_LINE_CLI_SCHEME.USERS WHERE LOGIN = ?";
    private Connection connection;

    public UserInfoDAO(Connection connection) {
        this.connection = connection;
    }

    public UserInfo searchUserLogin(String userLogin) throws SQLException {
        try {
            PreparedStatement statement = connection.prepareStatement(selectUsersWhereLogin);
            System.out.println("Логин: " + userLogin);
            statement.setString(1, userLogin);
            //connection.commit();
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()){
                System.out.println(resultSet.getInt("user_id" + ", "+ "user_login"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
           // connection.rollback();
        }
        return null;
    }
}
