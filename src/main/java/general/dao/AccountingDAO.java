//package general.dao;
package general.dao;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import general.dom.*;
import general.Main;


/**
 * Created by Pavel on 10.04.2017.
 */
public class AccountingDAO {
   // public static final String insertAccounting = "INSERT INTO GOOD_LINE_CLI_SCHEME.USER_SEANS(user_resource_id, user_seans_date_start, user_seans_date_end, user_seans_volume) VALUES (?, ?, ?, ?)";
    public static final String insertAccounting = "INSERT INTO USER_SEANS(USER_RESOURCE_ID, USER_SEANS_DATE_START, USER_SEANS_DATE_END, USER_SEANS_VOLUME) VALUES (?, ?, ?, ?)";
    private Connection connection;

   public AccountingDAO(Connection connection) {
        this.connection = connection;
    }

    public void addUserSeans(Accounting accounting) {

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(insertAccounting);

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
