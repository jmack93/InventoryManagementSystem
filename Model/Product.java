/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author John R. McKahan II
 */
public class Product 
{
private ObservableList<Part> associatedParts = FXCollections.observableArrayList();

protected int id;
protected String name;
protected double price = 0.0;
protected int stock;
protected int min;
protected int max;

//Constructor
public Product (int id, String name, double price, int stock, int min, int max) 
{
this.id = id;
this.name = name;
this.price = price;
this.stock = stock;
this.min = min;
this.max = max;
}
//Setters
public void setId(int id)
{
    this.id = id;
}
public void setName(String name)
{
    this.name = name;
}
public void setPrice (double price)
    
{
        this.price = price;
}

public void setStock(int stock)
{
    this.stock = stock;
}

public void setMin(int min)
{
    this.min = min;
}
public void setMax(int max)
{
    this.max = max;
}

//Getters
public int getId()
{
    return id;
}

public String getName()
{
    return name;
}
public double getPrice()
{
    return price;
}
public int getStock()
{
    return stock;
}
public int getMin()
{
    return min;
}
public int getMax()
{
    return max;
}

//For adding a part
public void addAssociatedPart(Part part)
{
    associatedParts.add(part);
}

//To return all associated parts
public ObservableList<Part> getAllAssociatedParts()
{
    
return associatedParts;
        
}

//To delete an associated part
public boolean deleteAssociatedPart(Part selectedAspart)
{
for (int i = 0; i < associatedParts.size(); i++)
{
if (associatedParts.get(i).getId() == selectedAspart.getId())
{
  associatedParts.remove(i);
  break;
} 
else
{
return false;
}
}
    return true;
}
}

