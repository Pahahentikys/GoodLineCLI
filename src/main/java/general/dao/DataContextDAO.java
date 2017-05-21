/**
 * Created by Pavel on 30.03.2017.
 */
package general.dao;
import  java.sql.*;

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

    public Connection getConnection() throws SQLException, ClassNotFoundException
    {
            try {
              return DriverManager.getConnection(dataBaseUrl, dataBaseUserName, dataBasePassword);
            }
            catch (Exception ex)
            {
                throw ex;
            }
    }

}