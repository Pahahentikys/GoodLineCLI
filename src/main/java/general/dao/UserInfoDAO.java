package general.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import general.dom.*;

/**
 * Created by Pavel on 06.04.2017.
 */
public class UserInfoDAO {
//    public static final String selectUsersWhereLogin = "SELECT * FROM GOOD_LINE_CLI_SCHEME.USERS WHERE USER_LOGIN = ?";
    public static final String selectUsersWhereLogin = "SELECT * FROM USERS WHERE USER_LOGIN = ?";
    private Connection connection;

    public UserInfoDAO(Connection connection) {
        this.connection = connection;
    }

    public UserInfo searchUserLogin(String userLogin) throws SQLException {
        try {
            PreparedStatement statement = connection.prepareStatement(selectUsersWhereLogin);
            System.out.println("Логин: " + userLogin);
            statement.setString(1, userLogin);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {

                System.out.println("ID: " + resultSet.getInt("USER_ID") + ", " + resultSet.getString("USER_LOGIN") + ", " + resultSet.getString("USER_PASS_HASH") + ", " + resultSet.getString("USER_SALT"));
                return new UserInfo()
                        .setUserId(resultSet.getInt("USER_ID"))
                        .setUserLogin(resultSet.getString("USER_LOGIN"))
                        .setUserHashPassword(resultSet.getString("USER_PASS_HASH"))
                        .setUserSalt(resultSet.getString("USER_SALT"));

            } else {
                System.out.println("В БД нет записей с таким логином!");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
