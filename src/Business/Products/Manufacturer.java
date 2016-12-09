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
public class Manufacturer {
    int id;
    String name;
    int branchNum;
    String repName;

    public Manufacturer() {
    }
    public Manufacturer(int id, String name, int branchNum) {
        this.id = id;
        this.name = name;
        this.branchNum = branchNum;
    }
    public Manufacturer(int id, String name, int branchNum, String repName) {
        this.id = id;
        this.name = name;
        this.branchNum = branchNum;
        this.repName = repName;
    }

    public int getID() {
        return id;
    }
    public String getName() {
        return name;
    }
    public int getBranchNumber() {
        return branchNum;
    }
    public String getRepName() {
        return repName;
    }

    public void setBranchNumber(int branchNum) {
        this.branchNum = branchNum;
    }
    public void setId(int id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setRepName(String repName) {
        this.repName = repName;
    }
    
    public String toString(){
        return String.format("%s branch %d\nID: %d\nRepresented by %s",getName(),getBranchNumber(),getID(),getRepName());
    }
}
