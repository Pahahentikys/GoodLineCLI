package general.servlets;

import com.google.gson.Gson;
import general.dao.UserInfoDAO;
import general.dom.UserInfo;
import inject.logger.InjectLogger;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
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
//@AllArgsConstructor(access = AccessLevel.PUBLIC)
//@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class UserServlet extends HttpServlet {

    @InjectLogger
    Logger logger;

    @Inject
    Gson gson;

    private String jsonResp;

    private String JSON_RESP_USER_MESSAGE_NOT_FOUND = "Пользователя с текущим id в БД не найдено";

    @Inject
    private UserInfoDAO userInfoDAO;

//    @Inject
//    public UserServlet (UserInfoDAO userInfoDAO){this.userInfoDAO = userInfoDAO;}

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

        else {
            searchUserInfoWithId(Integer.parseInt(req.getParameter("id")));
        }

        res.setContentType("application/json");
        res.setCharacterEncoding("UTF-8");
        res.getWriter().write(jsonResp);
    }

    private void searchUserInfoWithId(int userInfoId){
        UserInfo userInfo = userInfoDAO.searchUserInfoWhereId(userInfoId);
        if(userInfo != null){
            jsonResp = gson.toJson(userInfo);
        }
        else {
            jsonResp = JSON_RESP_USER_MESSAGE_NOT_FOUND;
        }
    }
}
