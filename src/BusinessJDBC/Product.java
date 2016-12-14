package BusinessJDBC;
/**
 * This is the super class for the product constructor
 */
import java.util.*;
/**
 *
 * @author Lee_G
 */
public class Product 
{
    private int id;
    private String name;
    private String manufacturerName;
    private double price;
    private int stock;
    private String productType;

    /**
     * product constructor
     * @param name
     * @param manufacturerName
     * @param price
     * @param stock
     * @param productType 
     */
    public Product(String name, String manufacturerName, double price, int stock, String productType) {
        this.name = name;
        this.manufacturerName = manufacturerName;
        this.price = price;
        this.stock = stock;
        this.productType = productType;
    }
    
    
    
    public int getID(){
        return id;
    }

    public double getPrice(){
        return price;
    }
    
    /**
     * returns product name
     * @return 
     */
    public String getName()
    {
        return this.name;
    }
    
    /**
     * returns product type
     * @return 
     */
    public String getProductType()
    {
        return this.productType;
    }
    
    /**
     * returns manufacturer information
     * @return 
     */
    public String getManufacturerName()
    {
        return this.manufacturerName;
    }
    
    /**
     * returns the stock
     * @return 
     */
    public int getStock()
    {
        return this.stock;
    }

    public void setID(int id) {
        this.id = id;
    }

    public void setManufacturerName(String manufacturerName)
    {
        this.manufacturerName = manufacturerName;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    
    
    /**
     * sets the product name
     * @param productName 
     */
    public void setProductName(String productName)
    {
        this.name = productName;
    }
    
    /**
     * sets the product type
     * @param productType 
     */
    public void setProductType(String productType)
    {
        this.productType = productType;
    }
    /**
     * sets the stock
     * @param stock 
     */
    public void setStock (int stock)
    {
        this.stock = stock;
    }
    
    /**
     * this gets information on the product
     * @return 
     */
    @Override
    public String toString()
    {
        String productInformation = "";
        
        productInformation += "\nProduct Name:\t\t" + getName();
        productInformation += "\nProduct Type:\t\t" + getProductType();
        productInformation += "\nProduct Price:\t\t$" + getPrice();
        productInformation += "\nManufacturer Name:\t" + this.getManufacturerName();
        productInformation += "\nProduct Stock:\t\t" + getStock();
        
        return productInformation;
    }
}
