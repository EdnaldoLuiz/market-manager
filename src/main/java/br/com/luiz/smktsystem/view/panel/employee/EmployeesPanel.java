package br.com.luiz.smktsystem.view.panel.employee;

import br.com.luiz.smktsystem.service.EmployeerService;
import br.com.luiz.smktsystem.service.dto.EmployeerListDTO;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.text.NumberFormat;
import java.util.List;

public class EmployeesPanel extends JPanel {

    private EmployeerService employeerService;
    private JTable employeesTable;

    public EmployeesPanel(EmployeerService employeerService) {
        this.employeerService = employeerService;
        initializeUI();
        loadEmployeeData();
    }

    private void initializeUI() {
        setLayout(new BorderLayout());

        employeesTable = new JTable();
        employeesTable.setFont(new Font("Arial", Font.PLAIN, 16));

        employeesTable.setRowHeight(30);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        employeesTable.setDefaultRenderer(Object.class, centerRenderer);

        JScrollPane scrollPane = new JScrollPane(employeesTable);

        add(scrollPane, BorderLayout.CENTER);
    }

    private void loadEmployeeData() {
        List<EmployeerListDTO> employeeList = employeerService.listEmployeers();

        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Name");
        model.addColumn("Email");
        model.addColumn("CPF");

        for (EmployeerListDTO employee : employeeList) {
            model.addRow(new Object[]{employee.getName(), employee.getEmail(), formatCPF(employee.getCpf())});
        }

        employeesTable.setModel(model);
    }

    private String formatCPF(String cpf) {
        try {
            Long cpfNumber = Long.parseLong(cpf);
            NumberFormat cpfFormat = NumberFormat.getNumberInstance();
            cpfFormat.setMinimumIntegerDigits(9);
            cpfFormat.setGroupingUsed(false);
            String formattedCPF = cpfFormat.format(cpfNumber);
            return formattedCPF.substring(0, 3) + "." + formattedCPF.substring(3, 6) + "." + formattedCPF.substring(6, 9) + "-" + formattedCPF.substring(9);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return cpf;
        }
    }
}
