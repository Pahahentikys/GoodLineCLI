package general.dao;

import general.dom.Accounting;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.sql.*;
import java.util.List;

public class AccountingDAO {

    @Inject
    private EntityManager entityManager;

    public List<Accounting> getAllUserSeanses() {
        return entityManager.createQuery("FROM general.dom.Accounting", Accounting.class).getResultList();
    }

    public Accounting searchAccountingWithId(int userSeansId) {
        return entityManager.createQuery("FROM general.dom.Accounting a WHERE a.accountingId = :userSeansId", Accounting.class)
                .setParameter("userSeansId", userSeansId)
                .getSingleResult();
    }

    public List<Accounting> searchAccountingWithUserResId(int userResId) {
        return entityManager.createQuery("FROM Accounting a WHERE a.userResources.userResourcesId = :userResId", Accounting.class)
                .setParameter("userResId", userResId)
                .getResultList();
    }

    public void addUserSeans(Accounting accounting) {
        entityManager.getTransaction().begin();
        entityManager.persist(accounting);
        entityManager.flush();
        entityManager.getTransaction().commit();
    }

}
