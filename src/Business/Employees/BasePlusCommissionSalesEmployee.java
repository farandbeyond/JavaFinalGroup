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
public class BasePlusCommissionSalesEmployee extends CommissionSalesEmployee {
    double salary;
    public BasePlusCommissionSalesEmployee() {
    }
    public BasePlusCommissionSalesEmployee(String firstName, String lastName) {
        super(firstName, lastName);
    }
    public BasePlusCommissionSalesEmployee(double salary, double rateOfPay, String firstName, String lastName){
        super(rateOfPay, firstName, lastName);
        this.salary = salary;
    }
    public BasePlusCommissionSalesEmployee(double salary,double commissionRate,String firstName, String lastName, String gender, String address, String email, String homePhoneNum, String cellPhoneNum, String sin, String position, String department, int birthday, int birthmonth, int birthyear) {
        super(commissionRate,firstName, lastName, gender, address, email, homePhoneNum, cellPhoneNum, sin, position, department,birthday, birthmonth, birthyear);
        this.salary = salary;
    }
    public double getSalary(){return salary;}
    
    public void setSalary(double salary){this.salary = salary;}
    
    public String toString(){
        return super.toString()+String.format("\nMakes %f base",getSalary());
    }
}
