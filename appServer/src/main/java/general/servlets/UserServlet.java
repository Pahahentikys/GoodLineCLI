package general.servlets;

import com.google.gson.Gson;
import general.dao.UserInfoDAO;
import general.dom.UserInfo;
import inject.logger.InjectLogger;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import java.io.IOException;
import java.util.List;

@Singleton
public class UserServlet extends HttpServlet {

    @InjectLogger
    Logger logger;

    @Inject
    Gson gson;

    private String jsonResp;

    @Inject
    private UserInfoDAO userInfoDAO;

    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {

        logger.debug("It's UserServlet!");

        if(req.getParameter("id") == null){
            logger.debug("ID пользователя не получен, выводится весь список юзеров!");
            List<UserInfo> userInfos = userInfoDAO.getAllUsersInfo();
            logger.debug("Запрос пошёл!");
            jsonResp = gson.toJson(userInfos);
            logger.debug("Юзеры: {}", jsonResp);
        }

        res.setContentType("application/json");
        res.getWriter().write(jsonResp);
    }
}
