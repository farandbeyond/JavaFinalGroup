/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.Employees;

/**
 *
 * @author Connor
 */
public class SalaryEmployee extends Employee{
    double salary;

    public SalaryEmployee() {
    }
    public SalaryEmployee(String firstName, String lastName) {
        super(firstName, lastName);
    }
    public SalaryEmployee(double salary, String firstName, String lastName) {
        super(firstName, lastName);
        this.salary = salary;
    }
    public SalaryEmployee(double salary, String firstName, String lastName, String gender, String address, String email, String homePhoneNumber, 
            String cellPhoneNumber, String sin, String position, String department, int birthday, int birthmonth, int birthyear) {
        super(firstName, lastName,gender, address, email, homePhoneNumber, cellPhoneNumber, sin, position, department, birthday, birthmonth, birthyear);
        this.salary = salary;
    }

    public double getSalary() {
        return salary;
    }
    public double getPay(int hours){
        return salary*hours;
    }
    
    public void setSalary(double salary) {
        this.salary = salary;
    }
    
    
            
}
