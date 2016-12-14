package BusinessJDBC;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.*;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 *
 * @author Connor
 */
public class BackOfficeJDBC extends JFrame {

    private final static String dbLogin = "gc200313751", dbPass = "HBw?UQ--", dbConnect = "sql.computerstudi.es:3306/gc200313751";

    //Database panes
    DatabasePane employeePane, productPane, manufacturerPane, userPane;

    //tabbed panes
    JTabbedPane mainTabbedPane, invTabbedPane;

    //JPanes
    JPanel headPanel, footPanel, salesPane, mainSalesTop, mainSalesCenter, mainSalesBottom,
            customerPane, mainCustomerTop, mainCustomerCenter, mainCustomerBottom;

    //buttons
    JButton exitButton, createSalesButton, clearFormButton, editSalesButton, deleteSalesButton, searchSalesButton,
            searchCustomerButton, createCustomerButton, editCustomerButton, deleteCustomerButton;

    //JTextFields
    JTextField txtCustomerID, txtEmpNo, txtProdID, txtPurchaseDate, txtTotalCost, txtPotentialCommission,
            txtCustomerFirstName, txtCustomerLastName, txtBillingAddress, txtPhoneNumber, txtSignUpDate, txtSalesCustomerID;

    //JComboBoxes
    //sales combobox
    private JComboBox<String> sales;
    private static final String[] salesTypes = {"Creates Sales", "Search Sales", "Edit Sales"};

    //customer combobox
    private JComboBox<String> customer;
    private static final String[] customerTypes = {"Creates Customers", "Search Customers", "Edit Customers"};

    //listeners
    boolean admin;
    String username;

    public BackOfficeJDBC() {

        //frame setup
        super("Back Office");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        //change the size of the FRAME
        setPreferredSize(new Dimension(600, 500));

        //center the screen
        setLocationRelativeTo(null);

        //login
        userPane = new DatabasePane(dbLogin, dbPass, dbConnect, "JavaUsers");
        userPane.setDateAutoInsert(4); // this disables input on the DATE column for 'creation date'
        login();
        //set up the header and footer
        headerSetup();
        footerSetup();
        //setup inner panels
        buildSalesPanel();
        buildCustomerPanel();
        //setup the main area
        databasePaneSetup();
        tabbedPaneSetup();
        //add everything to the frame
        add(headPanel, BorderLayout.NORTH);
        add(mainTabbedPane, BorderLayout.CENTER);
        add(footPanel, BorderLayout.SOUTH);
        setVisible(true);
        pack();
    }

    private void login() {
        JPanel mainPane = new JPanel(),
                loginPane = new JPanel(),
                registerPane = new JPanel();
        JTextField userName = new JTextField(),
                password = new JPasswordField(),
                regUserName = new JTextField(),
                regPassword = new JPasswordField();
        //login pane is made obvious
        loginPane.setBorder(BorderFactory.createRaisedBevelBorder());
        loginPane.setLayout(new GridLayout(3, 2));
        loginPane.setPreferredSize(new Dimension(180, 75));
        loginPane.add(new JLabel("Login"));
        loginPane.add(new JLabel(""));
        loginPane.add(new JLabel("Username:"));
        loginPane.add(userName);
        loginPane.add(new JLabel("Password:"));
        loginPane.add(password);

        registerPane.setBorder(BorderFactory.createRaisedBevelBorder());
        registerPane.setLayout(new GridLayout(3, 2));
        registerPane.setPreferredSize(new Dimension(180, 75));
        registerPane.add(new JLabel("Register"));
        registerPane.add(new JLabel(""));
        registerPane.add(new JLabel("Username:"));
        registerPane.add(regUserName);
        registerPane.add(new JLabel("Password:"));
        registerPane.add(regPassword);

        mainPane.add(loginPane);
        mainPane.add(registerPane);
        String[] buttonOptions = {"Login", "Register", "Cancel"};
        int numberOfTries = 0;
        boolean login = false;
        do {
            int option = JOptionPane.showOptionDialog(null, mainPane, "Login or Register",
                    JOptionPane.NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, buttonOptions, buttonOptions[0]);
            if (option == -1 || option == 2) {//if you press cancel or hit the X
                System.exit(0);
            } else if (option == 0) {//if you are logging in
                boolean canLogIn = false;
                for (int i = 0; i < userPane.getRows(); i++) {
                    String[] userCheck = userPane.getRowData(i);
                    //column 1 is username, column 2 is password
                    if (userCheck[1].equals(userName.getText()) && userCheck[2].equals(password.getText())) {
                        canLogIn = true;
                        //column 3 is boolean, representing wether the account is an admin account
                        admin = userCheck[3].equals("true");
                    }
                }
                if (!canLogIn) {
                    JOptionPane.showMessageDialog(null, "Account does not exist");
                } else {
                    login = true;
                    username = userName.getText();
                }
            }
            if (option == 1) {//if you hit register
                userPane.insertValues(//register a new user and contine
                        new String[]{regUserName.getText(), regPassword.getText(), "false", gregToSqlDate(new GregorianCalendar())});
                login = true;
                admin = false;
                username = regUserName.getText();
            }
            if (!login && numberOfTries > 2) {
                JOptionPane.showMessageDialog(null, "Tries Exceeded, Exiting...");
                System.exit(0);
            }
        } while (!login);
    }

