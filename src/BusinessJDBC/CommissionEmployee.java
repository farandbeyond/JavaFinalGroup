package Employee;

import java.util.*;

/**
 *
 * @author Lee_G
 */
public class CommissionEmployee extends Employee
{
    private double grossSales, commissionRate;
    
    /**
     * This is the Commission Sales Employee constructor which extends employee constructor
     * @param firstName
     * @param lastName
     * @param gender
     * @param phoneNumber
     * @param employeeAddress
     * @param employeeNumber
     * @param employeeSIN
     * @param year
     * @param month
     * @param day
     * @param title
     * @param commissionRate 
     */
    public CommissionEmployee(String firstName, String lastName, String gender, String phoneNumber, String employeeAddress, int employeeNumber, 
                                   int employeeSIN, int year, int month, int day, String title, double grossSales, double commissionRate)
    {
        super(firstName, lastName, gender, phoneNumber, employeeAddress, 
              employeeNumber, employeeSIN, year, month, day, title);
        
        this.commissionRate = commissionRate;
    }
    
    /**
     * returns the employee's commission rate
     * @return 
     */
    public double getCommissionRate()
    {
        return this.commissionRate;
    }
    
    /**
     * return the employee's gross sales
     * @return 
     */
    public double getGrossSales()
    {
        return this.grossSales;
    }
    
    /**
     * sets the employee's commission rate
     * @param commissionRate 
     */
    public void setCommissionRate(double commissionRate)
    {
        this.commissionRate = commissionRate;
    }
    
    /**
     * sets the employee's gross sales
     * @param grossSales 
     */
    public void setGrossSales(double grossSales)
    {
        this.grossSales = grossSales;
    }
    
    /**
     * Returns employee's information, name, age, title, employee number, 
     * hire date, commission rate and gross sales
     * @return 
     */
    @Override
    public String toString()
    {
        return super.toString() + "\nCommission Rate: \t\t" + getCommissionRate()
                + "\nGross Sales: \t\t" + getGrossSales();
    }
    
    /**
     * returns the employee's weekly salary
     * @return 
     */
    @Override
    public double weeklySalary()
    {
          return grossSales * commissionRate;
    }
}
