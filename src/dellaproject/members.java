package dellaproject;
import dellaproject.FXMLDocumentController;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

class members{
 public void addmembers(String memberName) throws SQLException{
        FXMLDocumentController fx=new FXMLDocumentController();
        Connection connection =  fx.connect();
        Statement st = connection.createStatement();
        st.executeUpdate("insert into members VALUES('"+memberName+"')");
    }
    public ObservableList<String> retrievemembers() throws SQLException{
       FXMLDocumentController fx1=new FXMLDocumentController();
       Connection connection1 =fx1.connect();
       Statement st1=connection1.createStatement();
       ResultSet allmembers = st1.executeQuery("select mname from members");
       ArrayList <String> all_members=new ArrayList <String>();      
       ObservableList<String> membernames = FXCollections.observableArrayList();
       while(allmembers.next()) {
           all_members.add(allmembers.getString("mname"));
       }
       membernames.addAll(all_members);
       return membernames;
    }
    public void deletemembers(String name_member) throws SQLException{
       FXMLDocumentController fx3=new FXMLDocumentController();
       Connection connection3 =fx3.connect();
       Statement st2=connection3.createStatement();  
       st2.executeUpdate("delete from members where mname= '"+ name_member+"' ");
    }
    public ObservableList<String> AvailableTeams(String selected_member) throws SQLException{
        FXMLDocumentController fx4=new FXMLDocumentController();
        Connection connection4=fx4.connect();
        Statement Available=connection4.createStatement();
        ResultSet rs=Available.executeQuery("select tname from teams where tname  not in (select team from association where member='"+selected_member+"')");
        ArrayList <String> available_members=new ArrayList <String>();
       while(rs.next()) {
           available_members.add(rs.getString("tname"));
       }
       ObservableList<String> availablemembers = FXCollections.observableArrayList(available_members);
        return availablemembers;
    }
     public void addaffliation(String team,String member) throws SQLException{
         FXMLDocumentController fx5=new FXMLDocumentController();
         Connection connection5=fx5.connect();
        Statement affliation=connection5.createStatement();
        affliation.executeQuery("Insert into association values('"+team+"','"+member+"')");
         
     }
     public void removeaffliation(String team,String member) throws SQLException{
          FXMLDocumentController fx6=new FXMLDocumentController();
          Connection connection6=fx6.connect();
        Statement affliation=connection6.createStatement();
        affliation.executeQuery("delete from association where team='"+team+"' and member='"+member+"'" );
     }
     public ObservableList<String> currentTeams(String selected_member) throws SQLException{
         FXMLDocumentController fx7=new FXMLDocumentController();
          Connection connection7=fx7.connect();
          Statement st=connection7.createStatement();
          ResultSet rs=st.executeQuery("select team from association where member='"+selected_member+"'");
        ArrayList <String> currentteams=new ArrayList<String>();
        while(rs.next()){
            currentteams.add(rs.getString("team"));
         }
        ObservableList<String> current_teams = FXCollections.observableArrayList(currentteams);
        return current_teams;
     }

}