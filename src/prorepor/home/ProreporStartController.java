
package prorepor.home;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import prorepor.Prorepor;

/**
 * FXML Controller class
 *
 * @author SLyT
 */
public class ProreporStartController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }  
    
    @FXML
    private void goProreporItems() throws IOException {
    Prorepor.ShowProreporView();
    Prorepor.ShowProreporItems();
    
}
}