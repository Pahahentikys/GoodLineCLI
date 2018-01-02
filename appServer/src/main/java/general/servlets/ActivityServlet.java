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

    private final String ACTIVITY_NOT_FOUND = "Активности с таким id не существует";

    @Inject
    private AccountingDAO accountingDAO;

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        logger.debug("It's ActivityServlet!");

        if (req.getParameter("id") == null) {
            logger.debug("ID сеанса не получен, выводится весь список сеансов!");
            List<Accounting> accountings = accountingDAO.getAllUserSeanses();
            logger.debug("Запрос на вывод всех сеансов пошёл!");
            jsonResp = gson.toJson(accountings);
            logger.debug("Сеансы: {}", jsonResp);
        } else {
            searchAccountingWhereId(Integer.parseInt(req.getParameter("id")));
        }
        if (req.getParameter("authorityId") != null) {
            searchAccountingWhereUserResId(Integer.parseInt(req.getParameter("authorityId")));
        }
        resp.setContentType("application/json");
        resp.getWriter().write(jsonResp);
    }

    private void searchAccountingWhereId(int accountingId) {
        Accounting accounting = accountingDAO.searchAccountingWithId(accountingId);
        if (accounting == null) {
            jsonResp = gson.toJson(ACTIVITY_NOT_FOUND);
        } else {
            jsonResp = gson.toJson(accounting);
        }
    }

    private void searchAccountingWhereUserResId(int userResId) {
        Accounting accounting = accountingDAO.searchAccountingWithUserResId(userResId);
        if (accounting == null) {
            jsonResp = gson.toJson(ACTIVITY_NOT_FOUND);
        } else {
            jsonResp = gson.toJson(accounting);
        }
    }
}
