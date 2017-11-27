package general.dao;

import general.dom.UserInfo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserInfoDAO {

    private static final Logger logger = LogManager.getLogger(UserInfoDAO.class.getName());

    private static final String SELECT_WHERE_USER_LOGIN = "SELECT * FROM USERS WHERE USER_LOGIN = ?";

    private static final String SELECT_ALL_USERS = "SELECT * FROM USERS";

    private Connection connection;

    @Inject
    public UserInfoDAO(Connection connection) {
        this.connection = connection;
    }

    public List<UserInfo> getAllUsersInfo() {
        logger.debug("Готовим запрос: " + SELECT_ALL_USERS);

        List<UserInfo> userInfos = new ArrayList<>();

        try {
            PreparedStatement statement = connection.prepareStatement(SELECT_ALL_USERS);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                logger.debug("Запрос выполнен. Наполнение данными объекта UserInfo");
                userInfos.add(
                        UserInfo.builder()
                                .userId(resultSet.getInt("USER_ID"))
                                .userLogin(resultSet.getString("USER_LOGIN"))
                                .userHashPassword(resultSet.getString("USER_PASS_HASH"))
                                .userSalt(resultSet.getString("USER_SALT"))
                                .build());
            }

            return userInfos;

        } catch (SQLException e) {
            logger.error("Ошибка доступа к БД, приложение не работает!", e);
        }

        return null;
    }

    public UserInfo searchUserLogin(String userLogin) throws SQLException {
        logger.debug("Готовим запрос: " + SELECT_WHERE_USER_LOGIN);
        try {
            PreparedStatement statement = connection.prepareStatement(SELECT_WHERE_USER_LOGIN);
            statement.setString(1, userLogin);
            logger.debug("Выполнить запрос: " + statement.toString());
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                logger.debug("Запрос выполнен. Наполнение данными объекта UserInfo");
                return UserInfo.builder()
                        .userId(resultSet.getInt("USER_ID"))
                        .userLogin(resultSet.getString("USER_LOGIN"))
                        .userHashPassword(resultSet.getString("USER_PASS_HASH"))
                        .userSalt(resultSet.getString("USER_SALT"))
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
