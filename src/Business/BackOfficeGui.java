/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business;

import Business.Employees.*;
import Business.Products.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

/**
 *
 * @author Connor
 */
public class BackOfficeGui extends JFrame{
    //instance background holder
    ArrayList<Employee> employees = new ArrayList<>();
    ArrayList<Manufacturer> manufacturers = new ArrayList<>();
    ArrayList<Product> products = new ArrayList<>();
    //frame header and footer
    JPanel headPanel, footPanel;
    JButton exitButton;
    //frame center
    JTabbedPane hrInvTabbedPane, hrCreateSearchTabbedPane, invCreateSearchTabbedPane, invCreateTabbedPane, mainTabbedPane;
    //add employee menu
    JPanel addEmployeePanel, employeeInfoPanel, radioButtonPanel;
    JRadioButton salaryEmpRadio, hourlyEmpRadio, commissionEmpRadio, baseCommissionEmpRadio;
    ButtonGroup employeeTypeGroup;
    JButton addEmployeeButton;
        //basic employee
    JTextField firstNameField, lastNameField, genderField, addressField, 
            emailField, homeNumberField, cellNumberField, sinField, 
            positionField, departmentField, birthDayField, birthMonthField, birthYearField,
        //salaryEmployee|basePlusCommissionSales
            salaryField,
        //hourly employee
            hoursField, payRateField,
        //commission employee|basePlusCommissionSales
            commissionRateField;
    //labels that are disabled in add employee
    JLabel salaryLabel, hoursLabel, payRateLabel, commissionRateLabel;
    //add product menu
    JPanel addProductPanel,productInfoPanel, productButtonPanel, productBlankSpacePanel;
    JTextField productIdField, productNameField, productDescriptionField, 
            productSupplierIdField, productCostField, productPriceField, productStockField;
    JButton addProductButton;
    //add manufacturer menu
    JPanel addManufacturerMainPanel, addManufacturerInformationPanel, addManufacturerButtonPanel, manufacturerBlankSpacePanel;
    JTextField manufacturerIdField, manufacturerNameField, manufacturerBranchNumField, manufacturerRepNameField;
    JButton addManufacturerButton;
    //HR search panel
    JPanel hrSearchMainPanel, hrSearchHeadPanel;
    JTextField hrSearchField;
    JTextArea hrSearchResultArea;
    //INV search panel
    JPanel invSearchMainPanel, invSearchHeadPanel, invSearchCentrePanel;
    JTextField invSearchField;
    JTextArea invSearchResultArea;
    JComboBox<String> invSearchTypeBox;

