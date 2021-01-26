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
import static java.lang.Double.max;
import static java.lang.Double.min;
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
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author John R. McKahan II
 */
public class AddPartController implements Initializable {

    @FXML
    private RadioButton inHouseButton;
    @FXML
    private ToggleGroup addpart;
    @FXML
    private RadioButton outsourcedButton;
    @FXML
    private TextField idTextField;
    @FXML
    private TextField nameTextField;
    @FXML
    private TextField invTextField;
    @FXML
    private TextField priceTextField;
    @FXML
    private TextField maxTextField;
    @FXML
    private Label alternateLabel;
    @FXML
    private TextField minTextField;
    @FXML
    private Button saveButton;
    @FXML
    private Button cancelButton;
    @FXML
    private String partSource;
    @FXML
    private TextField partSourceTextField;
    
 

    //When the cancel button is pushed it will return the user to the main screen.
    public void cancelButtonPushed(ActionEvent event) throws IOException
    {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    alert.setTitle("Return to Main Screen");
    alert.setHeaderText("Pressing OK will return you to the main screen");
    alert.setContentText("No modifications will be made");
    Optional<ButtonType> result = alert.showAndWait();
    if (result.get() == ButtonType.OK)
    {
    Parent mainScreenParent = FXMLLoader.load(getClass().getResource("MainScreen.fxml"));
    Scene mainScreenScene = new Scene(mainScreenParent);
    Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
    window.setScene(mainScreenScene);
    window.show();
    }
    }
    
    //Switch to in house
    public void switchInHouse(ActionEvent event)
    {
    alternateLabel.setText("Machine Id");
    partSource = "Inhouse";
    partSourceTextField.setText("Machine Id");
    }
    
    public void switchOutsourced(ActionEvent event)
    {
    alternateLabel.setText("Company Name");
    partSource = "Outsourced";
    partSourceTextField.setText("Company Name");
    }
    
    //For saving items
      public void saveButtonPushed(ActionEvent event) throws IOException
      
      //This handles adding InHouse parts to the inventory.
       {
        if  (partSource =="Inhouse")
        {
            int maxNum;
            int minNum;
        InHouse newPart = new InHouse( 
        Integer.parseInt(idTextField.getText()),
        nameTextField.getText(),
        Double.parseDouble(priceTextField.getText()),
        Integer.parseInt(invTextField.getText()),   
        maxNum = Integer.parseInt(maxTextField.getText()),
        minNum = Integer.parseInt(minTextField.getText()),
        Integer.parseInt(partSourceTextField.getText()));
        
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
        //This adds the parts to the inventory
        Inventory.addPart(newPart);
       
        //This takes the user back to the main scene
    Parent mainScreenParent = FXMLLoader.load(getClass().getResource("MainScreen.fxml"));
    Scene mainScreenScene = new Scene(mainScreenParent);
    Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
    window.setScene(mainScreenScene);
    window.show();
        }
   
           }
        //This handles adding Outsourced parts to the invnentory.
        else if (partSource =="Outsourced") 
             {
            int maxNum;
            int minNum;
            
        Outsourced newPart = new Outsourced( 
        Integer.parseInt(idTextField.getText()),
        nameTextField.getText(),
        Double.parseDouble(priceTextField.getText()),
        Integer.parseInt(invTextField.getText()),   
        maxNum = Integer.parseInt(maxTextField.getText()),
        minNum = Integer.parseInt(minTextField.getText()),
        (partSourceTextField.getText()));
        
        //As before, this handles making sure the minimum is less than the max
        if (maxNum <= minNum) {
            Alert alert = new Alert (Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
        alert.setHeaderText("Minimum is higher or equal to the maximum");
        alert.setContentText("Please make sure the maximum is higher than the minimum");
        
        alert.showAndWait();
        }
        
        if (maxNum > minNum) {
             
            //Adds the part to the inventory
            Inventory.addPart(newPart);
            
        
        //Takes the user back to the main scene
    Parent mainScreenParent = FXMLLoader.load(getClass().getResource("MainScreen.fxml"));
    Scene mainScreenScene = new Scene(mainScreenParent);
    Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
    window.setScene(mainScreenScene);
    window.show();
      
       
      }
      
      }
      }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    //This will make it so that the part Id does not get changed,
    idTextField.setEditable(false);
    
   
    
    /* This will generate the partId, which will start at 113 beginning where the
    test data ends off, and set it to the IdTextField
    */
    int genPartId = Inventory.partIdGen();
    idTextField.setText("" + genPartId);
    partSource="Inhouse";
    }    
    
}
