package general.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataContextDAO {

    private String dataBaseDriver;
    private String dataBaseUrl;
    private String dataBaseUserName;
    private String dataBasePassword;

    public String getDataBaseDriver() {
        return dataBaseDriver;
    }

    public DataContextDAO setDataBaseDriver(String dataBaseDriver) {
        this.dataBaseDriver = dataBaseDriver;
        return this;
    }

    public String getDataBaseUrl() {
        return dataBaseUrl;
    }

    public DataContextDAO setDataBaseUrl(String dataBaseUrl) {
        this.dataBaseUrl = dataBaseUrl;
        return this;
    }

    public String getDataBaseUserName() {
        return dataBaseUserName;
    }

    public DataContextDAO setDataBaseUserName(String dataBaseUserName) {
        this.dataBaseUserName = dataBaseUserName;
        return this;
    }

    public String getDataBasePassword() {
        return dataBasePassword;
    }

    public DataContextDAO setDataBasePassword(String dataBasePassword) {
        this.dataBasePassword = dataBasePassword;
        return this;
    }

    public Connection getConnection() throws SQLException, ClassNotFoundException {
            return DriverManager.getConnection(dataBaseUrl, dataBaseUserName, dataBasePassword);
    }
}
