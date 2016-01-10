/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dellaproject;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

/**
 *
 * @author bhavs
 */
public class actionItemClass {
    public void saveForm(FXMLDocumentController fx) throws SQLException, ParseException {
              
        System.out.println("create a new action form");
       Connection con1 =  fx.connect();
       Statement st = con1.createStatement();
       String name = fx.getAction_Name().getText();
       System.out.println(name);
       String Description = fx.getAction_Description().getText();
       System.out.println(Description);
       String Resolution = fx.getAction_Resolution().getText();
       String sql = "SELECT name FROM sample";
        ResultSet rs = con1.createStatement().executeQuery(sql);
        ObservableList<String> itemList = FXCollections.observableArrayList();
        while(rs.next()){
            String str = rs.getString("name");
            itemList.add(str);
        }
        //if the action item name already exists alert message is shown.
        if(itemList.contains(name)) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("Name already exists");
            alert.setContentText("please select another name for the action item");

            alert.showAndWait();
            return;
        }
        // if the action item name is empty, warning is shown
        else if(name.equals("")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("Name cannot be empty");
            alert.setContentText("please select ar name for your action item");

            alert.showAndWait();
            return;
        }
        //inserting values into the database
        sql = "INSERT INTO sample VALUES('"+name+"','"+Description+"','"+Resolution+"')";
        st.executeUpdate(sql);
        st.close();
    }
    public void clearForm(FXMLDocumentController fx) throws SQLException{
        System.out.println("clear this form");
        fx.getAction_Name().setText("");
        fx.getAction_Description().setText("");
        fx.getAction_Resolution().setText("");
    }
    public void updateActionItem(FXMLDocumentController fx) throws SQLException {
        System.out.println("update action form");
       Connection con1 =  fx.connect();
       Statement st = con1.createStatement();
       String name = fx.getAction_Name().getText();
       String Description = fx.getAction_Description().getText();
       String Resolution = fx.getAction_Resolution().getText();
       String sql = "UPDATE sample SET description='"+Description+"',resolution='"+Resolution+"'WHERE name='"+name+"'";
       System.out.println(name+Description+Resolution);
        ResultSet rs = con1.createStatement().executeQuery(sql);
        
    }
    public void displayActionItemScreen(FXMLDocumentController fx) throws SQLException {
        System.out.println("action item screen is clicked");
        String item = fx.console_actionItemList.getSelectionModel().getSelectedItem();
        System.out.println(item);
        Connection con3 = fx.connect();
        String str = "SELECT * FROM sample where name='"+item+"'";
        ResultSet rs = con3.createStatement().executeQuery(str);
        while(rs.next()){
            String name = rs.getString("name");
            String desc = rs.getString("description");
            String res = rs.getString("resolution");
            fx.getAction_Name().setText(name);
            fx.getAction_Description().setText(desc);
            fx.getAction_Resolution().setText(res);
            
        }
    }
    
}
