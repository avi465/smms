package com.avi;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.print.PrinterException;
import java.io.*;
import java.text.MessageFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Objects;
import java.util.Scanner;
import java.util.Stack;

public class Access extends JFrame {
    static String[][] billData = new String[1000][4];
    int noOfBillDataRow = 0;
    static float[] productQuantity = new float[1000];
    Access(){
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setSize(800,600);
        this.setResizable(false);
        this.setTitle("Super market management system");

        JLayeredPane containerLayeredPane = new JLayeredPane();
        containerLayeredPane.setLayout(null);
        containerLayeredPane.setBounds(0,0,800,600);


        JPanel accessPanel = new JPanel();
        accessPanel.setLayout(new BorderLayout());
        accessPanel.setBounds(0,0,800,600);

        JPanel dashboardPanel = new JPanel();
        dashboardPanel.setLayout(new BorderLayout());
        dashboardPanel.setBounds(0,0,800,600);
        dashboardPanel.setVisible(false);

//        Access frame start
        JLayeredPane accessLayeredPane = new JLayeredPane();
        accessLayeredPane.setLayout(null);
        accessLayeredPane.setPreferredSize(new Dimension(600, 400));
        JPanel signInPanel = new JPanel();
        JPanel signUpPanel = new JPanel();
        JPanel panel1 = new JPanel();
        JPanel panel2 = new JPanel();
        JPanel panel3 = new JPanel();
        JPanel panel4 = new JPanel();
        signInPanel.setBounds(0,0,600,400);
        signUpPanel.setBounds(0,0,600,400);
        panel1.setPreferredSize(new Dimension(100, 100));
        panel2.setPreferredSize(new Dimension(100, 100));
        panel3.setPreferredSize(new Dimension(100, 100));
        panel4.setPreferredSize(new Dimension(100, 100));
        signInPanel.setLayout(null);
        signInPanel.setBackground(Color.WHITE);
        signUpPanel.setLayout(null);
        signUpPanel.setBackground(Color.WHITE);


//        SignInPanel Component
        JLabel titleLabel = new JLabel("Welcome Back");
        titleLabel.setBounds(200,20,200,32);
        titleLabel.setFont(new Font("Poppins",Font.BOLD,24));

        JLabel usernameLabel = new JLabel("Enter Username");
        usernameLabel.setBounds(70,80,200,25);
        usernameLabel.setForeground(Color.GRAY);
        usernameLabel.setFont(new Font("Poppins",Font.PLAIN,14));

        JTextField usernameField = new JTextField(50);
        usernameField.setBounds(70,110,460,36);
        usernameField.setFont(new Font("Poppins",Font.PLAIN,14));

        JLabel passwordLabel = new JLabel("Enter Password");
        passwordLabel.setBounds(70,160,200,25);
        passwordLabel.setForeground(Color.GRAY);
        passwordLabel.setFont(new Font("Poppins",Font.PLAIN,14));

        JPasswordField passwordField = new JPasswordField();
        passwordField.setBounds(70,190,460,36);
        passwordField.setFont(new Font("Poppins",Font.PLAIN,14));

        JButton signinButton = new JButton("Signin");
        signinButton.setBounds(70,260,460,36);
        signinButton.setFont(new Font("Poppins",Font.PLAIN,14));
        signinButton.setBackground(Color.BLACK);
        signinButton.setForeground(Color.WHITE);
        signinButton.setFocusPainted(false);
        signinButton.setRolloverEnabled(false);
        signinButton.addActionListener(e -> {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());
            if (!usernameField.getText().equals("") && passwordField.getPassword().length != 0){
                Scanner sc = new Scanner(System.in);
                try {
                    File myObj = new File("users.txt");
                    Scanner myReader = new Scanner(myObj);
                    while(myReader.hasNext()){
                        String data = myReader.nextLine();
                        String[] token = data.split(",");
                        String usernameFromDb = token[0];
                        String passwordFromDb = token[1];
                        if (username.equals(usernameFromDb) && password.equals(passwordFromDb)) {
                        JOptionPane.showMessageDialog(signInPanel,"Sucessfully signed in");
                            accessPanel.setVisible(false);
                            dashboardPanel.setVisible(true);
                            usernameField.setText("");
                            passwordField.setText("");
                            break;
                        }else if (!myReader.hasNext()){
                            int option = JOptionPane.showConfirmDialog(signInPanel,"Invalid username/password\nTry again?");
                            if (option == 0){
                                usernameField.setText("");
                                passwordField.setText("");
                            }else {
                                System.exit(0);
                            }
                        }
                    }
                myReader.close();
                } catch (FileNotFoundException exception) {
                    JOptionPane.showMessageDialog(signInPanel,"File not found");
                    exception.printStackTrace();
                }
            }else if (usernameField.getText().equals("")){
                JOptionPane.showMessageDialog(signInPanel,"Username field can't be empty");
            }else if (passwordField.getPassword().length == 0){
                JOptionPane.showMessageDialog(signInPanel,"Password field can't be empty");
            }
        });

        JButton createNewAccount = new JButton("<HTML><U>Create new account?</U></HTML>");
        createNewAccount.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        createNewAccount.setBounds(45,310,200,25);
        createNewAccount.setForeground(Color.GRAY);
        createNewAccount.setFont(new Font("Poppins",Font.PLAIN,14));
        createNewAccount.setFocusPainted(false);
        createNewAccount.setRolloverEnabled(false);
        createNewAccount.setBorder(null);
        createNewAccount.setContentAreaFilled(false);
        createNewAccount.addActionListener(e -> {
            signInPanel.setVisible(false);
            signUpPanel.setVisible(true);
        });

        signInPanel.add(titleLabel);
        signInPanel.add(usernameLabel);
        signInPanel.add(usernameField);
        signInPanel.add(passwordLabel);
        signInPanel.add(passwordField);
        signInPanel.add(signinButton);
        signInPanel.add(createNewAccount);
//        SignInPanel Component


//        SignUpPanel Component
        JLabel signUpTitleLabel = new JLabel("Create new account");
        signUpTitleLabel.setBounds(170,20,260,32);
        signUpTitleLabel.setFont(new Font("Poppins",Font.BOLD,24));

        JLabel signUpUsernameLabel = new JLabel("Enter Username");
        signUpUsernameLabel.setBounds(70,80,200,25);
        signUpUsernameLabel.setForeground(Color.GRAY);
        signUpUsernameLabel.setFont(new Font("Poppins",Font.PLAIN,14));

        JTextField signUpUsernameField = new JTextField(50);
        signUpUsernameField.setBounds(70,110,460,36);
        signUpUsernameField.setFont(new Font("Poppins",Font.PLAIN,14));

        JLabel signUpPasswordLabel = new JLabel("Enter Password");
        signUpPasswordLabel.setBounds(70,160,200,25);
        signUpPasswordLabel.setForeground(Color.GRAY);
        signUpPasswordLabel.setFont(new Font("Poppins",Font.PLAIN,14));

        JPasswordField signUpPasswordField = new JPasswordField();
        signUpPasswordField.setBounds(70,190,460,36);
        signUpPasswordField.setFont(new Font("Poppins",Font.PLAIN,14));

