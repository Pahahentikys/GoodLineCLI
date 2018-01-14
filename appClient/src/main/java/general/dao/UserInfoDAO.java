package general.dao;

import general.dom.UserInfo;
import lombok.*;
import lombok.extern.log4j.Log4j2;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.sql.Connection;
import java.util.List;

@Log4j2
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class UserInfoDAO {

    @Inject
    private EntityManager entityManager;

    public List<UserInfo> getAllUsersInfo() {
        List<UserInfo> result = entityManager.createQuery("FROM general.dom.UserInfo", UserInfo.class).getResultList();
        return result;
    }

    public UserInfo searchUserLogin(String userLogin) {
        return entityManager.createQuery("FROM general.dom.UserInfo u WHERE u.userLogin = :login", UserInfo.class)
                .setParameter("login", userLogin)
                .getSingleResult();
    }

    public UserInfo searchUserInfoWhereId(int userInfoId) {
        return entityManager.createQuery("FROM general.dom.UserInfo u WHERE u.userId = :userInfoId", UserInfo.class)
                .setParameter("userInfoId", userInfoId)
                .getSingleResult();
    }
}