    public BackOfficeGui(){
        super("Back Office");
        //frame setup
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        setLayout(new BorderLayout());
        //panel setup
        headerSetup();
        footerSetup();
        //calls all the panel setup functions below
        mainSetup();
        //add panels to the frame
        add(headPanel, BorderLayout.PAGE_START);
        add(hrInvTabbedPane, BorderLayout.CENTER);
        add(footPanel, BorderLayout.PAGE_END);
        //make it look good
        pack();
    }
    private void headerSetup(){
        //setting up the header
        headPanel = new JPanel();
        headPanel.add(new JLabel("Welcome"));
    }
    private void footerSetup(){
        //setting up footer
        footPanel = new JPanel();
        exitButton = new JButton("Exit");
        exitButton.addActionListener(new ButtonListener());
        footPanel.add(exitButton);
    }
    private void mainSetup(){
        //setting up main panel
        mainTabbedPane = new JTabbedPane();
        hrInvTabbedPane = new JTabbedPane();
        hrCreateSearchTabbedPane = new JTabbedPane();
        invCreateSearchTabbedPane = new JTabbedPane();
        invCreateTabbedPane = new JTabbedPane();
        //calls all component setup
        allMainPanelsSetup();
        //outermost tabbed pane
        hrInvTabbedPane.addTab("HR", null, hrCreateSearchTabbedPane, "HR Options");
        hrInvTabbedPane.addTab("INV", null, invCreateSearchTabbedPane, "Inventory Options");
        //HR tabbed pane
        hrCreateSearchTabbedPane.addTab("Search", null, hrSearchMainPanel, "Search Employees");
        hrCreateSearchTabbedPane.addTab("Create", null, addEmployeePanel, "Add a new Employee");
        //INV tabbed pane
        invCreateSearchTabbedPane.addTab("Search", null, invSearchMainPanel, "Find a Product or Manufacturer");
        invCreateSearchTabbedPane.addTab("Create", null, invCreateTabbedPane, "Add a product or Manufacturer");
        //INV/ADD tabbed pane
        invCreateTabbedPane.addTab("Products", null, addProductPanel, "Add a product");
        invCreateTabbedPane.addTab("Manufacturers",null, addManufacturerMainPanel, "Add a manufacturer");
    }
    private void allMainPanelsSetup(){
        //setup the add employee menu
        addEmployeeInit();
        addEmployeeSetup();
        addEmployeeRadioButtonSetup();
        addEmployeeFinishSetup();
        //setup the add product menu
        addProductInit();
        addProductSetup();
        addProductFinishSetup();
        //setup the add manufacturer menu
        addManufacturerInit();
        addManufacturerSetup();
        addManufacturerFinishSetup();
        //setup the search HR panel
        searchHRInit();
        searchHRSetup();
        searchHRFinishSetup();
        //setup the search INV panel
        searchINVInit();
        searchINVSetup();
        searchINVFinishSetup();
    }
    //general setup for the add Employee JPanel
    private void addEmployeeInit(){
        //initialize components and add layouts
        addEmployeePanel = new JPanel();
        employeeInfoPanel = new JPanel();
        radioButtonPanel = new JPanel();
        
        salaryEmpRadio = new JRadioButton();
        hourlyEmpRadio = new JRadioButton();
        commissionEmpRadio = new JRadioButton();
        baseCommissionEmpRadio = new JRadioButton();
        
        employeeTypeGroup = new ButtonGroup();
        
        addEmployeeButton = new JButton("Add employee to list");
        
        firstNameField = new JTextField(15);
        lastNameField = new JTextField(15);
        genderField = new JTextField(15); 
        addressField = new JTextField(15); 
        emailField = new JTextField(15); 
        homeNumberField = new JTextField(15); 
        cellNumberField = new JTextField(15); 
        sinField = new JTextField(15);
        positionField = new JTextField(15); 
        departmentField = new JTextField(15); 
        birthDayField = new JTextField(15); 
        birthMonthField = new JTextField(15); 
        birthYearField = new JTextField(15);
        //salaryEmployee|basePlusCommissionSales
        salaryField = new JTextField(15);
        //hourly employee
        hoursField = new JTextField(15); 
        payRateField = new JTextField(15);
        //commission employee|basePlusCommissionSales
        commissionRateField = new JTextField(15);
        //construct the "somtimes" labels
        salaryLabel = new JLabel("Salary:");
        hoursLabel = new JLabel("Number of Hours:");
        payRateLabel = new JLabel("Rate of Hourly Pay:");
        commissionRateLabel = new JLabel("Commission Rate:");
        //listeners and layouts
        addEmployeeButton.addActionListener(new EmployeeAddListener());
        addEmployeePanel.setLayout(new BorderLayout());
        
        employeeInfoPanel.setLayout(new GridLayout(17,2));
    }
    private void addEmployeeSetup(){
        //add everything
        employeeInfoPanel.add(new JLabel("First Name:"));
        employeeInfoPanel.add(firstNameField);
        employeeInfoPanel.add(new JLabel("Last Name:"));
        employeeInfoPanel.add(lastNameField);
        employeeInfoPanel.add(new JLabel("Gender:"));
        employeeInfoPanel.add(genderField);
        employeeInfoPanel.add(new JLabel("Address:"));
        employeeInfoPanel.add(addressField);
        employeeInfoPanel.add(new JLabel("Email:"));
        employeeInfoPanel.add(emailField);
        employeeInfoPanel.add(new JLabel("Home Phone Number:"));
        employeeInfoPanel.add(homeNumberField);
        employeeInfoPanel.add(new JLabel("Cell Phone Number:"));
        employeeInfoPanel.add(cellNumberField);
        employeeInfoPanel.add(new JLabel("SIN Number:"));
        employeeInfoPanel.add(sinField);
        employeeInfoPanel.add(new JLabel("Position:"));
        employeeInfoPanel.add(positionField);
        employeeInfoPanel.add(new JLabel("Department:"));
        employeeInfoPanel.add(departmentField);
        employeeInfoPanel.add(new JLabel("Day of Birth:"));
        employeeInfoPanel.add(birthDayField);
        employeeInfoPanel.add(new JLabel("Month of Birth:"));
        employeeInfoPanel.add(birthMonthField);
        employeeInfoPanel.add(new JLabel("Year of Birth:"));
        employeeInfoPanel.add(birthYearField);
        //the employee subclass inputs
        employeeInfoPanel.add(salaryLabel);
        employeeInfoPanel.add(salaryField);
        employeeInfoPanel.add(hoursLabel);
        employeeInfoPanel.add(hoursField);
        employeeInfoPanel.add(payRateLabel);
        employeeInfoPanel.add(payRateField);
        employeeInfoPanel.add(commissionRateLabel);
        employeeInfoPanel.add(commissionRateField);
    }
    private void addEmployeeRadioButtonSetup(){
        //setup the radio buttons and actionListeners
        radioButtonPanel.setLayout(new FlowLayout());
        salaryEmpRadio.addActionListener(new EmployeeAddListener());
        hourlyEmpRadio.addActionListener(new EmployeeAddListener());
        commissionEmpRadio.addActionListener(new EmployeeAddListener());
        baseCommissionEmpRadio.addActionListener(new EmployeeAddListener());
        //do the onClick event for the Salary employee radio button (select button and disable text boxes)
        salaryEmpRadio.doClick();
        //add to group and panel
        employeeTypeGroup.add(salaryEmpRadio);
        employeeTypeGroup.add(hourlyEmpRadio);
        employeeTypeGroup.add(commissionEmpRadio);
        employeeTypeGroup.add(baseCommissionEmpRadio);
        
        radioButtonPanel.add(new JLabel("Salary Employee"));
        radioButtonPanel.add(salaryEmpRadio);
        radioButtonPanel.add(new JLabel("Hourly Employee"));
        radioButtonPanel.add(hourlyEmpRadio);
        radioButtonPanel.add(new JLabel("Commission Employee"));
        radioButtonPanel.add(commissionEmpRadio);
        radioButtonPanel.add(new JLabel("Base plus Commission Employee"));
        radioButtonPanel.add(baseCommissionEmpRadio);
    }
    private void addEmployeeFinishSetup(){
        //add everything to main panel
        addEmployeePanel.add(employeeInfoPanel, BorderLayout.CENTER);
        addEmployeePanel.add(radioButtonPanel, BorderLayout.PAGE_START);
        addEmployeePanel.add(addEmployeeButton, BorderLayout.PAGE_END);
    }
    //general setup for the add Product JPanel
    private void addProductInit(){
        //init everything for add Product pane
        addProductPanel = new JPanel();
        productInfoPanel = new JPanel();
        productButtonPanel = new JPanel();
        productBlankSpacePanel = new JPanel();
        productBlankSpacePanel.setPreferredSize(new Dimension(300,150));
        
        
        productIdField = new JTextField(); 
        productNameField = new JTextField();
        productDescriptionField = new JTextField();
        productSupplierIdField = new JTextField();
        productCostField = new JTextField();
        productPriceField = new JTextField();
        productStockField = new JTextField();
        
        addProductButton = new JButton("Add product");
        
        //panel layouts
        productInfoPanel.setLayout(new GridLayout(8,2));
        addProductPanel.setLayout(new BorderLayout(2,10));
    }
    private void addProductSetup(){
        //add all the things to the product info panel
        productInfoPanel.add(new JLabel("ID"));
        productInfoPanel.add(productIdField);
        productInfoPanel.add(new JLabel("Name"));
        productInfoPanel.add(productNameField);
        productInfoPanel.add(new JLabel("Description"));
        productInfoPanel.add(productDescriptionField);
        productInfoPanel.add(new JLabel("Supplier ID"));
        productInfoPanel.add(productSupplierIdField);
        productInfoPanel.add(new JLabel("Cost"));
        productInfoPanel.add(productCostField);
        productInfoPanel.add(new JLabel("Price"));
        productInfoPanel.add(productPriceField);
        productInfoPanel.add(new JLabel("Starting Stock"));
        productInfoPanel.add(productStockField);
        
        productButtonPanel.add(addProductButton);
    }
    private void addProductFinishSetup(){
        //add all Add product components to the main panel
        
        addProductPanel.add(productInfoPanel, BorderLayout.NORTH);
        addProductPanel.add(productBlankSpacePanel, BorderLayout.CENTER);
        addProductPanel.add(productButtonPanel, BorderLayout.SOUTH);
        
        addProductButton.addActionListener(new ButtonListener());
    }
    //general setup for the add Manufacturer JPanel
    private void addManufacturerInit(){
        //initialize all components
        addManufacturerMainPanel = new JPanel();
        addManufacturerInformationPanel = new JPanel();
        addManufacturerButtonPanel = new JPanel();
        manufacturerBlankSpacePanel = new JPanel();
        manufacturerBlankSpacePanel.setPreferredSize(new Dimension(300,230));
        
        manufacturerIdField = new JTextField(); 
        manufacturerNameField = new JTextField();
        manufacturerBranchNumField = new JTextField();
        manufacturerRepNameField = new JTextField();
        
        addManufacturerButton = new JButton("Add Manufacturer");
        //layout setup
        addManufacturerInformationPanel.setLayout(new GridLayout(4,2));
        addManufacturerMainPanel.setLayout(new BorderLayout(2,10));
    }
    private void addManufacturerSetup(){
        //add components to panels
        addManufacturerInformationPanel.add(new JLabel("ID"));
        addManufacturerInformationPanel.add(manufacturerIdField);
        addManufacturerInformationPanel.add(new JLabel("Name"));
        addManufacturerInformationPanel.add(manufacturerNameField);
        addManufacturerInformationPanel.add(new JLabel("Branch Number"));
        addManufacturerInformationPanel.add(manufacturerBranchNumField);
        addManufacturerInformationPanel.add(new JLabel("Representative Name"));
        addManufacturerInformationPanel.add(manufacturerRepNameField);
        
        addManufacturerButtonPanel.add(addManufacturerButton);
    }
    private void addManufacturerFinishSetup(){
        //add panels and leftover components to main panel
        addManufacturerMainPanel.add(addManufacturerInformationPanel, BorderLayout.NORTH);
        addManufacturerMainPanel.add(manufacturerBlankSpacePanel, BorderLayout.CENTER);
        addManufacturerMainPanel.add(addManufacturerButtonPanel, BorderLayout.SOUTH);
        //add listener
        addManufacturerButton.addActionListener(new ButtonListener());
    }
    //general setup for the Search employee JPanel
    private void searchHRInit(){
        //init everything
        hrSearchMainPanel = new JPanel();
        hrSearchHeadPanel = new JPanel();
        
        hrSearchField = new JTextField(30);
        
        hrSearchResultArea = new JTextArea();
        hrSearchResultArea.setPreferredSize(new Dimension(300,300));
        
        hrSearchMainPanel.setLayout(new BorderLayout());
    }
    private void searchHRSetup(){
        //add to non-main panels
        hrSearchHeadPanel.add(hrSearchField); 
    }
    private void searchHRFinishSetup(){
        //add to main panels
        hrSearchMainPanel.add(hrSearchHeadPanel, BorderLayout.NORTH);
        hrSearchMainPanel.add(hrSearchResultArea, BorderLayout.CENTER);
    }
    //general setup for the Search Products/Manufacturers JPanel
    private void searchINVInit(){
        //init everything
        invSearchMainPanel = new JPanel();
        invSearchHeadPanel = new JPanel();
        invSearchCentrePanel  = new JPanel();
        
        invSearchField = new JTextField(30);
        
        invSearchResultArea = new JTextArea();
        invSearchResultArea.setPreferredSize(new Dimension(100,100));
        
        invSearchTypeBox = new JComboBox<String>();
        invSearchTypeBox.addItem("Products");
        invSearchTypeBox.addItem("Manufacturers");
        //layouts
        invSearchHeadPanel.setLayout(new FlowLayout());
        invSearchMainPanel.setLayout(new BorderLayout());
        invSearchCentrePanel.setLayout(new BorderLayout());
    }
    private void searchINVSetup(){
        //add to non main panels
        invSearchHeadPanel.add(invSearchField, BorderLayout.WEST); 
        invSearchHeadPanel.add(invSearchTypeBox, BorderLayout.NORTH);
        
        invSearchCentrePanel.add(invSearchResultArea, BorderLayout.CENTER);
    }
    private void searchINVFinishSetup(){
        //add to main panels
        invSearchMainPanel.add(invSearchHeadPanel, BorderLayout.NORTH);
        invSearchMainPanel.add(invSearchCentrePanel, BorderLayout.CENTER);
    }
    
