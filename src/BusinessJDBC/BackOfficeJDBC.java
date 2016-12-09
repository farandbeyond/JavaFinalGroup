/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessJDBC;

import java.awt.*;
import java.util.*;
import javax.swing.*;

/**
 *
 * @author Connor
 */
public class BackOfficeJDBC extends JFrame{
    private final static String dbLogin = "gc200313751", dbPass = "HBw?UQ--", dbConnect = "sql.computerstudi.es:3306/gc200313751";
    DatabasePane employeePane, productPane, manufacturerPane, userPane, salesPane, customersPane;
    JTabbedPane mainTabbedPane, invTabbedPane;
    JPanel headPanel, footPanel;
    JButton exitButton;
    boolean admin;
    String username;
    public BackOfficeJDBC(){
        //frame setup
        super("Back Office");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        setLayout(new BorderLayout());
        //login
        userPane = new DatabasePane(dbLogin, dbPass, dbConnect, "JavaUsers");
        userPane.setDateAutoInsert(4); // this disables input on the DATE column for 'creation date'
        login();
        //set up the header and footer
        headerSetup();
        footerSetup();
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
    private void login(){
        JPanel mainPane = new JPanel(),
                loginPane = new JPanel(),
                registerPane = new JPanel();
        JTextField userName = new JTextField(),
                password = new JPasswordField(),
                regUserName = new JTextField(),
                regPassword = new JPasswordField();
        //login pane is made obvious
        loginPane.setBorder(BorderFactory.createRaisedBevelBorder());
        loginPane.setLayout(new GridLayout(3,2));
        loginPane.setPreferredSize(new Dimension(180,75));
        loginPane.add(new JLabel("Login"));
        loginPane.add(new JLabel(""));
        loginPane.add(new JLabel("Username:"));
        loginPane.add(userName);
        loginPane.add(new JLabel("Password:"));
        loginPane.add(password);
        
        registerPane.setBorder(BorderFactory.createRaisedBevelBorder());
        registerPane.setLayout(new GridLayout(3,2));
        registerPane.setPreferredSize(new Dimension(180,75));
        registerPane.add(new JLabel("Register"));
        registerPane.add(new JLabel(""));
        registerPane.add(new JLabel("Username:"));
        registerPane.add(regUserName);
        registerPane.add(new JLabel("Password:"));
        registerPane.add(regPassword);
        
        mainPane.add(loginPane);
        mainPane.add(registerPane);
        String[] buttonOptions = {"Login","Register","Cancel"};
        int numberOfTries = 0;
        boolean login = false;
        do{
            int option = JOptionPane.showOptionDialog(null, mainPane, "Login or Register", 
                    JOptionPane.NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, buttonOptions, buttonOptions[0]);
            if(option==-1 || option==2){//if you press cancel or hit the X
                System.exit(0);
            }else if(option == 0){//if you are logging in
                boolean canLogIn = false;
                for(int i=0;i<userPane.getRows();i++){
                    String[] userCheck = userPane.getRowData(i);
                    //column 1 is username, column 2 is password
                    if(userCheck[1].equals(userName.getText())&&userCheck[2].equals(password.getText())){
                        canLogIn = true;
                        //column 3 is boolean, representing wether the account is an admin account
                        admin = userCheck[3].equals("true");
                    }
                }
                if(!canLogIn){
                    JOptionPane.showMessageDialog(null, "Account does not exist");
                }else{
                    login = true;
                    username = userName.getText();
                }
            }
            if(option == 1){//if you hit register
                userPane.insertValues(//register a new user and contine
                        new String[]{regUserName.getText(), regPassword.getText(), "false", gregToSqlDate(new GregorianCalendar())});
                login = true;
                admin = false;
                username = regUserName.getText();
            }
            if(!login && numberOfTries > 2){
                JOptionPane.showMessageDialog(null, "Tries Exceeded, Exiting...");
                System.exit(0);
            }
        }while(!login);
    }
    private void headerSetup(){
        //setting up the header
        headPanel = new JPanel();
        headPanel.add(new JLabel("Welcome "+username));
    }
    private void footerSetup(){
        //setting up footer
        footPanel = new JPanel();
        exitButton = new JButton("Exit");
        exitButton.addActionListener(e -> exit());
        footPanel.add(exitButton);
    }
    private void databasePaneSetup(){
        employeePane = new DatabasePane(dbLogin, dbPass, dbConnect, "JavaEmployees");
        employeePane.deleteEnabled(admin);
        employeePane.updateEnabled(admin);
        
        productPane = new DatabasePane(dbLogin, dbPass, dbConnect, "JavaProducts");
        productPane.deleteEnabled(admin);
        productPane.updateEnabled(admin);
        
        manufacturerPane = new DatabasePane(dbLogin, dbPass, dbConnect, "JavaManufacturers");
        manufacturerPane.deleteEnabled(admin);
        manufacturerPane.updateEnabled(admin);
        salesPane = new DatabasePane(dbLogin, dbPass, dbConnect, "JavaSales");
        salesPane.deleteEnabled(admin);
        salesPane.updateEnabled(admin);
        salesPane.setDateAutoInsert(4); // this disables input on the DATE column for 'creation date'
        customersPane = new DatabasePane(dbLogin, dbPass, dbConnect, "JavaCustomers");
        customersPane.deleteEnabled(admin);
        customersPane.updateEnabled(admin);
        customersPane.setDateAutoInsert(5); // this disables input on the DATE column for 'creation date'
    }
    private void tabbedPaneSetup(){
        invTabbedPane = new JTabbedPane();
        invTabbedPane.addTab("Product", null, productPane, "Product Control");
        invTabbedPane.addTab("Manufacturer", null, manufacturerPane, "Manufacturer Control");
        mainTabbedPane = new JTabbedPane();
        mainTabbedPane.addTab("HR", null, employeePane, "Employee Control");
        mainTabbedPane.addTab("Inventory", null, invTabbedPane, "Inventory Control");
        mainTabbedPane.addTab("Sales", null, salesPane, "Sales Control");
        mainTabbedPane.addTab("Customers", null, customersPane, "Customer Control");
        if(admin)
            mainTabbedPane.addTab("Admin", null, userPane, "Admin User Control");
    }
    
    //lambda button functions
    private void exit(){
        System.exit(0);
    }
    //translation
    public static String gregToSqlDate(GregorianCalendar g){
        String date = "";
        date+=g.get(Calendar.YEAR)+"-";
        date+=g.get(Calendar.MONTH)+1+"-";
        date+=g.get(Calendar.DATE);
        return date;
    }
    //main
    public static void main(String[] args) {
        BackOfficeJDBC d = new BackOfficeJDBC();
    }
}
