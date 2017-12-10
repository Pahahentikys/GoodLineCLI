import com.google.gson.Gson;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Singleton;
import com.google.inject.matcher.Matchers;
import com.google.inject.servlet.GuiceServletContextListener;
import com.google.inject.servlet.ServletModule;
import general.dao.AccountingDAO;
import general.dao.UserInfoDAO;
import general.dao.UserResourceDAO;
import general.servlets.*;
import inject.logger.Log4JTypeListener;
import providers.DataBaseConnectionProvider;
import providers.GsonProvider;

import javax.servlet.annotation.WebListener;
import java.sql.Connection;

@WebListener
public class GuiceServletConfig extends GuiceServletContextListener {

    @Override
    protected Injector getInjector() {
        return Guice.createInjector(new ServletModule() {
            @Override
            protected void configureServlets() {

                // Конфигурация пути до UserServlet.
                serve("/ajax/user").with(UserServlet.class);

                // Конфигурация пути до AuthorityServlet.
                serve("/ajax/authority").with(AuthorityServlet.class);

                // Конфигурация пути до ActivityServlet.
                serve("/ajax/activity").with(ActivityServlet.class);

                // Конфигурация пути до GetServlet.
                serve("/echo/get").with(GetServlet.class);

                // Конфигурация пути до PostServlet.
                serve("/echo/post").with(PostServlet.class);

                // Привязка кастомного слушателя.
                bindListener(Matchers.any(), new Log4JTypeListener());

                // Привязка провайдера для сериализации
                bind(Gson.class).toProvider(GsonProvider.class).in(Singleton.class);

                // Привязка провайдера для соединения с базой данных.
                bind(Connection.class).toProvider(DataBaseConnectionProvider.class).in(Singleton.class);

                bind(UserInfoDAO.class);

                bind(UserResourceDAO.class);

                bind(AccountingDAO.class);

            }

        });
    }
}
