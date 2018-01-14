package general.dao;

import general.dom.UserResources;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.sql.SQLException;
import java.util.List;

public class UserResourceDAO {

    @Inject
    private EntityManager entityManager;

    public List<UserResources> getAllAccessRightsForResources() {
        return entityManager.createQuery("FROM general.dom.UserResources", UserResources.class).getResultList();
    }

    public UserResources getPathUserResource(String userResourcePath, String userRole) throws SQLException {
        return entityManager.createQuery("FROM general.dom.UserResources r WHERE" +
                "(r.resourcePath LIKE :userResourcePath) AND " +
                "(r.userRole LIKE :userRole)", UserResources.class)
                .setParameter("userResourcePath", userResourcePath)
                .setParameter("userRole", userRole)
                .getSingleResult();
    }

    public UserResources findIdRes(String path) throws SQLException {
        return entityManager.createQuery("FROM general.dom.UserResources.userResourcesId u WHERE u.resourcePath = :path", UserResources.class)
                .setParameter("path", path)
                .getSingleResult();
    }

    public UserResources searchAccessRightWhereUserResId(int userResId) {
        return entityManager.createQuery("FROM general.dom.UserResources u WHERE u.userResourcesId = :userResId", UserResources.class)
                .setParameter("userResId", userResId)
                .getSingleResult();
    }

    public List<UserResources> searchAccessRightWhereUserId(int userId) {
        return entityManager.createQuery("FROM general.dom.UserResources u WHERE u.userInfo.userId = :userId", UserResources.class)
                .setParameter("userId", userId)
                .getResultList();
    }
}
