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

//        String dbUrl = System.getenv("JDBC_DATABASE_URL");
//        String username = System.getenv("JDBC_DATABASE_USERNAME");
//        String password = System.getenv("JDBC_DATABASE_PASSWORD");
//
        DataContextDAO dataContextDAO;
        dataContextDAO = DataContextDAO.builder()
//                .dataBaseDriver("org.postgresql.Driver")
                .dataBaseDriver(env.get("JDBC_DATABASE_DRIVER"))
                .dataBaseDialect(env.get("JDBC_DATABASE_DIALECT"))
//                .dataBaseUrl("jdbc:h2:./src/main/resources/db/GoodLineCLIPostgre5;MODE=PostgreSQL")
//                .dataBaseUserName("Pavel")
//                .dataBasePassword("1234")

                .dataBaseUrl(env.get("JDBC_DATABASE_URL"))
                .dataBaseUserName(env.get("JDBC_DATABASE_USERNAME"))
                .dataBasePassword(env.get("JDBC_DATABASE_PASSWORD"))

//                .dataBaseUrl(dbUrl)
//                .dataBaseUserName(username)
//                .dataBasePassword(password)

//                .dataBaseUrl(env.get("jdbc:postgres://ghahoxdilmndej:2f2389c38217e4aa34e08029b3874009562d677a46889715dfccb8d04b594d90@ec2-23-21-201-255.compute-1.amazonaws.com:5432/dfda0o2uae7qt4"))
//                .dataBaseUserName(env.get("ghahoxdilmndej"))
//                .dataBasePassword(env.get("2f2389c38217e4aa34e08029b3874009562d677a46889715dfccb8d04b594d90"))

                .build();


        Flyway flyway = new Flyway();
        flyway.setDataSource(dataContextDAO.getDataBaseUrl(), dataContextDAO.getDataBaseUserName(), dataContextDAO.getDataBasePassword());
        flyway.migrate();

        Map<String, String> properties = new HashMap<>();

//        properties.put("javax.persistence.jdbc.url", env.get("JDBC_DATABASE_URL"));
//        properties.put("javax.persistence.jdbc.user", env.get("JDBC_DATABASE_USERNAME"));
//        properties.put("javax.persistence.jdbc.password", env.get("JDBC_DATABASE_PASSWORD"));
//        properties.put("javax.persistence.jdbc.driver", env.get("JDBC_DATABASE_DRIVER"));

        properties.put("hibernate.connection.url", env.get("JDBC_DATABASE_URL"));
        properties.put("hibernate.connection.user", env.get("JDBC_DATABASE_USERNAME"));
        properties.put("hibernate.connection.password", env.get("JDBC_DATABASE_PASSWORD"));
        properties.put("hibernate.connection.driver", env.get("JDBC_DATABASE_DRIVER"));
        properties.put("hibernate.dialect", env.get("JDBC_DATABASE_DIALECT"));

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("GoodLineCLI", properties);
//        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("GoodLineCLI");
        entityManager = entityManagerFactory.createEntityManager();
    }

    @Override
    public EntityManager get() {
        return entityManager;
    }

}


