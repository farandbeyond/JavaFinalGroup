package BusinessJDBC;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 *
 * @author Mikhail & Ryan
 */
public class GUIFinal extends JFrame
{
    //** Panels **\\
                   //panels for HR tab
    private JPanel titlePanel, employeePanel = new JPanel(), employeePositionInfoPanel, employeeTypeInfoPanel, employeeBasicInfoPanel, 
                   //panels for INV tab
                   productPanel, manufacturerPanel,
                   //bottom panel for buttons
                   buttonPanel;
    
    //** Labels **\\
    //panel labels
    private JLabel lblTitle,
        //basic employee info
                   lblFirstName, lblLastName, lblBirthdate, lblAddress, lblGender, lblContactNum,
        //positional employee info
                   lblEmpTitle, lblEmpNum, 
        //employee type info
                   lblCommissionRate, lblTotalSales, lblPayRate, lblHoursWorked, lblSalary,
        //product info
                   lblProductName, lblProductType, lblProductPrice, lblProductStock,
        //manufacturer info
                   lblManufacturerName, lblManufacturerContactNum, lblManufacturerAddress,
        //customer info
            
        //
            
        //app labels
                   lblSearch;
    
    //** Textfields **\\
        //basic employee info
    private JTextField txtFirstName, txtLastName, txtBirthdate, txtAddress, txtGender, txtEmployeeID, txtContactNum,
        //positional employee info
                       txtEmpTitle, txtEmpNum,
        //employee type info
                       txtCommissionRate, txtTotalSales, txtPayRate, txtHoursWorked, txtSalary,
        //product info
                       txtProductName, txtProductType, txtProductPrice, txtProductStock,
        //manufacturer info
                       txtManufacturerName, txtManufacturerContactNum, txtManufacturerAddress,
        //search textfield
                       txtSearch;
    
    //** Dropdown lists **\\
    private JComboBox<String> selectEmpType, selectHR, 
                              selectINVProduct, selectProductManufacturer, 
                              selectINVManufacturer;
    private static final String[] EMP_TYPES = {"(Select an Employee Type)", "Hourly", "Salary", "Commission"},
                                  HR_OPTIONS = {"(Select an action)", "Create Employee", "Search Employee", "Edit Employee"},
                                  PRODUCT_OPTIONS = {"(Select an action)", "Create Product", "Search Product", "Edit Product"},
                                  MANUFACTURER_OPTIONS = {"(Select an action)", "Create Manufacturer", "Search Manufacturer", "Edit Manufacturer"};
    