        JButton signUpButton = new JButton("Signup");
        signUpButton.setBounds(70,260,460,36);
        signUpButton.setFont(new Font("Poppins",Font.PLAIN,14));
        signUpButton.setBackground(Color.BLACK);
        signUpButton.setForeground(Color.WHITE);
        signUpButton.setFocusPainted(false);
        signUpButton.setRolloverEnabled(false);
        signUpButton.addActionListener(e -> {
            String username = signUpUsernameField.getText();
            String password = new String(signUpPasswordField.getPassword());
            if (!signUpUsernameField.getText().equals("") && signUpPasswordField.getPassword().length != 0){
                Scanner sc = new Scanner(System.in);
                try {
                    File myObj = new File("users.txt");
                    if (myObj.createNewFile()) {
                        System.out.println("File created: " + myObj.getName());
                    }
                } catch (IOException exception) {
                    System.out.println("An error occurred.");
                    exception.printStackTrace();
                }
//            writing in file
                try {
                    FileWriter myWriter = new FileWriter("users.txt", true);
                    myWriter.write(username + "," + password + "\n");
                    myWriter.close();
                    JOptionPane.showMessageDialog(signUpPanel,"Signup Successful");
                    signUpPanel.setVisible(false);
                    signInPanel.setVisible(true);
                    signUpUsernameField.setText("");
                    signUpPasswordField.setText("");
                } catch (IOException exception) {
                    JOptionPane.showMessageDialog(signUpPanel,"Something went wrong");
                    exception.printStackTrace();
                }
            }else if (signUpUsernameField.getText().equals("")){
                JOptionPane.showMessageDialog(signUpPanel,"Username field can't be empty");
            }else if (signUpPasswordField.getPassword().length == 0){
                JOptionPane.showMessageDialog(signUpPanel,"Password field can't be empty");
            }
        });

        JButton alreadyHaveAnAccountButton = new JButton("<HTML><U>Already have an account? Signin</U></HTML>");
        alreadyHaveAnAccountButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        alreadyHaveAnAccountButton.setBounds(65,310,240,25);
        alreadyHaveAnAccountButton.setForeground(Color.GRAY);
        alreadyHaveAnAccountButton.setFont(new Font("Poppins",Font.PLAIN,14));
        alreadyHaveAnAccountButton.setFocusPainted(false);
        alreadyHaveAnAccountButton.setRolloverEnabled(false);
        alreadyHaveAnAccountButton.setBorder(null);
        alreadyHaveAnAccountButton.setContentAreaFilled(false);
        alreadyHaveAnAccountButton.addActionListener(e -> {
            signUpPanel.setVisible(false);
            signInPanel.setVisible(true);
        });

        signUpPanel.add(signUpTitleLabel);
        signUpPanel.add(signUpUsernameLabel);
        signUpPanel.add(signUpUsernameField);
        signUpPanel.add(signUpPasswordLabel);
        signUpPanel.add(signUpPasswordField);
        signUpPanel.add(signUpButton);
        signUpPanel.add(alreadyHaveAnAccountButton);
//        SignUpPanel Component
//        Access panel ends



//        Dashboard panel starts
        JPanel sideMenu = new JPanel();
        sideMenu.setLayout(null);
        sideMenu.setPreferredSize(new Dimension(200,600));
        sideMenu.setBackground(Color.WHITE);

        JLayeredPane dashboardContentLayeredPane = new JLayeredPane();
        dashboardContentLayeredPane.setLayout(null);
        dashboardContentLayeredPane.setPreferredSize(new Dimension(600,600));

        JPanel newBillPanel = new JPanel();
        newBillPanel.setLayout(null);
        newBillPanel.setBounds(0,0,600,600);
        newBillPanel.setVisible(false);

        JPanel addProductPanel = new JPanel();
        addProductPanel.setLayout(null);
        addProductPanel.setBounds(0,0,600,600);
        addProductPanel.setVisible(false);

        JPanel availableStockPanel = new JPanel();
        availableStockPanel.setLayout(null);
        availableStockPanel.setBounds(0,0,600,600);
        availableStockPanel.setVisible(false);

        JPanel updateStockPanel = new JPanel();
        updateStockPanel.setLayout(null);
        updateStockPanel.setBounds(0,0,600,600);
        updateStockPanel.setVisible(false);

        JPanel salesPanel = new JPanel();
        salesPanel.setLayout(null);
        salesPanel.setBounds(0,0,600,600);
        salesPanel.setVisible(false);

        JPanel aboutPanel = new JPanel();
        aboutPanel.setLayout(null);
        aboutPanel.setBounds(0,0,600,600);
        aboutPanel.setVisible(false);

        JButton newBill = new JButton("New Bill");
        JButton addProduct = new JButton("Add Product");
        JButton availableStock = new JButton("Available Product");
        JButton updateStock = new JButton("Update Product");
        JButton sales = new JButton("Sales");
        JButton about = new JButton("About");
        JButton signOut = new JButton("Signout");

//        New bill start
        int count = Functions.recordCount("products.txt");

        JLabel newBillTitle = new JLabel("New Bill");
        newBillTitle.setBounds(20,20,360,25);
        newBillTitle.setFont(new Font("Poppins",Font.BOLD,20));

        JLabel newBillProductNameLabel = new JLabel("Product name");
        newBillProductNameLabel.setBounds(20,65,250,25);
        newBillProductNameLabel.setForeground(Color.GRAY);
        newBillProductNameLabel.setFont(new Font("Poppins",Font.PLAIN,14));

        JComboBox newBillProductNameField = new JComboBox(new String[]{"---Select---"});
        newBillProductNameField.setBounds(20,85,250,36);
        newBillProductNameField.setFont(new Font("Poppins",Font.PLAIN,14));
        newBillProductNameField.setBackground(Color.WHITE);
        newBillProductNameField.setEditable(true);
        newBillProductNameField.setSelectedIndex(0);

        JLabel newBillProductQuantityLabel = new JLabel("Quantity");
        newBillProductQuantityLabel.setBounds(280,65,250,25);
        newBillProductQuantityLabel.setForeground(Color.GRAY);
        newBillProductQuantityLabel.setFont(new Font("Poppins",Font.PLAIN,14));

        JTextField newBillProductQuantityField = new JTextField(50);
        newBillProductQuantityField.setBounds(280,85,250,36);
        newBillProductQuantityField.setFont(new Font("Poppins",Font.PLAIN,14));

        JLabel newBillTotalLabel = new JLabel("Total: 0");
        newBillTotalLabel.setBounds(280,510,250,32);
        newBillTotalLabel.setOpaque(true);
        newBillTotalLabel.setBackground(Color.BLACK);
        newBillTotalLabel.setForeground(Color.WHITE);
        newBillTotalLabel.setFont(new Font("Poppins",Font.PLAIN,14));

        DefaultTableModel tableModel = new DefaultTableModel();
        tableModel.addColumn("Product");
        tableModel.addColumn("Price");
        tableModel.addColumn("Quantity");
        tableModel.addColumn("Amount");

        JTable newBillTable = new JTable(tableModel);
        newBillTable.getTableHeader().setFont(new Font("Poppins",Font.PLAIN,14));
        newBillTable.setFont(new Font("Poppins",Font.PLAIN,14));
        newBillTable.setShowGrid(false);
        newBillTable.setRowHeight(newBillTable.getRowHeight() + 10);
        DefaultTableCellRenderer defaultHeaderRenderer = (DefaultTableCellRenderer) newBillTable.getTableHeader().getDefaultRenderer();
        defaultHeaderRenderer.setHorizontalAlignment(JLabel.LEFT);
        newBillTable.getTableHeader().setDefaultRenderer(defaultHeaderRenderer);
        final DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
        renderer.setBorder(null);
        newBillTable.getTableHeader().setDefaultRenderer(renderer);

