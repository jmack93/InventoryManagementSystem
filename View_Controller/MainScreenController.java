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
import javafx.application.Platform;
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
import javafx.scene.control.Alert.AlertType;
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
public class MainScreenController implements Initializable {

    @FXML
    private Button exitButton;
    @FXML
    private Button searchPartsButton;
    @FXML
    private Button searchProductsButton;
    @FXML
    private TextField partsSearchBox;
    @FXML
    private TextField productsSearchBox;
    @FXML
    private Button addPartsButton;
    @FXML
    private Button modifyPartsButton;
    @FXML
    private Button deletePartsButton;
    @FXML
    private Button addProductsButton;
    @FXML
    private Button modifyProductsButton;
    @FXML
    private Button deleteProductsButton;
    @FXML
    private TableView<Part> partsTableView;
    @FXML
    private TableColumn<Part, String> partNameCol;
    @FXML
    private TableColumn<Part, Integer> partInventoryCol;
    @FXML
    private TableColumn<Part, Double> partPriceCol;
    @FXML
    private TableColumn<Part, Integer> partIdCol;
    @FXML
    private TableView<Product> productsTableView;
    @FXML
    private TableColumn<Product, String> productNameCol;
    @FXML
    private TableColumn<Product, Integer> productInvCol;
    @FXML
    private TableColumn<Product, Double> productPriceCol;
    @FXML
    private TableColumn<Product, Integer> productIdCol;
    private static ObservableList<Part> allParts = FXCollections.observableArrayList();
    private static ObservableList<Part> partInvSearch = FXCollections.observableArrayList();
    private static ObservableList<Product> allProducts = FXCollections.observableArrayList();
    private static ObservableList<Product> productInvSearch = FXCollections.observableArrayList();
    
    
    //Changing to the AddPart scene
    public void addPartButtonPushed(ActionEvent event) throws IOException
    {
    Parent addPartParent = FXMLLoader.load(getClass().getResource("AddPart.fxml"));
    Scene addPartScene = new Scene(addPartParent);
    Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
    window.setScene(addPartScene);
    window.show();
    }
    
    //Changing to ModifyPart scene
     public void modifyPartButtonPushed(ActionEvent event) throws IOException
     { 
         if (!partsTableView.getSelectionModel().isEmpty())
         {
Stage stage; 
Parent root;       
stage=(Stage) modifyPartsButton.getScene().getWindow();
FXMLLoader loader=new FXMLLoader(getClass().getResource("ModifyPart.fxml"));
root = loader.load();
Scene scene = new Scene(root);
stage.setScene(scene);
stage.show();
ModifyPartController controller = loader.getController();
Part chosenPart=partsTableView.getSelectionModel().getSelectedItem();
controller.setThePart(chosenPart);
         }
    }
     
    //Changing to AddProduct scene
     public void addProductButtonPushed(ActionEvent event) throws IOException
    {
    Parent addProductParent = FXMLLoader.load(getClass().getResource("AddProduct.fxml"));
    Scene addProductScene = new Scene(addProductParent);
    Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
    window.setScene(addProductScene);
    window.show();
    }
    
    //Changing to ModifyProduct scene
     public void modifyProductButtonPushed(ActionEvent event) throws IOException   
{ 
if (!productsTableView.getSelectionModel().isEmpty())
{
Stage stage; 
Parent root;       
stage=(Stage) modifyProductsButton.getScene().getWindow();
FXMLLoader loader=new FXMLLoader(getClass().getResource("ModifyProduct.fxml"));
root = loader.load();
Scene scene = new Scene(root);
stage.setScene(scene);
stage.show();
ModifyProductController controller = loader.getController();
Product chosenProduct=productsTableView.getSelectionModel().getSelectedItem();
controller.setTheProduct(chosenProduct);
}
}
    
    
     //To exit the program and a prompt will generate to confirm.
    public void exitButtonPushed(ActionEvent event)
    {Alert alert = new Alert(AlertType.CONFIRMATION);
    alert.setTitle("Close Program");
    alert.setHeaderText("Pressing Ok will close the program");
    alert.setContentText("The program will close");
    Optional<ButtonType> result = alert.showAndWait();
    if (result.get() == ButtonType.OK)
    {
        Platform.exit();
    }
    }
    
