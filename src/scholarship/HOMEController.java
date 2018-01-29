/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scholarship;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author bflor
 */
public class HOMEController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    //INITIALIZING BUTTONS
    @FXML
    Button ADDHOURS;
    @FXML
    Button ADDVOLUNTEER;
    @FXML
    Button SEARCH;
    @FXML
    Button DRAW;
    
    public void openADDHOURS (ActionEvent event) throws IOException {
        Stage stage = (Stage) ADDHOURS.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("ADDHOURS.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void openLOTTERY (ActionEvent event) throws IOException {
        Stage stage = (Stage) DRAW.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("LOTTERY.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void openNEWVOLUNTEER (ActionEvent event) throws IOException {
        Stage stage = (Stage) ADDVOLUNTEER.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("NEWVOLUNTEER.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