        JScrollPane newBillScrollPane = new JScrollPane(newBillTable);
        newBillScrollPane.setBounds(20,195,510,295);

        JButton newBillAddButton = new JButton("Add");
        newBillAddButton.setBounds(20,140,250,36);
        newBillAddButton.setFont(new Font("Poppins",Font.PLAIN,14));
        newBillAddButton.setBackground(Color.DARK_GRAY);
        newBillAddButton.setForeground(Color.WHITE);
        newBillAddButton.setFocusPainted(false);
        newBillAddButton.setRolloverEnabled(false);
        newBillAddButton.addActionListener(e ->{
            float price = 0;
            float quantity = 0;
            if (newBillProductNameField.getSelectedIndex() == 0){
                JOptionPane.showMessageDialog(newBillPanel,"Please select item","Select item",JOptionPane.WARNING_MESSAGE);
            }else if(newBillProductQuantityField.getText().equals("")){
                JOptionPane.showMessageDialog(newBillPanel,"Enter quantity","Enter quantity",JOptionPane.WARNING_MESSAGE);
            }else {
                quantity = Float.parseFloat(newBillProductQuantityField.getText());
                try {
                    File myObj = new File("products.txt");
                    Scanner myReader = new Scanner(myObj);
                    while (myReader.hasNext()) {
                        String data = myReader.nextLine();
                        String[] token = data.split(",");
                        if(((String)newBillProductNameField.getSelectedItem()).equals(token[0])){
                            price = Float.parseFloat(token[2]);
                        }
                    }
                    myReader.close();
                } catch (FileNotFoundException exception) {
                    JOptionPane.showMessageDialog(availableStock,"File not found");
                    exception.printStackTrace();
                }
                if(quantity != 0.0){
                    tableModel.insertRow(tableModel.getRowCount(), new Object[] { (String)newBillProductNameField.getSelectedItem(),
                            price,
                            newBillProductQuantityField.getText(),
                            price*quantity
                    });
                    productQuantity[noOfBillDataRow] = quantity;
                    billData[noOfBillDataRow][0] = (String)newBillProductNameField.getSelectedItem();
                    billData[noOfBillDataRow][1] = Float.toString(price);
                    billData[noOfBillDataRow][2] = newBillProductQuantityField.getText();
                    billData[noOfBillDataRow][3] = Float.toString(price*quantity);
                    noOfBillDataRow++;
                }
                String[] data = newBillTotalLabel.getText().split(":");
                float total = Float.parseFloat(data[1]);
                total = total+(price*quantity);
                newBillTotalLabel.setText("Total: "+String.valueOf(total));

                newBillProductNameField.setSelectedIndex(0);
                newBillProductQuantityField.setText("");
            }
        });

        JButton newBillButton = new JButton("Create bill");
        newBillButton.setBounds(280,140,250,36);
        newBillButton.setFont(new Font("Poppins",Font.PLAIN,14));
        newBillButton.setBackground(Color.BLACK);
        newBillButton.setForeground(Color.WHITE);
        newBillButton.setFocusPainted(false);
        newBillButton.setRolloverEnabled(false);
        newBillButton.addActionListener(e ->{
            newBillInfo();
        });

        JButton newBillClearButton = new JButton("Clear");
        newBillClearButton.setBounds(20,510,250,32);
        newBillClearButton.setFont(new Font("Poppins",Font.PLAIN,14));
        newBillClearButton.setBackground(Color.BLACK);
        newBillClearButton.setForeground(Color.WHITE);
        newBillClearButton.setFocusPainted(false);
        newBillClearButton.setRolloverEnabled(false);
        newBillClearButton.addActionListener(e ->{
            billData = new String[1000][4];
            noOfBillDataRow = 0;
            productQuantity = new float[1000];

            tableModel.setRowCount(0);
            newBillProductQuantityField.setText("");
            newBillProductNameField.setSelectedIndex(0);
        });

        newBill.setFont(new Font("Poppins",Font.PLAIN,14));
        newBill.setBackground(Color.BLACK);
        newBill.setForeground(Color.WHITE);
        newBill.setFocusPainted(false);
        newBill.setRolloverEnabled(false);
        newBill.setBounds(20,20,160,36);
        newBill.addActionListener(e ->{
            newBillPanel.setVisible(true);
            addProductPanel.setVisible(false);
            availableStockPanel.setVisible(false);
            updateStockPanel.setVisible(false);
            salesPanel.setVisible(false);
            aboutPanel.setVisible(false);

            billData = new String[1000][4];
            noOfBillDataRow = 0;
            productQuantity = new float[1000];

            tableModel.setRowCount(0);
            newBillProductQuantityField.setText("");
            newBillProductNameField.setSelectedIndex(0);

            newBillProductNameField.removeAllItems();
            newBillProductNameField.addItem("---Select---");
            String[] productNameData = Functions.getColumnData("products.txt",0);
            for (int i = 0;i < Functions.recordCount("products.txt");i++){
                newBillProductNameField.addItem(productNameData[i]);
            }
        });
//        New bill ends

//        Add product start
        JLabel addProductTitle = new JLabel("Add Product");
        addProductTitle.setBounds(20,20,360,25);
        addProductTitle.setFont(new Font("Poppins",Font.BOLD,20));

        JLabel productNameLabel = new JLabel("Product name");
        productNameLabel.setBounds(20,65,200,25);
        productNameLabel.setForeground(Color.GRAY);
        productNameLabel.setFont(new Font("Poppins",Font.PLAIN,14));

        JTextField productNameField = new JTextField(50);
        productNameField.setBounds(20,85,460,36);
        productNameField.setFont(new Font("Poppins",Font.PLAIN,14));

        JLabel productIdLabel = new JLabel("Product id");
        productIdLabel.setBounds(20,140,200,25);
        productIdLabel.setForeground(Color.GRAY);
        productIdLabel.setFont(new Font("Poppins",Font.PLAIN,14));

        JTextField productIdField = new JTextField(50);
        productIdField.setBounds(20,160,460,36);
        productIdField.setFont(new Font("Poppins",Font.PLAIN,14));

        JLabel productPriceLabel = new JLabel("Product price");
        productPriceLabel.setBounds(20,215,200,25);
        productPriceLabel.setForeground(Color.GRAY);
        productPriceLabel.setFont(new Font("Poppins",Font.PLAIN,14));

        JTextField productPriceField = new JTextField(50);
        productPriceField.setBounds(20,235,460,36);
        productPriceField.setFont(new Font("Poppins",Font.PLAIN,14));

        JLabel productStockLabel = new JLabel("Product stock");
        productStockLabel.setBounds(20,290,200,25);
        productStockLabel.setForeground(Color.GRAY);
        productStockLabel.setFont(new Font("Poppins",Font.PLAIN,14));

        JTextField productStockField = new JTextField(50);
        productStockField.setBounds(20,310,460,36);
        productStockField.setFont(new Font("Poppins",Font.PLAIN,14));

