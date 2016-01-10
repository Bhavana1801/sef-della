/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dellaproject;
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
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.Timer;
import java.util.TimerTask;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
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
    @FXML private Circle console_circle;
    @FXML private Circle console_Circle;
    
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
    @FXML private Circle action_Circle;
    //members screen
    
    @FXML private TextField members_name;
    @FXML private ListView<String> members_indiv;
    @FXML private ListView<String> members_avail;
    @FXML private ListView<String> members_current;
    @FXML private Button members_addToList;
    @FXML private Button members_removeFromList;
    @FXML private Button members_addAffliation;
    @FXML private Button members_removeAffliation;
    @FXML private Circle members_Circle;
    @FXML private Label console_label;
    @FXML private Label action_label;
    @FXML private Label members_label;
    @FXML private Label teams_label;
    
    
     //teams screen variables
    
    @FXML private TextField teams_name;
    @FXML private ListView<String> teams_known;
    @FXML private ListView<String> teams_avail;
    @FXML private ListView<String> teams_current;
    @FXML private Button teams_addToList;
    @FXML private Button teams_removeFromList;
    @FXML private Button teams_addAssociation;
    @FXML private Button teams_removeAssociation;
    @FXML private Circle teams_Circle;
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

    public Label getLabel() {
        return label;
    }

    public void setLabel(Label label) {
        this.label = label;
    }

    public Tab getConsole_tab() {
        return console_tab;
    }

    public void setConsole_tab(Tab console_tab) {
        this.console_tab = console_tab;
    }

    public Tab getAction_tab() {
        return action_tab;
    }

    public void setAction_tab(Tab action_tab) {
        this.action_tab = action_tab;
    }

    public Tab getMembers_tab() {
        return members_tab;
    }

    public void setMembers_tab(Tab members_tab) {
        this.members_tab = members_tab;
    }

    public Tab getTeams_tab() {
        return teams_tab;
    }

    public void setTeams_tab(Tab teams_tab) {
        this.teams_tab = teams_tab;
    }

    public Button getQuitButton() {
        return quitButton;
    }

    public void setQuitButton(Button quitButton) {
        this.quitButton = quitButton;
    }

    public ListView<String> getConsole_actionItemList() {
        return console_actionItemList;
    }

    public void setConsole_actionItemList(ListView<String> console_actionItemList) {
        this.console_actionItemList = console_actionItemList;
    }

    public ComboBox<String> getConsole_inclusionFactor() {
        return console_inclusionFactor;
    }

    public void setConsole_inclusionFactor(ComboBox<String> console_inclusionFactor) {
        this.console_inclusionFactor = console_inclusionFactor;
    }

    public ComboBox<String> getConsole_sortingDirection() {
        return console_sortingDirection;
    }

    public void setConsole_sortingDirection(ComboBox<String> console_sortingDirection) {
        this.console_sortingDirection = console_sortingDirection;
    }

    public ComboBox<String> getConsole_firstSortingFactor() {
        return console_firstSortingFactor;
    }

    public void setConsole_firstSortingFactor(ComboBox<String> console_firstSortingFactor) {
        this.console_firstSortingFactor = console_firstSortingFactor;
    }

    public ComboBox<String> getConsole_secondSortingFactor() {
        return console_secondSortingFactor;
    }

    public void setConsole_secondSortingFactor(ComboBox<String> console_secondSortingFactor) {
        this.console_secondSortingFactor = console_secondSortingFactor;
    }

    public Label getConsole_CreationDate() {
        return console_CreationDate;
    }

    public void setConsole_CreationDate(Label console_CreationDate) {
        this.console_CreationDate = console_CreationDate;
    }

    public Label getConsole_dueDate() {
        return console_dueDate;
    }

    public void setConsole_dueDate(Label console_dueDate) {
        this.console_dueDate = console_dueDate;
    }

    public Label getConsole_Status() {
        return console_Status;
    }

    public void setConsole_Status(Label console_Status) {
        this.console_Status = console_Status;
    }

    public Label getConsole_memberDetails() {
        return console_memberDetails;
    }

    public void setConsole_memberDetails(Label console_memberDetails) {
        this.console_memberDetails = console_memberDetails;
    }

    public Label getConsole_teamDetails() {
        return console_teamDetails;
    }

    public void setConsole_teamDetails(Label console_teamDetails) {
        this.console_teamDetails = console_teamDetails;
    }

    public TextField getConsole_name() {
        return console_name;
    }

    public void setConsole_name(TextField console_name) {
        this.console_name = console_name;
    }

    public TextArea getConsole_description() {
        return console_description;
    }

    public void setConsole_description(TextArea console_description) {
        this.console_description = console_description;
    }

    public TextArea getConsole_resolution() {
        return console_resolution;
    }

    public void setConsole_resolution(TextArea console_resolution) {
        this.console_resolution = console_resolution;
    }

    public Circle getConsole_circle() {
        return console_circle;
    }

    public void setConsole_circle(Circle console_circle) {
        this.console_circle = console_circle;
    }

    public Circle getConsole_Circle() {
        return console_Circle;
    }

    public void setConsole_Circle(Circle console_Circle) {
        this.console_Circle = console_Circle;
    }

    public ComboBox<String> getAction_actionItems() {
        return action_actionItems;
    }

    public void setAction_actionItems(ComboBox<String> action_actionItems) {
        this.action_actionItems = action_actionItems;
    }

    public ComboBox<String> getAction_inclusionFactor() {
        return action_inclusionFactor;
    }

    public void setAction_inclusionFactor(ComboBox<String> action_inclusionFactor) {
        this.action_inclusionFactor = action_inclusionFactor;
    }

    public ComboBox<String> getAction_sortingDirection() {
        return action_sortingDirection;
    }

    public void setAction_sortingDirection(ComboBox<String> action_sortingDirection) {
        this.action_sortingDirection = action_sortingDirection;
    }

    public ComboBox<String> getAction_firstSortingFactor() {
        return action_firstSortingFactor;
    }

    public void setAction_firstSortingFactor(ComboBox<String> action_firstSortingFactor) {
        this.action_firstSortingFactor = action_firstSortingFactor;
    }

    public ComboBox<String> getAction_secondSortingFactor() {
        return action_secondSortingFactor;
    }

    public void setAction_secondSortingFactor(ComboBox<String> action_secondSortingFactor) {
        this.action_secondSortingFactor = action_secondSortingFactor;
    }

    public TextField getAction_Name() {
        return action_Name;
    }

    public void setAction_Name(TextField action_Name) {
        this.action_Name = action_Name;
    }

    public TextArea getAction_Description() {
        return action_Description;
    }

    public void setAction_Description(TextArea action_Description) {
        this.action_Description = action_Description;
    }

    public TextArea getAction_Resolution() {
        return action_Resolution;
    }

    public void setAction_Resolution(TextArea action_Resolution) {
        this.action_Resolution = action_Resolution;
    }

    public ComboBox<String> getAction_Member() {
        return action_Member;
    }

    public void setAction_Member(ComboBox<String> action_Member) {
        this.action_Member = action_Member;
    }

    public ComboBox<String> getAction_Team() {
        return action_Team;
    }

    public void setAction_Team(ComboBox<String> action_Team) {
        this.action_Team = action_Team;
    }

    public TextField getAction_dueDate() {
        return action_dueDate;
    }

    public void setAction_dueDate(TextField action_dueDate) {
        this.action_dueDate = action_dueDate;
    }

    public Button getAction_Update() {
        return action_Update;
    }

    public void setAction_Update(Button action_Update) {
        this.action_Update = action_Update;
    }

    public Label getAction_creation() {
        return action_creation;
    }

    public void setAction_creation(Label action_creation) {
        this.action_creation = action_creation;
    }

    public ComboBox<String> getAction_Status() {
        return action_Status;
    }

    public void setAction_Status(ComboBox<String> action_Status) {
        this.action_Status = action_Status;
    }

    public Circle getAction_Circle() {
        return action_Circle;
    }

    public void setAction_Circle(Circle action_Circle) {
        this.action_Circle = action_Circle;
    }

    public TextField getMembers_name() {
        return members_name;
    }

    public void setMembers_name(TextField members_name) {
        this.members_name = members_name;
    }

    public ListView<String> getMembers_indiv() {
        return members_indiv;
    }

    public void setMembers_indiv(ListView<String> members_indiv) {
        this.members_indiv = members_indiv;
    }

    public ListView<String> getMembers_avail() {
        return members_avail;
    }

    public void setMembers_avail(ListView<String> members_avail) {
        this.members_avail = members_avail;
    }

    public ListView<String> getMembers_current() {
        return members_current;
    }

    public void setMembers_current(ListView<String> members_current) {
        this.members_current = members_current;
    }

    public Button getMembers_addToList() {
        return members_addToList;
    }

    public void setMembers_addToList(Button members_addToList) {
        this.members_addToList = members_addToList;
    }

    public Button getMembers_removeFromList() {
        return members_removeFromList;
    }

    public void setMembers_removeFromList(Button members_removeFromList) {
        this.members_removeFromList = members_removeFromList;
    }

    public Button getMembers_addAffliation() {
        return members_addAffliation;
    }

    public void setMembers_addAffliation(Button members_addAffliation) {
        this.members_addAffliation = members_addAffliation;
    }

    public Button getMembers_removeAffliation() {
        return members_removeAffliation;
    }

    public void setMembers_removeAffliation(Button members_removeAffliation) {
        this.members_removeAffliation = members_removeAffliation;
    }

    public Circle getMembers_Circle() {
        return members_Circle;
    }

    public void setMembers_Circle(Circle members_Circle) {
        this.members_Circle = members_Circle;
    }

    public Label getConsole_label() {
        return console_label;
    }

    public void setConsole_label(Label console_label) {
        this.console_label = console_label;
    }

    public Label getAction_label() {
        return action_label;
    }

    public void setAction_label(Label action_label) {
        this.action_label = action_label;
    }

    public Label getMembers_label() {
        return members_label;
    }

    public void setMembers_label(Label members_label) {
        this.members_label = members_label;
    }

    public Label getTeams_label() {
        return teams_label;
    }

    public void setTeams_label(Label teams_label) {
        this.teams_label = teams_label;
    }

    public TextField getTeams_name() {
        return teams_name;
    }

    public void setTeams_name(TextField teams_name) {
        this.teams_name = teams_name;
    }

    public ListView<String> getTeams_known() {
        return teams_known;
    }

    public void setTeams_known(ListView<String> teams_known) {
        this.teams_known = teams_known;
    }

    public ListView<String> getTeams_avail() {
        return teams_avail;
    }

    public void setTeams_avail(ListView<String> teams_avail) {
        this.teams_avail = teams_avail;
    }

    public ListView<String> getTeams_current() {
        return teams_current;
    }

    public void setTeams_current(ListView<String> teams_current) {
        this.teams_current = teams_current;
    }

    public Button getTeams_addToList() {
        return teams_addToList;
    }

    public void setTeams_addToList(Button teams_addToList) {
        this.teams_addToList = teams_addToList;
    }

    public Button getTeams_removeFromList() {
        return teams_removeFromList;
    }

    public void setTeams_removeFromList(Button teams_removeFromList) {
        this.teams_removeFromList = teams_removeFromList;
    }

    public Button getTeams_addAssociation() {
        return teams_addAssociation;
    }

    public void setTeams_addAssociation(Button teams_addAssociation) {
        this.teams_addAssociation = teams_addAssociation;
    }

    public Button getTeams_removeAssociation() {
        return teams_removeAssociation;
    }

    public void setTeams_removeAssociation(Button teams_removeAssociation) {
        this.teams_removeAssociation = teams_removeAssociation;
    }

    public Circle getTeams_Circle() {
        return teams_Circle;
    }

    public void setTeams_Circle(Circle teams_Circle) {
        this.teams_Circle = teams_Circle;
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
        
        new Timer().schedule(
         new TimerTask() {
             @Override
              public void run() {
              String Status = new InternetConnection().getInternetStatus();
              if(Status.equals("Online")) {
                  console_Circle.setFill(Color.GREEN);
                  action_Circle.setFill(Color.GREEN);
                  members_Circle.setFill(Color.GREEN);
                  teams_Circle.setFill(Color.GREEN);
                     boolean isOnline = true;
              }
              else {
                    console_Circle.setFill(Color.RED);
                    action_Circle.setFill(Color.RED);
                    members_Circle.setFill(Color.RED);
                    teams_Circle.setFill(Color.RED);
                       console_label.setText("Trying to reconnect...!!!");
                       action_label.setText("Trying to reconnect...!!!");
                       members_label.setText("Trying to reconnect...!!!");
                       teams_label.setText("Trying to reconnect...!!!");
                       
                     boolean isOnline = false;
              }
      }
         }, 0, 500);
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
        actionItemClass obj = new actionItemClass();
        obj.saveForm(this);
    }
    
    //the form is cleared when we click clear form button
    public void clearForm(ActionEvent event) throws SQLException{
        actionItemClass obj = new actionItemClass();
        obj.clearForm(this);
    }
    //displays list of action items list on console screen
    public void consoleDisplay() throws SQLException {
        ConsoleClass obj = new ConsoleClass();
        obj.consoleDisplay(this);
    }
    //details of a particular item selected is shown on console screen
    public void consoleSelectItem() throws SQLException{
        System.out.println("came here");
        ConsoleClass obj = new ConsoleClass();
        obj.consoleSelectItem(this);
    }
    //the action item form is updated.
    public void updateActionItem() throws SQLException {
        actionItemClass obj = new actionItemClass();
        obj.updateActionItem(this);
        
    }
    //select an item from console and if we click action item screen,details are displayed
    public void displayActionItemScreen() throws SQLException {
        actionItemClass obj = new actionItemClass();
        obj.displayActionItemScreen(this);
    }
    public void consoleSortingDirection() throws SQLException {
        ConsoleClass obj = new ConsoleClass();
        obj.consoleSortingDirection(this);
    }
    // addToList button is disabled when user clicks on right side box
    public void disableAddToList() {
        members_addToList.setDisable(true);
        members_removeFromList.setDisable(false);
        teams_addToList.setDisable(true);
        teams_removeFromList.setDisable(false);
    }
    // removeFromList button is disabled when user clicks on left side box
    public void disableRemoveFromList() {
        System.out.println("disable remove from list");
        members_addToList.setDisable(false);
        members_removeFromList.setDisable(true);
        teams_addToList.setDisable(false);
        teams_removeFromList.setDisable(true);
        
    }
    // addAffliation button is disabled when user clicks on right side box
    public void disableAddAffliation() {
        System.out.println("disable add affliation");
        members_addAffliation.setDisable(true);
        members_removeAffliation.setDisable(false);
    }
    // removeAffliation button is disabled when user clicks on left side box
    public void disableRemoveAffliation() {
        members_removeAffliation.setDisable(true);
        members_addAffliation.setDisable(false);
    }
    // addAssociation button is disabled when user clicks on right side box
    public void disableAddAssociation() {
        System.out.println("disable add affliation");
        teams_addAssociation.setDisable(true);
        teams_removeAssociation.setDisable(false);
    }
    // removeAssociation button is disabled when user clicks on left side box
    public void disableRemoveAssociation() {
        teams_removeAssociation.setDisable(true);
        teams_addAssociation.setDisable(false);
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
    
    public void consoleFirstSorting() {
        
    }
    public void consoleSecondSorting() {
        
    }
    public void consoleInclusionFactor() {
        
    }
    public void addMember() throws SQLException{
        
       members m=new members();
        String membername=members_name.getText();
        m.addmembers(membername);
        retreivemember();
    }
    public void removeMember() throws SQLException{
       String name = members_indiv.getSelectionModel().getSelectedItem();
        members m1 =new members();
        m1.deletemembers(name);
        retreivemember();
    }
    public void retreivemember() throws SQLException{
         members m2= new members();
        ObservableList<String> allmember = FXCollections.observableArrayList();
        allmember=m2.retrievemembers();
        members_indiv.setItems(allmember);
    }
    public void showAvailableTeams() throws SQLException{
       members m3=new members();
     ObservableList<String> availableteam = FXCollections.observableArrayList();
     String name=members_indiv.getSelectionModel().getSelectedItem();
      availableteam=m3.AvailableTeams(name);
      members_avail.setItems(availableteam);
      showCurrentTeams(name);
    }
    public void showCurrentTeams(String name) throws SQLException{
        members m4=new members();
        ObservableList<String> currentteam = FXCollections.observableArrayList();
     //String name=members_indiv.getSelectionModel().getSelectedItem();
      currentteam=m4.currentTeams(name);
      members_current.setItems(currentteam);
    }
    public void addAffiliation() throws SQLException {
       String teamname=(String)members_avail.getSelectionModel().getSelectedItem();
       String  membername=(String)members_indiv.getSelectionModel().getSelectedItem();     
       members m5=new members();
       m5.addaffliation(teamname,membername);
       showAvailableTeams();
       showCurrentTeams(membername);
    }
    public void removeAffiliation() throws SQLException {
      String teamname=(String)members_avail.getSelectionModel().getSelectedItem();
       String  membername=(String)members_indiv.getSelectionModel().getSelectedItem();
       members m4=new members();
       m4.removeaffliation(teamname,membername);
       showAvailableTeams();
       showCurrentTeams(membername);
    }
    public void addTeam() throws SQLException {
        teams t=new teams();
        String teamname=teams_name.getText();
        t.addteam(teamname);
        retrieveteams();
    }
    public void teamsScreen() throws SQLException {
        teams_addToList.setDisable(true);
        teams_removeFromList.setDisable(true);
        teams_addAssociation.setDisable(true);
        teams_removeAssociation.setDisable(true);
        showAvailableMembers();
    }
    public void membersScreen() throws SQLException {
        members_addToList.setDisable(true);
        members_removeFromList.setDisable(true);
        members_addAffliation.setDisable(true);
        members_removeAffliation.setDisable(true);
//        ObservableList<String> teamsList = FXCollections.observableArrayList();
//        teams t = new teams();
//        teamsList = t.retrieveteams();
        
    }
    
    public void removeTeam() throws SQLException{
        String name = teams_name.getText();
        teams t1=new teams();
        t1.deleteteam(name);
        retrieveteams();
    }
     public void retrieveteams() throws SQLException{
         teams t2= new teams();
        ObservableList<String> allteams = FXCollections.observableArrayList();
        allteams=t2.retrieveteams();
        teams_known.setItems(allteams);
    }
      public void showAvailableMembers() throws SQLException {
        disableAddAffliation();
        Connection connection1=connect();
        Statement Available1=connection1.createStatement();
        String team=(String) teams_known.getSelectionModel().getSelectedItem();
        ResultSet rs=Available1.executeQuery("select mname from members where mname  not in (select member from association where team='"+team+"')");
        ArrayList <String> availablemember=new ArrayList <String>();
       while(rs.next()) {
           availablemember.add(rs.getString("mname"));
       }
       ObservableList<String> avilable_members = FXCollections.observableArrayList(availablemember);
       teams_avail.setItems(avilable_members);
       showcurrentmembers(team);
        
    }
       public void showcurrentmembers(String selected_team) throws SQLException{
        //System.out.println(sr);
        Connection connection=connect();
        Statement st=connection.createStatement();
        ResultSet rs=st.executeQuery("select member from association where team='"+selected_team+"'");
        ArrayList <String> currentmembers=new ArrayList<>();
        while(rs.next()){
            currentmembers.add(rs.getString("member"));
         }
        ObservableList<String> current_members = FXCollections.observableArrayList(currentmembers);
        teams_current.setItems(current_members);
        
     }
    public void addAssociation() throws SQLException {
        String team_associated=(String)teams_known.getSelectionModel().getSelectedItem();
        String member_associated_toteam=(String)teams_avail.getSelectionModel().getSelectedItem();
        Connection connection=connect();
        Statement association=connection.createStatement();
        association.executeUpdate("Insert into association values('"+team_associated+"','"+member_associated_toteam+"')");
        showcurrentmembers(team_associated);
        showAvailableMembers(); 
    }
    public void removeAssociation() throws SQLException {
        Connection connection=connect();
        Statement st=connection.createStatement();
         String team_associated=(String)teams_known.getSelectionModel().getSelectedItem();
        String member_associated_toteam=(String)teams_avail.getSelectionModel().getSelectedItem();
        st.executeUpdate("delete from association where team='"+team_associated+"' and member='"+member_associated_toteam+"'" );
        showcurrentmembers(team_associated);
        showAvailableMembers();
    }
}