    private void headerSetup() {
        //setting up the header
        headPanel = new JPanel();
        headPanel.add(new JLabel("Welcome " + username));
    }

    private void footerSetup() {
        //setting up footer
        footPanel = new JPanel();
        exitButton = new JButton("Exit");
        exitButton.addActionListener(e -> exit());
        footPanel.add(exitButton);
    }

    private void databasePaneSetup() {
        employeePane = new DatabasePane(dbLogin, dbPass, dbConnect, "JavaEmployees");
        employeePane.deleteEnabled(admin);
        employeePane.updateEnabled(admin);

        productPane = new DatabasePane(dbLogin, dbPass, dbConnect, "JavaProducts");
        productPane.deleteEnabled(admin);
        productPane.updateEnabled(admin);

        manufacturerPane = new DatabasePane(dbLogin, dbPass, dbConnect, "JavaManufacturers");
        manufacturerPane.deleteEnabled(admin);
        manufacturerPane.updateEnabled(admin);

        salesPane = new JPanel();
        salesPane.setLayout(new BorderLayout(0, 0));

        Insets insets = salesPane.getInsets();
        Dimension size = salesPane.getPreferredSize();

        salesPane.add(mainSalesTop, BorderLayout.NORTH);
        salesPane.add(mainSalesCenter, BorderLayout.CENTER);
        salesPane.add(mainSalesBottom, BorderLayout.SOUTH);

        salesPane.setPreferredSize(new Dimension(150, 150));// hardCoded sizing
        salesPane.setMaximumSize(mainSalesCenter.getPreferredSize());
        pack();

        customerPane = new JPanel();
        customerPane.setLayout(new BorderLayout());
        customerPane.add(mainCustomerTop, BorderLayout.NORTH);
        customerPane.add(mainCustomerBottom, BorderLayout.SOUTH);

    }

