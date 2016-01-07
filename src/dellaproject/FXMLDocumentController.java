/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dellaproject;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 *
 * @author bhavs
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private Label label;
    //console screen
    @FXML ListView<String> console_actionItemList;
    @FXML private ComboBox<String> console_inclusionFactor;
    @FXML private ComboBox<String> console_sortingDirection;
    @FXML private ComboBox<String> console_firstSortingFactor;
    @FXML private ComboBox<String> console_secondSortingFactor;
    @FXML private Label console_Creation;
    @FXML private Label console_dueDate;
    @FXML private Label console_Status;
    @FXML private Label console_memberDetails;
    @FXML private Label console_teamDetails;
    @FXML private TextField console_name;
    @FXML private TextArea console_description;
    @FXML private TextArea console_resolution;
    //action items screen
    @FXML private ComboBox<String> action_actionItems;
    @FXML private final ComboBox<String> action_inclusionFactor;
    @FXML private ComboBox<String> action_sortingDirection;
    @FXML private ComboBox<String> action_firstSortingFactor;
    @FXML private ComboBox<String> action_secondSortingFactor;
    @FXML private TextField action_Name;
    @FXML private TextArea action_Description;
    @FXML private TextArea action_Resolution;
    @FXML private ComboBox<String> action_Member;
    @FXML private ComboBox<String> action_Team;
    @FXML private TextField action_dueDate;
    @FXML private Button action_Update;

    //members screen
    
    @FXML private TextField members_name;
    @FXML private ListView<String> members_indiv;
    @FXML private ListView<String> members_avail;
    @FXML private ListView<String> members_current;
    @FXML private Button members_addToList;
    @FXML private Button members_removeFromList;
    @FXML private Button members_addAffliation;
    @FXML private Button members_removeAffliation;
     //teams screen variables
    
    @FXML private TextField teams_name;
    @FXML private ListView<String> teams_known;
    @FXML private ListView<String> teams_avail;
    @FXML private ListView<String> teams_current;
    @FXML private Button teams_addToList;
    @FXML private Button teams_removeFromList;
    @FXML private Button teams_addAssociation;
    @FXML private Button teams_removeAssociation;
    public FXMLDocumentController() {
        this.action_inclusionFactor = new ComboBox<>();
        this.action_sortingDirection = new ComboBox<>();
        this.action_firstSortingFactor = new ComboBox<>();
        this.action_secondSortingFactor = new ComboBox<>();
        this.console_inclusionFactor = new ComboBox<>();
        this.console_sortingDirection = new ComboBox<>();
        this.console_firstSortingFactor = new ComboBox<>();
        this.console_secondSortingFactor = new ComboBox<>();
        this.action_actionItems = new ComboBox<>();
        this.console_actionItemList = new ListView<>();
        this.action_Name = new TextField();
        this.action_Description = new TextArea();
        this.action_Resolution = new TextArea();
        this.console_Creation = new Label();
        this.console_dueDate = new Label();
        this.console_Status = new Label();
//        this.console_Description = new TextField();
        this.console_memberDetails = new Label();
        this.console_teamDetails = new Label();
        this.action_Member = new ComboBox<>();
        this.action_Team = new ComboBox<>();
        this.action_dueDate = new TextField();
//        this.console_actionItemList = new ListView<>();
    }
    
    public Connection connect() throws SQLException{
        System.out.println("hello");
            
        Connection con=null;
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            System.out.println("came");
            con = (Connection) DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","bhavs","bhavs");
            System.out.println("succesfull connection");
        }
        catch(ClassNotFoundException cnfe) {
            System.out.println("connection failed"+cnfe);
        }
       
        return con;
    }
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        try {
            // TODO
            connect();
        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            consoleDisplay();
        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    //the form is saved-action item form
    public void saveForm(ActionEvent event) throws SQLException {
        System.out.println("create a new action form");
       Connection con1 =  connect();
       Statement st = con1.createStatement();
       String name = action_Name.getText();
       String Description = action_Description.getText();
       String Resolution = action_Resolution.getText();
       String sql = "SELECT name FROM sample";
        ResultSet rs = con1.createStatement().executeQuery(sql);
        ObservableList<String> itemList = FXCollections.observableArrayList();
        while(rs.next()){
            String str = rs.getString("name");
//            System.out.println(name);
            itemList.add(str);
        }
        System.out.println(itemList);
        System.out.println(name);
        //if the action item name already exists alert message is shown.
        if(itemList.contains(name)) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("Name already exists");
            alert.setContentText("please select another name for the action item");

            alert.showAndWait();
            return;
        }
        // if the action item name is empty, warning is shown
        else if(name.equals("")) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("Name cannot be empty");
            alert.setContentText("please select ar name for your action item");

            alert.showAndWait();
            return;
        }
        
        sql = "INSERT INTO sample VALUES('"+name+"','"+Description+"','"+Resolution+"')";
        st.executeUpdate(sql);
        st.close();
