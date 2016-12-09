package Employee;
/**
 * This is the super class for the employee constructor
 */
import java.util.*;


/**
 *
 * @author Lee_G
 */
public abstract class Employee 
{
    private String firstName, lastName, gender, phoneNumber, 
            employeeAddress, title, employeeStatus;
    private int employeeNumber, employeeSIN;
    private Date hireDate;
    private GregorianCalendar dateOfBirth;
    private double weeklySalary;

    /**
     * This is the employee constructor
     * @param firstName
     * @param lastName
     * @param gender
     * @param phoneNumber
     * @param employeeAddress
     * @param employeeNumber
     * @param employeeSIN
     * @param year
     * @param month
     * @param title 
     */
    public Employee(String firstName, String lastName, String gender, String phoneNumber, String employeeAddress, 
                    int employeeNumber, int employeeSIN, int year, int month, int day, String title)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.employeeAddress = employeeAddress;
        this.phoneNumber = phoneNumber;
        this.employeeNumber = employeeNumber;
        this.employeeSIN = employeeSIN;
        
        this.dateOfBirth = new GregorianCalendar();
        this.dateOfBirth.set(Calendar.YEAR, year);
        this.dateOfBirth.set(Calendar.MONTH, month - 1);
        this.dateOfBirth.set(Calendar.DATE, day);
        
        this.hireDate = new GregorianCalendar().getTime();
        this.title = title;
    }
    
    /**
     * Returns the Employee's first name
     * @return 
     */
    public String getFirstName()
    {
        return this.firstName;
    }
    
    /**
     * returns the Employee's last name
     * @return 
     */
    public String getLastName()
    {
        return this.lastName;
    }
    
    /**
     * returns the employee's full name
     * @return 
     */
    public String getFullName()
    {
        return this.firstName + " " + this.lastName;
    }
    
    /**
     * Returns the gender of the Employee
     * @return 
     */
    public String getGender()
    {
        return this.gender;
    }
    
    /**
     * This returns the employees phone number
     * @return 
     */
    public String getPhoneNumber()
    {
        return this.phoneNumber;
    }
    
    /**
     * this returns the employees address
     * @return 
     */
    public String getEmployeeAddress()
    {
        return this.employeeAddress;
    }
    
    /**
     * this returns the employees SIN
     * @return 
     */
    public int getEmployeeSIN()
    {
        return this.employeeSIN;
    }
    
    /**
     * gets the date the employee was hired
     * @return 
     */
    public Date getDateHired()
    {
        return this.hireDate;
    }
    
    /**
     * this returns the employee's birthday
     * @return 
     */
    public Date getBirthday()
    {
        Date currentDate = new GregorianCalendar().getTime();
        
        if (dateOfBirth == null)
        {
            return currentDate;
        } else return dateOfBirth.getTime();
    }
    
    /**
     * this returns the employee's age
     * @return 
     */
    public int getAge()
    {
        Calendar now = Calendar.getInstance();
       if(this.dateOfBirth.get(Calendar.DAY_OF_YEAR) > now.get(Calendar.DAY_OF_YEAR))
       {
        return now.get(Calendar.YEAR) - this.dateOfBirth.get(Calendar.YEAR);
       } else return now.get(Calendar.YEAR) - this.dateOfBirth.get(Calendar.YEAR) - 1;
    }
    
    /**
     * returns title of employee
     * @return 
     */
    public String getTitle()
    {
        return this.title;
    }
    
    /**
     * returns the status of the employee
     * @return 
     */
    public String getEmployeeStatus()
    {
        return this.employeeStatus;
    }
    
    /**
     * This returns how many years since the employee was hired
     * @return 
     */
    public int getYearsHired()
    {
        GregorianCalendar currentDate = new GregorianCalendar();
        int currentYear = currentDate.get(Calendar.YEAR);
        int yearHired = hireDate.getYear();
        int yearsHired = currentYear - yearHired;
        
        return yearsHired;
    }

    /**
     * returns the employee's earnings
     * @return 
     */
    public double getWeeklySalary()
    {
        return this.weeklySalary;
    }
    
    /**
     * return the employee's employee Number
     * @return 
     */
    public int getEmployeeNumber()
    {
        return this.employeeNumber;
    }
    
    /**
     * Sets the employee's first name
     * @param firstName
     */
    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }
    
    /**
     * Sets the employee's last name
     * @param lastName 
     */
    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }
    
    /**
     * Sets the employees gender
     * @param gender 
     */
    public void setGender(String gender)
    {
        this.gender = gender;
    }
    
    /**
     * Sets the birthday of the employee
     * @param year
     * @param month
     * @param day 
     */
    public void setBirthday(int year, int month, int day)
    {
       this.dateOfBirth = new GregorianCalendar();
       this.dateOfBirth.set(Calendar.YEAR, year);
       this.dateOfBirth.set(Calendar.MONTH, month);
       this.dateOfBirth.set(Calendar.DATE, day);
    }
    
    /**
     * Sets the hire date of the employee
     * @param year
     * @param month
     * @param day 
     */
    public void setHireDate(int year, int month, int day)
    {
        GregorianCalendar newDate = new GregorianCalendar();
        newDate.set(Calendar.YEAR, year);
        newDate.set(Calendar.MONTH, month - 1);
        newDate.set(Calendar.DATE, day);
        
        this.hireDate = newDate.getTime();
    }
    
    /**
     * this sets the employees number
     * @param employeeNumber 
     */
    public void setEmployeeNumber(int employeeNumber)
    {
        this.employeeNumber = employeeNumber;
    }
    
    /**
     * This sets the employee's phone number
     * @param employeePhoneNumber 
     */
    public void setPhoneNumber(String employeePhoneNumber)
    {
        this.phoneNumber = employeePhoneNumber;
    }
    
    /**
     * this sets the employee's address
     * @param employeeAddress 
     */
    public void setAddress(String employeeAddress)
    {
        this.employeeAddress = employeeAddress;
    }
    
    /**
     * this sets the employee's SIN
     * @param employeeSIN 
     */
    public void setEmployeeSIN(int employeeSIN)
    {
        this.employeeSIN = employeeSIN;
    }
    
    /**
     * this sets the employee's title
     * @param title
     */
    public void setTitle(String title)
    {
        this.title = title;
    }
    
    /**
     * Sets the status of the employee
     * @param employeeStatus 
     */
    public void setEmployeeStatus(String employeeStatus)
    {
        this.employeeStatus = employeeStatus;
    }
    
    /**
     * Returns employee's information, name, age, title, employee number, hire date
     * @return 
     */
    @Override
    public String toString()
    {
        String employeeInformation = "";
        
        employeeInformation += "\nName: \t\t\t" + getFullName();
        employeeInformation += "\nAge: \t\t\t" + getAge();
        employeeInformation += "\nPosition: \t\t" + getTitle();
        employeeInformation += "\nEmployee Id: \t\t" + getEmployeeNumber();
        employeeInformation += "\nHire Date: \t\t" + getDateHired();
        
        return employeeInformation;
    }
    
    public abstract double weeklySalary();
}