    private void buildSalesPanel() {
        mainSalesTop = new JPanel();
        mainSalesTop.setLayout(new GridLayout(5, 5));
        mainSalesTop.setMaximumSize(mainSalesTop.getPreferredSize());

        //initialize TOP panel components
        txtSalesCustomerID = new JTextField(6);
        txtEmpNo = new JTextField(6);
        txtProdID = new JTextField(6);
        txtPurchaseDate = new JTextField(8);

        //set a border for the TOP panel
        mainSalesTop.setBorder(
                BorderFactory.createTitledBorder("Sales Information"));

        //add the components to the TOP panel
        mainSalesTop.add(new JLabel("Customer ID:"));
        mainSalesTop.add(txtSalesCustomerID);
        mainSalesTop.add(new JLabel("Employee Number:"));
        mainSalesTop.add(txtEmpNo);
        mainSalesTop.add(new JLabel("Product ID:"));
        mainSalesTop.add(txtProdID);
        mainSalesTop.add(new JLabel("Purchase Date:"));
        mainSalesTop.add(txtPurchaseDate);

        //create Center Panel
        mainSalesCenter = new JPanel();

        //  mainSalesCenter.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 0));
// 
//        mainSalesCenter.setPreferredSize(new Dimension(0,0));// hardCoded sizing
//        mainSalesCenter.setMaximumSize(mainSalesCenter.getPreferredSize());
        pack();

        //initialize Center panel components
        txtTotalCost = new JTextField(10);
        txtPotentialCommission = new JTextField(10);

        //set a border for the Center panel
        mainSalesCenter.setBorder(
                BorderFactory.createTitledBorder("Sales Cost/Commissions"));

        //add the components to the TOP panel
        mainSalesCenter.add(new JLabel("Total Cost:"));
        mainSalesCenter.add(txtTotalCost);
        mainSalesCenter.add(new JLabel("Potential Commission:"));
        mainSalesCenter.add(txtPotentialCommission);

        //create the mainSalesBottom panel with just a combobox
        mainSalesBottom = new JPanel();

//        mainSalesBottom.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
//        mainSalesBottom.setPreferredSize(new Dimension(150, 150));// hardCoded sizing
//        mainSalesBottom.setMaximumSize(mainSalesBottom.getPreferredSize());
        //**Combo Boxes**\\
        sales = new JComboBox<>(salesTypes);
        sales.setMaximumRowCount(salesTypes.length);
        mainSalesBottom.add(sales);
        sales.setSelectedIndex(-1);

        //**Action Listener for Sales**\\
        sales.addItemListener((ItemEvent event) -> {
            footPanel.removeAll();

            //if the user selects "Creates Sales"
            if (sales.getSelectedItem() == "Creates Sales") {

                footPanel.removeAll();
                pack();
                createSalesButton = new JButton("Create Sales");
                footPanel.add(createSalesButton);

                clearFormButton = new JButton("Clear Form");

                //clear form action listener
                clearFormButton.addActionListener((ActionEvent a) -> {

                    txtSalesCustomerID.setText("");
                    txtEmpNo.setText("");
                    txtProdID.setText("");
                    txtPurchaseDate.setText("");
                    txtTotalCost.setText("");
                    txtPotentialCommission.setText("");

                });

                footPanel.add(clearFormButton);

                exitButton = new JButton("Exit");
                exitButton.addActionListener(e -> exit());
                footPanel.add(exitButton);
                pack();
            } //if the user selects "Search Sales"
            else if (sales.getSelectedItem() == "Search Sales") {

                footPanel.removeAll();
                pack();
                searchSalesButton = new JButton("Search Sales");
                footPanel.add(searchSalesButton);

                clearFormButton = new JButton("Clear Form");
                clearFormButton.addActionListener((ActionEvent a) -> {
                    txtSalesCustomerID.setText("");
                    txtEmpNo.setText("");
                    txtProdID.setText("");
                    txtPurchaseDate.setText("");
                    txtTotalCost.setText("");
                    txtPotentialCommission.setText("");
                });
                footPanel.add(clearFormButton);

                exitButton = new JButton("Exit");
                exitButton.addActionListener(e -> exit());
                footPanel.add(exitButton);
                pack();
            } //if the user selects "Edit Sales"
            else if (sales.getSelectedItem() == "Edit Sales") {

                footPanel.removeAll();
                pack();
                searchSalesButton = new JButton("Search Sales");
                footPanel.add(searchSalesButton);

                editSalesButton = new JButton("Edit Sale");
                footPanel.add(editSalesButton);

                deleteSalesButton = new JButton("Delete Sale");
                footPanel.add(deleteSalesButton);

                exitButton = new JButton("Exit");
                exitButton.addActionListener(e -> exit());
                footPanel.add(exitButton);
                pack();
            } else {

                footPanel.removeAll();

                exitButton = new JButton("Exit");
                exitButton.addActionListener(e -> exit());
                footPanel.add(exitButton);
            }
            pack();
        }
        );

    }

