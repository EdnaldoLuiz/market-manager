package br.com.luiz.smktsystem.service.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import br.com.luiz.smktsystem.app.model.Employeer;

public class EmployeerDAO {
    private EntityManager entityManager;

    public EmployeerDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void createEmployeer(Employeer employeer) {
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();
            entityManager.persist(employeer);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public Employeer findEmployeerByEmail(String email) {
        try {
            String jpql = "SELECT e FROM Employeer e WHERE e.email = :email";
            Query query = entityManager.createQuery(jpql, Employeer.class);
            query.setParameter("email", email);
            return (Employeer) query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean isEmailExists(String email) {
        try {
            String jpql = "SELECT COUNT(e) FROM Employeer e WHERE e.email = :email";
            Query query = entityManager.createQuery(jpql);
            query.setParameter("email", email);
            Long count = (Long) query.getSingleResult();
            return count > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
