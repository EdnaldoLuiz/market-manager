package br.com.luiz.smktsystem.utils;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class JpaUtil {
  private static final String PERSISTENCE_UNIT_NAME = "PERSISTENCE";

  private static EntityManagerFactory factory;

  static {
    factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
  }

  public static EntityManager getEntityManager() {
    return factory.createEntityManager();
  }

  public static void close() {
    if (factory != null && factory.isOpen()) {
      factory.close();
    }
  }
}
