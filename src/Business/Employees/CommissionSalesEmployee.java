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
public class CommissionSalesEmployee extends Employee{
    double commissionRate, totalSales;
    public CommissionSalesEmployee() {
    }
    public CommissionSalesEmployee(String firstName, String lastName) {
        super(firstName, lastName);
    }
    public CommissionSalesEmployee(double commissionRate, String firstName, String lastName) {
        super(firstName, lastName);
        this.commissionRate = commissionRate;
    }
    public CommissionSalesEmployee(double commissionRate, String firstName, String lastName, String gender, String address, String email, String homePhoneNum, 
            String cellPhoneNum, String sin, String position, String department,
            int birthday, int birthmonth, int birthyear) {
        super(firstName, lastName, gender, address, email, homePhoneNum, cellPhoneNum, sin, position, department,birthday, birthmonth, birthyear);
        this.commissionRate = commissionRate;
        this.totalSales = 0;
    }

    public double getCommissionRate() {return commissionRate;}
    public double getTotalSales() {return totalSales;}
    
    
    public void setCommissionRate(double commissionRate) {this.commissionRate = commissionRate;}
    public void setTotalSales(double totalSales) {this.totalSales = totalSales;}
    public void clearSales(){totalSales = 0;}
    
    public String toString(){
        return super.toString()+String.format("\nCommission rate of %f\nCurrent sales of %f",getCommissionRate(),getTotalSales());
    }
}
