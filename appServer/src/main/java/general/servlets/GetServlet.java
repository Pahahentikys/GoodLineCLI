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
public class GetServlet extends HttpServlet {

    @InjectLogger
    Logger logger;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        logger.debug("It's GetServlet!");

        logger.debug("ID: " + req.getParameter("id"));

        // Отрисовка html-страницы при помощи jsp
        req.getRequestDispatcher("/getServletPage.jsp").forward(req, resp);
    }
}
