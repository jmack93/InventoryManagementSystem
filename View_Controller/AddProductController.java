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
public class AddProductController implements Initializable {

    @FXML
    private TextField idField;
    private TextField productNameField;
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
    private TableView<Part> partTable;
    @FXML
    private TableView<Part> searchTable;
    @FXML
    private TextField partsSearchBox;
          
    @FXML
    private static ObservableList<Part> partInvSearch = FXCollections.observableArrayList();
    @FXML
    private static ObservableList<Part> aParts = FXCollections.observableArrayList();
    
    @FXML
    Product newProduct;
    Product chosenProduct;
    @FXML
    private TextField nameField;
    @FXML
    private Button cancelButton;

//Delete button
 public void deleteButtonPushed(ActionEvent event)
 {
     if (!searchTable.getSelectionModel().isEmpty())
        {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Deleting Part Confirmation");
        alert.setHeaderText("Delete Associated Part?");
        alert.setContentText("This will remove the associated part from the product");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK)
        {
        Part selectedPart = searchTable.getSelectionModel().getSelectedItem();
       
   
   
        aParts.remove(selectedPart);
            boolean deleteAssociatedPart = newProduct.deleteAssociatedPart(selectedPart);
        searchTable.refresh();
      
        
        }
        }
 }




//Add button

public void addButtonPushed(ActionEvent event)
{
    
//This will handle visually showing the added parts in the add parts table below

aParts.add(partTable.getSelectionModel().getSelectedItem());

 botName.setCellValueFactory(new PropertyValueFactory<Part,String>("name"));
 botInv.setCellValueFactory(new PropertyValueFactory<Part,Integer>("stock"));
 botPrice.setCellValueFactory(new PropertyValueFactory<Part,Double>("price"));
 botId.setCellValueFactory(new PropertyValueFactory<Part,Integer>("id"));
searchTable.setItems(aParts);

//This will add the associated part to the new product
newProduct.addAssociatedPart(partTable.getSelectionModel().getSelectedItem());
searchTable.refresh();
}
    
    
//Search Button
     public void searchButtonPushed(ActionEvent event)
{
if (!partsSearchBox.getText().isEmpty()){
String searchPart=partsSearchBox.getText();
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
if (partsSearchBox.getText().isEmpty())
{
    partTable.setItems(Inventory.getAllParts());
    partTable.refresh();
}
}

    
      
     
 
    //Save button
    public void saveButtonPushed(ActionEvent event) throws IOException
    
        {
        int minNum;
        int maxNum;
        newProduct = new Product( 
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
        
  for (int i = 0; i < searchTable.getItems().size(); i++){
 for(Part p: searchTable.getItems()){
    

newProduct.addAssociatedPart(p);

 }     

 
        
 Inventory.addProduct(newProduct);
      
    
       
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
    
    
        
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //This clears the associated parts from the last time so that they do not automatically pop up
        aParts.clear();
        
        //Sets the parts table with data 
         topName.setCellValueFactory(new PropertyValueFactory<Part,String>("name"));
        topInv.setCellValueFactory(new PropertyValueFactory<Part,Integer>("stock"));
        topPrice.setCellValueFactory(new PropertyValueFactory<Part,Double>("price"));
        topId.setCellValueFactory(new PropertyValueFactory<Part,Integer>("id"));
        partTable.setItems(Inventory.getAllParts());
        
        //Generates the product Id
        int genProdId = Inventory.prodIdGen();
    
        //This sets up the search table
        idField.setEditable(false);
        idField.setText("" + genProdId);
 botName.setCellValueFactory(new PropertyValueFactory<Part,String>("name"));
 botInv.setCellValueFactory(new PropertyValueFactory<Part,Integer>("stock"));
 botPrice.setCellValueFactory(new PropertyValueFactory<Part,Double>("price"));
 botId.setCellValueFactory(new PropertyValueFactory<Part,Integer>("id"));
searchTable.setItems(aParts);
        
}
}

