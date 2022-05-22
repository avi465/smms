package com.avi;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class NewBillInfo extends JFrame {
    NewBillInfo(){
        this.setLayout(null);
        this.setSize(600,600);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setTitle("Custumer Info");

        JLabel customerInfoTitle = new JLabel("Customer Info");
        customerInfoTitle.setBounds(20,20,360,25);
        customerInfoTitle.setFont(new Font("Poppins",Font.BOLD,20));

        JLabel customerNameLabel = new JLabel("Customer name");
        customerNameLabel.setBounds(20,65,250,25);
        customerNameLabel.setForeground(Color.GRAY);
        customerNameLabel.setFont(new Font("Poppins",Font.PLAIN,14));

        JTextField customerNameField = new JTextField(50);
        customerNameField.setBounds(20,85,250,36);
        customerNameField.setFont(new Font("Poppins",Font.PLAIN,14));

        JLabel customerPhNoLabel = new JLabel("Phone no");
        customerPhNoLabel.setBounds(280,65,250,25);
        customerPhNoLabel.setForeground(Color.GRAY);
        customerPhNoLabel.setFont(new Font("Poppins",Font.PLAIN,14));

        JTextField customerPhNoField = new JTextField(50);
        customerPhNoField.setBounds(280,85,250,36);
        customerPhNoField.setFont(new Font("Poppins",Font.PLAIN,14));

        DefaultTableModel tableModel = new DefaultTableModel();
        tableModel.addColumn("Product");
        tableModel.addColumn("Price");
        tableModel.addColumn("Quantity");
        tableModel.addColumn("Amount");

        JTable customerInfoTable = new JTable(tableModel);
        customerInfoTable.getTableHeader().setFont(new Font("Poppins",Font.PLAIN,14));
        customerInfoTable.setFont(new Font("Poppins",Font.PLAIN,14));
        customerInfoTable.setShowGrid(false);
        customerInfoTable.setRowHeight(customerInfoTable.getRowHeight() + 10);
        DefaultTableCellRenderer defaultHeaderRenderer = (DefaultTableCellRenderer) customerInfoTable.getTableHeader().getDefaultRenderer();
        defaultHeaderRenderer.setHorizontalAlignment(JLabel.LEFT);
        customerInfoTable.getTableHeader().setDefaultRenderer(defaultHeaderRenderer);
        final DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
        renderer.setBorder(null);
        customerInfoTable.getTableHeader().setDefaultRenderer(renderer);

        JScrollPane customerInfoScrollPane = new JScrollPane(customerInfoTable);
        customerInfoScrollPane.setBounds(20,140,510,350);

        JButton saveButton = new JButton("Save");
        saveButton.setBounds(20,510,250,36);
        saveButton.setFont(new Font("Poppins",Font.PLAIN,14));
        saveButton.setBackground(Color.BLACK);
        saveButton.setForeground(Color.WHITE);
        saveButton.setFocusPainted(false);
        saveButton.setRolloverEnabled(false);

        JButton printButton = new JButton("Print");
        printButton.setBounds(280,510,250,36);
        printButton.setFont(new Font("Poppins",Font.PLAIN,14));
        printButton.setBackground(Color.BLACK);
        printButton.setForeground(Color.WHITE);
        printButton.setFocusPainted(false);
        printButton.setRolloverEnabled(false);


        this.add(customerInfoTitle);
        this.add(customerNameLabel);
        this.add(customerNameField);
        this.add(customerPhNoLabel);
        this.add(customerPhNoField);
        this.add(customerInfoScrollPane);
        this.add(saveButton);
        this.add(printButton);
        this.setVisible(true);
    }
}
