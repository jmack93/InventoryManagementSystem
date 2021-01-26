/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View_Controller;

import Model.InHouse;
import Model.Inventory;
import Model.Outsourced;
import Model.Part;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author John R. McKahan II
 */
public class ModifyPartController implements Initializable {

    @FXML
    private RadioButton inhouseButton;
    @FXML
    private ToggleGroup addpart;
    @FXML
    private RadioButton outButton;
    @FXML
    private TextField idField;
    @FXML
    private TextField nameField;
    @FXML
    private TextField invField;
    @FXML
    private TextField priceField;
    @FXML
    private TextField maxField;
    @FXML
    private TextField altField;
    @FXML
    private Label alternateLabel;
    @FXML
    private TextField minField;
    @FXML
    private Button saveButton;
    @FXML
    private Button cancelButton;
    private String partSource;
    Part chosenPart;
    
    
    //Sets the part's information into the scene
    public void setThePart(Part chosenPart)
   {
   this.chosenPart = chosenPart; 
if (chosenPart instanceof InHouse)
{  
   partSource = "InHouse";
   alternateLabel.setText("Machine Id");
   nameField.setText(chosenPart.getName());
   inhouseButton.setSelected(true);
   idField.setText(new Integer (chosenPart.getId()).toString());
   maxField.setText(new Integer (chosenPart.getMax()).toString());
   minField.setText(new Integer (chosenPart.getMin()).toString());
   invField.setText(new Integer (chosenPart.getStock()).toString());
   priceField.setText(new Double (chosenPart.getPrice()).toString());
   altField.setText(new Integer (((InHouse) chosenPart).getMachineId()).toString());
   
   }
 if (chosenPart instanceof Outsourced)
         {
   partSource = "Outsourced";
   alternateLabel.setText("Company Name");
   nameField.setText(chosenPart.getName());
   outButton.setSelected(true);
   idField.setText(Integer.toString(chosenPart.getId()));
   maxField.setText(Integer.toString(chosenPart.getMax()));
   minField.setText(Integer.toString(chosenPart.getMin()));
   invField.setText(Integer.toString(chosenPart.getStock()));
   priceField.setText(Double.toString(chosenPart.getPrice()));
   altField.setText(((Outsourced) chosenPart).getCompanyName());
             
              
         }
   }
    
    //This will change the label's text to Company Name for outsourced parts.
    public void outButton()
    {
    alternateLabel.setText("Company Name");
    partSource = "Outsourced";
    }
    
    
    // This will change the label to machine Id for in house parts.
    public void inButton()
    {
    alternateLabel.setText("Machine Id");
    partSource = "InHouse";
    }
    
    //Save button
    public void saveButtonPushed(ActionEvent event) throws IOException
    {
    if (partSource == "InHouse")
    {
            int maxNum;
            int minNum;
        InHouse newPart = new InHouse( 
        Integer.parseInt(idField.getText()),
        nameField.getText(),
        Double.parseDouble(priceField.getText()),
        Integer.parseInt(invField.getText()),   
        maxNum = Integer.parseInt(maxField.getText()),
        minNum = Integer.parseInt(minField.getText()),
        Integer.parseInt(altField.getText()));
        
        /*This will make sure that the maximum is higher than the minimum.
        This will also prevent the minimum from being equal to the maximum.
        This also creates an alert box to let the user know if they need to change
        the minimum and maximum values. 
        */
         if (maxNum <= minNum) {
            Alert alert = new Alert (Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
        alert.setHeaderText("Minimum is higher or equal to the maximum");
        alert.setContentText("Please make sure the maximum is higher than the minimum");
        alert.showAndWait();
        }
        if (maxNum > minNum){
        //This adds the modified part to the inventory and removes the old
        Inventory.addPart(newPart);
        Inventory.deletePart(chosenPart);
       
        //This takes the user back to the main scene
    Parent mainScreenParent = FXMLLoader.load(getClass().getResource("MainScreen.fxml"));
    Scene mainScreenScene = new Scene(mainScreenParent);
    Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
    window.setScene(mainScreenScene);
    window.show();
        }
    }
         //This handles adding modified Outsourced parts to the invnentory.
        else if (partSource =="Outsourced") 
       {
        int maxNum;
        int minNum;
            
        Outsourced newPart = new Outsourced( 
        Integer.parseInt(idField.getText()),
        nameField.getText(),
        Double.parseDouble(priceField.getText()),
        Integer.parseInt(invField.getText()),   
        maxNum = Integer.parseInt(maxField.getText()),
        minNum = Integer.parseInt(minField.getText()),
        (altField.getText()));
        
        //As before, this handles making sure the minimum is less than the max
        if (maxNum <= minNum) {
            Alert alert = new Alert (Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
        alert.setHeaderText("Minimum is higher or equal to the maximum");
        alert.setContentText("Please make sure the maximum is higher than the minimum");
        
        alert.showAndWait();
        }
        
        if (maxNum > minNum) {
             
            //Adds the modified part to the inventory and removes the old.
            Inventory.addPart(newPart);
            Inventory.deletePart(chosenPart);
            
        
        //Takes the user back to the main scene
    Parent mainScreenParent = FXMLLoader.load(getClass().getResource("MainScreen.fxml"));
    Scene mainScreenScene = new Scene(mainScreenParent);
    Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
    window.setScene(mainScreenScene);
    window.show();
      
       
      }
      
      }
    }
    
    public void cancelButtonPushed(ActionEvent event) throws IOException
    {
    Alert alert = new Alert(AlertType.CONFIRMATION);
    alert.setTitle("Return to Main Screen");
    alert.setHeaderText("Pressing OK will return you to the main screen");
    alert.setContentText("No modifications will be made");
    Optional<ButtonType> result = alert.showAndWait();
    if (result.get() == ButtonType.OK) {
    Parent mainScreenParent = FXMLLoader.load(getClass().getResource("MainScreen.fxml"));
    Scene mainScreenScene = new Scene(mainScreenParent);
    Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
    window.setScene(mainScreenScene);
    window.show();
    }
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        idField.setEditable(false);
        
        
        
    }    

   
        
    }
    

