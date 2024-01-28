package br.com.luiz.smktsystem.view.dialog;

import javax.swing.*;
import br.com.luiz.smktsystem.app.enums.Role;
import br.com.luiz.smktsystem.app.model.Employeer;
import br.com.luiz.smktsystem.service.EmployeerService;
import br.com.luiz.smktsystem.service.dto.EmployeerRegisterDTO;
import br.com.luiz.smktsystem.utils.javax.CustomButton;
import br.com.luiz.smktsystem.utils.javax.CustomColor;
import br.com.luiz.smktsystem.utils.products.ResizeIcon;
import br.com.luiz.smktsystem.view.panel.EmployeesPanel;

import java.awt.*;

public class AddEmployeerDialog extends JFrame {

    private JTextField nameField;
    private JTextField emailField;
    private JTextField cpfField;
    private JComboBox<String> roleComboBox;
    private EmployeerService service;
    private EmployeesPanel panel;

    public AddEmployeerDialog(EmployeerService service, EmployeesPanel panel) {
        this.service = service;
        this.panel = panel;
        setLayout(new GridBagLayout());

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.insets = new Insets(10, 10, 10, 10);

        Font font = new Font("Arial", Font.PLAIN, 18);

        ImageIcon adminIcon = ResizeIcon.createResizedIcon("/icons/employees.png", 80, 80);
        JLabel iconLabel = new JLabel(adminIcon);
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 2;
        add(iconLabel, constraints);

        constraints.gridwidth = 1;

        nameField = createField("Nome:", font, 1, constraints, 10);
        emailField = createField("Email:", font, 2, constraints, 10);
        cpfField = createField("CPF:", font, 3, constraints, 10);

        JLabel roleLabel = new JLabel("Cargo:         ");
        roleLabel.setFont(font);
        constraints.gridx = 0;
        constraints.gridy = 4;
        add(roleLabel, constraints);

        roleComboBox = new JComboBox<>();
        roleComboBox.setFont(font);
        for (Role role : Role.values()) {
            roleComboBox.addItem(role.getDescription());
        }
        constraints.gridx = 1;
        add(roleComboBox, constraints);

        CustomButton addButton = new CustomButton("Adicionar", CustomColor.MAIN_RED, Color.WHITE, 100, 30, 18,
                e -> addEmployeer());
        constraints.gridx = 0;
        constraints.gridy = 5;
        constraints.gridwidth = 2;
        add(addButton, constraints);

        pack();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((screenSize.getWidth() - this.getWidth()) / 2);
        int y = (int) ((screenSize.getHeight() - this.getHeight()) / 2);
        setLocation(x, y);
    }

    private JTextField createField(String labelText, Font font, int gridy, GridBagConstraints constraints,
            int columns) {
        JLabel label = new JLabel(labelText);
        label.setFont(font);
        constraints.gridx = 0;
        constraints.gridy = gridy;
        add(label, constraints);

        JTextField field = new JTextField(columns);
        field.setFont(font);
        constraints.gridx = 1;
        add(field, constraints);

        return field;
    }

    private void addEmployeer() {
        String name = nameField.getText();
        String email = emailField.getText();
        String cpf = cpfField.getText();
        String selectedRoleDescription = (String) roleComboBox.getSelectedItem();

        Role role;
        if ("Admin".equals(selectedRoleDescription)) {
            role = Role.ADMIN;
        } else {
            role = Role.EMPLOYEE;
        }

        EmployeerRegisterDTO registerDTO = new EmployeerRegisterDTO(name, email, cpf, role);
        service.registerEmployeer(new Employeer(registerDTO));
        nameField.setText("");
        emailField.setText("");
        cpfField.setText("");
        roleComboBox.setSelectedIndex(0);
        panel.updateEmployeeData();
        this.dispose();
    }
}