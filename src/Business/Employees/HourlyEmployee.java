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
public class HourlyEmployee extends Employee{
    int numberOfHours;
    double payRate;

    public HourlyEmployee() {
    }
    public HourlyEmployee(String firstName, String lastName) {
        super(firstName, lastName);
    }
    public HourlyEmployee(int numberOfHours, double payRate, String firstName, String lastName) {
        super(firstName, lastName);
        this.numberOfHours = numberOfHours;
        this.payRate = payRate;
    }
    public HourlyEmployee(int numberOfHours, double payRate, String firstName, String lastName, String gender, String address, 
            String email, String homePhoneNumber, String cellPhoneNumber, String sin, String position, String department, 
            int birthday, int birthmonth, int birthyear) {
        super(firstName, lastName, gender, address, email, homePhoneNumber, cellPhoneNumber, sin, position, department, birthday, birthmonth, birthyear);
        this.numberOfHours = numberOfHours;
        this.payRate = payRate;
    }

    public int getNumberOfHours() {
        return numberOfHours;
    }
    public double getPayRate() {
        return payRate;
    }
    public double getPay(){
        return numberOfHours*payRate;
    }

    public void setNumberOfHours(int numberOfHours) {
        this.numberOfHours = numberOfHours;
    }
    public void setPayRate(double payRate) {
        this.payRate = payRate;
    }
    
    
}
