package general.dao;

import lombok.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Builder
//@NoArgsConstructor(access = AccessLevel.PUBLIC)
//@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class DataContextDAO {

    @Getter
    private String dataBaseDriver;

    @Getter
    private String dataBaseUrl;

    @Getter
    private String dataBaseUserName;

    @Getter
    private String dataBasePassword;

    private String dataBaseDialect;

    public Connection getConnection() throws SQLException, ClassNotFoundException {
        return DriverManager.getConnection(dataBaseUrl, dataBaseUserName, dataBasePassword);
    }

}