        JButton addProductButton = new JButton("Add Product");
        addProductButton.setBounds(20,365,460,36);
        addProductButton.setFont(new Font("Poppins",Font.PLAIN,14));
        addProductButton.setBackground(Color.BLACK);
        addProductButton.setForeground(Color.WHITE);
        addProductButton.setFocusPainted(false);
        addProductButton.setRolloverEnabled(false);

        JButton addProductClearButton = new JButton("Clear Field");
        addProductClearButton.setBounds(20,420,460,36);
        addProductClearButton.setFont(new Font("Poppins",Font.PLAIN,14));
        addProductClearButton.setBackground(Color.DARK_GRAY);
        addProductClearButton.setForeground(Color.WHITE);
        addProductClearButton.setFocusPainted(false);
        addProductClearButton.setRolloverEnabled(false);
        addProductClearButton.addActionListener(e ->{
            productNameField.setText("");
            productIdField.setText("");
            productPriceField.setText("");
            productStockField.setText("");
        });

        addProduct.setFont(new Font("Poppins",Font.PLAIN,14));
        addProduct.setBackground(Color.BLACK);
        addProduct.setForeground(Color.WHITE);
        addProduct.setFocusPainted(false);
        addProduct.setRolloverEnabled(false);
        addProduct.setBounds(20,70,160,36);
        addProduct.addActionListener(e ->{
            newBillPanel.setVisible(false);
            addProductPanel.setVisible(true);
            availableStockPanel.setVisible(false);
            updateStockPanel.setVisible(false);
            salesPanel.setVisible(false);
            aboutPanel.setVisible(false);
        });
        addProductButton.addActionListener(e ->{
            Scanner sc = new Scanner(System.in);
            String name = productNameField.getText();
            String id = productIdField.getText();
            String price = productPriceField.getText();
            String stock = productStockField.getText();

//        file creation
            try {
                File myObj = new File("products.txt");
                if (myObj.createNewFile()) {
                    System.out.println("File created: " + myObj.getName());
                }
            } catch (IOException exception) {
                JOptionPane.showMessageDialog(addProduct,"Unable to add product");
                exception.printStackTrace();
            }
//            writing in file
            try {
                FileWriter myWriter = new FileWriter("products.txt", true);
                myWriter.write(name + "," + id + "," + Float.parseFloat(price) + "," + Float.parseFloat(stock) + "\n");
                myWriter.close();
                JOptionPane.showMessageDialog(addProductPanel,"Product added");

                productNameField.setText("");
                productIdField.setText("");
                productPriceField.setText("");
                productStockField.setText("");
            } catch (IOException exception) {
                JOptionPane.showMessageDialog(addProduct,"Error adding product");
                exception.printStackTrace();
            }
        });
//        Add product end

//        Available stock start
        String[] stockColumnName = {"Product", "Id", "Price", "Stock"};

        JLabel availableStockTitle = new JLabel("Available Stock");
        availableStockTitle.setBounds(20,20,360,25);
        availableStockTitle.setFont(new Font("Poppins",Font.BOLD,20));

        DefaultTableModel availableStockTableModel = new DefaultTableModel();
        availableStockTableModel.addColumn("Product");
        availableStockTableModel.addColumn("Product Id");
        availableStockTableModel.addColumn("Price");
        availableStockTableModel.addColumn("Stock");

        JTable availableStockTable = new JTable(availableStockTableModel);
        availableStockTable.getTableHeader().setFont(new Font("Poppins",Font.PLAIN,14));
        availableStockTable.setFont(new Font("Poppins",Font.PLAIN,14));
        availableStockTable.setShowGrid(false);
        availableStockTable.setRowHeight(availableStockTable.getRowHeight() + 10);
        DefaultTableCellRenderer availableStockHeaderRenderer = (DefaultTableCellRenderer) availableStockTable.getTableHeader().getDefaultRenderer();
        availableStockHeaderRenderer.setHorizontalAlignment(JLabel.LEFT);
        availableStockTable.getTableHeader().setDefaultRenderer(availableStockHeaderRenderer);
        final DefaultTableCellRenderer availableStockRenderer = new DefaultTableCellRenderer();
        availableStockRenderer.setBorder(null);
        availableStockTable.getTableHeader().setDefaultRenderer(availableStockRenderer);

        JScrollPane availableStockScrollPane = new JScrollPane(availableStockTable);
        availableStockScrollPane.setBounds(20,65,545,450);

        availableStock.setFont(new Font("Poppins",Font.PLAIN,14));
        availableStock.setBackground(Color.BLACK);
        availableStock.setForeground(Color.WHITE);
        availableStock.setFocusPainted(false);
        availableStock.setRolloverEnabled(false);
        availableStock.setBounds(20,120,160,36);
        availableStock.addActionListener(e ->{
            newBillPanel.setVisible(false);
            addProductPanel.setVisible(false);
            availableStockPanel.setVisible(true);
            updateStockPanel.setVisible(false);
            salesPanel.setVisible(false);
            aboutPanel.setVisible(false);

            availableStockTableModel.setRowCount(0);
            String[][] stockData = Functions.fileData("products.txt",4);
            for (int i = 0; i < Functions.recordCount("products.txt");i++){
                availableStockTableModel.insertRow(availableStockTableModel.getRowCount(), new Object[] {
                        stockData[i][0],
                        stockData[i][1],
                        stockData[i][2],
                        stockData[i][3]
                });
            }
        });

//        Available stock end

//        Update stock start
        JLabel updateStockTitle = new JLabel("Update Stock");
        updateStockTitle.setBounds(20,20,360,25);
        updateStockTitle.setFont(new Font("Poppins",Font.BOLD,20));

        JLabel updateStockProductIdLabel = new JLabel("Product id");
        updateStockProductIdLabel.setBounds(20,65,250,25);
        updateStockProductIdLabel.setForeground(Color.GRAY);
        updateStockProductIdLabel.setFont(new Font("Poppins",Font.PLAIN,14));

        JComboBox updateStockProductIdField = new JComboBox(new String[]{"---Select---"});
        updateStockProductIdField.setBounds(20,85,250,36);
        updateStockProductIdField.setFont(new Font("Poppins",Font.PLAIN,14));
        updateStockProductIdField.setBackground(Color.WHITE);
        updateStockProductIdField.setEditable(true);
        updateStockProductIdField.setSelectedIndex(0);

        JLabel updateStockProductNameLabel = new JLabel("Product name");
        updateStockProductNameLabel.setBounds(20,140,250,25);
        updateStockProductNameLabel.setForeground(Color.GRAY);
        updateStockProductNameLabel.setFont(new Font("Poppins",Font.PLAIN,14));

        JTextField updateStockProductNameField = new JTextField(50);
        updateStockProductNameField.setBounds(20,160,250,36);
        updateStockProductNameField.setFont(new Font("Poppins",Font.PLAIN,14));
        updateStockProductNameField.setEditable(false);
        updateStockProductNameField.setBackground(Color.WHITE);

        JLabel updateStockProductPriceLabel = new JLabel("Price");
        updateStockProductPriceLabel.setBounds(20,215,250,25);
        updateStockProductPriceLabel.setForeground(Color.GRAY);
        updateStockProductPriceLabel.setFont(new Font("Poppins",Font.PLAIN,14));

