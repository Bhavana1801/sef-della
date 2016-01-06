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
//    @FXML private TextField console_Name;
//    @FXML private final TextField console_Description;
//    @FXML private TextField console_Resolution;
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
//    private final ListView console_actionItemList;
   
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
    public void saveForm(ActionEvent event) throws SQLException {
        System.out.println("create a new action form");
       Connection con1 =  connect();
       Statement st = con1.createStatement();
       String name = action_Name.getText();
       String Description = action_Description.getText();
       String Resolution = action_Resolution.getText();
       String sql;
        sql = "INSERT INTO sample VALUES('"+name+"','"+Description+"','"+Resolution+"')";
        st.executeUpdate(sql);
    }
    public void clearForm(ActionEvent event) throws SQLException{
        System.out.println("clear this form");
        action_Name.setText("");
        action_Description.setText("");
        action_Resolution.setText("");
//        consoleDisplay();
    }
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
    
}
