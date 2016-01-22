package dellaproject;
import dellaproject.FXMLDocumentController;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class teams{
  public void addteam(FXMLDocumentController fx) throws SQLException{
        //FXMLDocumentController fx=new FXMLDocumentController();
        Connection connection =  fx.connect();
        String team_name=fx.getTeams_name().getText();
        Statement st = connection.createStatement();
        st.executeUpdate("insert into teams VALUES('"+team_name+"')");
        fx.retrieveteams();
    }
    public ObservableList<String> retrieveteams(FXMLDocumentController fx) throws SQLException{
//       FXMLDocumentController fx1=new FXMLDocumentController();
       Connection connection1 =fx.connect();
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
    public void deleteteam(FXMLDocumentController fx2) throws SQLException{
       //FXMLDocumentController fx2=new FXMLDocumentController();
       String teamname=fx2.getTeams_known().getSelectionModel().getSelectedItem();
       Connection connection2 =fx2.connect();
   Statement st2x=connection2.createStatement();
   st2x.executeUpdate("delete from association where team='"+teamname+"'");
       Statement st2=connection2.createStatement();  
       st2.executeUpdate("delete from teams where tname= '"+teamname+"' ");
       fx2.retrieveteams();
       fx2.showAvailableMembers();
       fx2.getTeams_current().setItems(null);
       fx2.getTeams_avail().setItems(null);
       
    }


}