        JTextField updateStockProductPriceField = new JTextField(50);
        updateStockProductPriceField.setBounds(20,235,250,36);
        updateStockProductPriceField.setFont(new Font("Poppins",Font.PLAIN,14));
        updateStockProductPriceField.setEditable(false);
        updateStockProductPriceField.setBackground(Color.WHITE);

        JLabel updateStockProductStockLabel = new JLabel("Stock");
        updateStockProductStockLabel.setBounds(20,290,250,25);
        updateStockProductStockLabel.setForeground(Color.GRAY);
        updateStockProductStockLabel.setFont(new Font("Poppins",Font.PLAIN,14));

        JTextField updateStockProductStockField = new JTextField(50);
        updateStockProductStockField.setBounds(20,310,250,36);
        updateStockProductStockField.setFont(new Font("Poppins",Font.PLAIN,14));
        updateStockProductStockField.setEditable(false);
        updateStockProductStockField.setBackground(Color.WHITE);

        JButton updateStockSelectButton = new JButton("Select");
        JButton updateStockDeleteButton = new JButton("Delete");
        JButton updateStockUpdateButton = new JButton("Update");

        updateStock.setFont(new Font("Poppins",Font.PLAIN,14));
        updateStock.setBackground(Color.BLACK);
        updateStock.setForeground(Color.WHITE);
        updateStock.setFocusPainted(false);
        updateStock.setRolloverEnabled(false);
        updateStock.setBounds(20,170,160,36);
        updateStock.addActionListener(e ->{
            newBillPanel.setVisible(false);
            addProductPanel.setVisible(false);
            availableStockPanel.setVisible(false);
            updateStockPanel.setVisible(true);
            salesPanel.setVisible(false);
            aboutPanel.setVisible(false);

            updateStockProductIdField.setSelectedIndex(0);
            updateStockProductNameField.setText("");
            updateStockProductPriceField.setText("");
            updateStockProductStockField.setText("");
            updateStockProductNameField.setEditable(false);
            updateStockProductPriceField.setEditable(false);
            updateStockProductStockField.setEditable(false);
            updateStockDeleteButton.setEnabled(false);
            updateStockUpdateButton.setEnabled(false);

            updateStockProductIdField.removeAllItems();
            updateStockProductIdField.addItem("---Select---");
            String[] productIdData = Functions.getColumnData("products.txt",1);
            for (int i = 0;i < Functions.recordCount("products.txt");i++){
                updateStockProductIdField.addItem(productIdData[i]);
            }
        });

        updateStockDeleteButton.setBounds(20,365,250,36);
        updateStockDeleteButton.setFont(new Font("Poppins",Font.PLAIN,14));
        updateStockDeleteButton.setBackground(Color.BLACK);
        updateStockDeleteButton.setForeground(Color.WHITE);
        updateStockDeleteButton.setFocusPainted(false);
        updateStockDeleteButton.setRolloverEnabled(false);
        updateStockDeleteButton.setEnabled(false);
        updateStockDeleteButton.addActionListener(e ->{
            try {
                File inputFile = new File("products.txt");
                if (!inputFile.isFile()) {
                    System.out.println("File does not exist");
                    return;
                }
                File tempFile = new File(inputFile.getAbsolutePath() + ".tmp");
                BufferedReader br = new BufferedReader(new FileReader("products.txt"));
                PrintWriter pw = new PrintWriter(new FileWriter(tempFile));
                String line = null;
                //Read from the original file and write to the new
                //unless content matches data to be removed.
                while ((line = br.readLine()) != null) {
                    String[] token = line.split(",");
                    if (!updateStockProductIdField.getSelectedItem().equals(token[1])){
                        pw.println(line);
                        pw.flush();
                    }
                }
                pw.close();
                br.close();
                //Delete the original file
                if (!inputFile.delete()) {
                    System.out.println("Could not delete file");
                    return;
                }
                //Rename the new file to the filename the original file had.
                if (!tempFile.renameTo(inputFile))
                    System.out.println("Could not rename file");
            }
            catch (FileNotFoundException ex) {
                ex.printStackTrace();
            }catch (IOException ex) {
                ex.printStackTrace();
            }

            updateStockProductNameField.setText("");
            updateStockProductPriceField.setText("");
            updateStockProductStockField.setText("");
            updateStockProductIdField.setSelectedIndex(0);
            updateStockProductNameField.setEditable(false);
            updateStockProductPriceField.setEditable(false);
            updateStockProductStockField.setEditable(false);
            updateStockDeleteButton.setEnabled(false);
            updateStockUpdateButton.setEnabled(false);
            JOptionPane.showMessageDialog(updateStockPanel,"Product removed");
        });

        updateStockUpdateButton.setBounds(280,365,250,36);
        updateStockUpdateButton.setFont(new Font("Poppins",Font.PLAIN,14));
        updateStockUpdateButton.setBackground(Color.BLACK);
        updateStockUpdateButton.setForeground(Color.WHITE);
        updateStockUpdateButton.setFocusPainted(false);
        updateStockUpdateButton.setRolloverEnabled(false);
        updateStockUpdateButton.setEnabled(false);
        updateStockUpdateButton.addActionListener(e ->{
            try {
                File inputFile = new File("products.txt");
                if (!inputFile.isFile()) {
                    System.out.println("File does not exist");
                    return;
                }
                File tempFile = new File(inputFile.getAbsolutePath() + ".tmp");
                BufferedReader br = new BufferedReader(new FileReader("products.txt"));
                PrintWriter pw = new PrintWriter(new FileWriter(tempFile));
                String line = null;
                //Read from the original file and write to the new
                //unless content matches data to be removed.
                while ((line = br.readLine()) != null) {
                    String[] token = line.split(",");
                    if (updateStockProductIdField.getSelectedItem().equals(token[1])){
                        pw.println(updateStockProductNameField.getText()+","+updateStockProductIdField.getSelectedItem()+","+updateStockProductPriceField.getText()+","+updateStockProductStockField.getText());
                        pw.flush();
                    }else {
                        pw.println(line);
                    }
                }
                pw.close();
                br.close();
                //Delete the original file
                if (!inputFile.delete()) {
                    System.out.println("Could not delete file");
                    return;
                }
                //Rename the new file to the filename the original file had.
                if (!tempFile.renameTo(inputFile))
                    System.out.println("Could not rename file");
            }
            catch (FileNotFoundException ex) {
                ex.printStackTrace();
            }catch (IOException ex) {
                ex.printStackTrace();
            }

            updateStockProductNameField.setText("");
            updateStockProductPriceField.setText("");
            updateStockProductStockField.setText("");
            updateStockProductIdField.setSelectedIndex(0);
            updateStockProductNameField.setEditable(false);
            updateStockProductPriceField.setEditable(false);
            updateStockProductStockField.setEditable(false);
            updateStockDeleteButton.setEnabled(false);
            updateStockUpdateButton.setEnabled(false);
            JOptionPane.showMessageDialog(updateStockPanel,"Product updated");
        });

