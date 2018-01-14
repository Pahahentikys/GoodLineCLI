package general;

import com.google.inject.AbstractModule;
import general.dao.AccountingDAO;
import general.dao.UserInfoDAO;
import general.dao.UserResourceDAO;
import general.ioc.providers.EntityManagerProvider;
import general.serv.AuthorizationService;
import general.serv.DataBaseContext;

import javax.persistence.EntityManager;

class GuiceClientConfig extends AbstractModule {

    @Override
    protected void configure() {

        // Привязка для менеджера сущностей БД
        bind(EntityManager.class).toProvider(EntityManagerProvider.class);

        bind(UserInfoDAO.class);

        bind(UserResourceDAO.class);

        bind(AccountingDAO.class);

        bind(DataBaseContext.class);

        bind(AuthorizationService.class);

    }
}
