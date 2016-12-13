package BusinessJDBC;
import static BusinessJDBC.BackOfficeJDBC.gregToSqlDate;
import java.awt.*;
import java.awt.event.*;
import java.util.GregorianCalendar;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
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
 * @author Mikhail & Ryan
 */
public class GUIFinal extends JFrame
{
    private final static String dbLogin = "gc200313751", dbPass = "HBw?UQ--", dbConnect = "sql.computerstudi.es:3306/gc200313751";
    
    //** Panels **\\
    JTabbedPane mainTabbedPane, invTabbedPane;
    
    private DatabasePane userPane;
    
    private JPanel titlePanel,
                   //panels for HR tab
                   employeePanel, employeePositionInfoPanel, employeeTypeInfoPanel, employeeBasicInfoPanel, employeeSelectPanel, hrDropdownHolderPanel,
                   //panels for INV tab
                   productPanel, productInfoPanel, productDropdownPanel, productSelectManufacturerPanel, manufacturerPanel, manufacturerDropdownPanel,
            
                   headPanel, salesPanel, mainSalesTop, mainSalesCenter, mainSalesBottom,
                   customerPanel, mainCustomerTop, mainCustomerCenter, mainCustomerBottom,
            
            
                   //bottom panel for buttons
                   footPanel;
    
    //** Labels **\\
    //panel labels
    private JLabel lblTitle,
        //employee position info
                   lblFirstName, lblLastName, lblBirthdate, lblAddress, lblGender, lblContactNum, lblEmpTitle, lblEmpNum, 
        //employee type info
                   lblCommissionRate, lblTotalSales, lblPayRate, lblHoursWorked, lblSalary,
        //product info
                   lblProductName, lblProductType, lblProductPrice, lblProductStock,
        //manufacturer info
                   lblManufacturerName, lblManufacturerContactNum, lblManufacturerAddress,
        //app labels
                   lblSearch;
    
    //** Textfields **\\
        //employee position info
    private JTextField txtFirstName, txtLastName, txtBirthdate, txtAddress, txtGender, txtEmployeeID, txtContactNum, txtEmpTitle, txtEmpNum,
        //employee type info
                       txtCommissionRate, txtTotalSales, txtPayRate, txtHoursWorked, txtSalary,
        //product info
                       txtProductName, txtProductType, txtProductPrice, txtProductStock,
        //manufacturer info
                       txtManufacturerName, txtManufacturerContactNum, txtManufacturerAddress,
        //search textfield
                       txtSearch,
            
            
            txtCustomerID, txtEmpNo, txtProdID, txtPurchaseDate, txtTotalCost, txtPotentialCommission,
            txtCustomerFirstName, txtCustomerLastName, txtBillingAddress, txtPhoneNumber, txtSignUpDate, txtSalesCustomerID;
    
    //** Dropdown lists **\\
    private JComboBox<String> selectEmpType, selectHR, 
                              selectINVProduct, selectProductManufacturer, 
                              selectINVManufacturer,
                              sales, customer;
    
    private static final String[] EMP_TYPES = {"(Select an Employee Type)", "Hourly", "Salary", "Commission"},
                                  HR_OPTIONS = {"", "Create Employee", "Search Employee", "Edit Employee"},
                                  PRODUCT_OPTIONS = {"", "Create Product", "Search Product", "Edit Product"},
                                  MANUFACTURER_OPTIONS = {"", "Create Manufacturer", "Search Manufacturer", "Edit Manufacturer"},
                                  customerTypes = {"Creates Customers", "Search Customers", "Edit Customers"},
                                  salesTypes = {"Creates Sales", "Search Sales", "Edit Sales"};
    
    //** Buttons **\\
    private JButton btnExit, btnEdit, btnSearch, btnCreate, btnDelete, btnClear,
            
            createEmployeeButton, searchEmployeeButton, editEmployeeButton, deleteEmployeeButton,
            createProductButton, searchProductButton, editProductButton, deleteProductButton,
            createManufacturerButton, searchManufacturerButton, editManufacturerButton, deleteManufacturerButton,
            clearFormButton, 
            createSalesButton, editSalesButton, deleteSalesButton, searchSalesButton,
            searchCustomerButton, createCustomerButton, editCustomerButton, deleteCustomerButton, exitButton;
    
