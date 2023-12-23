package br.com.luiz.smktsystem.utils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

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