//        st.executeUpdate(sql);
    }
    //the form is cleared when we click clear form button
    public void clearForm(ActionEvent event) throws SQLException{
        System.out.println("clear this form");
        action_Name.setText("");
        action_Description.setText("");
        action_Resolution.setText("");
    }
    //displays list of action items list on console screen
    public void consoleDisplay() throws SQLException {
        System.out.println("console display");
        Connection con2 = connect();
        
        String str = "SELECT name FROM sample";
        ResultSet rs = con2.createStatement().executeQuery(str);
        ObservableList<String> itemList = FXCollections.observableArrayList();
        while(rs.next()){
            String name = rs.getString("name");
            System.out.println(name);
            itemList.add(name);
        }
        console_actionItemList.setItems(itemList); 
    }
    //details of a particular item selected is shown on console screen
    public void consoleSelectItem() throws SQLException{
        System.out.println("an item is selected from console");
        String item = console_actionItemList.getSelectionModel().getSelectedItem();
        System.out.println(item);
        Connection con3 = connect();
        String str = "SELECT * FROM sample where name='"+item+"'";
        ResultSet rs = con3.createStatement().executeQuery(str);
        while(rs.next()){
            String name = rs.getString("name");
            String desc = rs.getString("description");
            String res = rs.getString("resolution");
            
            System.out.println(res+"jdash");
            console_name.setText(name);
            console_description.setText(desc);
            console_resolution.setText(res);
            
        }
    }
    //the action item form is updated.
    public void updateActionItem() throws SQLException {
        System.out.println("update action form");
       Connection con1 =  connect();
       Statement st = con1.createStatement();
       String name = action_Name.getText();
       String Description = action_Description.getText();
       String Resolution = action_Resolution.getText();
       String sql = "UPDATE sample SET description='"+Description+"',resolution='"+Resolution+"'WHERE name='"+name+"'";
       System.out.println(name+Description+Resolution);
        ResultSet rs = con1.createStatement().executeQuery(sql);
        
    }
    public void displayActionItemScreen() throws SQLException {
        System.out.println("action item screen is clicked");
        String item = console_actionItemList.getSelectionModel().getSelectedItem();
        System.out.println(item);
        Connection con3 = connect();
        String str = "SELECT * FROM sample where name='"+item+"'";
        ResultSet rs = con3.createStatement().executeQuery(str);
        while(rs.next()){
            String name = rs.getString("name");
            String desc = rs.getString("description");
            String res = rs.getString("resolution");
            
//            System.out.println(res+"jdash");
            action_Name.setText(name);
            action_Description.setText(desc);
            action_Resolution.setText(res);
            
        }
    }
    // addToList button is disabled when user clicks on right side box
    public void disableAddToList() {
        members_addToList.setDisable(true);
        teams_addToList.setDisable(true);
    }
    // removeFromList button is disabled when user clicks on left side box
    public void disableRemoveFromList() {
        members_removeFromList.setDisable(true);
    }
    // addAffliation button is disabled when user clicks on right side box
    public void disableAddAffliation() {
        members_addAffliation.setDisable(true);
    }
    // removeAffliation button is disabled when user clicks on left side box
    public void disableRemoveAffliation() {
        members_removeAffliation.setDisable(true);
    }
    
    //application is closed
    public void closeApplication(ActionEvent event) {
        
    }
    
}
