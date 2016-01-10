/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dellaproject;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author bhavs
 */
public class ConsoleClass {
    public void consoleDisplay(FXMLDocumentController fx) throws SQLException {
        System.out.println("console display");
        Connection con2 = fx.connect();
        //retrieving all the action item names from database
        String str = "SELECT name FROM sample";
        ResultSet rs = con2.createStatement().executeQuery(str);
        ObservableList<String> itemList = FXCollections.observableArrayList();
        while(rs.next()){
            String name = rs.getString("name");
//            System.out.println(name);
            itemList.add(name);
        }
        //displays all the list of action item names to the console screen
        fx.console_actionItemList.setItems(itemList); 
    }
    public void consoleSelectItem(FXMLDocumentController fx) throws SQLException{
        System.out.println("an item is selected from console");
        String item = fx.console_actionItemList.getSelectionModel().getSelectedItem();
        System.out.println(item);
        Connection con3 = fx.connect();
        String str = "SELECT * FROM sample where name='"+item+"'";
        ResultSet rs = con3.createStatement().executeQuery(str);
        while(rs.next()){
            String name = rs.getString("name");
            String desc = rs.getString("description");
            String res = rs.getString("resolution");
            System.out.println(name+"jjjj");
            fx.getConsole_name().setText(name);
            fx.getConsole_description().setText(desc);
            fx.getConsole_resolution().setText(res);
            
        }
    }
    public void consoleSortingDirection(FXMLDocumentController fx) throws SQLException {
        System.out.println("console sorting direction");
        Connection con = fx.connect();
        String cmd = fx.getConsole_sortingDirection().getSelectionModel().getSelectedItem();
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
            fx.console_actionItemList.setItems(list);
            return;
        }
        ObservableList<String> list2 = FXCollections.observableArrayList();
        for (int i = list.size()-1;i>=0;i--) {
            list2.add(list.get(i));
        }
        fx.console_actionItemList.setItems(list2);
    }
}
