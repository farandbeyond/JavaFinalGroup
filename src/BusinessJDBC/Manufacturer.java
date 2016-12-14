/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessJDBC;

import java.util.ArrayList;

/**
 *
 * @author Lee_G
 */
public class Manufacturer 
{
    //for(Product p:productList){
    
    private int id;
    private String name, contactNumber, address;

    public Manufacturer (String manufacturerName, String manufacturerContactNumber, String manufacturerAddress)
    {
        this.name = manufacturerName;
        this.contactNumber = manufacturerContactNumber;
        this.address = manufacturerAddress;
    }
    /**
     * returns the manufacturer's name
     * @return 
     */
    public String getName()
    {
        return this.name;
    }
    /**
     * returns manufacturer's contact number
     * @return 
     */
    public String getContactNumber()
    {
        return this.contactNumber;
    }
    /**
     * this gets the manufacturer's address
     * @return 
     */
    public String getAddress()
    {
        return this.address;
    }

    public int getId() {
        return id;
    }

    
    
    
    /**
     * this sets the manufacturer name
     * @param manufacturerName 
     */
    public void setName(String manufacturerName)
    {
        this.name = manufacturerName;
    }
    /**
     * this sets the manufacturer's contact number
     * @param manufacturerContactNumber 
     */
    public void setContactNumber(String manufacturerContactNumber)
    {
        this.contactNumber = manufacturerContactNumber;
    }
    /**
     * this sets the manufacturer's address
     * @param manufacturerAddress
     */
    public void setAddress(String manufacturerAddress)
    {
        this.address = manufacturerAddress;
    }

    public void setId(int id) {
        this.id = id;
    }

    
    /**
     * Returns information about the manufacturer, name, contact, address
     * @return 
     */
    @Override
    public String toString()
    {
        String manufacturerInformation = "";
        
        manufacturerInformation += "\nManufacturer Name:\t\t" + getName();
        manufacturerInformation += "\nManufacturer Contact:\t\t" + getContactNumber();
        manufacturerInformation += "\nManufacturer Address:\t\t" + getAddress();
                
        return manufacturerInformation;
    }
}
