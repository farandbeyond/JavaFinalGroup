/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finalassignment;

import java.util.Date;
import java.util.GregorianCalendar;

/**
 *
 * @author Lee_G
 */
public class Customer
{
     private String firstName, lastName, billingAddress, phoneNumber;
     private int customerID;
     private Date signUpDate;
     
     public Customer (String firstName, String lastName, String billingAddress, String phoneNumber, int customerID)
     {
         this.firstName = firstName;
         this.lastName = lastName;
         this.billingAddress = billingAddress;
         this.phoneNumber = phoneNumber;
         this.customerID = customerID;
         this.signUpDate = new GregorianCalendar().getTime();
     }
     
     public String getFirstName()
     {
         return firstName;
     }
     
     public String getLastName()
     {
         return lastName;
     }
     
     public String getBillingAddress()
     {
         return billingAddress;
     }
     
     public String getPhoneNumber()
     {
         return phoneNumber;
     }
     
     public int getCustomerID()
     {
         return customerID;
     }
     
     public Date getSignUpDate()
     {
         return signUpDate;
     }
     
     public String getFullName()
     {
         return firstName + " " + lastName;
     }
     
     public void setFirstName(String firstName)
     {
         this.firstName = firstName;
     }
     
     public void setLastName(String lastName)
     {
         this.lastName = lastName;
     }
     
     public void setBillingAddress(String billingAddress)
     {
         this.billingAddress = billingAddress;
     }
     
     public void setPhoneNumber(String phoneNumber)
     {
         this.phoneNumber = phoneNumber;
     }
     
     public void setCustomerID(int customerID)
     {
         this.customerID = customerID;
     }
     
    @Override
    public String toString()
    {
        String customerInformation = "";
        
        customerInformation += "\nName: \t\t\t" + getFullName();
        customerInformation += "\nCustomer ID: \t\t\t" + getCustomerID();
        customerInformation += "\nBilling Address: \t\t" + getBillingAddress();
        customerInformation += "\nPhone Number: \t\t" + getPhoneNumber();
        customerInformation += "\nSign Up Date: \t\t" + getSignUpDate();
        
        return customerInformation;
    }
}
