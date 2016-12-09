/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.Employees;

import Business.ServiceClass;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 *
 * @author Connor
 */
public abstract class Employee {
    private int employeeID;
    private String firstName;
    private String lastName;
    @Deprecated
    private double wage;
    private String gender;
    private String address;
    private String email;
    private String homePhoneNumber;
    private String cellPhoneNumber;
    private String sin;
    private String position;
    private String department;
    private GregorianCalendar dateOfBirth;
    private GregorianCalendar dateOfHire;
    Employee(){
        
    }
    Employee(String firstName, String lastName){
        this.employeeID = ServiceClass.getNextID();
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Employee(String firstName, String lastName,String gender, String address, String email, 
            String homePhoneNumber, String cellPhoneNumber, String sin, 
            String position, String department, int birthday, int birthmonth, int birthyear) {
        //this.employeeID = employeeID;
        this.employeeID = ServiceClass.getNextID();
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.address = address;
        this.email = email;
        this.homePhoneNumber = homePhoneNumber;
        this.cellPhoneNumber = cellPhoneNumber;
        this.sin = sin;
        this.position = position;
        this.department = department;
        dateOfHire = new GregorianCalendar();
        Date now = new GregorianCalendar().getTime();
        dateOfHire.set(now.getDay(),now.getMonth()-1,now.getYear());
        dateOfBirth = new GregorianCalendar();
        dateOfBirth.set(Calendar.YEAR, birthyear);
        dateOfBirth.set(Calendar.MONTH, birthmonth-1);
        dateOfBirth.set(Calendar.DATE, birthday);
    }
    
    @Deprecated
    public double getSalary(int hours){
        return wage*hours;
    }
    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public String getFullName(){
        return firstName+" "+lastName;
    }
    public int getID() {
        return employeeID;
    }
    public String getDepartment() {
        return department;
    }
    public String getPosition() {
        return position;
    }
    public String getGender() {
        return gender;
    }
    public String getAddress() {
        return address;
    }
    public String getEmail() {
        return email;
    }
    public String getHomePhoneNumber() {
        return homePhoneNumber;
    }
    public String getCellPhoneNumber() {
        return cellPhoneNumber;
    }
    public String getSin() {
        return sin;
    }
    @Deprecated
    public double getWage() {
        return wage;
    }

    public GregorianCalendar getDateOfBirth() {
        return dateOfBirth;
    }

    public GregorianCalendar getDateOfHire() {
        return dateOfHire;
    }
    
    
    public void setName(String firstName, String lastName){
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setHomePhoneNumber(String homePhoneNumber) {
        this.homePhoneNumber = homePhoneNumber;
    }
    public void setCellPhoneNumber(String cellPhoneNumber) {
        this.cellPhoneNumber = cellPhoneNumber;
    }
    public void setSin(String sin) {
        this.sin = sin;
    }
    @Deprecated
    public void setWage(double wage) {
        this.wage = wage;
    }
    public void setDepartment(String department) {
        this.department = department;
    }
    public void setPosition(String position) {
        this.position = position;
    }
    public void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
    }

    public void setDateOfBirth(int day, int month, int year) {
        this.dateOfBirth.set(day, month-1, year);
    }
    public void setDateOfHire(int day, int month, int year) {
        this.dateOfHire.set(day,month-1,year);
    }
    
    //returns a basic, 1 line string, of the employee's id and name
    public String basicToString(){
        return String.format("ID: %d; Name: %s %s",getID(),getFirstName(),getLastName());
    }
    public String toString(){
        return String.format("ID: %d\n%s %s, %s\n%s\n%s in %s\nlives at: %s, call at: %s or %s",
getID(),getFirstName(),getLastName(),getPosition(),getGender(),getPosition(),getDepartment(),getAddress(),getHomePhoneNumber(),getCellPhoneNumber());
    }
    
}
