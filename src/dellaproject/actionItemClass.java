/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dellaproject;

import java.sql.Connection;
import java.util.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
    public void alertDate() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText("duedate cannot be less than current date");
        alert.setContentText("please enter correct date in yyyy/mm/dd format");
        alert.showAndWait();
    }
    public void alertNameExists() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("Name already exists");
            alert.setContentText("please select another name for the action item");

            alert.showAndWait();
    }
    public void alertNameIsEmpty() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("Name cannot be empty");
            alert.setContentText("please select ar name for your action item");
            alert.showAndWait();
    }
    public void alertWrongDate() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Warning Dialog");
            alert.setHeaderText("wrong date format");
            alert.setContentText("please enter the date in correct format");
            alert.setContentText("yyyy/mm/dd");

            alert.showAndWait();
    }
    public void saveForm(FXMLDocumentController fx) throws SQLException, ParseException {
              
        System.out.println("create a new action form");
        boolean forDate = false;
        Connection con1 =  fx.connect();
        Statement st = con1.createStatement();
        String name = fx.getAction_Name().getText();
        String Description = fx.getAction_Description().getText();
        String Resolution = fx.getAction_Resolution().getText();
        String date2 = fx.getAction_dueDate().getText();
        String status = fx.getAction_Status().getSelectionModel().getSelectedItem();
        Date creationDate = new Date();
        Date dueDate = new Date();
        //stores the system date as the creation date into the database(date1).
        DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
        Date dateobj = new Date();
        String date1 = df.format(dateobj);
        try {
            creationDate = df.parse(date1);
            dueDate = df.parse(date2);
        }
        catch(ParseException e) {
            
        }
        System.out.println("date"+creationDate+"...."+dueDate);
        //checking duedate entered is valid.
        try {
            System.out.println("in try");
            df.parse(date2);
            if(date2.length()==10) {
                if(dueDate.compareTo(creationDate)>=0) {
                           forDate = true;
                }
                else {
                    alertDate();
                    return;
                }
            } 
        }
        catch(ParseException e) {
            System.out.println("in catch");
        }
        String sql = "SELECT name FROM sample";
        ResultSet rs = con1.createStatement().executeQuery(sql);
        ObservableList<String> itemList = FXCollections.observableArrayList();
        while(rs.next()){
            String str = rs.getString("name");
            itemList.add(str);
        }
        //if the action item name already exists alert message is shown.
        if(itemList.contains(name)&&forDate==true) {
            alertNameExists();
            return;
        }
        // if the action item name is empty, warning is shown
        else if(name.equals("") && forDate ==true) {
            alertNameIsEmpty();
            return;
        }
        //if duedate is not in correct format.
        else if(forDate==false){
            alertWrongDate();
            return;
        }
        //inserting values into the database
        sql = "INSERT INTO sample VALUES('"+name+"','"+Description+"','"+Resolution+"','"+date1+"','"+date2+"','"+status+"')";
        st.executeUpdate(sql);
        st.close();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText("Hurray!!!");
            alert.setContentText("your action item is created succesfully!!!");
            alert.showAndWait();
        fx.clearForm();
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
       String date1 = fx.getAction_dueDate().getText();
       String date2 = fx.getAction_dueDate().getText();
       String status = fx.getAction_Status().getSelectionModel().getSelectedItem();
       boolean forDate = false;
       Date dueDate = new Date();
       Date creationDate = new Date();
       
       //checking duedate entered is valid.
        DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
        try {
            System.out.println("in try");
            dueDate = df.parse(date2);
            creationDate = df.parse(date1);
            if(date2.length()==10) {
                if(dueDate.compareTo(creationDate)>=0) {
                           forDate = true;
                }
                else {
                    alertDate();
                    return;
                }
            } 
        }
        catch(ParseException e) {
            System.out.println("in catch");
        }
//        String sql = "SELECT name FROM sample";
//        ResultSet rs = con1.createStatement().executeQuery(sql);
//        ObservableList<String> itemList = FXCollections.observableArrayList();
//        while(rs.next()){
//            String str = rs.getString("name");
//            itemList.add(str);
//        }
//        //if the action item name already exists alert message is shown.
//        if(itemList.contains(name)&&forDate==true) {
//            alertNameExists();
//            return;
//        }
        // if the action item name is empty, warning is shown
        if(name.equals("") && forDate ==true) {
            alertNameIsEmpty();
            return;
        }
        //if duedate is not in correct format.
        else if(forDate==false){
            alertWrongDate();
            return;
        }
        //inserting values into the database
       String sql = "UPDATE sample SET description='"+Description+"',resolution='"+Resolution+"',duedate='"+date2+"',status='"+status+"' WHERE name = '"+name+"'";
       ResultSet rs = con1.createStatement().executeQuery(sql);
       Alert alert = new Alert(Alert.AlertType.INFORMATION);
       alert.setTitle("Information Dialog");
       alert.setHeaderText("Hurray!!!");
       alert.setContentText("your action item is updated succesfully!!!");
       alert.showAndWait();
        
    }
    public void displayActionItemScreen(FXMLDocumentController fx,String item) throws SQLException {
        System.out.println("action item screen is clicked");
        
        System.out.println(item);
        Connection con3 = fx.connect();
        //displays current date.
        DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
        Date dateobj = new Date();
        String date1 = df.format(dateobj);
        fx.getAction_creation().setText(date1);
        String str = "SELECT * FROM sample where name='"+item+"'";
        ResultSet rs = con3.createStatement().executeQuery(str);
        while(rs.next()){
            String name = rs.getString("name");
            String desc = rs.getString("description");
            String res = rs.getString("resolution");
            String date = rs.getString("creation");
            String duedate = rs.getString("duedate");
            String status = rs.getString("status");
            fx.getAction_Name().setText(name);
            fx.getAction_Description().setText(desc);
            fx.getAction_Resolution().setText(res);
            fx.getAction_creation().setText(date);  
            fx.getAction_dueDate().setText(duedate);
            fx.getAction_Status().getSelectionModel().select(status);
        }
        fx.getAction_actionItems().setItems(fx.itemList);
        if(item != null && !item.isEmpty()) {
           fx.getAction_actionItems().getSelectionModel().select(item);
        }
    }
    public void deleteActionItem(FXMLDocumentController fx) throws SQLException {
        Connection con3 = fx.connect();
        String name = fx.getAction_Name().getText();
        String str = "DELETE FROM sample where name='"+name+"'";
        ResultSet rs = con3.createStatement().executeQuery(str);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText("Hurray!!!");
            alert.setContentText("your action item '"+name+"' is deleted succesfully!!!");
            alert.showAndWait();
        fx.clearForm();
        fx.clearForm();
    }
    
}
