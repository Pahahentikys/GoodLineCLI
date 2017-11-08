package general.servlets;

import inject.logger.InjectLogger;
import org.apache.logging.log4j.Logger;

import javax.inject.Singleton;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import java.io.IOException;

@Singleton
public class UserServlet extends HttpServlet {

    @InjectLogger
    Logger logger;

    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        super.service(req, res);

        logger.debug("It's UserServlet!");
    }
}
