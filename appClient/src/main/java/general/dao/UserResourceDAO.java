package general.dao;

import general.dom.UserResources;
import general.dom.UserRoles;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserResourceDAO {

    private static final Logger logger = LogManager.getLogger(UserResourceDAO.class.getName());

    private Connection connection;

    @Inject
    public UserResourceDAO(Connection connection) {
        this.connection = connection;
    }


    final String SELECT_WHERE_USER_RESOURCE_PATH = "SELECT * FROM USER_RESOURCES WHERE" +
            "(USER_RESOURCES.USER_RESOURCE_PATH LIKE ?) AND " +
            "(USER_RESOURCES.USER_RESOURCE_ROLE LIKE ?)";

    final String SELECT_USER_RESOURCE_ID = "SELECT USER_RESOURCE_ID FROM USER_RESOURCES WHERE USER_RESOURCES.USER_RESOURCE_PATH = ?";

    final String SELECT_ALL_ACCESS_RIGHTS_FOR_RESOURCES = "SELECT * FROM USER_RESOURCES";

    final String SELECT_ALL_ACCESS_RIGHT_FOR_RESOURCE_WHERE_RESOURCE_ID = "SELECT * FROM USER_RESOURCES WHERE USER_RESOURCE_ID = ?";

    public List<UserResources> getAllAccessRightsForResources() {
        List<UserResources> userResources = new ArrayList<>();
        logger.debug("Готовим запрос: " + SELECT_ALL_ACCESS_RIGHTS_FOR_RESOURCES);
        try {
            PreparedStatement statement = connection.prepareStatement(SELECT_ALL_ACCESS_RIGHTS_FOR_RESOURCES);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                userResources.add(
                        UserResources.builder()
                                .userResUserId(resultSet.getInt("USER_ID"))
                                .resourcePath(resultSet.getString("USER_RESOURCE_PATH"))
                                .userRole(UserRoles.valueOf(resultSet.getString("USER_RESOURCE_ROLE")))
                                .build());
            }
            return userResources;

        } catch (SQLException e) {
            logger.error("Ошибка доступа к БД, приложение не работает!", e);
        }

        return null;
    }

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

    public UserResources searchAccessRightWhereUserResId(int userResId) {
        logger.debug("Подготовить запрос: " + SELECT_ALL_ACCESS_RIGHT_FOR_RESOURCE_WHERE_RESOURCE_ID);
        try {
            PreparedStatement statement = connection.prepareStatement(SELECT_ALL_ACCESS_RIGHT_FOR_RESOURCE_WHERE_RESOURCE_ID);
            statement.setInt(1, userResId);
            logger.debug("Выполнить запрос: " + statement.toString());
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                logger.debug("Запрос выполнен. Наполнение данными объекта UserResource");
                return UserResources.builder()
                        .userResUserId(resultSet.getInt("USER_ID"))
                        .userRole(UserRoles.valueOf(resultSet.getString("USER_RESOURCE_ROLE")))
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
