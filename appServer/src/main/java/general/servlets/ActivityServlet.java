package general.servlets;

import inject.logger.InjectLogger;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ActivityServlet extends HttpServlet {

    @InjectLogger
    Logger logger;

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.service(req, resp);

        logger.debug("It's ActivityServlet!");
    }
}
