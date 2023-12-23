import br.com.luiz.smktsystem.app.model.Employeer;
import br.com.luiz.smktsystem.service.dto.EmployeerRegisterDTO;
import br.com.luiz.smktsystem.service.impl.EmployeerMapper;
import br.com.luiz.smktsystem.utils.JpaUtil;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class Main {
    public static void main(String[] args) {
        EntityManager entityManager = JpaUtil.getEntityManager();

        EmployeerRegisterDTO registerDTO = new EmployeerRegisterDTO();
        registerDTO.setCpf("12345678910");
        registerDTO.setEmail("emailTeste@gmail.com");
        registerDTO.setName("usuarioTeste");
        registerDTO.setPassword("senhaTeste");

        Employeer employeer = EmployeerMapper.INSTANCE.mapDtoToEntity(registerDTO);

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
        } finally {
            entityManager.close();
            JpaUtil.close();
        }
    }
}
