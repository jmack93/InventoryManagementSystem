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
public abstract class Part 

{  
protected int id;
protected String name;
protected double price = 0.0;
protected int stock;
protected int min;
protected int max;
   
   
//Constructor
public Part (int id, String name, double price, int stock, int min, int max) 
{
this.id = id;
this.name = name;
this.price = price;
this.stock = stock;
this.min = min;
this.max = max;
}

//Setting part price
public void setPrice(double price)
{
this.price=price;
}

//Setting part Id
public void setId (int id)
{
this.id = id;
}

//Setting part name
public void setName(String name)
{
this.name = name;
}

//Setting part stock

public void setStock(int stock)
{
this.stock = stock;    
}
//Getting part price
public double getPrice()
{
return price;
}
//Setting part minimum
public void setMin(int min)
{
this.min=min;
}
//Setting part maximum
public void setMax(int max)
{
this.max=max;
}
//Getting part name
public String getName()
{
return name;
}
//Getting part min
public int getMin()
{
return min;
}
//Getting part max
public int getMax()
{
return max;
}
//Getting part stock
public int getStock()
{
return stock;
}
public int getId()
{
return id;
}
}