    //listeners
    boolean admin;
    String username;
    
    //** GUI ASSEMBLY **\\
    public GUIFinal()
    {
        super("Chill Mart");
        setLayout(new BorderLayout());
        
        //login
        userPane = new DatabasePane(dbLogin, dbPass, dbConnect, "JavaUsers");
        userPane.setDateAutoInsert(4); // this disables input on the DATE column for 'creation date'
        login();
        //creating all the tab panes (named after their layer tiers in the JFrame)
        //JTabbedPane mainTabbedPane = new JTabbedPane(),
        //            tier2TabPane = new JTabbedPane();
        
        //Methods for building the content panels
        
        buildTitlePanel();
        buildSalesPanel();
        buildCustomerPanel();
        buildHRPanel();
        buildINVProductPanel();
        buildINVManufacturerPanel();
        
        
        buildFootPanel();
        
        tabbedPaneSetup();
        /*
        employeePanel.setLayout(new GridLayout(0,1));
        employeePanel.add(employeePositionInfoPanel, BorderLayout.NORTH);
        employeePanel.add(employeeTypeInfoPanel, BorderLayout.CENTER);
        //employeePanel.add(hrComboBoxHolderPanel, BorderLayout.SOUTH);
        employeePanel.add(hrDropdownHolderPanel, BorderLayout.SOUTH);
        //employeePanel.add(selectHR, BorderLayout.SOUTH);
        
        invTabbedPane.addTab("Products", null, productPanel, "Products");
        invTabbedPane.addTab("Manufacturers", null, manufacturerPanel, "Manufacturers");
        
        //top level tab panes for HR and INV, which hold all the lower level tab panes
        mainTabbedPane.addTab("HR", null, employeePanel, "HR");
        mainTabbedPane.addTab("Inventory", null, invTabbedPane, "INV");
        */
        //adding the main top level panels to the frame
        add(titlePanel, BorderLayout.NORTH);
        add(mainTabbedPane, BorderLayout.CENTER);
        add(footPanel, BorderLayout.SOUTH);
        
        setVisible(true);
        //pack to make the elements on the interface compact
        pack();
    }
    
    //Running the GUI
    public static void main(String[] args)
    {
        //initializing a new MishMartGUI
        GUIFinal chillMartInterface = new GUIFinal();
        //Setting a fixed size
        chillMartInterface.setSize(900, 375);
        chillMartInterface.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        chillMartInterface.setVisible(true);
    }
    
    private void buildTitlePanel() 
    {
        //initializing the greeting panel
        titlePanel = new JPanel();
        //initializing, changing the font and adding a greeting label
        lblTitle = new JLabel("Welcome to Chill Mart");
        lblTitle.setFont(new Font("Arial", Font.BOLD, 22));
        titlePanel.add(lblTitle);
        titlePanel.setBorder(BorderFactory.createRaisedBevelBorder());
    }

