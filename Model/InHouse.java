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
public class InHouse extends Part {
    private int machineId;

    public InHouse(int id, String name, double price, int stock, int max, int min, int machineId) {
        super(id, name, price, stock, min, max);
        this.setMachineId(machineId);
    
    }

   


//Set Machine Id
public void setMachineId(int machineId)
{
this.machineId = machineId;
}
//Get Machine Id
public int getMachineId()
{
return this.machineId;
}
}