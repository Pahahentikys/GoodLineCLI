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
    public String getDataBaseUrl() {
        return dataBaseUrl;
    }
    public String getDataBaseUserName() {
        return dataBaseUserName;
    }
    public String getDataBasePassword() {
        return dataBasePassword;
    }
    public Connection getConnection() throws SQLException, ClassNotFoundException {
        return DriverManager.getConnection(dataBaseUrl, dataBaseUserName, dataBasePassword);
    }

    public DataContextDAO withDataBaseDriver(String dataBaseDriver) {
        this.dataBaseDriver = dataBaseDriver;
        return this;
    }
    public DataContextDAO withDataBaseUrl(String dataBaseUrl) {
        this.dataBaseUrl = dataBaseUrl;
        return this;
    }
    public DataContextDAO withDataBaseUserName(String dataBaseUserName) {
        this.dataBaseUserName = dataBaseUserName;
        return this;
    }
    public DataContextDAO withDataBasePassword(String dataBasePassword) {
        this.dataBasePassword = dataBasePassword;
        return this;
    }

}
