package Employee;

import java.util.*;

/**
 *
 * @author Lee_G
 */
public class HourlyEmployee extends Employee
{
    private double hourlyRate, hoursWorked;
    
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
     * @param hourlyRate 
     */
    public HourlyEmployee(String firstName, String lastName, String gender, String phoneNumber, String employeeAddress, int employeeNumber, 
                                   int employeeSIN, int year, int month, int day, String title, double hourlyRate, double hoursWorked)
    {
        super(firstName, lastName, gender, phoneNumber, employeeAddress, 
              employeeNumber, employeeSIN, year, month, day, title);
        
        this.hourlyRate = hourlyRate;
        this.hoursWorked = hoursWorked;
    }
    
    /**
     * returns the employee's hourly rate
     * @return 
     */
    public double getHourlyRate()
    {
        return this.hourlyRate;
    }
    
    /**
     * return the employee's hours worked
     * @return 
     */
    public double getHoursWorked()
    {
        return this.hoursWorked;
    }
    
    /**
     * sets the employee's commission rate
     * @param hourlyRate
     */
    public void setHourlyRate(double hourlyRate)
    {
        this.hourlyRate = hourlyRate;
    }
    
    /**
     * sets the employee's gross sales
     * @param hoursWorked
     */
    public void setHoursWorked(double hoursWorked)
    {
        this.hoursWorked = hoursWorked;
    }
    
    /**
     * Returns employee's information, name, age, title, employee number, 
     * hire date, hourly rate, hours worked
     * @return 
     */
    @Override
    public String toString()
    {
        return super.toString() + "\nHourly Rate: \t\t" + getHourlyRate()
                + "\nHours Worked: \t\t" + getHoursWorked();
    }
    
    /**
     * returns the employee's weekly salary
     * @return 
     */
    @Override
    public double weeklySalary()
    {
          return this.hourlyRate * this.hoursWorked;
    }
}