        updateStockSelectButton.setBounds(280,85,250,36);
        updateStockSelectButton.setFont(new Font("Poppins",Font.PLAIN,14));
        updateStockSelectButton.setBackground(Color.BLACK);
        updateStockSelectButton.setForeground(Color.WHITE);
        updateStockSelectButton.setFocusPainted(false);
        updateStockSelectButton.setRolloverEnabled(false);
        updateStockSelectButton.addActionListener(e ->{
            updateStockProductNameField.setText("");
            updateStockProductPriceField.setText("");
            updateStockProductStockField.setText("");

            updateStockProductNameField.setEditable(false);
            updateStockProductPriceField.setEditable(false);
            updateStockProductStockField.setEditable(false);

            try {
                File myObj = new File("products.txt");
                Scanner myReader = new Scanner(myObj);
                while (myReader.hasNext()) {
                    String data = myReader.nextLine();
                    String[] token = data.split(",");
                    if (updateStockProductIdField.getSelectedItem().equals(token[1])){
                        updateStockProductNameField.setText(token[0]);
                        updateStockProductPriceField.setText(token[2]);
                        updateStockProductStockField.setText(token[3]);

                        updateStockProductNameField.setEditable(true);
                        updateStockProductPriceField.setEditable(true);
                        updateStockProductStockField.setEditable(true);

                        updateStockDeleteButton.setEnabled(true);
                        updateStockUpdateButton.setEnabled(true);
                    }
                }
                myReader.close();
            } catch (FileNotFoundException exception) {
                JOptionPane.showMessageDialog(availableStock,"File not found");
                exception.printStackTrace();
            }
        });
//        Update stock end

//        Sales start
        String[] salesSearchByFieldData = {"---Select---","Bill No","Customer Name","Phone NO"};

        JLabel salesTitle = new JLabel("Sales");
        salesTitle.setBounds(20,20,360,25);
        salesTitle.setFont(new Font("Poppins",Font.BOLD,20));

        JLabel salesSearchByLabel = new JLabel("Search by");
        salesSearchByLabel.setBounds(20,65,250,25);
        salesSearchByLabel.setForeground(Color.GRAY);
        salesSearchByLabel.setFont(new Font("Poppins",Font.PLAIN,14));

        JComboBox salesSearchByField = new JComboBox(salesSearchByFieldData);
        salesSearchByField.setBounds(20,85,250,36);
        salesSearchByField.setFont(new Font("Poppins",Font.PLAIN,14));
        salesSearchByField.setBackground(Color.WHITE);
        salesSearchByField.setEditable(false);
        salesSearchByField.setSelectedIndex(0);

        JLabel salesSearchBoxLabel = new JLabel("Search");
        salesSearchBoxLabel.setBounds(20,130,250,25);
        salesSearchBoxLabel.setForeground(Color.GRAY);
        salesSearchBoxLabel.setFont(new Font("Poppins",Font.PLAIN,14));

        JTextField salesSearchBoxField = new JTextField(50);
        salesSearchBoxField.setBounds(20,150,250,36);
        salesSearchBoxField.setFont(new Font("Poppins",Font.PLAIN,14));

        JButton salesSearchButton = new JButton("Search");
        salesSearchButton.setBounds(280,150,250,36);
        salesSearchButton.setFont(new Font("Poppins",Font.PLAIN,14));
        salesSearchButton.setBackground(Color.BLACK);
        salesSearchButton.setForeground(Color.WHITE);
        salesSearchButton.setFocusPainted(false);
        salesSearchButton.setRolloverEnabled(false);

        DefaultTableModel salesTableModel = new DefaultTableModel();
        salesTableModel.addColumn("Bill No");
        salesTableModel.addColumn("Date");
        salesTableModel.addColumn("Time");
        salesTableModel.addColumn("Amount");
        salesTableModel.addColumn("Name");
        salesTableModel.addColumn("Mob No");

        JTable salesTable = new JTable(salesTableModel);
        salesTable.getTableHeader().setFont(new Font("Poppins",Font.PLAIN,14));
        salesTable.setFont(new Font("Poppins",Font.PLAIN,14));
        salesTable.setShowGrid(false);
        salesTable.setRowHeight(salesTable.getRowHeight() + 10);
        DefaultTableCellRenderer salesDefaultHeaderRenderer = (DefaultTableCellRenderer) salesTable.getTableHeader().getDefaultRenderer();
        salesDefaultHeaderRenderer.setHorizontalAlignment(JLabel.LEFT);
        salesTable.getTableHeader().setDefaultRenderer(salesDefaultHeaderRenderer);
        final DefaultTableCellRenderer salesRenderer = new DefaultTableCellRenderer();
        salesRenderer.setBorder(null);
        salesTable.getTableHeader().setDefaultRenderer(salesRenderer);

        JScrollPane salesScrollPane = new JScrollPane(salesTable);
        salesScrollPane.setBounds(20,200,510,295);

        JButton salesUpdateButton = new JButton("Update");
        salesUpdateButton.setBounds(20,505,250,36);
        salesUpdateButton.setFont(new Font("Poppins",Font.PLAIN,14));
        salesUpdateButton.setBackground(Color.BLACK);
        salesUpdateButton.setForeground(Color.WHITE);
        salesUpdateButton.setFocusPainted(false);
        salesUpdateButton.setRolloverEnabled(false);
        salesUpdateButton.setEnabled(false);

        JButton salesPrintButton = new JButton("Print");
        salesPrintButton.setBounds(280,505,250,36);
        salesPrintButton.setFont(new Font("Poppins",Font.PLAIN,14));
        salesPrintButton.setBackground(Color.BLACK);
        salesPrintButton.setForeground(Color.WHITE);
        salesPrintButton.setFocusPainted(false);
        salesPrintButton.setRolloverEnabled(false);
        salesPrintButton.setEnabled(false);

        sales.setFont(new Font("Poppins",Font.PLAIN,14));
        sales.setBackground(Color.BLACK);
        sales.setForeground(Color.WHITE);
        sales.setFocusPainted(false);
        sales.setRolloverEnabled(false);
        sales.setBounds(20,220,160,36);
        sales.addActionListener(e ->{
            newBillPanel.setVisible(false);
            addProductPanel.setVisible(false);
            availableStockPanel.setVisible(false);
            updateStockPanel.setVisible(false);
            salesPanel.setVisible(true);
            aboutPanel.setVisible(false);

            salesTableModel.setRowCount(0);
            int salesRowCount = Functions.recordCount("sales.txt");
            String[][] salesData = Functions.fileData("sales.txt",6);
            for (int i=0;i<salesRowCount;i++){
                salesTableModel.insertRow(salesTableModel.getRowCount(), new Object[] {
                        salesData[i][0],
                        salesData[i][1],
                        salesData[i][2],
                        salesData[i][3],
                        salesData[i][4],
                        salesData[i][5],
                });
            }
        });

        salesSearchButton.addActionListener(e ->{
            int searchBy = salesSearchByField.getSelectedIndex();
            String salesSearchTableData = switch (searchBy) {
                case 1 -> Functions.search("sales.txt", 0, salesSearchBoxField.getText());
                case 2 -> Functions.search("sales.txt", 4, salesSearchBoxField.getText());
                case 3 -> Functions.search("sales.txt", 5, salesSearchBoxField.getText());
                default -> "";
            };

            if (salesSearchByField.getSelectedIndex() == 0){
                JOptionPane.showMessageDialog(salesPanel,"Please select search by options");
            }else if (salesSearchByField.getSelectedIndex() != 0 && !Objects.equals(salesSearchTableData, "")){
                salesTableModel.setRowCount(0);
                salesPrintButton.setEnabled(true);
                salesUpdateButton.setEnabled(true);
            }else if (salesSearchBoxField.getText().equals("")){
                JOptionPane.showMessageDialog(salesPanel,"Search box can't be empty");
            }else{
                JOptionPane.showMessageDialog(salesPanel,"Data not found");
            }

            String[] salesSearchTableDataChunk = salesSearchTableData.split(",");
            salesTableModel.insertRow(salesTableModel.getRowCount(), new Object[] {
                    salesSearchTableDataChunk[0],
                    salesSearchTableDataChunk[1],
                    salesSearchTableDataChunk[2],
                    salesSearchTableDataChunk[3],
                    salesSearchTableDataChunk[4],
                    salesSearchTableDataChunk[5],
            });
        });

