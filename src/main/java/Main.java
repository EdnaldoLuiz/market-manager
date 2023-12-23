import br.com.luiz.smktsystem.model.User;
import br.com.luiz.smktsystem.utils.JpaUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public class Main {
    public static void main(String[] args) {
        EntityManager entityManager = JpaUtil.getEntityManager();

        // Exemplo de persistência
        User user = new User();
        user.setId(2L);
        user.setEmail("emailTeste@gmail.com");
        user.setName("usuarioTeste");
        user.setPassword("senhaTeste");

        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();
            entityManager.persist(user);
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
}
