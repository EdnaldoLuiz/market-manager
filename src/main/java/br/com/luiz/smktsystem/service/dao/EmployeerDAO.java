package br.com.luiz.smktsystem.service.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

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
}
