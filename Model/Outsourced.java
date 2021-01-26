/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author John R. McKahan II
 */
public class Outsourced extends Part {
    private String companyName;
    public Outsourced(int id, String name, double price, int stock, int max, int min, String companyName) {
        super(id, name, price, stock, min, max);
        this.companyName = companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyName() {
        return companyName;
    }
    
}
