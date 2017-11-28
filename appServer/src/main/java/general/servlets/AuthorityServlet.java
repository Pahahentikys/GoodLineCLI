package general.servlets;

import com.google.gson.Gson;
import general.dao.UserResourceDAO;
import general.dom.UserResources;
import inject.logger.InjectLogger;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Singleton
public class AuthorityServlet extends HttpServlet {

    @InjectLogger
    Logger logger;

    @Inject
    Gson gson;

    private String jsonResp;

    @Inject
    private UserResourceDAO userResourceDAO;

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.service(req, resp);

        logger.debug("It's AuthorityServlet!");

        if (req.getParameter("id") == null) {
            logger.debug("ID права доступа не получен, выводится весь список прав доступа!");
            List<UserResources> userResources = userResourceDAO.getAllAccessRightsForResources();
            logger.debug("Запрос на вывод всех прав пошёл!");
            jsonResp = gson.toJson(userResources);
            logger.debug("Права: {}", jsonResp);
        }

        resp.setContentType("application/json");
        resp.getWriter().write(jsonResp);
    }
}
