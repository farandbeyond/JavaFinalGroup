package BusinessJDBC;

import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 *
 * @author Lee_G
 */
public class Sale
{
    private int customerID, employeeNumber;
    private Date purchaseDate;
    private int productID;
    
    public Sale (int customerID, int employeeNumber, int productID)
    {
        this.customerID = customerID;
        this.employeeNumber = employeeNumber;
        this.purchaseDate = new GregorianCalendar().getTime();
        this.productID = productID;
    }
    
    public int getCustomerID()
    {
        return customerID;
    }
    
    public int getEmployeeNumber()
    {
        return employeeNumber;
    }
    
    public Date getPurchaseDate()
    {
        return purchaseDate;
    }
    
    public int getProductID()
    {
        return productID;
    }
    
    public void setProductID(int productID)
    {
        this.productID = productID;
    }
    public void setCustomerID(int customerID)
    {
        this.customerID = customerID;
    }
    
    public void setEmployeeNumber(int employeeNumber)
    {
        this.employeeNumber = employeeNumber;
    }
    
    public void setPurchaseDate(Date purchaseDate)
    {
        this.purchaseDate = purchaseDate;
    }
    
    @Override
    public String toString()
    {
        String salesReceipt = "";
        
        salesReceipt += "\nCustomer: \t\t\t" + getCustomerID();
        salesReceipt += "\nPurchase Date: \t\t" + getPurchaseDate();
        salesReceipt += "\nProducts: \n" + getProductID();
        salesReceipt += "\nEmployee Id: \t\t" + getEmployeeNumber();
        
        return salesReceipt;
    }
}
