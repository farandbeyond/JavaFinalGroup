/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.Products;

/**
 *
 * @author Connor
 */
public class Product {
    private int id;
    private String name;
    private String description;
    private int supplierID;
    private double cost;
    private double price;
    private int stock;

    public Product() {
    }
    public Product(int id, double cost){
        this.id = id;
        this.cost = cost;
    }
    public Product(int id, String name, String description, int supplierID, double cost, 
            double price, int stock) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.supplierID = supplierID;
        this.cost = cost;
        this.price = price;
        this.stock = stock;
    }

    public int getID() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getDescription() {
        return description;
    }
    public int getManufacturerID() {
        return supplierID;
    }
    public double getCost() {
        return cost;
    }
    public double getPrice() {
        return price;
    }
    public double getProfit(){
        return price - cost;
    }
    public double getProfitMargin(){
        return (price/cost)*100;
    }
    public int getStock() {
        return stock;
    }

    public void setId(int id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setManufacturer(int supplier) {
        this.supplierID = supplier;
    }
    public void setCost(double cost) {
        this.cost = cost;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public void setStock(int stock) {
        this.stock = stock;
    } 
    public void restock(int stock){
        this.stock+=stock;
    }
    
    public String toString(){
        return String.format("%s ID: %d\n%s\nManufacturerID: %d\nCost: %f\nStock: %d, Sell price: %f",getName(),getID(),getDescription(),getManufacturerID(),getCost(),getStock(), getPrice());
    }
}
