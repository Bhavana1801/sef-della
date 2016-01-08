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
import javafx.scene.control.Tab;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.util.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 *
 * @author bhavs
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private Label label;
    //tab pane
    @FXML private Tab console_tab;
    @FXML private Tab action_tab;
    @FXML private Tab members_tab;
    @FXML private Tab teams_tab;
    @FXML private Button quitButton;
    //console screen
    @FXML ListView<String> console_actionItemList;
    @FXML private ComboBox<String> console_inclusionFactor;
    @FXML private ComboBox<String> console_sortingDirection;
    @FXML private ComboBox<String> console_firstSortingFactor;
    @FXML private ComboBox<String> console_secondSortingFactor;
    @FXML private Label console_CreationDate;
    @FXML private Label console_dueDate;
    @FXML private Label console_Status;
    @FXML private Label console_memberDetails;
    @FXML private Label console_teamDetails;
    @FXML private TextField console_name;
    @FXML private TextArea console_description;
    @FXML private TextArea console_resolution;
    
    //action items screen
    @FXML private ComboBox<String> action_actionItems;
    @FXML private ComboBox<String> action_inclusionFactor;
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
    @FXML private Label action_creation;
    @FXML private ComboBox<String> action_Status;

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
        this.console_CreationDate = new Label();
        this.console_dueDate = new Label();
        this.console_Status = new Label();
        this.console_memberDetails = new Label();
        this.console_teamDetails = new Label();
        this.action_Member = new ComboBox<>();
        this.action_Team = new ComboBox<>();
        this.action_dueDate = new TextField();
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
        try {
            consoleDisplay();
        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        initializeAll();
    }
    // it initializes all the comboBoxes to first values in it.
    public void initializeAll() {
        console_inclusionFactor.getSelectionModel().selectFirst();
        console_sortingDirection.getSelectionModel().selectFirst();
        console_firstSortingFactor.getSelectionModel().selectFirst();
        console_secondSortingFactor.getSelectionModel().selectFirst();
        action_inclusionFactor.getSelectionModel().selectFirst();
        action_sortingDirection.getSelectionModel().selectFirst();
        action_firstSortingFactor.getSelectionModel().selectFirst();
        action_secondSortingFactor.getSelectionModel().selectFirst();
        action_Status.getSelectionModel().selectFirst();
        action_Member.getSelectionModel().selectFirst();
        action_Team.getSelectionModel().selectFirst();
    }
    //the form is saved-action item form
    public void saveForm(ActionEvent event) throws SQLException, ParseException {
        System.out.println("create a new action form");
       Connection con1 =  connect();
       Statement st = con1.createStatement();
       DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
       Date date = new Date();
       action_creation.setText(dateFormat.format(date));
       String name = action_Name.getText();
       String Description = action_Description.getText();
       String Resolution = action_Resolution.getText();
       Date dueDate  = dateFormat.parse(action_dueDate.getText());
       System.out.println("date: "+dateFormat.format(dueDate));
       String sql = "SELECT name FROM sample";
        ResultSet rs = con1.createStatement().executeQuery(sql);
        ObservableList<String> itemList = FXCollections.observableArrayList();
        while(rs.next()){
            String str = rs.getString("name");
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
        //inserting values into the database
        sql = "INSERT INTO sample VALUES('"+name+"','"+Description+"','"+Resolution+"','"+dueDate+"')";
        st.executeUpdate(sql);
        st.close();
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
        //retrieving all the action item names from database
        String str = "SELECT name FROM sample";
        ResultSet rs = con2.createStatement().executeQuery(str);
        ObservableList<String> itemList = FXCollections.observableArrayList();
        while(rs.next()){
            String name = rs.getString("name");
            System.out.println(name);
            itemList.add(name);
        }
        //displays all the list of action item names to the console screen
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
    //select an item from console and if we click action item screen,details are displayed
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
            action_Name.setText(name);
            action_Description.setText(desc);
            action_Resolution.setText(res);
            
        }
    }
    // addToList button is disabled when user clicks on right side box
    public void disableAddToList() {
        members_addToList.setDisable(true);
        members_removeFromList.setDisable(false);
        teams_addToList.setDisable(true);
    }
    // removeFromList button is disabled when user clicks on left side box
    public void disableRemoveFromList() {
        members_removeFromList.setDisable(true);
        members_addToList.setDisable(false);
    }
    // addAffliation button is disabled when user clicks on right side box
    public void disableAddAffliation() {
        members_addAffliation.setDisable(true);
        members_removeAffliation.setDisable(false);
    }
    // removeAffliation button is disabled when user clicks on left side box
    public void disableRemoveAffliation() {
        members_removeAffliation.setDisable(true);
        members_addAffliation.setDisable(false);
    }
    
    //application is closed
    @FXML
    private void closeButtonAction(){
        // get a handle to the stage
        System.out.println("close");
        Stage stage = (Stage) quitButton.getScene().getWindow();
        // do what you have to do
        stage.close();
    }

    /**
     * if we click on the sorting direction, the action items are sorted and displayed
     * @throws SQLException
     */
    @FXML
    public void consoleSortingDirection() throws SQLException {
        System.out.println("console sorting direction");
        Connection con = connect();
        String cmd = console_sortingDirection.getSelectionModel().getSelectedItem();
        System.out.println(cmd);
        String sql = "SELECT name FROM sample";
        ResultSet rs = con.createStatement().executeQuery(sql);
        ObservableList<String> list = FXCollections.observableArrayList();
        while(rs.next()){
            String name = rs.getString("name"); 
            list.add(name);
        }
        FXCollections.sort(list);
        if(cmd.equals("Small to Large")) {
            console_actionItemList.setItems(list);
            return;
        }
        ObservableList<String> list2 = FXCollections.observableArrayList();
        for (int i = list.size()-1;i>=0;i--) {
            list2.add(list.get(i));
        }
        console_actionItemList.setItems(list2);
    }
    public void consoleFirstSorting() {
        
    }
    public void consoleSecondSorting() {
        
    }
    public void consoleInclusionFactor() {
        
    }
}