    //** Buttons **\\
    private JButton btnExit, btnEdit, btnSearch, btnCreate, btnDelete, btnClear;
    
    
    //** GUI ASSEMBLY **\\
    public GUIFinal()
    {
        super("Chill Mart");
        setLayout(new BorderLayout());
        
        //creating all the tab panes (named after their layer tiers in the JFrame)
        JTabbedPane tier1TabPane = new JTabbedPane(),
                    tier2TabPane = new JTabbedPane();
        
        //Methods for building the content panels
        buildTitlePanel();
        buildHRPanel();
        buildINVProductPanel();
        buildINVManufacturerPanel();
        
        buildButtonPanel();
        
        employeePanel.setLayout(new GridLayout(0,1));
        employeePanel.add(employeeBasicInfoPanel, BorderLayout.NORTH);
        employeePanel.add(employeePositionInfoPanel, BorderLayout.CENTER);
        employeePanel.add(employeeTypeInfoPanel, BorderLayout.SOUTH);
        
        tier2TabPane.addTab("Products", null, productPanel, "Products");
        tier2TabPane.addTab("Manufacturers", null, manufacturerPanel, "Manufacturers");
        
        //top level tab panes for HR and INV, which hold all the lower level tab panes
        tier1TabPane.addTab("HR", null, employeePanel, "HR");
        tier1TabPane.addTab("Inventory", null, tier2TabPane, "INV");
        
        //adding the main top level panels to the frame
        add(titlePanel, BorderLayout.NORTH);
        add(tier1TabPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
        
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
        employeeBasicInfoPanel = new JPanel();
        employeePositionInfoPanel = new JPanel(); 
        employeeTypeInfoPanel = new JPanel();
        
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
        
        selectHR.addActionListener(
                new ActionListener()
                {
                  @Override
                  public void actionPerformed(ActionEvent event)
                  {
                      
                  }
            
        });
        
        //initialize components for basic employee info
        lblFirstName = new JLabel("First Name:");
        txtFirstName = new JTextField(10);
        lblLastName = new JLabel("Last Name:");
        txtLastName = new JTextField(10);
        lblBirthdate = new JLabel("Birth Date:");
        txtBirthdate = new JTextField(10);
        lblGender = new JLabel("Gender:");
        txtGender = new JTextField(2);
        lblAddress = new JLabel("Address:");
        txtAddress = new JTextField(18);
        lblContactNum = new JLabel("Contact Num:");
        txtContactNum = new JTextField(10);
        
        //set a border for basic info
        employeeBasicInfoPanel.setBorder(
                BorderFactory.createTitledBorder("Employee Information"));
        
        lblEmpTitle = new JLabel("Title:");
        txtEmpTitle = new JTextField(10);
        lblEmpNum = new JLabel("Employee Num:");
        txtEmpNum = new JTextField(10);
        
        //set a border for position info
        employeePositionInfoPanel.setBorder(
                BorderFactory.createTitledBorder("Position Information"));
        
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
        
        //add the components to the basic employee info subpanels
        employeeBasicInfoPanel.add(lblFirstName);
        employeeBasicInfoPanel.add(txtFirstName);
        employeeBasicInfoPanel.add(lblLastName);
        employeeBasicInfoPanel.add(txtLastName);
        employeeBasicInfoPanel.add(lblBirthdate);
        employeeBasicInfoPanel.add(txtBirthdate);
        employeeBasicInfoPanel.add(lblGender);
        employeeBasicInfoPanel.add(txtGender);
        employeeBasicInfoPanel.add(lblAddress);
        employeeBasicInfoPanel.add(txtAddress);
        employeeBasicInfoPanel.add(lblContactNum);
        employeeBasicInfoPanel.add(txtContactNum);
        
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
        
        
    }

    private void buildINVManufacturerPanel() 
    {
        manufacturerPanel = new JPanel();
        
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
        
        //initializing the manufacturer selector combo box, to be populated from the database
        selectProductManufacturer = new JComboBox<String>();
        
        //initializing the combo box for selecting product options and populating it with the possible options
        selectINVProduct = new JComboBox<String>(PRODUCT_OPTIONS);
        selectINVProduct.setMaximumRowCount(PRODUCT_OPTIONS.length);
        
        selectINVProduct.addActionListener(
                new ActionListener()
                {
                  @Override
                  public void actionPerformed(ActionEvent event)
                  {
                      
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
        productPanel.setBorder(
                BorderFactory.createTitledBorder("Product Information"));
        
        //add all the components to the product panel
        productPanel.add(lblProductName);
        productPanel.add(txtProductName);
        productPanel.add(lblProductType);
        productPanel.add(txtProductType);
        productPanel.add(lblProductPrice);
        productPanel.add(txtProductPrice);
        productPanel.add(lblProductStock);
        productPanel.add(txtProductStock);
    }
    
    private void buildButtonPanel() 
    {
        //create the panel
        buttonPanel = new JPanel();
        btnCreate = new JButton("Create");
        btnExit = new JButton("Exit");
        btnExit.addActionListener(new ExitButtonHandler());
        buttonPanel.add(btnCreate);
        buttonPanel.add(btnExit);
    }
    
    //Handler for the create button
    private class CreateButtonHandler implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent event)
        {
            
        }
    }
    
    //Handler for the exit button
    private class ClearButtonHandler implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent event)
        {
            
        }
    }
    
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
        }
    }

    
}
