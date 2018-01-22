package providers;

import com.google.inject.Provider;
import general.dao.DataContextDAO;
import org.flywaydb.core.Flyway;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class EntityManagerProvider implements Provider<EntityManager> {

    private EntityManager entityManager;

    public EntityManagerProvider() {

        Map<String, String> env = System.getenv();

        DataContextDAO dataContextDAO;
        dataContextDAO = DataContextDAO.builder()
                .dataBaseDriver("org.postgresql.Driver")
//                .dataBaseUrl("jdbc:h2:./src/main/resources/db/GoodLineCLIPostgre3;MODE=PostgreSQL")
//                .dataBaseUserName("Pavel")
//                .dataBasePassword("1234")
                .dataBaseUrl(env.get("JDBC_DATABASE_URL"))
                .dataBaseUserName(env.get("JDBC_DATABASE_USERNAME"))
                .dataBasePassword(env.get("JDBC_DATABASE_PASSWORD"))

                .build();


        Flyway flyway = new Flyway();
        flyway.setDataSource(dataContextDAO.getDataBaseUrl(), dataContextDAO.getDataBaseUserName(), dataContextDAO.getDataBasePassword());
        flyway.migrate();

        Map<String, String> properties = new HashMap<>();

        properties.put("javax.persistence.jdbc.url", env.get("JDBC_DATABASE_URL"));
        properties.put("javax.persistence.jdbc.user", env.get("JDBC_DATABASE_USERNAME"));
        properties.put("javax.persistence.jdbc.password", env.get("JDBC_DATABASE_PASSWORD"));

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("GoodLineCLI", properties);
        entityManager = entityManagerFactory.createEntityManager();
    }

    @Override
    public EntityManager get() {
        return entityManager;
    }

}


