package providers;

import com.google.inject.Provider;
import general.dao.DataContextDAO;
import org.flywaydb.core.Flyway;

import java.sql.Connection;
import java.sql.SQLException;


public class DataBaseConnectionProvider implements Provider<Connection> {

    private final DataContextDAO dataContextDAO;

    public DataBaseConnectionProvider() {
        dataContextDAO = DataContextDAO.builder()
                .dataBaseDriver("org.h2.Driver")
                .dataBaseUrl("jdbc:h2:file:./src/main/resources/db/GoodLineCLI")
                .dataBaseUserName("Pavel")
                .dataBasePassword("1234")
                .build();


        Flyway flyway = new Flyway();
        flyway.setDataSource(dataContextDAO.getDataBaseUrl(), dataContextDAO.getDataBaseUserName(), dataContextDAO.getDataBasePassword());
        flyway.migrate();

    }


    @Override
    public Connection get() {
        try (Connection connection = dataContextDAO.getConnection()) {

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }
}
