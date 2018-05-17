/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
public class ScoresHomeController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

     @FXML
    private void goForm1Class1() throws IOException {
    Prorepor.ShowForm1Class1();
    }
    @FXML
    private void goForm2Page() throws IOException {
    Prorepor.ShowForm2Scene();
    }
    
}
