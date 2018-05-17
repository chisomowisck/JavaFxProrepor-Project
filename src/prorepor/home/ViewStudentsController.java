package prorepor.home;

import java.io.IOException;
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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import prorepor.Prorepor;

/**
 * FXML Controller class
 *
 * @author SLyT
 */
public class ViewStudentsController implements Initializable {

    @FXML
    private TableView<ViewStudentDetails> tableStudents;
    @FXML
    private TableColumn<ViewStudentDetails, String> sidColumn;
    @FXML
    private TableColumn<ViewStudentDetails, String> fnameColumn;
    @FXML
    private TableColumn<ViewStudentDetails, String> lnameColumn;
    private DBConnection dc;
    private ObservableList<ViewStudentDetails> data;
    @FXML
    private TextField searchStudentField;
    @FXML
    private TextField fnameField;
    @FXML
    private TextField sidField;
    @FXML
    private TextField lnameField;
    String name;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        dc = new DBConnection();
        loadStudentsDB();

    }

    @FXML
    private void loadStudentsDB() {

        try {
            Connection conn = dc.Connect();
            data = FXCollections.observableArrayList();
            //Execute querry and store in a result set
            ResultSet rs = conn.createStatement().executeQuery("select* from students");
            while (rs.next()) {
                //get strings from db, which ever way
                data.add(new ViewStudentDetails(rs.getString("ssid"), rs.getString("fname"), rs.getString("lname")));
            }
        } catch (SQLException ex) {
            System.err.println("Error" + ex);
        }

        //set cell value factory to table view
        //NB: propertyValue must be the same with what we set in that class
        sidColumn.setCellValueFactory(new PropertyValueFactory<>("sid"));
        fnameColumn.setCellValueFactory(new PropertyValueFactory<>("lname"));
        lnameColumn.setCellValueFactory(new PropertyValueFactory<>("fname"));

        tableStudents.setItems(null);
        tableStudents.setItems(data);
    }

    @FXML
    private void searchStudent(KeyEvent event) {
        FilteredList filter = new FilteredList(data, e -> true);
        searchStudentField.textProperty().addListener((Observable, oldValue, newValue)
                -> {
            filter.setPredicate((Predicate<? super ViewStudentDetails>) (ViewStudentDetails Std)
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
        sort.comparatorProperty().bind(tableStudents.comparatorProperty());
        tableStudents.setItems(sort);
         

    }

    @FXML
    private void addStudent() {

        try {
            Connection conn = dc.Connect();
            PreparedStatement myStmt = null;
            myStmt = conn.prepareStatement("INSERT INTO STUDENTS" + "(ssid,fname,lname)" + "VALUES(?,?,?)");

            myStmt.setString(1, sidField.getText());
            myStmt.setString(2, lnameField.getText());
            myStmt.setString(3, fnameField.getText());

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
            ResultSet rs = conn.createStatement().executeQuery("select* from students");
            ViewStudentDetails user = (ViewStudentDetails) tableStudents.getSelectionModel().getSelectedItem();
            String query = "select* from students";
            PreparedStatement myStmt = null;
            myStmt = conn.prepareStatement(query);

            tempStudent = user.getfname();

            sidField.setText(user.getSid());
            fnameField.setText(user.getlname());
            lnameField.setText(user.getfname());

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
            ResultSet rs = conn.createStatement().executeQuery("select* from students");
            ViewStudentDetails user = (ViewStudentDetails) tableStudents.getSelectionModel().getSelectedItem();
            String query = "delete from students where ssid=?";
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
            String query = "update students set ssid=?, fname=?,lname=? where fname='" + tempStudent + "'";

            Connection conn = dc.Connect();
            //ResultSet rs = conn.createStatement().executeQuery("select* from students");
            ViewStudentDetails user = (ViewStudentDetails) tableStudents.getSelectionModel().getSelectedItem();
            PreparedStatement myStmt = null;
            myStmt = conn.prepareStatement(query);
            myStmt.setString(1, sidField.getText());
            myStmt.setString(2, lnameField.getText());
            myStmt.setString(3, fnameField.getText());

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
    private void goViewStudents() throws IOException {
    Prorepor.ShowProreporItems();
    }

    //Clearing the fields
    public void clearField() {
        sidField.clear();
        lnameField.clear();
        fnameField.clear();
    }

}
