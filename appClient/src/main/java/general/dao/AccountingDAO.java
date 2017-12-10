package general.dao;

import general.dom.Accounting;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AccountingDAO {

    private static final Logger logger = LogManager.getLogger(AccountingDAO.class.getName());

    private static final String INSERT_ACCOUNTING = "INSERT INTO USER_SEANS(USER_RESOURCE_ID, USER_SEANS_DATE_START, USER_SEANS_DATE_END, USER_SEANS_VOLUME) VALUES (?, ?, ?, ?)";

    private static final String SELECT_ALL_USER_SEANSES = "SELECT * FROM USER_SEANS";

    private Connection connection;

    @Inject
    public AccountingDAO(Connection connection) {
        this.connection = connection;
    }

    public List<Accounting> getAllUserSeanses() {
        List<Accounting> accountings = new ArrayList<>();
        logger.debug("Готовим запрос: " + SELECT_ALL_USER_SEANSES);
        try {
            PreparedStatement statement = connection.prepareStatement(SELECT_ALL_USER_SEANSES);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                accountings.add(
                        Accounting.builder()
                                .accountingId(resultSet.getInt("USER_SEANS_ID"))
                                .resourceId(resultSet.getInt("USER_RESOURCE_ID"))
                                .startAccountingDate(resultSet.getString("USER_SEANS_DATE_START"))
                                .endAccountingDate(resultSet.getString("USER_SEANS_DATE_END"))
                                .volumeOfUseRes(resultSet.getString("USER_SEANS_VOLUME"))
                                .build()
                );

            }
            return accountings;

        } catch (SQLException e) {
            logger.error("Ошибка доступа к БД, приложение не работает!", e);
        }
        return null;
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
