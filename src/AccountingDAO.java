import java.sql.*;

/**
 * Created by Pavel on 10.04.2017.
 */
public class AccountingDAO {
    private Connection connection;

    AccountingDAO(Connection connection) {
        this.connection = connection;
    }

    private void addUserSeans(Accounting accounting) {

        try {

            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO GOOD_LINE_CLI_SCHEME.USER_SEANS(user_resource_id, user_seans_date_start, user_seans_date_end, user_seans_volume) VALUES (?, ?, ?, ?)");

            preparedStatement.setInt(1, accounting.getResourceUserId());

            preparedStatement.setDate(2, Date.valueOf(accounting.getStartAccountingDate()));

            preparedStatement.setDate(3, Date.valueOf(accounting.getEndAccountingDate()));

            preparedStatement.setInt(4, Integer.parseInt(accounting.getVolumeOfUseRes()));

            ResultSet resultSet = preparedStatement.executeQuery();

            System.out.println("Сессия сохранена!" + resultSet.getInt("user_seans_id") +
                    resultSet.getInt("user_resource_id") +
                    resultSet.getDate("user_seans_date_start") +
                    resultSet.getDate("user_seans_date_end") +
                    resultSet.getInt("user_seans_volume")
            );

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
