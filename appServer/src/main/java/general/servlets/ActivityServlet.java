package general.servlets;

import com.google.gson.Gson;
import general.dao.AccountingDAO;
import general.dom.Accounting;
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
public class ActivityServlet extends HttpServlet {

    @InjectLogger
    Logger logger;

    @Inject
    Gson gson;

    private String jsonResp;

    @Inject
    private AccountingDAO accountingDAO;

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        logger.debug("It's ActivityServlet!");

        if(req.getParameter("id")==null){
            logger.debug("ID сеанса не получен, выводится весь список сеансов!");
            List<Accounting> accountings = accountingDAO.getAllUserSeanses();
            logger.debug("Запрос на вывод всех сеансов пошёл!");
            jsonResp = gson.toJson(accountings);
            logger.debug("Сеансы: {}", jsonResp );
        }

        resp.setContentType("application/json");
        resp.getWriter().write(jsonResp);
    }
}
