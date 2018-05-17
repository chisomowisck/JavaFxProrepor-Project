/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prorepor.form1;

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
public class Class1Controller implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }   
    
     @FXML
    private void goEast() throws IOException {
    Prorepor.ShowForm1Class2();
    }
    
    @FXML
    private void goWest() throws IOException {
    Prorepor.ShowForm1Class3();
    }
    
}
