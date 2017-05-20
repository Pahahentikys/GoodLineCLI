import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by Pavel on 10.04.2017.
 */
public class AccountingDAO {
    private Connection connection;

    AccountingDAO(Connection connection) {
        this.connection = connection;
    }

    public void addUserSeans(Accounting accounting) {

        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO GOOD_LINE_CLI_SCHEME.USER_SEANS(user_resource_id, user_seans_date_start, user_seans_date_end, user_seans_volume) VALUES (?, ?, ?, ?)");

            preparedStatement.setInt(1, accounting.getResourceId());

            preparedStatement.setDate(2, Date.valueOf(accounting.getStartAccountingDate()));

            preparedStatement.setDate(3, Date.valueOf(accounting.getEndAccountingDate()));

            preparedStatement.setInt(4, Integer.parseInt(String.valueOf(accounting.getVolumeOfUseRes())));

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
