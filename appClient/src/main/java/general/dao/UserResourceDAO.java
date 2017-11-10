package general.dao;

import general.dom.UserResources;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserResourceDAO {

    private static final Logger logger = LogManager.getLogger(UserResourceDAO.class.getName());

    Connection connection;

    public UserResourceDAO(Connection connection) {
        this.connection = connection;
    }


    final String SELECT_WHERE_USER_RESOURCE_PATH = "SELECT * FROM USER_RESOURCES WHERE" +
            "(USER_RESOURCES.USER_RESOURCE_PATH LIKE ?) AND " +
            "(USER_RESOURCES.USER_RESOURCE_ROLE LIKE ?)";

    final String SELECT_USER_RESOURCE_ID = "SELECT USER_RESOURCE_ID FROM USER_RESOURCES WHERE USER_RESOURCES.USER_RESOURCE_PATH = ?";

    public UserResources getPathUserResource(String userResourcePath, String userRole) throws SQLException {
        logger.debug("Подготовить запрос: " + SELECT_WHERE_USER_RESOURCE_PATH);
        String findPath = "";
        try {
            String[] arrayOfPath = userResourcePath.split("\\.");

            for (String path : arrayOfPath) {
                findPath += path;
                PreparedStatement statement = connection.prepareStatement(SELECT_WHERE_USER_RESOURCE_PATH);
                statement.setString(1, findPath);
                statement.setString(2, userRole);
                logger.debug("Выполнить запрос: " + statement.toString());
                ResultSet resultSet = statement.executeQuery();

                if (resultSet.next()) {
                    logger.debug("Запрос выполнен. Наполнение данными объекта UserResource");
                    return UserResources.builder()
                            .userResResId(resultSet.getInt("USER_RESOURCE_ID"))
                            .resourcePath(resultSet.getString("USER_RESOURCE_PATH"))
                            .build();
                }

                findPath += ".";

                if (!resultSet.next()) {
                    logger.debug("В БД нет записей по условию");
                    return null;
                }
            }

        } catch (SQLException e) {
            logger.error("Ошибка доступа к БД, приложение не работает!", e);
            return null;
        }
        return null;
    }

    public UserResources findIdRes(String path) throws SQLException {
        logger.debug("Подготовить запрос: " + SELECT_USER_RESOURCE_ID);
        try {
            PreparedStatement statement = connection.prepareStatement(SELECT_USER_RESOURCE_ID);
            statement.setString(1, path);
            logger.debug("Выполнить запрос: " + statement.toString());
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                logger.debug("Запрос выполнен. Наполнение данными объекта UserResource");
                return UserResources.builder()
                        .userResResId(resultSet.getInt("USER_RESOURCE_ID"))
                        .build();
            } else {
                logger.debug("В БД нет записей по условию");
            }

        } catch (SQLException e) {
            logger.error("Ошибка доступа к БД, приложение не работает!", e);
        }
        return null;
    }
}
