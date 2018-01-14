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

    private final String JSON_RESP_ACCESS_RIGHT_NOT_FOUND = "Право доступа для ресурса с текущим id в БД не найдено";

    private final int EMTPY_USERS = 0;

    @Inject
    private UserResourceDAO userResourceDAO;

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        logger.debug("It's AuthorityServlet!");

        if (req.getParameter("id") == null) {
            logger.debug("ID права доступа не получен, выводится весь список прав доступа!");
            List<UserResources> userResources = userResourceDAO.getAllAccessRightsForResources();
            logger.debug("Запрос на вывод всех прав пошёл!");
            jsonResp = gson.toJson(userResources);
            logger.debug("Права: {}", jsonResp);
        } else {
            searchAuthorityWhereResId(Integer.parseInt(req.getParameter("id")));
        }

        if (req.getParameter("user_id") != null) {
            searchAuthorityWhereUserId(Integer.parseInt(req.getParameter("user_id")));
        }

        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().write(jsonResp);
    }

    private void searchAuthorityWhereResId(int resId) {

        UserResources userResources = userResourceDAO.searchAccessRightWhereUserResId(resId);
        if (userResources != null) {
            jsonResp = gson.toJson(userResources);
        } else {
            jsonResp = gson.toJson(JSON_RESP_ACCESS_RIGHT_NOT_FOUND);
        }
    }

    private void searchAuthorityWhereUserId(int userId) {
        List<UserResources> userResourcesList = userResourceDAO.searchAccessRightWhereUserId(userId);
        if (userResourcesList.size() == EMTPY_USERS) {
            jsonResp = gson.toJson(JSON_RESP_ACCESS_RIGHT_NOT_FOUND);
        } else {
            jsonResp = gson.toJson(userResourcesList);
        }
    }
}
