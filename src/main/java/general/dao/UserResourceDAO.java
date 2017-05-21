package general.dao;

import general.dom.UserResources;
import general.serv.AuthentifAndAuthorizService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Pavel on 23.04.2017.
 */
public class UserResourceDAO {

    private static final Logger logger = LogManager.getLogger(AuthentifAndAuthorizService.class.getName());

    Connection connection;

    public UserResourceDAO(Connection connection) {
        this.connection = connection;
    }

    //    final String selectUserResourcesWherePath = "SELECT * FROM GOOD_LINE_CLI_SCHEME.USER_RESOURCES WHERE" +
//            "(USER_RESOURCES.USER_RESOURCE_PATH LIKE ?) AND " +
//            "(USER_RESOURCES.USER_RESOURCE_ROLE LIKE ?)";
    final String selectUserResourcesWherePath = "SELECT * FROM USER_RESOURCES WHERE" +
            "(USER_RESOURCES.USER_RESOURCE_PATH LIKE ?) AND " +
            "(USER_RESOURCES.USER_RESOURCE_ROLE LIKE ?)";

    //    final String selectUserResourceId = "SELECT USER_RESOURCE_ID FROM GOOD_LINE_CLI_SCHEME.USER_RESOURCES WHERE USER_RESOURCES.USER_RESOURCE_PATH = ?";
    final String selectUserResourceId = "SELECT USER_RESOURCE_ID FROM USER_RESOURCES WHERE USER_RESOURCES.USER_RESOURCE_PATH = ?";

    public UserResources getPathUserResource(String userResourcePath, String userRole) throws SQLException {
        logger.debug("Подготовить запрос: " + selectUserResourcesWherePath);
        try {
            PreparedStatement statement = connection.prepareStatement(selectUserResourcesWherePath);
            System.out.println(userResourcePath + ", " + userRole);
            statement.setString(1, userResourcePath);
            statement.setString(2, userRole);
            //statement.setInt(3, resourceId);
            logger.debug("Выполнить запрос: " + statement.toString());
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                logger.debug("Запрос выполнен. Наполнение данными объекта UserResource");
                //System.out.println(resultSet.getString("USER_RESOURCE_PATH")
                //      + ", " + resultSet.getString("USER_RESOURCE_ROLE"));
                return new UserResources()
                        .setUserResResId(resultSet.getInt("USER_RESOURCE_ID"))
                        .setResourcePath(resultSet.getString("USER_RESOURCE_PATH"));
            } else {
                logger.debug("В БД нет записей по условию");
            }

        } catch (SQLException e) {
            logger.error("Ошибка доступа к БД, приложение не работает!");
            e.printStackTrace();
        }
        return null;


    }

    public UserResources findIdRes(String path) throws SQLException {
        logger.debug("Подготовить запрос: "+selectUserResourceId);
        try {
            PreparedStatement statement = connection.prepareStatement(selectUserResourceId);
            statement.setString(1, path);
            logger.debug("Выполнить запрос: "+statement.toString());
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                System.out.println(resultSet.getInt("USER_RESOURCE_ID"));
                return new UserResources()
                        .setUserResResId(resultSet.getInt("USER_RESOURCE_ID"));
            }
            else {
                logger.debug("В БД нет записей по условию");
            }

        } catch (SQLException e) {
            logger.error("Ошибка доступа к БД, приложение не работает!");
            e.printStackTrace();
        }

        return null;

    }
}
