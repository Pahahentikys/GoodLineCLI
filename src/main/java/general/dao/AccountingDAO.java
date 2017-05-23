package general.dao;

import general.dom.Accounting;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AccountingDAO {

    private static final Logger logger = LogManager.getLogger(AccountingDAO.class.getName());

    public static final String INSERT_ACCOUNTING = "INSERT INTO USER_SEANS(USER_RESOURCE_ID, USER_SEANS_DATE_START, USER_SEANS_DATE_END, USER_SEANS_VOLUME) VALUES (?, ?, ?, ?)";

    private Connection connection;

    public AccountingDAO(Connection connection) {
        this.connection = connection;
    }

    public void addUserSeans(Accounting accounting) {

        logger.debug("Подготовить запрос: " + INSERT_ACCOUNTING);
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_ACCOUNTING);
            preparedStatement.setInt(1, accounting.getResourceId());
            preparedStatement.setDate(2, Date.valueOf(accounting.getStartAccountingDate()));
            preparedStatement.setDate(3, Date.valueOf(accounting.getEndAccountingDate()));
            preparedStatement.setInt(4, Integer.parseInt(String.valueOf(accounting.getVolumeOfUseRes())));
            preparedStatement.executeUpdate();
            logger.debug("Запрос в БД выполнен успешно, пользовательский сеанс сохранён");

        } catch (SQLException e) {
            logger.error("Ошибка доступа к БД, приложение не работает!", e);
        }
    }
}
