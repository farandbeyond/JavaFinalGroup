package Employee;

import java.util.*;

/**
 *
 * @author Lee_G
 */
public class SalaryEmployee extends Employee
{
    private double yearlySalary;
    
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
     * @param yearlySalary
     */
    public SalaryEmployee(String firstName, String lastName, String gender, String phoneNumber, String employeeAddress, int employeeNumber, 
                                   int employeeSIN, int year, int month, int day, String title, double yearlySalary)
    {
        super(firstName, lastName, gender, phoneNumber, employeeAddress, 
              employeeNumber, employeeSIN, year, month, day, title);
        
        this.yearlySalary = yearlySalary;
    }
    
    /**
     * returns the employee's salary
     * @return 
     */
    public double getYearlySalary()
    {
        return this.yearlySalary;
    }
    
    /**
     * Returns employee's information, name, age, title, employee number, 
     * hire date, and yearly salary
     * @return 
     */
    @Override
    public String toString()
    {
        return super.toString() + "\nHourly Rate: \t\t" + this.getYearlySalary();
    }
    
    /**
     * returns the employee's weekly salary
     * @return 
     */
    @Override
    public double weeklySalary()
    {
          return this.yearlySalary / 52;
    }
}