    private void buildHRPanel() 
    {
        //initializing the panels and subpanels for the basic, positional, and type employee info
        employeePanel = new JPanel();
        employeePositionInfoPanel = new JPanel(); 
        employeePositionInfoPanel.setLayout(new GridLayout(3, 3));
        employeeTypeInfoPanel = new JPanel();
        hrDropdownHolderPanel = new JPanel();
        
        //initializing the employee type combo box and populating it with the employee types
        selectEmpType = new JComboBox<String>(EMP_TYPES);
        selectEmpType.setMaximumRowCount(EMP_TYPES.length);
        
        //creating an action listener to the selectEmpType combo box to change what components are 
        //visible on the position panel based on selection
        selectEmpType.addActionListener(
                new ActionListener()
                {
                  @Override
                  public void actionPerformed(ActionEvent event)
                  {
                      //if "Hourly" is selected, only show hourly employee inputs
                      if(selectEmpType.getSelectedItem() == "Hourly")
                      {
                          lblCommissionRate.setVisible(false);
                          txtCommissionRate.setVisible(false);
                          lblTotalSales.setVisible(false);
                          txtTotalSales.setVisible(false);
                          lblPayRate.setVisible(true);
                          txtPayRate.setVisible(true);
                          lblHoursWorked.setVisible(true);
                          txtHoursWorked.setVisible(true);
                          lblSalary.setVisible(false);
                          txtSalary.setVisible(false);
                      }
                      //if "Salary" is selected, only show salary employee inputs
                      else if (selectEmpType.getSelectedItem() == "Salary")
                      {
                          lblCommissionRate.setVisible(false);
                          txtCommissionRate.setVisible(false);
                          lblTotalSales.setVisible(false);
                          txtTotalSales.setVisible(false);
                          lblPayRate.setVisible(false);
                          txtPayRate.setVisible(false);
                          lblHoursWorked.setVisible(false);
                          txtHoursWorked.setVisible(false);
                          lblSalary.setVisible(true);
                          txtSalary.setVisible(true);
                      }
                      //if "Commission" is selected, only show commission employee inputs
                      else if (selectEmpType.getSelectedItem() == "Commission")
                      {
                          lblCommissionRate.setVisible(true);
                          txtCommissionRate.setVisible(true);
                          lblTotalSales.setVisible(true);
                          txtTotalSales.setVisible(true);
                          lblPayRate.setVisible(false);
                          txtPayRate.setVisible(false);
                          lblHoursWorked.setVisible(false);
                          txtHoursWorked.setVisible(false);
                          lblSalary.setVisible(false);
                          txtSalary.setVisible(false);
                      }
                  }
        });
        
        //initializing the combo box for selecting employee options and populating it with the possible options
        selectHR = new JComboBox<String>(HR_OPTIONS);
        selectHR.setMaximumRowCount(HR_OPTIONS.length);
        
        selectHR.addItemListener(new ItemListener() {

            @Override
            public void itemStateChanged(ItemEvent event) {
                footPanel.removeAll();

                //if the user selects "Creates Customer"
                if (selectHR.getSelectedItem() == "Create Employee") {

                    footPanel.removeAll();
                    pack();
                    createEmployeeButton = new JButton("Create Employee");
                    footPanel.add(createEmployeeButton);

                    clearFormButton = new JButton("Clear Form");

                    //clear form actionEvent
                    clearFormButton.addActionListener((ActionEvent a) -> {
                        txtFirstName.setText("");
                        txtLastName.setText("");
                        txtBirthdate.setText("");
                        txtAddress.setText("");
                        txtGender.setText("");
                        txtContactNum.setText("");
                        txtEmpTitle.setText("");
                        txtEmpNum.setText("");
                        txtCommissionRate.setText("");
                        txtTotalSales.setText("");
                        txtPayRate.setText("");
                        txtHoursWorked.setText("");
                        txtSalary.setText("");
                    });

                    footPanel.add(clearFormButton);

                    exitButton = new JButton("Exit");
                    //exit button action listener
                    exitButton.addActionListener(e -> exit());
                    footPanel.add(exitButton);
                    pack();
                } //if the user selects "Search Customers"
                else if (selectHR.getSelectedItem() == "Search Employee") {

                    footPanel.removeAll();
                    pack();
                    searchEmployeeButton = new JButton("Search Employees");
                    footPanel.add(searchEmployeeButton);

                    clearFormButton = new JButton("Clear Form");
                    clearFormButton.addActionListener((ActionEvent e) -> {
                        txtFirstName.setText("");
                        txtLastName.setText("");
                        txtBirthdate.setText("");
                        txtAddress.setText("");
                        txtGender.setText("");
                        txtContactNum.setText("");
                        txtEmpTitle.setText("");
                        txtEmpNum.setText("");
                        txtCommissionRate.setText("");
                        txtTotalSales.setText("");
                        txtPayRate.setText("");
                        txtHoursWorked.setText("");
                        txtSalary.setText("");
                    });
                    footPanel.add(clearFormButton);

                    exitButton = new JButton("Exit");
                    exitButton.addActionListener(e -> exit());
                    footPanel.add(exitButton);
                    pack();
                } //if the user selects "Edit Customers"
                else if (selectHR.getSelectedItem() == "Edit Employee") {

                    footPanel.removeAll();
                    pack();
                    searchEmployeeButton = new JButton("Search Employees");
                    footPanel.add(searchEmployeeButton);

                    editEmployeeButton = new JButton("Edit Employees");
                    footPanel.add(editEmployeeButton);

                    deleteEmployeeButton = new JButton("Delete Employees");
                    footPanel.add(deleteEmployeeButton);

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
        
        //initialize components for employee position info
        lblFirstName = new JLabel("First Name:");
        txtFirstName = new JTextField(10);
        lblLastName = new JLabel("Last Name:");
        txtLastName = new JTextField(10);
        lblBirthdate = new JLabel("Birth Date:");
        txtBirthdate = new JTextField(10);
        lblGender = new JLabel("Gender:");
        txtGender = new JTextField(2);
        lblAddress = new JLabel("Address:");
        txtAddress = new JTextField(10);
        lblContactNum = new JLabel("Contact Num:");
        txtContactNum = new JTextField(10);
        lblEmpTitle = new JLabel("Title:");
        txtEmpTitle = new JTextField(10);
        lblEmpNum = new JLabel("Employee Num:");
        txtEmpNum = new JTextField(10);
        
        //set a border for position info
        employeePositionInfoPanel.setBorder(
                BorderFactory.createTitledBorder("Position Information"));
        
        //adding the selectEmpType combobox to the position panel
        employeeTypeInfoPanel.add(selectEmpType);
        
        //initializing components specific to the employee type
        lblCommissionRate = new JLabel("Commission Rate:");
        txtCommissionRate = new JTextField(5);
        lblTotalSales = new JLabel("Total Sales:");
        txtTotalSales = new JTextField(5);
        lblPayRate = new JLabel("Pay Rate:");
        txtPayRate = new JTextField(5);
        lblHoursWorked = new JLabel("Hours Worked:");
        txtHoursWorked = new JTextField(5);
        lblSalary = new JLabel("Salary:");
        txtSalary = new JTextField(5);
        
        //set a border for type info
        employeeTypeInfoPanel.setBorder(
                BorderFactory.createTitledBorder("Type Information"));
        
        //add the components to the employee position info subpanel
        employeePositionInfoPanel.add(lblFirstName);
        employeePositionInfoPanel.add(txtFirstName);
        employeePositionInfoPanel.add(lblLastName);
        employeePositionInfoPanel.add(txtLastName);
        employeePositionInfoPanel.add(lblBirthdate);
        employeePositionInfoPanel.add(txtBirthdate);
        employeePositionInfoPanel.add(lblGender);
        employeePositionInfoPanel.add(txtGender);
        employeePositionInfoPanel.add(lblAddress);
        employeePositionInfoPanel.add(txtAddress);
        employeePositionInfoPanel.add(lblContactNum);
        employeePositionInfoPanel.add(txtContactNum);
        employeePositionInfoPanel.add(lblEmpTitle);
        employeePositionInfoPanel.add(txtEmpTitle);
        employeePositionInfoPanel.add(lblEmpNum);
        employeePositionInfoPanel.add(txtEmpNum);
        
        //adding the components to the type info panel
        employeeTypeInfoPanel.add(lblCommissionRate);
        employeeTypeInfoPanel.add(lblCommissionRate);
        employeeTypeInfoPanel.add(txtCommissionRate);
        employeeTypeInfoPanel.add(lblTotalSales);
        employeeTypeInfoPanel.add(txtTotalSales);
        employeeTypeInfoPanel.add(lblPayRate);
        employeeTypeInfoPanel.add(txtPayRate);
        employeeTypeInfoPanel.add(lblHoursWorked);
        employeeTypeInfoPanel.add(txtHoursWorked);
        employeeTypeInfoPanel.add(lblSalary);
        employeeTypeInfoPanel.add(txtSalary);
        
        //adding the selectHR combobox to the 
        hrDropdownHolderPanel.add(selectHR);
    }

    private void buildINVManufacturerPanel() 
    {
        manufacturerPanel = new JPanel();
        manufacturerDropdownPanel = new JPanel();
        
        
        //initializing the combo box for selecting manufacturer options and populating it with the possible options
        selectINVManufacturer = new JComboBox<String>(MANUFACTURER_OPTIONS);
        selectINVManufacturer.setMaximumRowCount(MANUFACTURER_OPTIONS.length);
        
        selectINVManufacturer.addActionListener(
                new ActionListener()
                {
                  @Override
                  public void actionPerformed(ActionEvent event)
                  {
                      
                  }
            
        });
        
        //initialize manufacturer components
        lblManufacturerName = new JLabel("Name:");
        txtManufacturerName = new JTextField(10);
        lblManufacturerContactNum = new JLabel("Contact Num:");
        txtManufacturerContactNum = new JTextField(6);
        lblManufacturerAddress = new JLabel("Address:");
        txtManufacturerAddress = new JTextField(6);
        
        //set a border
        manufacturerPanel.setBorder(
                BorderFactory.createTitledBorder("Manufacturer Information"));
        
        //add all the components to the product panel
        manufacturerPanel.add(lblManufacturerName);
        manufacturerPanel.add(txtManufacturerName);
        manufacturerPanel.add(lblManufacturerContactNum);
        manufacturerPanel.add(txtManufacturerContactNum);
        manufacturerPanel.add(lblManufacturerAddress);
        manufacturerPanel.add(txtManufacturerAddress);
    }

    private void buildINVProductPanel() 
    {
        productPanel = new JPanel();
        
        productInfoPanel = new JPanel();
        productSelectManufacturerPanel = new JPanel();
        productDropdownPanel = new JPanel();
        
        //initializing the manufacturer selector combo box, to be populated from the database
        selectProductManufacturer = new JComboBox<String>();
        
        //initializing the combo box for selecting product options and populating it with the possible options
        selectINVProduct = new JComboBox<String>(PRODUCT_OPTIONS);
        selectINVProduct.setMaximumRowCount(PRODUCT_OPTIONS.length);
        
        selectINVProduct.addItemListener(new ItemListener() {

            @Override
            public void itemStateChanged(ItemEvent event) {
                footPanel.removeAll();

                //if the user selects "Creates Product"
                if (selectINVProduct.getSelectedItem() == "Create Product") {

                    footPanel.removeAll();
                    pack();
                    createProductButton = new JButton("Create Product");
                    footPanel.add(createProductButton);

                    clearFormButton = new JButton("Clear Form");

                    //clear form actionEvent
                    clearFormButton.addActionListener((ActionEvent a) -> {
                        txtProductName.setText("");
                        txtProductType.setText("");
                        txtProductPrice.setText("");
                        txtProductStock.setText("");
                    });

                    footPanel.add(clearFormButton);

                    exitButton = new JButton("Exit");
                    //exit button action listener
                    exitButton.addActionListener(e -> exit());
                    footPanel.add(exitButton);
                    pack();
                } //if the user selects "Search Customers"
                else if (selectINVProduct.getSelectedItem() == "Search Product") {

                    footPanel.removeAll();
                    pack();
                    searchProductButton = new JButton("Search Product");
                    footPanel.add(searchProductButton);

                    clearFormButton = new JButton("Clear Form");
                    clearFormButton.addActionListener((ActionEvent e) -> {
                        txtProductName.setText("");
                        txtProductType.setText("");
                        txtProductPrice.setText("");
                        txtProductStock.setText("");
                    });
                    footPanel.add(clearFormButton);

                    exitButton = new JButton("Exit");
                    exitButton.addActionListener(e -> exit());
                    footPanel.add(exitButton);
                    pack();
                } //if the user selects "Edit Customers"
                else if (selectINVProduct.getSelectedItem() == "Edit Product") {

                    footPanel.removeAll();
                    pack();
                    searchProductButton = new JButton("Search Products");
                    footPanel.add(searchProductButton);

                    editProductButton = new JButton("Edit Products");
                    footPanel.add(editProductButton);

                    deleteProductButton = new JButton("Delete Products");
                    footPanel.add(deleteProductButton);

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
        
        //initialize product components
        lblProductName = new JLabel("Product Name:");
        txtProductName = new JTextField(10);
        lblProductType = new JLabel("Product Type:");
        txtProductType = new JTextField(10);
        lblProductPrice = new JLabel("Price: $");
        txtProductPrice = new JTextField(6);
        lblProductStock = new JLabel("Stock:");        
        txtProductStock = new JTextField(6);
        
        //set a border
        productInfoPanel.setBorder(
                BorderFactory.createTitledBorder("Product Information"));
        
        //add all the components to the product panel
        productInfoPanel.add(lblProductName);
        productInfoPanel.add(txtProductName);
        productInfoPanel.add(lblProductType);
        productInfoPanel.add(txtProductType);
        productInfoPanel.add(lblProductPrice);
        productInfoPanel.add(txtProductPrice);
        productInfoPanel.add(lblProductStock);
        productInfoPanel.add(txtProductStock);
        
        //adding the select manufacturer dropdown to the manufacturer select area for the product tab
        productSelectManufacturerPanel.add(selectProductManufacturer);
        
        //adding the selectINVProduct combobox to the product dropdown panel
        productDropdownPanel.add(selectINVProduct);
    }
    
    private void buildSalesPanel() {
        salesPanel = new JPanel();
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
        customerPanel = new JPanel();
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
    
    private void buildFootPanel() 
    {
        //create the panel
        footPanel = new JPanel();
        btnExit = new JButton("Exit");
        btnExit.addActionListener(new ExitButtonHandler());
        footPanel.add(btnExit);
    }
    
    //Handler for the create button
    private class CreateButtonHandler implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent event)
        {
            
        }
    }
    
    /*//Handler for the exit button
    private class ClearButtonHandler implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent event)
        {
            
        }
    }*/
    
    //Handler for the exit button
    private class SearchButtonHandler implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent event)
        {
            
        }
    }
    
    //Handler for the edit button
    private class EditButtonHandler implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent event)
        {
            
        }
    }
    
    //Handler for the exit button
    private class DeleteButtonHandler implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent event)
        {
            
        }
    }
    
    //Handler for the exit button, brings up a confirmation panel and shuts down the program upon confirmation
    private class ExitButtonHandler implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent event)
        {
            if(JOptionPane.showConfirmDialog(null, "Are you sure you want to exit?",
                    "Exit", JOptionPane.YES_NO_OPTION)== 3);
            System.exit(0);
        }
    }
    
    //lambda button functions
    private void exit() {
        System.exit(0);
    }
    
    
    private void tabbedPaneSetup() {
        invTabbedPane = new JTabbedPane();
        invTabbedPane.addTab("Product", null, productPanel, "Product Control");
        invTabbedPane.addTab("Manufacturer", null, manufacturerPanel, "Manufacturer Control");
        mainTabbedPane = new JTabbedPane();
        mainTabbedPane.addTab("HR", null, employeePanel, "Employee Control");
        
        employeePanel.setLayout(new GridLayout(0,1));
        employeePanel.add(employeePositionInfoPanel, BorderLayout.NORTH);
        employeePanel.add(employeeTypeInfoPanel, BorderLayout.CENTER);
        employeePanel.add(hrDropdownHolderPanel, BorderLayout.SOUTH);
        
        productPanel.setLayout(new BorderLayout());
        productPanel.add(productInfoPanel, BorderLayout.NORTH);
        productPanel.add(productSelectManufacturerPanel, BorderLayout.CENTER);
        productPanel.add(productDropdownPanel, BorderLayout.SOUTH);
        
        mainTabbedPane.addTab("Inventory", null, invTabbedPane, "Inventory Control");
        mainTabbedPane.addTab("Sales", null, salesPanel, "Sales Control");
        mainTabbedPane.addTab("Customers", null, customerPanel, "Customer Control");
        if (admin) {
            mainTabbedPane.addTab("Admin", null, userPane, "Admin User Control");
        }
        
        
        salesPanel.setLayout(new BorderLayout());
        salesPanel.add(mainSalesTop, BorderLayout.NORTH);
        salesPanel.add(mainSalesCenter, BorderLayout.CENTER);
        salesPanel.add(mainSalesBottom, BorderLayout.SOUTH);
        
        customerPanel.setLayout(new BorderLayout());
        customerPanel.setLayout(new BorderLayout());
        customerPanel.add(mainCustomerTop, BorderLayout.NORTH);
        customerPanel.add(mainCustomerBottom, BorderLayout.SOUTH);

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
    
    /*
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

        salesPanel.add(mainSalesTop, BorderLayout.NORTH);
        salesPanel.add(mainSalesCenter, BorderLayout.CENTER);
        salesPanel.add(mainSalesBottom, BorderLayout.SOUTH);

        salesPane.setPreferredSize(new Dimension(150, 150));// hardCoded sizing
        salesPane.setMaximumSize(mainSalesCenter.getPreferredSize());
        pack();

        customerPane = new JPanel();
        customerPane.setLayout(new BorderLayout());
        customerPane.add(mainCustomerTop, BorderLayout.NORTH);
        customerPane.add(mainCustomerBottom, BorderLayout.SOUTH);

    }*/

    
}