    //Delete part button with confirmation of deletion
    public void deletePart(ActionEvent event)
    {
        if (!partsTableView.getSelectionModel().isEmpty())
        {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Deleting Part Confirmation");
        alert.setHeaderText("Delete Part?");
        alert.setContentText("Are you sure?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK)
        {
        Part selectedPart = partsTableView.getSelectionModel().getSelectedItem();
        Inventory.deletePart(selectedPart);
        partInvSearch.remove(selectedPart);
        partsTableView.refresh();
        }
        }
    }
    
    //This will delete a product and will give a pop up for confirmation
    public void deleteProduct(ActionEvent event)
    {
        if (!productsTableView.getSelectionModel().isEmpty())
        {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Deleting Product Confirmation");
        alert.setHeaderText("Delete Product?");
        alert.setContentText("Are you sure?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK)
        {
        Product selectedProduct = productsTableView.getSelectionModel().getSelectedItem();
        Inventory.deleteProduct(selectedProduct);
        productInvSearch.remove(selectedProduct);
        productsTableView.refresh();
        
        }
        }
    }
    
    //This will search for parts and populate the parts tableview
    
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
partsTableView.setItems(partInvSearch);
partsTableView.refresh();  

}           
}
if (found==false){
Alert alert = new Alert(AlertType.INFORMATION);
alert.setTitle("No Part Found");
alert.setHeaderText("No Part Found");
alert.setContentText("Please try again, a part matching your search was not found");
alert.showAndWait();
partsTableView.setItems(Inventory.getAllParts());
partsTableView.refresh();
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
partsTableView.refresh();
}           
}
if (found==false)
{
Alert alert = new Alert(AlertType.INFORMATION);
alert.setTitle("No Part Found");
alert.setHeaderText("No Part");
alert.setContentText("Please try again, a part matching your search was not found");
alert.showAndWait();
}
partsTableView.setItems(partInvSearch);
partsTableView.refresh();  
}
}
 //If the search box is empty all of the products will be returned
 else if (partsSearchBox.getText().isEmpty())
 {
 partsTableView.setItems(Inventory.getAllParts());
 partsTableView.refresh();
 }
}
    // This will search for products and populate the product tableview
    public void productSearchPushed (ActionEvent event)
    { 
if (!productsSearchBox.getText().isEmpty())        
{              
String searchProduct=productsSearchBox.getText();
boolean found=false;
try
{
int productId=Integer.parseInt(searchProduct);
for(Product p: Inventory.getAllProducts()){
if(p.getId()==productId){    
found = true;
productInvSearch.add(p); 
productsTableView.setItems(productInvSearch);
productsTableView.refresh();  
}           
}
if (found==false){
Alert alert = new Alert(AlertType.INFORMATION);
alert.setTitle("No Product Found");
alert.setHeaderText("No Product Found");
alert.setContentText("Please try again, a product matching your search was not found");
alert.showAndWait();
}
}
catch(NumberFormatException e)
{
for(Product p: Inventory.getAllProducts())
{
if(p.getName().equals(searchProduct))
{
found=true;
productInvSearch.add(p);
}           
}
if (found==false)
{
Alert alert = new Alert(AlertType.INFORMATION);
alert.setTitle("No product Found");
alert.setHeaderText("No product found");
alert.setContentText("Please try again, a product matching your search was not found");
alert.showAndWait();
}
productsTableView.setItems(productInvSearch);
productsTableView.refresh();  
}
}
else if (productsSearchBox.getText().isEmpty())   
{
productsTableView.setItems(Inventory.getAllProducts());
productsTableView.refresh();
}
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        //Getting the part table set up with the test data
        partNameCol.setCellValueFactory(new PropertyValueFactory<Part,String>("name"));
        partInventoryCol.setCellValueFactory(new PropertyValueFactory<Part,Integer>("stock"));
        partPriceCol.setCellValueFactory(new PropertyValueFactory<Part,Double>("price"));
        partIdCol.setCellValueFactory(new PropertyValueFactory<Part,Integer>("id"));
        partsTableView.setItems(Inventory.getAllParts());
        
        //Getting the product table set up with the test data
       
        productNameCol.setCellValueFactory(new PropertyValueFactory<Product,String>("name"));
        productInvCol.setCellValueFactory(new PropertyValueFactory<Product,Integer>("stock"));
        productPriceCol.setCellValueFactory(new PropertyValueFactory<Product,Double>("price"));
        productIdCol.setCellValueFactory(new PropertyValueFactory<Product,Integer>("id"));
        productsTableView.setItems(Inventory.getAllProducts());
       
        
    }    
    
}
