/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package johnmckahaninventorysystem;

import Model.InHouse;
import Model.Inventory;
import static Model.Inventory.addPart;
import Model.Outsourced;
import Model.Product;
import Model.Part;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


/**
 *
 * @author John R. McKahan II
 */
public class JohnMcKahanInventorySystem extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Inventory someData = new Inventory();
        addingData(someData);
        Parent root = FXMLLoader.load(getClass().getResource ("/View_Controller/MainScreen.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
    }
    
    void addingData(Inventory someData)
    {
    //Adding some silly test data
        InHouse partyhat = new InHouse (100, "Party Hat", 5.59, 5, 10, 1, 100);
        InHouse lobster = new InHouse (101, "Lobster", 10.99, 5, 10, 1, 125);
        InHouse chocolate = new InHouse (102, "Chocolate", 8.99, 5, 10, 1, 100);
        InHouse cake = new InHouse (103, "Cake", 9.59, 5, 10, 11, 150);
        InHouse copper = new InHouse (104, "Copper", 5.00, 5, 10, 1, 100);
        Outsourced tin = new Outsourced(105, "Tin", 5.00, 5, 10, 1, "Boring Mine Company");
        Outsourced snail = new Outsourced(106, "Snail", 8.00, 5, 10, 1, "Snails and Sails Inc");
        Outsourced pufferfish = new Outsourced(107, "Pufferfish", 10.00, 10, 1, 10, "Zack's Fish Shack");
        Outsourced crab = new Outsourced(108, "Crab", 5.00, 5, 10, 1, "Dancing Crab Company");
        Outsourced pirate = new Outsourced(109, "Pirate", 5.50, 5, 10, 1, "Captain Dan's Pirates");
        Product seasickpirate = new Product(110, "Seasick Pirate", 30.00, 10, 1, 10);
        Product bronze = new Product(111, "Bronze", 50.00, 5, 1, 10);
        Product chocolatecake = new Product(112, "Chocolate Cake", 80.00, 5, 1, 10);
        
        //Adding the parts to the inventory
        Inventory.addPart(partyhat);
        Inventory.addPart(lobster);
        Inventory.addPart(chocolate);
        Inventory.addPart(cake);
        Inventory.addPart(tin);
        Inventory.addPart(copper);
        Inventory.addPart(snail);
        Inventory.addPart(pufferfish);
        Inventory.addPart(crab);
        Inventory.addPart(pirate);
        
        //Adding associated parts to the Seasick Pirate product and adding to inventory
        seasickpirate.addAssociatedPart(pirate);
        seasickpirate.addAssociatedPart(pufferfish);
        Inventory.addProduct(seasickpirate);
        
        //Adding tin and copper to the bronze product and adding to the inventory
        bronze.addAssociatedPart(tin);
        bronze.addAssociatedPart(copper);
        Inventory.addProduct(bronze);
        
        //Adding chocolate and cake to the Chocolate Cake product and adding to the inventory
        
        chocolatecake.addAssociatedPart(chocolate);
        chocolatecake.addAssociatedPart(cake);
        Inventory.addProduct(chocolatecake);
        
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
