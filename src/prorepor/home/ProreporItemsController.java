
package prorepor.home;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import prorepor.Prorepor;

public class ProreporItemsController implements Initializable {
    @FXML
    private Button viewKey;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void goViewStudents() throws IOException {
    Prorepor.ShowViewStudents();
    }
    
    @FXML
    private void goAddStudent() throws IOException {
    Prorepor.ShowAddStudentScene();
    }
    
    @FXML
    private void goScoresHome() throws IOException {
    Prorepor.ShowScoresHome();
    }
    
}
