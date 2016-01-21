/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dellaproject;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Hashtable;
import java.util.Set;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author bhavs
 */
public class ConsoleClass {
    Hashtable<String,ArrayList<String>> data = new Hashtable<>();
    ArrayList<String> dataList;
    public void getData(FXMLDocumentController fx) throws SQLException, ClassNotFoundException {
        Connection con = fx.connect();
        String str = "SELECT * from sample";
        ResultSet rs = con.createStatement().executeQuery(str);
        
        while(rs.next()) {
            dataList = new ArrayList<String>();
            dataList.add(rs.getString("name"));
            dataList.add(rs.getString("description"));
            dataList.add(rs.getString("resolution"));
            dataList.add(rs.getString("creation"));
            dataList.add(rs.getString("duedate"));
            dataList.add(rs.getString("status"));
            data.put(rs.getString("name"),dataList);
        }
        
        System.out.println(data);
        storeActionItem();
    }
    public void storeActionItem() throws ClassNotFoundException {
        System.out.println("storing into file");
        try {
            FileOutputStream fout = new FileOutputStream("actionitems.txt");
            ObjectOutputStream out = new ObjectOutputStream(fout);
            out.writeObject(data);
            fout.close();
        }
        catch(IOException e) {
            e.printStackTrace();
        }
        
    }
    public void readActionItem(FXMLDocumentController fx) throws ClassNotFoundException {
        System.out.println("reading data from file");
        
        try {
            FileInputStream fin = new FileInputStream("actionitems.txt");
            ObjectInputStream in = new ObjectInputStream(fin);
            
            fx.data2 = (Hashtable<String,ArrayList<String>>)in.readObject();
            in.close();
            fin.close();
        }
        catch(IOException e) {
            e.printStackTrace();
        }
        System.out.println("********************");
        System.out.println(fx.data2);
    }
    public void offlineConsoleDisplay(FXMLDocumentController fx) throws ClassNotFoundException {
        readActionItem(fx);
        fx.itemList = FXCollections.observableArrayList();
        System.out.println("offline console display");
        Set<String> keys = fx.data2.keySet();
        for(String key: keys){
            System.out.println(key);
            if(key!=null)
           fx.itemList.add(key);
        }
        System.out.println(fx.itemList);
        fx.console_actionItemList.setItems(fx.itemList);
    }
    public void consoleDisplay(FXMLDocumentController fx) throws SQLException, ClassNotFoundException {
        System.out.println("console display");
        //initializing the list everytime the display is clicked.
        System.out.println("dbstatus = "+fx.dbStatus);
        Connection con2 = fx.connect();
        if(fx.dbStatus == false) {
            offlineConsoleDisplay(fx);
            return;
        }
        
        fx.itemList =  FXCollections.observableArrayList();
        
        //retrieving all the action item names from database
        String str = "SELECT name FROM sample";
        ResultSet rs = con2.createStatement().executeQuery(str);
        while(rs.next()){
            String name = rs.getString("name");
            if(!fx.itemList.contains(name)) {
                fx.itemList.add(name);
            }
        }
        //displays all the list of action item names to the console screen
        fx.console_actionItemList.setItems(fx.itemList); 
    }
    
    
    public void offlineConsoleSelectItem(FXMLDocumentController fx,String item) {
        System.out.println("offline console select item");
            ArrayList<String> temp = new ArrayList<>();
            temp = fx.data2.get(item);
            fx.getConsole_name().setText(temp.get(0));
            fx.getConsole_description().setText(temp.get(1));
            fx.getConsole_resolution().setText(temp.get(2));
            fx.getConsole_CreationDate().setText(temp.get(3));
            fx.getConsole_dueDate().setText(temp.get(4));
            fx.getConsole_Status().setText(temp.get(5));
    }

    /**
     *
     * @param fx
     * @param item
     * @throws SQLException
     */
    public void consoleSelectItem(FXMLDocumentController fx,String item) throws SQLException{
        System.out.println("an item is selected from console");
        
        System.out.println(item);
        Connection con3 = fx.connect();
        if(fx.dbStatus == false) {
            offlineConsoleSelectItem(fx,item);
            return;
        }
        
        String str = "SELECT * FROM sample where name='"+item+"'";
        ResultSet rs = con3.createStatement().executeQuery(str);
        while(rs.next()){
            String name = rs.getString("name");
            String desc = rs.getString("description");
            String res = rs.getString("resolution");
            String creation = rs.getString("creation");
            String duedate = rs.getString("duedate");
            String status = rs.getString("status");
            fx.getConsole_name().setText(name);
            fx.getConsole_description().setText(desc);
            fx.getConsole_resolution().setText(res);
            fx.getConsole_CreationDate().setText(creation);
            fx.getConsole_dueDate().setText(duedate);
            fx.getConsole_Status().setText(status);
            
        }
    }
    public String consoleSelectedItem(FXMLDocumentController fx) {
      String item = fx.console_actionItemList.getSelectionModel().getSelectedItem();
        System.out.println(item);
        return item;
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
        System.out.println(list);
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
    public void consoleInclusionFactor(FXMLDocumentController fx) throws SQLException {
        System.out.println("console inclusion factor");
        Connection con = fx.connect();
        String cmd = fx.getConsole_inclusionFactor().getSelectionModel().getSelectedItem();
        System.out.println(cmd);
        if(cmd.equals("Open Action Items")) {
            cmd = "Open";
        }
        else if(cmd.equals("Closed Action Items")){
            cmd = "Closed";
        }
        else {
            consoleSortingDirection(fx);
            return;
        }
        System.out.println(cmd);
        String sql = "SELECT name FROM sample where status='"+cmd+"'";
        ResultSet rs = con.createStatement().executeQuery(sql);
        ObservableList<String> list = FXCollections.observableArrayList();
        while(rs.next()){
            String name = rs.getString("name"); 
            list.add(name);
        }
        System.out.println(list);
        fx.console_actionItemList.setItems(list);
    }
    public void consoleFirstSorting(FXMLDocumentController fx) throws SQLException {
        System.out.println("console first sorting");
        Connection con = fx.connect();
        String option = fx.getConsole_firstSortingFactor().getSelectionModel().getSelectedItem();
        ObservableList<String> list = FXCollections.observableArrayList();
        System.out.println(option);
        if(option.equals("Creation Date")) {
            sortingCreation(fx);
        }

    }
    public void sortingCreation(FXMLDocumentController fx) throws SQLException {
        String sql = "SELECT creation FROM sample";
        Connection con = fx.connect();
        ResultSet rs = con.createStatement().executeQuery(sql);
        ObservableList<String> list = FXCollections.observableArrayList();
        while(rs.next()){
            String name = rs.getString("creation"); 
            list.add(name);
        }
        list.add("2015/01/01");
        System.out.println(list);
        Collections.sort(list, new Comparator<String>() {
        DateFormat f = new SimpleDateFormat("yyyy/MM/dd");
        @Override
        public int compare(String o1, String o2) {
            try {
                return f.parse(o1).compareTo(f.parse(o2));
            } catch (ParseException e) {
                throw new IllegalArgumentException(e);
            }
        }
    });
    System.out.println(list);
    }
}
