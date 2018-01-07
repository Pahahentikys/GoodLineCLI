package general.dao;

import general.dom.UserInfo;
import lombok.extern.log4j.Log4j2;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Log4j2
public class UserInfoDAO {


    private static final String SELECT_WHERE_USER_LOGIN = "SELECT * FROM USERS WHERE USER_LOGIN = ?";

    private static final String SELECT_ALL_USERS = "SELECT * FROM USERS";

    private static final String SELECT_ALL_USERS_WITH_ID = "SELECT * FROM USERS WHERE USER_ID = ?";

    private Connection connection;

    @Inject
    private EntityManager entityManager;


    @Inject
    public UserInfoDAO(Connection connection) {
        this.connection = connection;
    }

    public List<UserInfo> getAllUsersInfo() {
        List<UserInfo> result = entityManager.createQuery("FROM general.dom.UserInfo", UserInfo.class).getResultList();
        return result;
    }

    public UserInfo searchUserLogin(String userLogin) throws SQLException {
        log.debug("Готовим запрос: " + SELECT_WHERE_USER_LOGIN);
        try {
            PreparedStatement statement = connection.prepareStatement(SELECT_WHERE_USER_LOGIN);
            statement.setString(1, userLogin);
            log.debug("Выполнить запрос: " + statement.toString());
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                log.debug("Запрос выполнен. Наполнение данными объекта UserInfo");
                return UserInfo.builder()
                        .userId(resultSet.getInt("USER_ID"))
                        .userLogin(resultSet.getString("USER_LOGIN"))
                        .userHashPassword(resultSet.getString("USER_PASS_HASH"))
                        .userSalt(resultSet.getString("USER_SALT"))
                        .build();
            } else {
                log.debug("В БД нет записей по условию");
            }

        } catch (SQLException e) {
            log.error("Ошибка доступа к БД, приложение не работает!", e);
        }
        return null;
    }

    public UserInfo searchUserInfoWhereId(int userInfoId) {
        log.debug("Готовим запрос: " + SELECT_ALL_USERS_WITH_ID);
        try {
            PreparedStatement statement = connection.prepareStatement(SELECT_ALL_USERS_WITH_ID);
            statement.setInt(1, userInfoId);
            log.debug("Выполнить запрос: " + statement.toString());
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return UserInfo.builder()
                        .userId(resultSet.getInt("USER_ID"))
                        .userLogin(resultSet.getString("USER_LOGIN"))
                        .userHashPassword(resultSet.getString("USER_PASS_HASH"))
                        .userSalt(resultSet.getString("USER_SALT"))
                        .build();
            } else {
                log.debug("В БД нет записей по условию");
            }
        } catch (SQLException e) {
            log.error("Ошибка доступа к БД, приложение не работает!", e);
        }

        return null;
    }
}
