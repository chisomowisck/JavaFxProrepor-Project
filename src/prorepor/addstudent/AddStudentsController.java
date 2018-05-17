/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prorepor.addstudent;

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
import javafx.scene.control.RadioButton;
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
public class AddStudentsController implements Initializable {

    @FXML
    private TableView<AddStudent> studentTable;
    @FXML
    private TableColumn<String, AddStudent> sIdColumn;
    @FXML
    private TableColumn<String, AddStudent> fNameColumn;
    @FXML
    private TableColumn<String, AddStudent> lNameColumn;
    @FXML
    private TableColumn<String, AddStudent> genderColumn;
    @FXML
    private TableColumn<String, AddStudent> formColumn;
    @FXML
    private TableColumn<String, AddStudent> classColumn;
    @FXML
    private TextField sIdField;
    @FXML
    private TextField classField;
    @FXML
    private TextField lNameField;
    @FXML
    private TextField formField;
    @FXML
    private TextField fNameField;
    private TextField genderField;
    private DBConnection dc;
    private ObservableList<AddStudent> data;
    String name;
    @FXML
    private TextField searchStudentField;
    @FXML
    private RadioButton maleRadiobtn;
    @FXML
    private RadioButton femaleRadiobtn;
    private String radioBtnLabel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        dc = new DBConnection();
        loadStudentsDB();
        toggleRadioButtons();

    }

    private void loadStudentsDB() {

        try {
            Connection conn = dc.Connect();
            data = FXCollections.observableArrayList();
            //Execute querry and store in a result set
            ResultSet rs = conn.createStatement().executeQuery("select* from student");
            while (rs.next()) {
                //get strings from db, which ever way
                data.add(new AddStudent(rs.getString("student_id"), rs.getString("first_name"), rs.getString("last_name"), rs.getString("gender"), rs.getString("form"), rs.getString("class")));
            }
        } catch (SQLException ex) {
            System.err.println("Error" + ex);
        }

        //set cell value factory to table view
        //NB: propertyValue must be the same with what we set in that class
        sIdColumn.setCellValueFactory(new PropertyValueFactory<>("sid"));
        fNameColumn.setCellValueFactory(new PropertyValueFactory<>("fname"));
        lNameColumn.setCellValueFactory(new PropertyValueFactory<>("lname"));
        genderColumn.setCellValueFactory(new PropertyValueFactory<>("gender"));
        formColumn.setCellValueFactory(new PropertyValueFactory<>("form"));
        classColumn.setCellValueFactory(new PropertyValueFactory<>("clas"));

        studentTable.setItems(null);
        studentTable.setItems(data);
    }

    //Radio Button 
    @FXML
    private void maleRadioBtn() {

        maleRadiobtn.setOnAction(e -> {
            if (maleRadiobtn.isSelected()) {
                radioBtnLabel = "Male";
            }
        });
    }

    @FXML
    private void femaleRadioBtn() {
        femaleRadiobtn.setOnAction(e -> {
            if (femaleRadiobtn.isSelected()) {
                radioBtnLabel = "Female";
            }
        });
    }

    @FXML
    private void addStudent() {

        try {
            Connection conn = dc.Connect();
            PreparedStatement myStmt = null;
            myStmt = conn.prepareStatement("INSERT INTO STUDENT" + "(student_id,first_name,last_name,gender,form,class)" + "VALUES(?,?,?,?,?,?)");

            myStmt.setString(1, sIdField.getText());
            myStmt.setString(2, fNameField.getText());
            myStmt.setString(3, lNameField.getText());
            myStmt.setString(4, radioBtnLabel);
            myStmt.setString(5, formField.getText());
            myStmt.setString(6, classField.getText());

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
            loadStudentsDB();

        } catch (SQLException el) {
            System.err.println("Error" + el);
        }

    }
    //for update
    static String tempStudent;

    @FXML
    public void showOnClick() {

        try {

            Connection conn = dc.Connect();
            ResultSet rs = conn.createStatement().executeQuery("select* from student");
            AddStudent user = (AddStudent) studentTable.getSelectionModel().getSelectedItem();
            String query = "select* from student";
            PreparedStatement myStmt = null;
            myStmt = conn.prepareStatement(query);

            tempStudent = user.getfname();

            sIdField.setText(user.getSid());
            fNameField.setText(user.getfname());
            lNameField.setText(user.getlname());
            //genderField.setText(user.getgender());
            formField.setText(user.getform());
            classField.setText(user.getclas());

            myStmt.close();

            rs.close();

        } catch (SQLException el) {
            System.err.println("Error" + el);
        }

    }

    @FXML
    public void deleteUser() {

        try {

            Connection conn = dc.Connect();
            ResultSet rs = conn.createStatement().executeQuery("select* from student");
            AddStudent user = (AddStudent) studentTable.getSelectionModel().getSelectedItem();
            String query = "delete from student where student_id=?";
            PreparedStatement myStmt = null;
            myStmt = conn.prepareStatement(query);
            myStmt.setString(1, user.getSid());
            myStmt.executeUpdate();
            //update alert dialogue
            name = user.getlname() + " " + user.getfname();
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
        loadStudentsDB();
    }

    @FXML
    public void UpdateStudent() {

        try {
            String query = "update student set student_id=?, first_name=?,last_name=?,gender=?, form=?, class=? where first_name='" + tempStudent + "'";

            Connection conn = dc.Connect();
            //ResultSet rs = conn.createStatement().executeQuery("select* from students");
            AddStudent user = (AddStudent) studentTable.getSelectionModel().getSelectedItem();
            PreparedStatement myStmt = null;
            myStmt = conn.prepareStatement(query);
            myStmt.setString(1, sIdField.getText());
            myStmt.setString(2, fNameField.getText());
            myStmt.setString(3, lNameField.getText());
            myStmt.setString(4, radioBtnLabel);
            myStmt.setString(5, formField.getText());
            myStmt.setString(6, classField.getText());

            //update alert dialogue
            name = user.getlname() + " " + user.getfname();
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
        loadStudentsDB();
    }

    @FXML
    private void searchStudent(MouseEvent event) {
        FilteredList filter = new FilteredList(data, e -> true);
        searchStudentField.textProperty().addListener((Observable, oldValue, newValue)
                -> {
            filter.setPredicate((Predicate<? super AddStudent>) (AddStudent Std)
                    -> {

                if (newValue == null || newValue.isEmpty()) {
                    return true;
                } else if (Std.getSid().contains(newValue) || Std.getfname().contains(newValue) || Std.getlname().contains(newValue)) {

                    return true;
                }
                return false;
            }
            );

        });
        SortedList sort = new SortedList(filter);
        sort.comparatorProperty().bind(studentTable.comparatorProperty());
        studentTable.setItems(sort);

    }

    private void toggleRadioButtons() {
        ToggleGroup toggle = new ToggleGroup();
        maleRadiobtn.setToggleGroup(toggle);
        femaleRadiobtn.setToggleGroup(toggle);
        //maleRadiobtn.setSelected(true);

    }

    //Clearing the fields
    public void clearField() {
        sIdField.clear();
        fNameField.clear();
        lNameField.clear();
        genderField.clear();
        formField.clear();
        classField.clear();
    }

}
