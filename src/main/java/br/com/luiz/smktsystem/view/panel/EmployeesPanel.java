package br.com.luiz.smktsystem.view.panel;

import br.com.luiz.smktsystem.service.EmployeerService;
import br.com.luiz.smktsystem.service.dto.EmployeerListDTO;
import br.com.luiz.smktsystem.utils.javax.CustomButton;
import br.com.luiz.smktsystem.utils.javax.CustomColor;
import br.com.luiz.smktsystem.view.dialog.AddEmployeerDialog;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import java.awt.*;
import java.text.NumberFormat;
import java.util.List;

public class EmployeesPanel extends JPanel {

    private EmployeerService employeerService;
    private JTable employeesTable;

    private static final String FONT_NAME = "Arial";
    private static final int FONT_SIZE = 16;
    private static final int ROW_HEIGHT = 30;

    public EmployeesPanel(EmployeerService employeerService) {
        this.employeerService = employeerService;
        initializeUI();
        loadEmployeeData();
    }

    private void initializeUI() {
        setLayout(new BorderLayout());

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton excluir = createButton("Excluir Funcionário", CustomColor.MAIN_RED, Color.WHITE, 200, 40, FONT_SIZE);
        JButton adicionar = createButton("Registrar Funcionário", CustomColor.MAIN_RED, Color.WHITE, 200, 40,
                FONT_SIZE);
        adicionar.addActionListener(e -> {
            AddEmployeerDialog dialog = new AddEmployeerDialog(employeerService);
            dialog.setVisible(true);
        });
        buttonPanel.add(adicionar);
        buttonPanel.add(excluir);

        add(buttonPanel, BorderLayout.PAGE_START);
        employeesTable = new JTable();
        employeesTable.setFont(new Font(FONT_NAME, Font.PLAIN, FONT_SIZE));

        employeesTable.setRowHeight(ROW_HEIGHT);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        employeesTable.setDefaultRenderer(Object.class, centerRenderer);

        JTableHeader tableHeader = employeesTable.getTableHeader();
        tableHeader.setFont(new Font(FONT_NAME, Font.BOLD, FONT_SIZE));

        JScrollPane scrollPane = new JScrollPane(employeesTable);
        add(scrollPane, BorderLayout.CENTER);
    }

    private JButton createButton(String text, Color backgroundColor, Color textColor, int width, int height,
            int fontSize) {
        return new CustomButton(text, backgroundColor, textColor, width, height, fontSize);
    }

    private void loadEmployeeData() {
        List<EmployeerListDTO> employeeList = employeerService.listEmployeers();

        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Nome");
        model.addColumn("Email");
        model.addColumn("CPF");
        model.addColumn("Cargo");

        for (EmployeerListDTO employee : employeeList) {
            model.addRow(new Object[] { employee.getName(), employee.getEmail(), formatCPF(employee.getCpf()),
                    employee.getRole().getDescription() });
        }

        employeesTable.setModel(model);
        employeesTable.setFont(new Font(FONT_NAME, Font.PLAIN, 16));
        employeesTable.setRowHeight(35);
    }

    private String formatCPF(String cpf) {
        try {
            Long cpfNumber = Long.parseLong(cpf);
            NumberFormat cpfFormat = NumberFormat.getNumberInstance();
            cpfFormat.setMinimumIntegerDigits(9);
            cpfFormat.setGroupingUsed(false);
            String formattedCPF = cpfFormat.format(cpfNumber);
            return formattedCPF.substring(0, 3) + "." + formattedCPF.substring(3, 6) + "."
                    + formattedCPF.substring(6, 9) + "-" + formattedCPF.substring(9);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return cpf;
        }
    }
}