        salesTable.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }
            @Override
            public void mousePressed(MouseEvent e) {
                String selectedCellValue = (String) salesTable.getValueAt(salesTable.getSelectedRow() , salesTable.getSelectedColumn());
                System.out.println(salesTable.getSelectedRow());
            }
            @Override
            public void mouseReleased(MouseEvent e) {

            }
            @Override
            public void mouseEntered(MouseEvent e) {

            }
            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
//        Sales end

//        About Start
        about.setFont(new Font("Poppins",Font.PLAIN,14));
        about.setBackground(Color.BLACK);
        about.setForeground(Color.WHITE);
        about.setFocusPainted(false);
        about.setRolloverEnabled(false);
        about.setBounds(20,270,160,36);
        about.addActionListener(e ->{
            newBillPanel.setVisible(false);
            addProductPanel.setVisible(false);
            availableStockPanel.setVisible(false);
            updateStockPanel.setVisible(false);
            salesPanel.setVisible(false);
            aboutPanel.setVisible(true);
        });

        JTextPane aboutTextPane = new JTextPane();
        aboutTextPane.setBounds(0,0,600,600);
        aboutTextPane.setFont(new Font("Poppins",Font.PLAIN,14));
        aboutTextPane.setMargin(new Insets(20, 20, 20, 20));
        aboutTextPane.setEditable(false);
        aboutTextPane.setText("DEVELOPED BY\n\n" +
                "AVINASH CHANDRA KARMJIT\n");
//        About end

        signOut.setFont(new Font("Poppins",Font.PLAIN,14));
        signOut.setBackground(Color.RED);
        signOut.setForeground(Color.WHITE);
        signOut.setFocusPainted(false);
        signOut.setRolloverEnabled(false);
        signOut.setBounds(20,320,160,36);
        signOut.addActionListener(e ->{
            dashboardPanel.setVisible(false);
            accessPanel.setVisible(true);

            newBillPanel.setVisible(false);
            addProductPanel.setVisible(false);
            availableStockPanel.setVisible(false);
            updateStockPanel.setVisible(false);
            salesPanel.setVisible(false);
            aboutPanel.setVisible(false);
        });

        sideMenu.add(newBill);
        sideMenu.add(addProduct);
        sideMenu.add(availableStock);
        sideMenu.add(updateStock);
        sideMenu.add(sales);
        sideMenu.add(about);
        sideMenu.add(signOut);

        dashboardContentLayeredPane.add(newBillPanel);
        dashboardContentLayeredPane.add(addProductPanel);
        dashboardContentLayeredPane.add(availableStockPanel);
        dashboardContentLayeredPane.add(updateStockPanel);
        dashboardContentLayeredPane.add(salesPanel);
        dashboardContentLayeredPane.add(aboutPanel);


        addProductPanel.add(addProductTitle);
        addProductPanel.add(productNameLabel);
        addProductPanel.add(productNameField);
        addProductPanel.add(productIdLabel);
        addProductPanel.add(productIdField);
        addProductPanel.add(productPriceLabel);
        addProductPanel.add(productPriceField);
        addProductPanel.add(productStockLabel);
        addProductPanel.add(productStockField);
        addProductPanel.add(addProductButton);
        addProductPanel.add(addProductClearButton);

        availableStockPanel.add(availableStockTitle);
        availableStockPanel.add(availableStockScrollPane);

        newBillPanel.add(newBillTitle);
        newBillPanel.add(newBillProductNameLabel);
        newBillPanel.add(newBillProductNameField);
        newBillPanel.add(newBillProductQuantityLabel);
        newBillPanel.add(newBillProductQuantityField);
        newBillPanel.add(newBillAddButton);
        newBillPanel.add(newBillButton);
        newBillPanel.add(newBillScrollPane);
        newBillPanel.add(newBillTotalLabel);
        newBillPanel.add(newBillClearButton);

        updateStockPanel.add(updateStockTitle);
        updateStockPanel.add(updateStockProductIdLabel);
        updateStockPanel.add(updateStockProductIdField);
        updateStockPanel.add(updateStockSelectButton);
        updateStockPanel.add(updateStockProductNameLabel);
        updateStockPanel.add(updateStockProductNameField);
        updateStockPanel.add(updateStockProductPriceLabel);
        updateStockPanel.add(updateStockProductPriceField);
        updateStockPanel.add(updateStockProductStockLabel);
        updateStockPanel.add(updateStockProductStockField);
        updateStockPanel.add(updateStockDeleteButton);
        updateStockPanel.add(updateStockUpdateButton);

        salesPanel.add(salesTitle);
        salesPanel.add(salesSearchByLabel);
        salesPanel.add(salesSearchByField);
        salesPanel.add(salesSearchBoxLabel);
        salesPanel.add(salesSearchBoxField);
        salesPanel.add(salesSearchButton);
        salesPanel.add(salesScrollPane);
        salesPanel.add(salesUpdateButton);
        salesPanel.add(salesPrintButton);

        aboutPanel.add(aboutTextPane);
