package general.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Pavel on 25.10.2017.
 */
public class GetServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // В данный объект будет помешаться некоторая html-структура.
        PrintWriter printerWriter = resp.getWriter();

        // Отрисовка необходимой html-структуры по обращению к данному сервлету.
        printerWriter.println("<html>");
        printerWriter.println("<body>");
        printerWriter.println("<h1>ID: " + req.getParameter("id") + "</h1>");
        printerWriter.println("</body>");
        printerWriter.println("</html>");
    }
}
