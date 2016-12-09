/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business;

import java.util.Scanner;

/**
 *
 * @author Connor
 */
public class ServiceClass {
    private static int lastEmployeeID = 0;
    private static int lastManufacturerID = 0;
    public static int getNextID(){
        lastEmployeeID++;
        return lastEmployeeID;
    }
    public static int getManufacturerID(){
        int id = lastManufacturerID;
        lastManufacturerID++;
        return id;
    }
    public static void welcomeMessage(){
        System.out.println("Welcome to BackOffice");
    }
    public static void mainMenu(){
        System.out.println("Select an Option: \n"
                + "1. Add employee to Database\n"
                + "2. Search your employees\n"
                + "3. Search your products\n"
                + "4. Exit");
    }
    public static void departMessage(){
       System.out.println("Have a nice day!");
    }
    public static void inputMessage(String inputName){
        System.out.println("Please input "+inputName+":");
    }
    public static void invalidMessage(){
        System.out.println("Invalid Input!");
    }
    public static void selectEmployeeType(){
        System.out.println("Adding an employee to the list.\n"
                + "Which employee type will be added?\n"
                + "1. Salary Employee\n"
                + "2. Hourly Employee\n"
                + "3. Commission Employee\n"
                + "4. Base+Commission Employee\n"
                + "5. Exit");
    }
    public static void employeeNotComplete(){
        System.out.println("Employee created, but some fields not completed\n"
                + "You can add remaining details in the view employee's menu");
    }
    public static void openEmployeeMenu(){
        System.out.println("This is the Employees menu");
    }
    public static void viewEmployeeMenu(){
        System.out.println("Type a name or id to search the list\n"
                + "type x0 to return to the main menu");
    }
    public static void openProductsMenu(){
        System.out.println("This is the Products menu");
    }
    public static void viewProductsMenu(){
        System.out.println("Type a name or id to search the list\n"
                + "type x0 to return to the main menu");
    }
    
    public static String getUserString(String varName, Scanner kb){
        String data;
        do{
            inputMessage(varName);
            if(checkInputForInvalid(data = kb.nextLine())){
                invalidMessage();
            }else{
                return data;
            }
        }while(true);
    }
    public static int getUserInt(String varName, Scanner kb){
        int data;
        do{
            try{
                inputMessage(varName);
                if(checkInputForInvalid(data = Integer.parseInt(kb.nextLine()))){
                    invalidMessage();
                }else{
                    return data;
                }
            }catch(NumberFormatException e){
                System.out.println("Invalid Input");
            }
        }while(true);
    }
        public static int getUserInt(Scanner kb){
        int data;
        do{
            try{
                if(checkInputForInvalid(data = Integer.parseInt(kb.nextLine()))){
                    invalidMessage();
                }else{
                    return data;
                }
            }catch(NumberFormatException e){
                System.out.println("Invalid Input");
            }
        }while(true);
    }
    
    public static double getUserDouble(String varName, Scanner kb){
        double data;
        do{
            inputMessage(varName);
            try{
                if(checkInputForInvalid(data = Double.parseDouble(kb.nextLine()))){
                    invalidMessage();
                }else{
                    return data;
                }
            }catch(NumberFormatException e){
                System.out.println("Invalid Input");
            }
        }while(true);
    }
    public static boolean keepGoing(Scanner kb){
        System.out.println("Would you like to continue inputting data?\n"
                + "1. Yes\n"
                + "2. No");
        try{
            return Integer.parseInt(kb.nextLine()) == 1;
        }catch(NumberFormatException e){
            System.out.println("Invalid input, defaulting to false");
            return false;
        }
    }    
    public static boolean getSupplierInfo(Scanner kb){
        System.out.println("Would you like to see supplier data from this entry?\n"
                + "1. Yes\n"
                + "2. No");
        try{
            return Integer.parseInt(kb.nextLine()) == 1;
        }catch(NumberFormatException e){
            System.out.println("Invalid input, defaulting to false");
            return false;
        }
    } 
    public static boolean checkInputForInvalid(String data){
        data = data.trim();
        return data.isEmpty();
    }
    public static boolean checkInputForInvalid(int data){
        //all int's must be between 1 and 9999
        return data<0 || data>10000;
    }
    public static boolean checkInputForInvalid(double data){
        //all doubles must be between 1.01 and 9999.99
        return data<1.00 || data>10000.00;
    }
    
    
}
