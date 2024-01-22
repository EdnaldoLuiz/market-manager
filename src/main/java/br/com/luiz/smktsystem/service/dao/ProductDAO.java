package br.com.luiz.smktsystem.service.dao;

import java.util.List;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import br.com.luiz.smktsystem.app.enums.Category;
import br.com.luiz.smktsystem.app.model.Product;

public class ProductDAO {

    private EntityManager entityManager;

    public ProductDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void createProduct(Product product) {
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();
            entityManager.persist(product);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public Product findProductById(UUID id) {
        try {
            return entityManager.find(Product.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void updateProduct(Product product) {
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();
            entityManager.merge(product);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public Product findProductByName(String name) {
        try {
            TypedQuery<Product> query = entityManager.createQuery("SELECT p FROM Product p WHERE p.name = :name",
                    Product.class);
            query.setParameter("name", name);
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public void deleteProduct(String name) {
        EntityTransaction transaction = entityManager.getTransaction();
    
        try {
            transaction.begin();
            Product product = findProductByName(name);
            if (product != null) {
                entityManager.remove(product);
                transaction.commit();
            }
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public List<Product> getAllProducts() {
        try {
            String jpql = "SELECT p FROM Product p";
            Query query = entityManager.createQuery(jpql, Product.class);
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Product> getProductsByCategory(Category category) {
        try {
            String jpql = "SELECT p FROM Product p WHERE p.category = :category";
            Query query = entityManager.createQuery(jpql, Product.class);
            query.setParameter("category", category);
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
