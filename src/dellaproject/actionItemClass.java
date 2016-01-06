/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dellaproject;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

/**
 *
 * @author bhavs
 */
public class actionItemClass {
    @FXML
    private TextField actionItemName;
    private String actionName;
    private TextField actionItemDescription;
    private String actionDescription;
    private TextField actionItemResolution;
    private String actionResolution;
    private TextField actionItemDueDate;
    private String dueDate;
    private ComboBox<String> actionItems;
    private ComboBox inclusionFactor;
    private ComboBox sortingDirection;
    private ComboBox fisrtSortingFactor;
    private ComboBox secondSortingFactor;
    private ComboBox actionItemStatus;
    private ComboBox assignedToMember;
    private ComboBox assignedToTeam;
    ObservableList<String> inclusionList = FXCollections.observableArrayList("All Action Items","Open Action Items","Closed Action Items");
}