//        Dashboard panel ends

        containerLayeredPane.add(accessPanel);
        containerLayeredPane.add(dashboardPanel);

        accessPanel.add(accessLayeredPane, BorderLayout.CENTER);
        accessLayeredPane.add(signInPanel, BorderLayout.CENTER);
        accessLayeredPane.add(signUpPanel, BorderLayout.CENTER);
        accessPanel.add(panel1, BorderLayout.NORTH);
        accessPanel.add(panel2, BorderLayout.SOUTH);
        accessPanel.add(panel3, BorderLayout.WEST);
        accessPanel.add(panel4, BorderLayout.EAST);

        dashboardPanel.add(sideMenu, BorderLayout.WEST);
        dashboardPanel.add(dashboardContentLayeredPane,BorderLayout.EAST);

        this.add(containerLayeredPane);
        this.setVisible(true);
    }


    public static void newBillInfo(){
        float total = 0;
        JFrame frame = new JFrame();
        frame.setLayout(null);
        frame.setSize(600,600);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setTitle("Custumer Info");

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

        for (int i=0;billData[i][0] != null;i++){
            if (!billData[i][0].equals("")){
                tableModel.insertRow(tableModel.getRowCount(), new Object[] {billData[i][0],billData[i][1],billData[i][2],billData[i][3]});
                total = total + Float.parseFloat(billData[i][3]);
            }
        }
        tableModel.insertRow(tableModel.getRowCount(), new Object[] {"","","Total:",total});

        JButton saveButton = new JButton("Save");
        saveButton.setBounds(20,510,250,36);
        saveButton.setFont(new Font("Poppins",Font.PLAIN,14));
        saveButton.setBackground(Color.BLACK);
        saveButton.setForeground(Color.WHITE);
        saveButton.setFocusPainted(false);
        saveButton.setRolloverEnabled(false);
        float finalTotal = total;
        saveButton.addActionListener(e ->{
            if (!customerNameField.getText().equals("") && !customerPhNoField.getText().equals("")){
//            File creation
                try {
                    File myObj = new File("sales.txt");
                    if (myObj.createNewFile()) {
                        System.out.println("File created: " + myObj.getName());
                    }
                } catch (IOException exception) {
                    JOptionPane.showMessageDialog(frame,"Unable to create sales file");
                    exception.printStackTrace();
                }
//            writing in file
                try {
                    LocalDateTime myObj = LocalDateTime.now();
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
                    String[] formatedDate = myObj.format(formatter).split(" ");

                    FileWriter myWriter = new FileWriter("sales.txt", true);
                    myWriter.write(
                            (Functions.recordCount("sales.txt")+1) + ","
                                    + formatedDate[0] + ","
                                    + formatedDate[1] + ","
                                    + finalTotal + ","
                                    + customerNameField.getText() +","
                                    + customerPhNoField.getText()
                                    + "\n"
                    );
                    myWriter.close();
                    JOptionPane.showMessageDialog(frame,"Bill saved");
                } catch (IOException exception) {
                    JOptionPane.showMessageDialog(frame,"Error saving data");
                    exception.printStackTrace();
                }

//                Updating function
                try {
                    File inputFile = new File("products.txt");
                    if (!inputFile.isFile()) {
                        System.out.println("File does not exist");
                        return;
                    }
                    File tempFile = new File(inputFile.getAbsolutePath() + ".tmp");
                    BufferedReader br = new BufferedReader(new FileReader("products.txt"));
                    PrintWriter pw = new PrintWriter(new FileWriter(tempFile));
                    String line = null;
                    //Read from the original file and write to the new
                    //unless content matches data to be removed.
                    while ((line = br.readLine()) != null) {
                        float tempProductQuantity = 0;
                        String[] token = line.split(",");
                        for (int i=0;billData[i][0] != null;i++){
                            if (!billData[i][0].equals("")){
                                if (billData[i][0].equals(token[0])){
                                    tempProductQuantity = tempProductQuantity + productQuantity[i];
                                }
                            }
                        }
                        if (tempProductQuantity > 0){
                            pw.println(token[0]+","+token[1]+","+token[2]+","+(Float.parseFloat(token[3])-tempProductQuantity));
                            pw.flush();
                        }else {
                            pw.println(line);
                        }
                    }
                    pw.close();
                    br.close();
                    //Delete the original file
                    if (!inputFile.delete()) {
                        System.out.println("Could not delete file");
                        return;
                    }
                    //Rename the new file to the filename the original file had.
                    if (!tempFile.renameTo(inputFile))
                        System.out.println("Could not rename file");
                }
                catch (FileNotFoundException ex) {
                    ex.printStackTrace();
                }catch (IOException ex) {
                    ex.printStackTrace();
                }

            }else if (customerNameField.getText().equals("")){
                JOptionPane.showMessageDialog(frame,"Please enter customer name");
            }else {
                JOptionPane.showMessageDialog(frame,"Please enter Phone no");
            }
        });

        JButton printButton = new JButton("Print");
        printButton.setBounds(280,510,250,36);
        printButton.setFont(new Font("Poppins",Font.PLAIN,14));
        printButton.setBackground(Color.BLACK);
        printButton.setForeground(Color.WHITE);
        printButton.setFocusPainted(false);
        printButton.setRolloverEnabled(false);
        printButton.addActionListener(e ->{
            if (!customerNameField.getText().equals("") && !customerPhNoField.getText().equals("")){
//              Saving function
//            File creation
                try {
                    File myObj = new File("sales.txt");
                    if (myObj.createNewFile()) {
                        System.out.println("File created: " + myObj.getName());
                    }
                } catch (IOException exception) {
                    JOptionPane.showMessageDialog(frame,"Unable to create sales file");
                    exception.printStackTrace();
                }
//            writing in file
                try {
                    LocalDateTime myObj = LocalDateTime.now();
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
                    String[] formatedDate = myObj.format(formatter).split(" ");

                    FileWriter myWriter = new FileWriter("sales.txt", true);
                    myWriter.write(
                            (Functions.recordCount("sales.txt")+1) + ","
                                    + formatedDate[0] + ","
                                    + formatedDate[1] + ","
                                    + finalTotal + ","
                                    + customerNameField.getText() +","
                                    + customerPhNoField.getText()
                                    + "\n"
                    );
                    myWriter.close();
                    JOptionPane.showMessageDialog(frame,"Bill saved");
                } catch (IOException exception) {
                    JOptionPane.showMessageDialog(frame,"Error saving data");
                    exception.printStackTrace();
                }

//                Updating function
                try {
                    File inputFile = new File("products.txt");
                    if (!inputFile.isFile()) {
                        System.out.println("File does not exist");
                        return;
                    }
                    File tempFile = new File(inputFile.getAbsolutePath() + ".tmp");
                    BufferedReader br = new BufferedReader(new FileReader("products.txt"));
                    PrintWriter pw = new PrintWriter(new FileWriter(tempFile));
                    String line = null;
                    //Read from the original file and write to the new
                    //unless content matches data to be removed.
                    while ((line = br.readLine()) != null) {
                        float tempProductQuantity = 0;
                        String[] token = line.split(",");
                        for (int i=0;billData[i][0] != null;i++){
                            if (!billData[i][0].equals("")){
                                if (billData[i][0].equals(token[0])){
                                    tempProductQuantity = tempProductQuantity + productQuantity[i];
                                }
                            }
                        }
                        if (tempProductQuantity > 0){
                            pw.println(token[0]+","+token[1]+","+token[2]+","+(Float.parseFloat(token[3])-tempProductQuantity));
                            pw.flush();
                        }else {
                            pw.println(line);
                        }
                    }
                    pw.close();
                    br.close();
                    //Delete the original file
                    if (!inputFile.delete()) {
                        System.out.println("Could not delete file");
                        return;
                    }
                    //Rename the new file to the filename the original file had.
                    if (!tempFile.renameTo(inputFile))
                        System.out.println("Could not rename file");

                }
                catch (FileNotFoundException ex) {
                    ex.printStackTrace();
                }catch (IOException ex) {
                    ex.printStackTrace();
                }

//            Printing function
                MessageFormat headerFormat = new MessageFormat("Supermart by team17");
                MessageFormat footerFormat = new MessageFormat("Designed by team17");
                try{
                    customerInfoTable.print(JTable.PrintMode.FIT_WIDTH,headerFormat,footerFormat);
                }catch (PrinterException exception){
                    JOptionPane.showMessageDialog(customerInfoTable,exception);
                }
            }else if (customerNameField.getText().equals("")){
                JOptionPane.showMessageDialog(frame,"Please enter customer name");
            }else {
                JOptionPane.showMessageDialog(frame,"Please enter Phone no");
            }
        });


        frame.add(customerInfoTitle);
        frame.add(customerNameLabel);
        frame.add(customerNameField);
        frame.add(customerPhNoLabel);
        frame.add(customerPhNoField);
        frame.add(customerInfoScrollPane);
        frame.add(saveButton);
        frame.add(printButton);
        frame.setVisible(true);
    }
}
