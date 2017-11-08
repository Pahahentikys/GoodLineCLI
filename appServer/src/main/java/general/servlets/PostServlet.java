package general.servlets;

import inject.logger.InjectLogger;
import org.apache.logging.log4j.Logger;

import javax.inject.Singleton;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Singleton
public class PostServlet extends HttpServlet {

    @InjectLogger
    Logger logger;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        logger.debug("It's PostServlet and method doPost");

        logger.debug("ID with it will redirected to getServlet (get?id)" + req.getParameter("id"));

        // Передача id в get-сервлет.
        resp.sendRedirect("get?id=" + req.getParameter("id"));
    }
}
