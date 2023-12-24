import br.com.luiz.smktsystem.service.EmployeerService;
import br.com.luiz.smktsystem.service.dao.EmployeerDAO;
import br.com.luiz.smktsystem.service.dto.EmployeerRegisterDTO;
import br.com.luiz.smktsystem.utils.JpaUtil;
import br.com.luiz.smktsystem.view.MainView;

import javax.persistence.EntityManager;
import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {

       SwingUtilities.invokeLater(() -> {
            new MainView();
        });


                // EntityManager entityManager = JpaUtil.getEntityManager();
        // EmployeerDAO employeerDAO = new EmployeerDAO(entityManager);
        // EmployeerService employeerService = new EmployeerService(employeerDAO);
        // EmployeerRegisterDTO registerDTO = new EmployeerRegisterDTO();
        // registerDTO.setName("Luiz");
        // registerDTO.setCpf("12345678910");
        // employeerService.registerEmployeer(registerDTO);
    }
}
