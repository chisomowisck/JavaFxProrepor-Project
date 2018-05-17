/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prorepor.form2;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import prorepor.home.DBConnection;

/**
 * FXML Controller class
 *
 * @author SLyT
 */
public class Form2PageController implements Initializable {

    @FXML
    private TableView<Form2> mathTable;
    @FXML
    private TableColumn<String, Form2> studentIdColumn;
    @FXML
    private TableColumn<String, Form2> fnameColumn;
    @FXML
    private TableColumn<String, Form2> lnameColumn;
    @FXML
    private TableColumn<String, Form2> subjecttColumn;
    @FXML
    private TableColumn<String, Form2> scoreColumn;
    @FXML
    private TableColumn<String, Form2> gradeColumn;
    @FXML
    private TableColumn<String, Form2> commentColumn;
    @FXML
    private TextField scoreField;
    @FXML
    private TextField lnameField;
    @FXML
    private TextField subjectField;
    @FXML
    private TextField fnameField;
    @FXML
    private TextField studentIdField;
    @FXML
    private TextField gradeField;
    @FXML
    private TextField commentField;
    private DBConnection dc;
    private ObservableList<Form2> data;
    @FXML
    private TextField searchField;
    @FXML
    private RadioMenuItem biologybtn;
    @FXML
    private RadioMenuItem physicsbtn;
    String subjectFilter;
    @FXML
    private RadioMenuItem chemistrybtn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        dc = new DBConnection();
        loadGrades();
        toggleRadioButtons();

    }

    private void loadGrades() {

        try {
            Connection conn = dc.Connect();
            data = FXCollections.observableArrayList();
            //Execute querry and store in a result set
            ResultSet rs = conn.createStatement().executeQuery("select* from grades");
            while (rs.next()) {
                //get strings from db, which ever way
                data.add(new Form2(rs.getString("student_id"), rs.getString("first_name"), rs.getString("last_name"), rs.getString("subject"), rs.getString("score"), rs.getString("grade"), rs.getString("comment")));
            }
        } catch (SQLException ex) {
            System.err.println("Error" + ex);
        }

        //set cell value factory to table view
        //NB: propertyValue must be the same with what we set in that class
        studentIdColumn.setCellValueFactory(new PropertyValueFactory<>("sid"));
        fnameColumn.setCellValueFactory(new PropertyValueFactory<>("fname"));
        lnameColumn.setCellValueFactory(new PropertyValueFactory<>("lname"));
        subjecttColumn.setCellValueFactory(new PropertyValueFactory<>("subject"));
        scoreColumn.setCellValueFactory(new PropertyValueFactory<>("score"));
        gradeColumn.setCellValueFactory(new PropertyValueFactory<>("grade"));
        commentColumn.setCellValueFactory(new PropertyValueFactory<>("comment"));

        mathTable.setItems(null);
        mathTable.setItems(data);
    }

    @FXML
    private void searchStuff(MouseEvent event) {
        FilteredList filter = new FilteredList(data, e -> true);
        searchField.textProperty().addListener((Observable, oldValue, newValue)
                -> {
            filter.setPredicate((Predicate<? super Form2>) (Form2 Std)
                    -> {

                if (newValue == null || newValue.isEmpty()) {
                    return true;
                } else if (Std.getfname().contains(newValue)) {

                    return true;
                }
                return false;
            }
            );

        });
        SortedList sort = new SortedList(filter);
        sort.comparatorProperty().bind(mathTable.comparatorProperty());
        mathTable.setItems(sort);
    }
    static String tempStudent;

    @FXML
    public void showOnClick() {

        try {

            Connection conn = dc.Connect();
            ResultSet rs = conn.createStatement().executeQuery("select* from grades");
            Form2 user = (Form2) mathTable.getSelectionModel().getSelectedItem();
            String query = "select* from grades";
            PreparedStatement myStmt = null;
            myStmt = conn.prepareStatement(query);

            tempStudent = user.getfname();

            studentIdField.setText(user.getSid());
            fnameField.setText(user.getfname());
            lnameField.setText(user.getlname());
            subjectField.setText(user.getsubject());
            scoreField.setText(user.getscore());
            gradeField.setText(user.getgrade());
            commentField.setText(user.getcomment());

            myStmt.close();

            rs.close();

        } catch (SQLException el) {
            System.err.println("Error" + el);
        }

    }

    String name;

    @FXML
    public void UpdateGrade() {

        try {
            String query = "update grades set student_id=?, first_name=?,last_name=?,subject=?, score=?, grade=?, comment=? where first_name='" + tempStudent + "'";

            Connection conn = dc.Connect();
            //ResultSet rs = conn.createStatement().executeQuery("select* from students");
            Form2 user = (Form2) mathTable.getSelectionModel().getSelectedItem();
            PreparedStatement myStmt = null;
            myStmt = conn.prepareStatement(query);
            myStmt.setString(1, studentIdField.getText());
            myStmt.setString(2, fnameField.getText());
            myStmt.setString(3, lnameField.getText());
            myStmt.setString(4, subjectField.getText());
            myStmt.setString(5, scoreField.getText());
            myStmt.setString(6, gradeField.getText());
            myStmt.setString(7, commentField.getText());

            //update alert dialogue
            name = user.getsubject() + " " + user.getscore();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Student Details Dialogue");
            alert.setHeaderText(null);
            alert.setContentText(name + " has been updated");
            alert.showAndWait();

            myStmt.execute();
            myStmt.close();
            //Clearing fieds
            clearField();
        } catch (SQLException el) {
            System.err.println("Error" + el);
        }
        loadGrades();
    }

    @FXML
    public void deleteGrade() {

        try {

            Connection conn = dc.Connect();
            ResultSet rs = conn.createStatement().executeQuery("select* from grades");
            Form2 user = (Form2) mathTable.getSelectionModel().getSelectedItem();
            String query = "delete from grades where score=?";
            PreparedStatement myStmt = null;
            myStmt = conn.prepareStatement(query);
            myStmt.setString(1, user.getscore());
            myStmt.executeUpdate();
            //update alert dialogue
            name = user.getsubject() + " " + user.getscore();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Student Details Dialogue");
            alert.setHeaderText(null);
            alert.setContentText(name + " has been deleted");
            alert.showAndWait();

            myStmt.close();

            rs.close();
            //Clearing fieds
            clearField();
        } catch (SQLException el) {
            System.err.println("Error" + el);
        }
        loadGrades();
    }

    private void toggleRadioButtons() {
        ToggleGroup toggle = new ToggleGroup();
        biologybtn.setToggleGroup(toggle);
        physicsbtn.setToggleGroup(toggle);
        chemistrybtn.setToggleGroup(toggle);
        
    }
    @FXML
    private void biology() {
        subjectFilter="biology";
        FilteredList filter = new FilteredList(data, e -> true);
        biologybtn.setOnAction(e -> {
            filter.setPredicate((Predicate<? super Form2>) (Form2 Std)
                    -> {
                return biologybtn.isSelected()&& Std.getsubject().equals(subjectFilter);
            });

        });
        SortedList sort = new SortedList(filter);
        sort.comparatorProperty().bind(mathTable.comparatorProperty());
        mathTable.setItems(sort); 
        

    }
    @FXML
    private void physics() {
        subjectFilter="physics";
        FilteredList filter = new FilteredList(data, e -> true);
       physicsbtn.setOnAction(e -> {
            filter.setPredicate((Predicate<? super Form2>) (Form2 Std)
                    -> {
                return physicsbtn.isSelected()&& Std.getsubject().equals(subjectFilter);
            });

        });
        SortedList sort = new SortedList(filter);
        sort.comparatorProperty().bind(mathTable.comparatorProperty());
        mathTable.setItems(sort); 
        

    }
    @FXML
    private void chemistry() {
        subjectFilter="chemistry";
        FilteredList filter = new FilteredList(data, e -> true);
       chemistrybtn.setOnAction(e -> {
            filter.setPredicate((Predicate<? super Form2>) (Form2 Std)
                    -> {
                return chemistrybtn.isSelected()&& Std.getsubject().equals(subjectFilter);
            });

        });
        SortedList sort = new SortedList(filter);
        sort.comparatorProperty().bind(mathTable.comparatorProperty());
        mathTable.setItems(sort);
        

    }

    
    @FXML
    private void addGrade() {

        try {
            Connection conn = dc.Connect();
            PreparedStatement myStmt = null;
            myStmt = conn.prepareStatement("INSERT INTO GRADES" + "(student_id,first_name,last_name,subject,score,grade,comment)" + "VALUES(?,?,?,?,?,?,?)");

            myStmt.setString(1, studentIdField.getText());
            myStmt.setString(2, fnameField.getText());
            myStmt.setString(3, lnameField.getText());
            myStmt.setString(4, subjectField.getText());
            myStmt.setString(5, scoreField.getText());
            myStmt.setString(6, gradeField.getText());
            myStmt.setString(7, commentField.getText());

            //Save alert dialogue
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Student Details Dialogue");
            alert.setHeaderText(null);
            alert.setContentText("Student has been added");
            alert.showAndWait();

            myStmt.execute();
            myStmt.close();

            //Clearing fieds
            clearField();
            loadGrades();

        } catch (SQLException el) {
            System.err.println("Error" + el);
        }

    }

    //Clearing the fields
    public void clearField() {
        studentIdField.clear();
        fnameField.clear();
        lnameField.clear();
        subjectField.clear();
        scoreField.clear();
        gradeField.clear();
        commentField.clear();
    }

}
