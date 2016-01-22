package dellaproject;
import dellaproject.FXMLDocumentController;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class members{
 public void addmembers(FXMLDocumentController fx) throws SQLException{
        //FXMLDocumentController fx=new FXMLDocumentController();
        Connection connection =  fx.connect();
        System.out.println("in add members");
        String memberName = fx.getMembers_name().getText();
        Statement st = connection.createStatement();
        st.executeUpdate("insert into members VALUES('"+memberName+"')");
        System.out.println("in add members end");
        fx.retreivemember();
    }
    public ObservableList<String> retrievemembers(FXMLDocumentController fx1) throws SQLException{
//       FXMLDocumentController fx1=new FXMLDocumentController();
        System.out.println("in retrieve members");
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
    public void deletemembers(FXMLDocumentController fx3) throws SQLException{
       Connection connection3 =fx3.connect();
       Statement st2x=connection3.createStatement();
       String name_member=fx3.getMembers_indiv().getSelectionModel().getSelectedItem();
       st2x.executeUpdate("delete from association where member='"+name_member+"'");
       Statement st2=connection3.createStatement();  
       st2.executeUpdate("delete from members where mname= '"+ name_member+"' ");
       fx3.retreivemember();
       fx3.getMembers_avail().setItems(null);
       fx3.getMembers_current().setItems(null);
      }
    public ObservableList<String> AvailableTeams(String selected_member,FXMLDocumentController fx) throws SQLException{
//        FXMLDocumentController fx4=new FXMLDocumentController();
        Connection connection4=fx.connect();
        Statement Available=connection4.createStatement();
        ResultSet rs=Available.executeQuery("select tname from teams where tname  not in (select team from association where member='"+selected_member+"')");
        ArrayList <String> available_members=new ArrayList <String>();
       while(rs.next()) {
           available_members.add(rs.getString("tname"));
       }
       ObservableList<String> availablemembers = FXCollections.observableArrayList(available_members);
        return availablemembers;
    }
     public void addaffliation(FXMLDocumentController fx5) throws SQLException{
//         FXMLDocumentController fx5=new FXMLDocumentController();
         String team=fx5.getMembers_avail().getSelectionModel().getSelectedItem();
         String member=fx5.getMembers_indiv().getSelectionModel().getSelectedItem();
         Connection connection5=fx5.connect();
        Statement affliation=connection5.createStatement();
        affliation.executeQuery("Insert into association values('"+team+"','"+member+"')");
         fx5.showAvailableTeams();
         fx5.showCurrentTeams(member);
     }
     public void removeaffliation(FXMLDocumentController fx6) throws SQLException{
//         FXMLDocumentController fx6=new FXMLDocumentController();
        String team=fx6.getMembers_current().getSelectionModel().getSelectedItem();
        String member=fx6.getMembers_indiv().getSelectionModel().getSelectedItem();  
        Connection connection6=fx6.connect();
        Statement affliation=connection6.createStatement();
        affliation.executeQuery("delete from association where team='"+team+"' and member='"+member+"'" );
        fx6.showAvailableTeams();
        fx6.showCurrentTeams(member);
     }
     public ObservableList<String> currentTeams(String selected_member,FXMLDocumentController fx) throws SQLException{
//         FXMLDocumentController fx7=new FXMLDocumentController();
          Connection connection7=fx.connect();
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