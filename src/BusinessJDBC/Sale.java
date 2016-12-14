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
    private ArrayList<Product> productList;
    
    public Sale (int customerID, int employeeNumber, ArrayList<Product> productList)
    {
        this.customerID = customerID;
        this.employeeNumber = employeeNumber;
        this.purchaseDate = new GregorianCalendar().getTime();
        this.productList = productList;
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
    
    public String getProductList()
    {
        String products = "";
        
        for(Product p: productList)
        {
            products = products + p.getName() + "\t " + p.getPrice() + "\n";
        }
        
        return products;
    }
    
    public double getTotalCost()
    {
        double total = 0;
        
        for(Product p: productList)
        {
            total += p.getCost();
        }
        
        return total;
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
    
    public void addProduct (Product product)
    {
        productList.add(product);
    }
    
    public void removeProduct (Product product)
    {
        productList.remove(product);
    }
    
    @Override
    public String toString()
    {
        String salesReceipt = "";
        
        salesReceipt += "\nCustomer: \t\t\t" + getCustomerID();
        salesReceipt += "\nPurchase Date: \t\t" + getPurchaseDate();
        salesReceipt += "\nProducts: \n" + getProductList();
        salesReceipt += "\nTotal Cost: \t\t$" + getTotalCost();
        salesReceipt += "\nEmployee Id: \t\t" + getEmployeeNumber();
        
        return salesReceipt;
    }
}
