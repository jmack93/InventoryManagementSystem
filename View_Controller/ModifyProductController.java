/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View_Controller;

import Model.Inventory;
import Model.Part;
import Model.Product;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author John R. McKahan II
 */
public class ModifyProductController implements Initializable {

    @FXML
    private TextField idField;
    @FXML
    private TextField nameField;
    @FXML
    private TextField inventoryField;
    @FXML
    private TextField priceField;
    @FXML
    private TextField maxField;
    @FXML
    private TextField minField;
    @FXML
    private TableColumn<Part, Integer> topId;
    @FXML
    private TableColumn<Part, String> topName;
    @FXML
    private TableColumn<Part, Integer> topInv;
    @FXML
    private TableColumn<Part, Double> topPrice;
    @FXML
    private TableColumn<Part, Integer> botId;
    @FXML
    private TableColumn<Part, String> botName;
    @FXML
    private TableColumn<Part, Integer> botInv;
    @FXML
    private TableColumn<Part, Double> botPrice;
    @FXML
    private Button searchButton;
    @FXML
    private TextField searchBox;
    @FXML
    private Button addButton;
    @FXML
    private Button deleteButton;
    @FXML
    private Button saveButton;
    @FXML
    private Button cancelButton;
    
    @FXML
    private TableView<Part> ascTable;
    @FXML
    private TableView<Part>partTable; 
    Product chosenProduct;
   
    @FXML
    private static ObservableList<Part> partInvSearch = FXCollections.observableArrayList();
    @FXML
    private static ObservableList<Part> aParts = FXCollections.observableArrayList();
    
    @FXML
    private static ObservableList<Part> newParts = FXCollections.observableArrayList();
    
    Product editedProduct;
    
    
public void addButtonPushed()
{
//This will handle visually showing the added parts in the add parts table below


aParts.add(partTable.getSelectionModel().getSelectedItem());
ascTable.setItems(aParts);
//This will add the associated part to the modified product
editedProduct.addAssociatedPart(partTable.getSelectionModel().getSelectedItem());
ascTable.refresh();
}

    
    //Set's the selected product's data into the scene
    
    public void setTheProduct(Product chosenProduct)
    {
     this.chosenProduct = chosenProduct;
     idField.setText(new Integer (chosenProduct.getId()).toString());
     maxField.setText(new Integer (chosenProduct.getMax()).toString());
     minField.setText(new Integer (chosenProduct.getMin()).toString());
     priceField.setText(new Double (chosenProduct.getPrice()).toString());
     nameField.setText(chosenProduct.getName());
     inventoryField.setText(new Integer (chosenProduct.getStock()).toString());
     aParts = chosenProduct.getAllAssociatedParts();
     ascTable.setItems(aParts);
     ascTable.refresh();
     
    }

    
    public void deleteButtonPushed(ActionEvent event)
 {
     if (!ascTable.getSelectionModel().isEmpty())
        {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Deleting Part Confirmation");
        alert.setHeaderText("Delete Associated Part?");
        alert.setContentText("This will remove the associated part from the product");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK)
        {
        Part selectedPart = ascTable.getSelectionModel().getSelectedItem();
       
   
        
        aParts.remove(selectedPart);
        editedProduct.deleteAssociatedPart(selectedPart);
        ascTable.refresh();
      
        
        }
        }
 }
public void searchButtonPushed(ActionEvent event)
{
if (!searchBox.getText().isEmpty()){
String searchPart=searchBox.getText();
boolean found=false;
try
{
int partId=Integer.parseInt(searchPart);
for(Part p: Inventory.getAllParts()){
if(p.getId()==partId){    
found = true;
partInvSearch.add(p);
partTable.setItems(partInvSearch);
partTable.refresh();  

}           
}
if (found==false){
Alert alert = new Alert(Alert.AlertType.INFORMATION);
alert.setTitle("No Part Found");
alert.setHeaderText("No Part Found");
alert.setContentText("Please try again, a part matching your search was not found");
alert.showAndWait();
partTable.setItems(Inventory.getAllParts());
partTable.refresh();
}
}
catch(NumberFormatException e)
{
for(Part p: Inventory.getAllParts())
{
if(p.getName().equals(searchPart))
{
found=true;
partInvSearch.add(p);
partTable.refresh();
}           
}
if (found==false)
{
Alert alert = new Alert(Alert.AlertType.INFORMATION);
alert.setTitle("No Part Found");
alert.setHeaderText("No Part");
alert.setContentText("Please try again, a part matching your search was not found");
alert.showAndWait();
}
partTable.setItems(partInvSearch);
partTable.refresh();  
}

}
if (searchBox.getText().isEmpty())
{
    partTable.setItems(Inventory.getAllParts());
    partTable.refresh();
}
}

public void saveButtonPushed(ActionEvent event) throws IOException
    
        {
        int minNum;
        int maxNum;
        editedProduct = new Product( 
        Integer.parseInt(idField.getText()),
        nameField.getText(),
        Double.parseDouble(priceField.getText()),
        Integer.parseInt(inventoryField.getText()),   
        minNum = Integer.parseInt(minField.getText()),
        maxNum = Integer.parseInt(maxField.getText()));
  
      
                
        /*This will make sure that the maximum is higher than the minimum.
        This will also prevent the minimum from being equal to the maximum.
        This also creates an alert box to let the user know if they need to change
        the minimum and maximum values. 
        */
        if (maxNum < minNum) {
        Alert alert = new Alert (Alert.AlertType.INFORMATION);
        alert.setTitle("Error");
        alert.setHeaderText("Minimum is higher or equal to the maximum");
        alert.setContentText("Please make sure the maximum is higher than the minimum");
        alert.showAndWait();
        }
        if (maxNum > minNum){
        //This adds the parts to the inventory
        for (int i = 0; i < ascTable.getItems().size(); i++){
        for(Part p: ascTable.getItems()){
    

editedProduct.addAssociatedPart(p);

 }     
        Inventory.addProduct(editedProduct);
        Inventory.deleteProduct(chosenProduct);
      
        ascTable.refresh();
       
        //This takes the user back to the main scene
    Parent mainScreenParent = FXMLLoader.load(getClass().getResource("MainScreen.fxml"));
    Scene mainScreenScene = new Scene(mainScreenParent);
    Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
    window.setScene(mainScreenScene);
    window.show();
    break;
        }
        }
        }
     
//When the cancel button is pushed it will return the user to the main screen.
  public void cancelButtonPressed(ActionEvent event) throws IOException
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
 

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
     idField.setEditable(false);
      
      topName.setCellValueFactory(new PropertyValueFactory<Part,String>("name"));
      topInv.setCellValueFactory(new PropertyValueFactory<Part,Integer>("stock"));
      topPrice.setCellValueFactory(new PropertyValueFactory<Part,Double>("price"));
      topId.setCellValueFactory(new PropertyValueFactory<Part,Integer>("id"));
      partTable.setItems(Inventory.getAllParts());
      
 botName.setCellValueFactory(new PropertyValueFactory<Part,String>("name"));
 botInv.setCellValueFactory(new PropertyValueFactory<Part,Integer>("stock"));
 botPrice.setCellValueFactory(new PropertyValueFactory<Part,Double>("price"));
 botId.setCellValueFactory(new PropertyValueFactory<Part,Integer>("id"));

     
    }    

    
    
}
