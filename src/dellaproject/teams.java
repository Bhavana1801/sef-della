package dellaproject;
import dellaproject.FXMLDocumentController;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

class teams{
  public void addteam(String team_name) throws SQLException{
        FXMLDocumentController fx=new FXMLDocumentController();
        Connection connection =  fx.connect();
        Statement st = connection.createStatement();
        st.executeUpdate("insert into teams VALUES('"+team_name+"')");
    }
    public ObservableList<String> retrieveteams() throws SQLException{
       FXMLDocumentController fx1=new FXMLDocumentController();
       Connection connection1 =fx1.connect();
       Statement st1=connection1.createStatement();
       ResultSet allteams = st1.executeQuery("select tname from teams");
       ArrayList <String> all_teams=new ArrayList <String>();      
       ObservableList<String> teams = FXCollections.observableArrayList();
       while(allteams.next()) {
           all_teams.add(allteams.getString("tname"));
       }
       teams.addAll(all_teams);
       return teams;
    }
    public void deleteteam(String teamname) throws SQLException{
       FXMLDocumentController fx2=new FXMLDocumentController();
       Connection connection2 =fx2.connect();
       Statement st2=connection2.createStatement();  
       st2.executeUpdate("delete from teams where tname= '"+teamname+"' ");
    }


}