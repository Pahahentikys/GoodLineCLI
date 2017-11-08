import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.matcher.Matchers;
import com.google.inject.servlet.GuiceServletContextListener;
import com.google.inject.servlet.ServletModule;
import general.servlets.ActivityServlet;
import general.servlets.UserServlet;
import inject.logger.Log4JTypeListener;

import javax.servlet.annotation.WebListener;

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
                serve("/ajax/authority").with(UserServlet.class);

                // Конфигурация пути до ActivityServlet.
                serve("/ajax/activity").with(ActivityServlet.class);

                // Привязка кастомного слушателя.
                bindListener(Matchers.any(), new Log4JTypeListener());

            }

        });
    }
}
