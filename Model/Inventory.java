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
public class Inventory {

// This will create the observable list for parts and products based upon the Part class and Product class
private static ObservableList<Part> allParts = FXCollections.observableArrayList();
private static ObservableList<Product> allProducts = FXCollections.observableArrayList();


//For use with generating the part Id, which will start where I left off on the test data.
private static int partIdNum = 109;

//For use with generating the product Id, which will start where I left off on the test data.
private static int prodIdNum = 112;

//For adding a new product
public static void addProduct (Product newProduct)
{
allProducts.add(newProduct);
    
}

//For adding a new part
public static void addPart (Part newPart)
{
allParts.add(newPart);
    
}

//To show all parts
public static ObservableList<Part> getAllParts()
{
return allParts;

}

//To show all products
public static ObservableList<Product> getAllProducts()
{
return allProducts;
        
}

//To look up a part by ID 
public static Part lookupPart (int partId)
{
    if(!allParts.isEmpty())
    {
for (int i=0; i < allParts.size(); i++)
{
if (allParts.get(i).id == partId)
{
return allParts.get(i);
       
}
    
}


}
    return null;
}
//To look up a part by name
public static Part lookupPart(String partName)
{
    
for (int i=0; i < allProducts.size(); i++)
{
if (allParts.get(i).name == partName)
{
return allParts.get(i);
    
}
    
}
return null;
}   


//To look up a product by Id
public static Product lookupProduct (int productId)
{
for (int i=0; i < allProducts.size(); i++)
{
if (allProducts.get(i).id == productId)
{
return allProducts.get(i);
       
}
    
}
return null;
}

//To look up a product by name
public static Product lookupProduct (String productName)
{
for (int i=0; i < allProducts.size(); i++)
{
if (allProducts.get(i).name == productName)
{
return allProducts.get(i);
       
}
    
}
return null;
}

//To update a part
public static void updatePart(int index, Part selectedPart)
        
{
allParts.set(index,selectedPart);
}

//To update a product
public static void updateProduct(int index, Product newProduct)
{
allProducts.set(index,newProduct);
}



//To delete a part
public static boolean deletePart(Part selectedPart)
{
        
 if( allParts.remove(selectedPart))
 {
    return true;
   
 }  
 
  else return false;
}


//For deleting a product
public static boolean deleteProduct(Product selectedProduct)
{
        
 if( allProducts.remove(selectedProduct))
 {
    return true;
   
 }  
 
  else return false;
}

public static Part partLookup (int partId)
{
    return allParts.get(partId);
    
}
/* This was not on the UML diagram, but this will create an integer.
The first time it runs, the part number generated should be 
113, which will pick up where I left off on the test data.
Every time the part screen is opened, this will be called, and a number will be
generated.
*/
public static int partIdGen()
{  
    int partIdGen = ++partIdNum;
   
    return partIdGen;
}
public static int prodIdGen()
{
    int prodIdGen = ++prodIdNum;
    return prodIdGen;
}

}