    //Listener classes
    private class EmployeeAddListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent ae) {
            if(ae.getSource() == addEmployeeButton){
                //if we click on the "add employee" button
                int day, month, year;
                try{
                    //check to see if all the inputs are numbers
                    day = Integer.parseInt(birthDayField.getText());
                    month = Integer.parseInt(birthMonthField.getText());
                    year = Integer.parseInt(birthYearField.getText());
                }catch(NumberFormatException e){
                    //if one is not, tell user and stop
                    JOptionPane.showMessageDialog(rootPane, "Either Day, month, or year (of birth) is not a number");
                    return;
                }
                //create variables of all the shared details
                String fName = firstNameField.getText();
                String lName = lastNameField.getText();
                String gender = genderField.getText();
                String address = addressField.getText();
                String email = emailField.getText();
                String homeNum = homeNumberField.getText();
                String cellNum = cellNumberField.getText();
                String sin = sinField.getText();
                String position = positionField.getText();
                String department = departmentField.getText();
                //find which radio button is selected...
                if(salaryEmpRadio.isSelected()){
                    try{
                        //ensure salary is a number
                        double salary = Double.parseDouble(salaryField.getText());
                        //and if so, add it to the employees arraylist
                        employees.add(new SalaryEmployee(
                        salary, fName, lName, gender, address, email, homeNum, cellNum, sin, position, department,day,month,year
                        ));
                    }catch(NumberFormatException e){
                        //if it is not a number, tell the user and don't add
                        JOptionPane.showMessageDialog(rootPane, "Salary is not a number");
                    }
                }else if(hourlyEmpRadio.isSelected()){
                    try{//ensure rate of pay and hours are numbers
                        double rateOfPay = Double.parseDouble(payRateField.getText());
                        int hours = Integer.parseInt(hoursField.getText());
                        //and if so, add it to the employees arraylist
                        employees.add(new HourlyEmployee(
                        hours, rateOfPay, fName, lName, gender, address, email, homeNum, cellNum, sin, position, department,day,month,year
                        ));
                    }catch(NumberFormatException e){
                        //if not numbers, tell the user and don't add
                        JOptionPane.showMessageDialog(rootPane, "Either Hours or Rate of Pay is not a number");
                    }
                }else if(commissionEmpRadio.isSelected()){
                    try{
                        //ensure rate of commission is a number
                        double rateOfCommission = Double.parseDouble(commissionRateField.getText());
                        //if so, add it to the list
                        employees.add(new CommissionSalesEmployee(
                        rateOfCommission, fName, lName, gender, address, email, homeNum, cellNum, sin, position, department,day,month,year
                        ));
                    }catch(NumberFormatException e){
                        //if not, inform the user and dont add
                        JOptionPane.showMessageDialog(rootPane, "Either Hours or Rate of Pay is not a number");
                    }
                }else if(baseCommissionEmpRadio.isSelected()){
                    try{
                        //ensure rate of commission and salary are numbers
                        double rateOfCommission = Double.parseDouble(commissionRateField.getText());
                        double salary = Double.parseDouble(payRateField.getText());
                        //and if so, add it to the list
                        employees.add(new BasePlusCommissionSalesEmployee(
                        salary, rateOfCommission, fName, lName, gender, address, email, homeNum, cellNum, sin, position, department,day,month,year
                        ));
                    }catch(NumberFormatException e){
                        //else tell the user and dont add
                        JOptionPane.showMessageDialog(rootPane, "Either Hours or Rate of Pay is not a number");
                    }
                }
            }else if(ae.getSource() == salaryEmpRadio){
                //enable necessary stuff
                salaryLabel.setEnabled(true);
                salaryField.setEnabled(true);
                //disable the rest
                hoursLabel.setEnabled(false);
                hoursField.setEnabled(false);
                payRateField.setEnabled(false);
                payRateLabel.setEnabled(false);
                commissionRateLabel.setEnabled(false);
                commissionRateField.setEnabled(false);
            }else if(ae.getSource() == hourlyEmpRadio){
                //enable necessary stuff
                hoursLabel.setEnabled(true);
                hoursField.setEnabled(true);
                payRateField.setEnabled(true);
                payRateLabel.setEnabled(true);
                //disable the rest
                salaryLabel.setEnabled(false);
                salaryField.setEnabled(false);
                commissionRateLabel.setEnabled(false);
                commissionRateField.setEnabled(false);
            }else if(ae.getSource() == commissionEmpRadio){
                //enable necessary stuff
                commissionRateLabel.setEnabled(true);
                commissionRateField.setEnabled(true);
                //disable the rest
                salaryLabel.setEnabled(false);
                salaryField.setEnabled(false);
                hoursLabel.setEnabled(false);
                hoursField.setEnabled(false);
                payRateField.setEnabled(false);
                payRateLabel.setEnabled(false);
                
            }else if(ae.getSource() == baseCommissionEmpRadio){
                //enable necessary stuff
                salaryLabel.setEnabled(true);
                salaryField.setEnabled(true);
                commissionRateLabel.setEnabled(true);
                commissionRateField.setEnabled(true);
                //disable the rest
                hoursLabel.setEnabled(false);
                hoursField.setEnabled(false);
                payRateField.setEnabled(false);
                payRateLabel.setEnabled(false);
            }
            
        }
        
    }
    private class ButtonListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent ae) {
            if(ae.getSource() == exitButton){
                System.exit(0);
            }else if(ae.getSource() ==  addProductButton){
                //add a product to the list
                int id, supplierID, stock;
                try{
                    id = Integer.parseInt(productIdField.getText());
                    supplierID = Integer.parseInt(productSupplierIdField.getText());
                    stock = Integer.parseInt(productStockField.getText());
                }catch(NumberFormatException e){
                    JOptionPane.showMessageDialog(rootPane, "ID, Supplier ID, and Stock must all be numbers");
                    return;
                }
                double cost, price;
                try{
                    cost = Double.parseDouble(productCostField.getText());
                    price = Double.parseDouble(productPriceField.getText());
                }catch(NumberFormatException e){
                        JOptionPane.showMessageDialog(rootPane, "Cost and Price must both be numbers");
                    return;
                }
                products.add(new Product(id, productNameField.getText(), productDescriptionField.getText(),supplierID,cost,price,stock));
            }else if(ae.getSource() == addManufacturerButton){
                System.out.println("Manufacturer Button Pressed");
            }
        }
        
    }
    //main
    public static void main(String[] args) {
        BackOfficeGui g = new BackOfficeGui();
    }
}
