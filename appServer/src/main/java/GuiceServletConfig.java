import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;
import com.google.inject.servlet.ServletModule;
import general.servlets.ActivityServlet;
import general.servlets.UserServlet;

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

                // Конфигурация пути до
                serve("/ajax/activity").with(ActivityServlet.class);

            }
        });
    }
}
