package providers;

import com.google.inject.Provider;
import general.dao.DataContextDAO;
import org.flywaydb.core.Flyway;

import javax.persistence.EntityManager;

public class EntityManagerProvider implements Provider<EntityManager> {

    private EntityManager entityManager;

    public EntityManagerProvider() {

        final DataContextDAO dataContextDAO;

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
    public EntityManager get() {
        return entityManager;
    }
}
