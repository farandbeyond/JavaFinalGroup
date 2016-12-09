/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business;

import Business.Employees.*;
import Business.Products.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Connor
 */
public class BackOffice {
    public static void main(String[] args) {
        Scanner kb;
        kb = new Scanner(System.in);
        int input;
        boolean keepGoing = true;
        //storage variable initilizations
        ArrayList<Employee> employees = new ArrayList<>();
        ArrayList<Product> products = new ArrayList<>();
        ArrayList<Manufacturer> manufacturers = new ArrayList<>();
        //default product/manufacturer list additions
        //                      id name     description            supplierid   cost price stock
        products.add(new Product(0,"Milk","1L 2% White Milk",       0,          4.00, 4.50,  6));
        products.add(new Product(1,"Milk","1L 2% Chocolate Milk",   0,          4.50, 5.50,  10));
        products.add(new Product(2,"Milk","1L 1% White Milk",       0,          4.00, 4.50,  4));
        //                                 id   name       branch number    representative name
        manufacturers.add(new Manufacturer(ServiceClass.getManufacturerID(),   "Milk and CO.",44,          "Chris"));
        //BackOffice run code
        ServiceClass.welcomeMessage();
        while(keepGoing){
            //1. add new employee 2. view employees 3. view products 4. exit
            ServiceClass.mainMenu();
            switch(ServiceClass.getUserInt(kb)){
                //new Employee
                case 1:
                     ServiceClass.selectEmployeeType();
                     //1. Salary 2. Hourly 3. Commission 4. BaseCommission
                     switch(ServiceClass.getUserInt(kb)){
                         //Salary
                         case 1:
                             String firstName1 = ServiceClass.getUserString("First Name", kb);
                             String lastName1 = ServiceClass.getUserString("Last Name", kb);
                             double salary1 = ServiceClass.getUserDouble("Salary", kb);
                             if(ServiceClass.keepGoing(kb)){
                                 String gender = ServiceClass.getUserString("Gender", kb);
                                 String address = ServiceClass.getUserString("Address", kb);
                                 String email = ServiceClass.getUserString("Email", kb);
                                 String homePhoneNum = ServiceClass.getUserString("Home Phone Number", kb);
                                 String cellPhoneNum = ServiceClass.getUserString("Cell Phone Number", kb);
                                 String position = ServiceClass.getUserString("Work Position", kb);
                                 String department = ServiceClass.getUserString("Department", kb);
                                 String sin = ServiceClass.getUserString("SIN number", kb);
                                 int dayOfBirth = ServiceClass.getUserInt("Day of Birth", kb);
                                 int monthOfBirth = ServiceClass.getUserInt("Month of Birth", kb);
                                 int yearOfBirth = ServiceClass.getUserInt("Year of Birth", kb);
                                 employees.add(new SalaryEmployee(salary1, firstName1, lastName1,gender,address,email,homePhoneNum, cellPhoneNum,sin,position,department,dayOfBirth,monthOfBirth,yearOfBirth));
                             }else{
                                 employees.add(new SalaryEmployee(salary1,firstName1, lastName1));
                                 ServiceClass.employeeNotComplete();
                             }break;
                             //Hourly
                         case 2:
                             String firstName2 = ServiceClass.getUserString("First Name", kb);
                             String lastName2 = ServiceClass.getUserString("Last Name", kb);
                             double payRate = ServiceClass.getUserDouble("Rate of Pay", kb);
                             int numberOfHours = ServiceClass.getUserInt("Number of Hours", kb);
                             if(ServiceClass.keepGoing(kb)){
                                 String gender = ServiceClass.getUserString("Gender", kb);
                                 String address = ServiceClass.getUserString("Address", kb);
                                 String email = ServiceClass.getUserString("Email", kb);
                                 String homePhoneNum = ServiceClass.getUserString("Home Phone Number", kb);
                                 String cellPhoneNum = ServiceClass.getUserString("Cell Phone Number", kb);
                                 String position = ServiceClass.getUserString("Work Position", kb);
                                 String department = ServiceClass.getUserString("Department", kb);
                                 String sin = ServiceClass.getUserString("SIN number", kb);
                                 int dayOfBirth = ServiceClass.getUserInt("Day of Birth", kb);
                                 int monthOfBirth = ServiceClass.getUserInt("Month of Birth", kb);
                                 int yearOfBirth = ServiceClass.getUserInt("Year of Birth", kb);
                                 employees.add(new HourlyEmployee(numberOfHours,payRate, firstName2, lastName2,gender,address,email,homePhoneNum, cellPhoneNum,sin,position,department,dayOfBirth,monthOfBirth,yearOfBirth));
                             }else{
                                 employees.add(new HourlyEmployee(numberOfHours,payRate,firstName2, lastName2));
                                 ServiceClass.employeeNotComplete();
                             }break;
                             //Commission
                         case 3:
                             String firstName3 = ServiceClass.getUserString("First Name", kb);
                             String lastName3 = ServiceClass.getUserString("Last Name", kb);
                             double commissionRate = ServiceClass.getUserDouble("Rate of Commission", kb);
                             if(ServiceClass.keepGoing(kb)){
                                 String gender = ServiceClass.getUserString("Gender", kb);
                                 String address = ServiceClass.getUserString("Address", kb);
                                 String email = ServiceClass.getUserString("Email", kb);
                                 String homePhoneNum = ServiceClass.getUserString("Home Phone Number", kb);
                                 String cellPhoneNum = ServiceClass.getUserString("Cell Phone Number", kb);
                                 String position = ServiceClass.getUserString("Work Position", kb);
                                 String department = ServiceClass.getUserString("Department", kb);
                                 String sin = ServiceClass.getUserString("SIN number", kb);
                                 int dayOfBirth = ServiceClass.getUserInt("Day of Birth", kb);
                                 int monthOfBirth = ServiceClass.getUserInt("Month of Birth", kb);
                                 int yearOfBirth = ServiceClass.getUserInt("Year of Birth", kb);
                                 employees.add(new CommissionSalesEmployee(commissionRate, firstName3, lastName3,gender,address,email,homePhoneNum, cellPhoneNum,sin,position,department,dayOfBirth,monthOfBirth,yearOfBirth));
                             }else{
                                 employees.add(new SalaryEmployee(commissionRate,firstName3, lastName3));
                                 ServiceClass.employeeNotComplete();
                             }break;
                             //Commission + Base
                         case 4:
                             String firstName4 = ServiceClass.getUserString("First Name", kb);
                             String lastName4 = ServiceClass.getUserString("Last Name", kb);
                             double salary4 = ServiceClass.getUserDouble("Salary", kb);
                             double commission2 = ServiceClass.getUserDouble("Rate of Commission", kb);
                             if(ServiceClass.keepGoing(kb)){
                                 String gender = ServiceClass.getUserString("Gender", kb);
                                 String address = ServiceClass.getUserString("Address", kb);
                                 String email = ServiceClass.getUserString("Email", kb);
                                 String homePhoneNum = ServiceClass.getUserString("Home Phone Number", kb);
                                 String cellPhoneNum = ServiceClass.getUserString("Cell Phone Number", kb);
                                 String position = ServiceClass.getUserString("Work Position", kb);
                                 String department = ServiceClass.getUserString("Department", kb);
                                 String sin = ServiceClass.getUserString("SIN number", kb);
                                 int dayOfBirth = ServiceClass.getUserInt("Day of Birth", kb);
                                 int monthOfBirth = ServiceClass.getUserInt("Month of Birth", kb);
                                 int yearOfBirth = ServiceClass.getUserInt("Year of Birth", kb);
                                 employees.add(new BasePlusCommissionSalesEmployee(salary4,commission2, firstName4, lastName4,gender,address,email,homePhoneNum, cellPhoneNum,sin,position,department,dayOfBirth,monthOfBirth,yearOfBirth));
                             }else{
                                 employees.add(new BasePlusCommissionSalesEmployee(salary4,commission2, firstName4, lastName4));
                                 ServiceClass.employeeNotComplete();
                             }break;
                         case 5:
                             break;
                     }break;
                    //View Employees
                case 2:
                    ServiceClass.openEmployeeMenu();
                    boolean keepGoingEmployee = true;
                    while(keepGoingEmployee){
                        ServiceClass.viewEmployeeMenu();
                        String search = kb.nextLine();
                        //exit string
                        if(search.equalsIgnoreCase("x0")){
                            keepGoingEmployee = false;
                        }else{
                            int numberOfResults = 0;
                            try{
                                //if the result is a number
                                int searchID = Integer.parseInt(search);
                                for(Employee em:employees){
                                    if(em.getID()==searchID){
                                        try{
                                             System.out.println(em.toString());
                                        }catch(NullPointerException ex){
                                             System.out.println(em.basicToString());
                                        }
                                        numberOfResults++;
                                    }
                                }
                            }catch(NumberFormatException e){
                                //if the result is not a number
                                for(Employee em:employees){
                                    NameSearcher:
                                    for(int i=0;i<em.getFullName().length()-search.length();i++){
                                        if(em.getFullName().regionMatches(true,i,search, 0,search.length())){
                                            try{
                                                 System.out.println(em.toString());
                                            }catch(NullPointerException ex){
                                                 System.out.println(em.basicToString());
                                            }
                                            numberOfResults++;
                                            //the break is to prevent redundant data from showing up
                                            //eg. Anna Anderson, with a search string of "an" would print data twice
                                            //the break stops the search loop after one success
                                            System.out.println("-------------");
                                            break NameSearcher;
                                        }
                                    }
                                }
                            }
                            System.out.println("Found "+numberOfResults+" in the list");
                            System.out.println("");
                        }
                    }break;
                     //View Products
                 case 3:
                    
                    ServiceClass.openProductsMenu();
                    boolean keepGoingProducts = true;
                    while(keepGoingProducts){
                        ServiceClass.viewProductsMenu();
                        System.out.print("");//this is hopefully fixing the error with newLine char and Scanner.nextLine()
                        //kb.next();
                        //this is bugged. i dont know why this is bugged. it is the exact same as it is above in employees
                        //the findProduct variable is never set to kb.nextLine, and is always empty
                        String findProduct = kb.nextLine();
                        if(findProduct.equalsIgnoreCase("x0")){
                            keepGoingProducts = false;
                        }else{
                            int numberOfResults = 0;
                            try{
                                //if the result is a number
                                int searchID = Integer.parseInt(findProduct);
                                for(Product p:products){
                                    if(p.getID()==searchID){
                                        System.out.println("-------------");
                                        System.out.println(p.toString());
                                        if(ServiceClass.getSupplierInfo(kb)){
                                            System.out.println(manufacturers.get(p.getManufacturerID()).toString());
                                            System.out.println("-------------");
                                        }
                                        numberOfResults++;
                                    }
                                }
                            }catch(NumberFormatException e){
                                //if the result is not a number
                                for(Product p:products){
                                    ProductSearcher:
                                    for(int i=0;i<p.getName().length()+p.getDescription().length()+1-findProduct.length();i++){
                                        if((p.getName()+" "+p.getDescription()).regionMatches(true,i,findProduct, 0,findProduct.length())){
                                            System.out.println(p.toString());
                                            numberOfResults++;
                                            //the break is to prevent redundant data from showing up
                                            //eg. White Milk, with a search string of "i" would print data twice
                                            //the break stops the search loop after one success
                                            System.out.println("-------------");
                                            if(ServiceClass.getSupplierInfo(kb)){
                                                System.out.println(manufacturers.get(p.getManufacturerID()).toString());
                                                System.out.println("-------------");
                                            }
                                            break ProductSearcher;
                                        }
                                    }//end of the product searcher loop
                                }
                            }
                            System.out.println("Found "+numberOfResults+" in the list");
                            System.out.println("");
                        }
                    }break; //end of product view menu
                   //Exit
                case 4:keepGoing = false;
            }
        }
        ServiceClass.departMessage();
    
    }
}
