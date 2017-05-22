package general.dao;

import general.dom.UserInfo;
import general.serv.AuthentifAndAuthorizService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Pavel on 06.04.2017.
 */
public class UserInfoDAO {

    private static final Logger logger = LogManager.getLogger(AuthentifAndAuthorizService.class.getName());

    public static final String selectUsersWhereLogin = "SELECT * FROM USERS WHERE USER_LOGIN = ?";

    private Connection connection;

    public UserInfoDAO(Connection connection) {
        this.connection = connection;
    }

    public UserInfo searchUserLogin(String userLogin) throws SQLException {
        logger.debug("Готовим запрос: " + selectUsersWhereLogin);
        try {
            PreparedStatement statement = connection.prepareStatement(selectUsersWhereLogin);
            statement.setString(1, userLogin);
            logger.debug("Выполнить запрос: " + statement.toString());
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                logger.debug("Запрос выполнен. Наполнение данными объекта UserInfo");
                return new UserInfo()
                        .setUserId(resultSet.getInt("USER_ID"))
                        .setUserLogin(resultSet.getString("USER_LOGIN"))
                        .setUserHashPassword(resultSet.getString("USER_PASS_HASH"))
                        .setUserSalt(resultSet.getString("USER_SALT"));

            } else {
                logger.debug("В БД нет записей по условию");
            }

        } catch (SQLException e) {
            logger.error("Ошибка доступа к БД, приложение не работает!");
            e.printStackTrace();
        }
        return null;
    }
}
