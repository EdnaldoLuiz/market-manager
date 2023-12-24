package br.com.luiz.smktsystem.utils.hibernate;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import br.com.luiz.smktsystem.utils.JpaUtil;

public class TransactionManager {

    public static void performTransaction(Operation operation) {
        EntityManager entityManager = JpaUtil.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();
            operation.execute(entityManager);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            entityManager.close();
            JpaUtil.close();
        }
    }

    public interface Operation {
        void execute(EntityManager entityManager);
    }
}

