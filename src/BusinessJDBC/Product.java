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
    private String description;
    private int manufacturerID;
    private double cost;
    private double price;
    private int stock;
    private String productType;

    /**
     * product constructor
     * @param id
     * @param name
     * @param description
     * @param manufacturerID
     * @param cost
     * @param price
     * @param stock
     * @param productType 
     */
    public Product(int id, String name, String description, int manufacturerID, double cost, double price, int stock, String productType) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.manufacturerID = manufacturerID;
        this.cost = cost;
        this.price = price;
        this.stock = stock;
        this.productType = productType;
    }
    
    
    
    public int getID(){
        return id;
    }
    public String getDescirption(){
        return description;
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
     * returns product cost
     * @return 
     */
    public double getCost()
    {
        return this.cost;
    }
    
    /**
     * returns manufacturer information
     * @return 
     */
    public int getManufacturerID()
    {
        return this.manufacturerID;
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

    public void setDescription(String description) {
        this.description = description;
    }

    public void setManufacturerID(int manufacturerID) {
        this.manufacturerID = manufacturerID;
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
     * sets the product cost
     * @param productCost 
     */
    public void setProductCost(Double productCost)
    {
        this.cost = productCost;
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
        productInformation += "\nProduct Cost:\t\t$" + getCost();
        productInformation += "\nManufacturer ID:\t" + this.getManufacturerID();
        productInformation += "\nProduct Stock:\t\t" + getStock();
        
        return productInformation;
    }
}
