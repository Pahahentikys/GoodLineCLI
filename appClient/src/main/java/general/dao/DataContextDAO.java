package general.dao;

import lombok.Builder;
import lombok.Getter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Builder
public class DataContextDAO {

    @Getter
    private String dataBaseDriver;

    @Getter
    private String dataBaseUrl;

    @Getter
    private String dataBaseUserName;

    @Getter
    private String dataBasePassword;

    public Connection getConnection() throws SQLException, ClassNotFoundException {
        return DriverManager.getConnection(dataBaseUrl, dataBaseUserName, dataBasePassword);
    }

}