    private void buildCustomerPanel() {
        mainCustomerTop = new JPanel();
        mainCustomerTop.setLayout(new GridLayout(3, 3));

        //initialize TOP panel components
        txtCustomerFirstName = new JTextField(16);
        txtCustomerLastName = new JTextField(16);
        txtBillingAddress = new JTextField(16);
        txtPhoneNumber = new JTextField(8);
        txtCustomerID = new JTextField(8);
        txtSignUpDate = new JTextField(8);

        //set a border for the TOP panel
        mainCustomerTop.setBorder(
                BorderFactory.createTitledBorder("Customer Information"));

        //add the components to the TOP panel
        mainCustomerTop.add(new JLabel("Customer First Name:"));
        mainCustomerTop.add(txtCustomerFirstName);
        mainCustomerTop.add(new JLabel("Customer Last Name:"));
        mainCustomerTop.add(txtCustomerLastName);
        mainCustomerTop.add(new JLabel("Billing Address:"));
        mainCustomerTop.add(txtBillingAddress);
        mainCustomerTop.add(new JLabel("Phone Number:"));
        mainCustomerTop.add(txtPhoneNumber);
        mainCustomerTop.add(new JLabel("Customer ID:"));
        mainCustomerTop.add(txtCustomerID);
        mainCustomerTop.add(new JLabel("Sign-up Date:"));
        mainCustomerTop.add(txtSignUpDate);

        //create the mainCustomerBottom panel with just a combobox
        mainCustomerBottom = new JPanel();

        mainCustomerBottom.setLayout(new FlowLayout());
        //**Combo Boxes**\\
        customer = new JComboBox<>(customerTypes);
        customer.setMaximumRowCount(customerTypes.length);
        mainCustomerBottom.add(customer);
        customer.setSelectedIndex(-1);

        //**Action Listener for Customer**\\
        customer.addItemListener(new ItemListener() {

            @Override
            public void itemStateChanged(ItemEvent event) {
                footPanel.removeAll();

                //if the user selects "Creates Customer"
                if (customer.getSelectedItem() == "Creates Customers") {

                    footPanel.removeAll();
                    pack();
                    createCustomerButton = new JButton("Create Customer");
                    footPanel.add(createCustomerButton);

                    clearFormButton = new JButton("Clear Form");

                    //clear form actionEvent
                    clearFormButton.addActionListener((ActionEvent a) -> {
                        txtCustomerFirstName.setText("");
                        txtCustomerLastName.setText("");
                        txtBillingAddress.setText("");
                        txtPhoneNumber.setText("");
                        txtCustomerID.setText("");
                        txtSignUpDate.setText("");
                    });

                    footPanel.add(clearFormButton);

                    exitButton = new JButton("Exit");
                    //exit button action listener
                    exitButton.addActionListener(e -> exit());
                    footPanel.add(exitButton);
                    pack();
                } //if the user selects "Search Customers"
                else if (customer.getSelectedItem() == "Search Customers") {

                    footPanel.removeAll();
                    pack();
                    searchCustomerButton = new JButton("Search Customers");
                    footPanel.add(searchCustomerButton);

                    clearFormButton = new JButton("Clear Form");
                    clearFormButton.addActionListener((ActionEvent e) -> {
                        txtCustomerFirstName.setText("");
                        txtCustomerLastName.setText("");
                        txtBillingAddress.setText("");
                        txtPhoneNumber.setText("");
                        txtCustomerID.setText("");
                        txtSignUpDate.setText("");
                    });
                    footPanel.add(clearFormButton);

                    exitButton = new JButton("Exit");
                    exitButton.addActionListener(e -> exit());
                    footPanel.add(exitButton);
                    pack();
                } //if the user selects "Edit Customers"
                else if (customer.getSelectedItem() == "Edit Customers") {

                    footPanel.removeAll();
                    pack();
                    searchCustomerButton = new JButton("Search Customers");
                    footPanel.add(searchCustomerButton);

                    editCustomerButton = new JButton("Edit Customers");
                    footPanel.add(editCustomerButton);

                    deleteCustomerButton = new JButton("Delete Customers");
                    footPanel.add(deleteCustomerButton);

                    exitButton = new JButton("Exit");
                    exitButton.addActionListener(e -> exit());
                    footPanel.add(exitButton);
                    pack();
                } else {

                    footPanel.removeAll();
                    pack();
                    exitButton = new JButton("Exit");
                    exitButton.addActionListener(e -> exit());
                    footPanel.add(exitButton);
                    pack();
                }
                pack();
            }
        });

    }

    private void tabbedPaneSetup() {
        invTabbedPane = new JTabbedPane();
        invTabbedPane.addTab("Product", null, productPane, "Product Control");
        invTabbedPane.addTab("Manufacturer", null, manufacturerPane, "Manufacturer Control");
        mainTabbedPane = new JTabbedPane();
        mainTabbedPane.addTab("HR", null, employeePane, "Employee Control");

        mainTabbedPane.addTab("Inventory", null, invTabbedPane, "Inventory Control");
        mainTabbedPane.addTab("Sales", null, salesPane, "Sales Control");
        mainTabbedPane.addTab("Customers", null, customerPane, "Customer Control");
        if (admin) {
            mainTabbedPane.addTab("Admin", null, userPane, "Admin User Control");
        }

        //make a change listener that resets the footpanel when you change tabs on the mainTab
        ChangeListener changeListener = (ChangeEvent changeEvent) -> {
            JTabbedPane sourceTabbedPane = (JTabbedPane) changeEvent.getSource();
            int index = sourceTabbedPane.getSelectedIndex();

            if (sourceTabbedPane.getTitleAt(index).equals("HR")) {
                footPanel.removeAll();
                pack();
                exitButton = new JButton("Exit");
                exitButton.addActionListener(f -> exit());
                footPanel.add(exitButton);
                pack();
            } else {
                footPanel.removeAll();
                pack();
                exitButton = new JButton("Exit");
                exitButton.addActionListener(f -> exit());
                footPanel.add(exitButton);
                pack();
            }
        };
        mainTabbedPane.addChangeListener(changeListener);
    }

    //lambda button functions
    private void exit() {
        System.exit(0);
    }

    //translation
    public static String gregToSqlDate(GregorianCalendar g) {
        String date = "";
        date += g.get(Calendar.YEAR) + "-";
        date += g.get(Calendar.MONTH) + 1 + "-";
        date += g.get(Calendar.DATE);
        return date;
    }

    //main
    public static void main(String[] args) {
        BackOfficeJDBC d = new BackOfficeJDBC();
    }
}