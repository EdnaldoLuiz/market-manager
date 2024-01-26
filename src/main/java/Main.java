import javax.persistence.EntityManager;
import javax.swing.SwingUtilities;

import br.com.luiz.smktsystem.service.EmployeerService;
import br.com.luiz.smktsystem.service.dao.EmployeerDAO;
import br.com.luiz.smktsystem.utils.hibernate.JpaUtil;
import br.com.luiz.smktsystem.view.panel.LoginPanel;

public class Main {
    public static void main(String[] args) {
        EntityManager entityManager = JpaUtil.getEntityManager();
        EmployeerDAO employeerDAO = new EmployeerDAO(entityManager);
        EmployeerService employeerService = new EmployeerService(employeerDAO);
        SwingUtilities.invokeLater(() -> new LoginPanel(employeerService));
    }
}
