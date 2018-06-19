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
import javafx.scene.control.*;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author bflor
 */
public class VOLUNTEERINFORMATIONController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML
    Button OK;
    @FXML
    TextArea area;
    @FXML
    Button view;
    
    public void openHOME (ActionEvent event) throws IOException {
        Stage stage = (Stage) OK.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("HOME.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void postWinner(ActionEvent event) throws IOException {
        System.out.println(text);
        area.setText(text);
        OK.setVisible(true);
        view.setVisible(false);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
      LOTTERYController l = new LOTTERYController();
      String[] winner = l.getWinner();
      public String text= "Name"+winner[0]+"\nSchool: "+winner[2]+"\nGraduating Year: "+winner[1]+"\nAddress: "+winner[4]+"\nTotal Entry Hours: "+winner[3];
}
