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
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Owner
 */
public class SearchVolunteerController implements Initializable {
    @FXML
    TextField fn;
    @FXML
    TextField ln;
    @FXML
    Button searchButton;
    @FXML
    Button homeButton;
    @FXML
    TextArea info;
    @FXML
    Label fnl;
    @FXML
    Label lnl;
    @FXML
    Button restart;
    /**
     * Initializes the controller class.
     */
     public void postInfo(ActionEvent event) throws IOException {
      String[] volunteerInfo = s.search(fn.getText(), ln.getText());
      if (volunteerInfo[0].equals("NOTFOUND")) {
       JOptionPane.showMessageDialog(null, "Volunteer not found.");
       
      }
      else {
      String text= "Name"+volunteerInfo[3]+"\nSchool: "+volunteerInfo[1]+"\nGraduating Year: "+volunteerInfo[0]+"\nAddress: "+volunteerInfo[5];
      info.setText(text);
      info.setVisible(true);
      lnl.setVisible(false);
      fnl.setVisible(false);
      fn.setVisible(false);
      ln.setVisible(false);
      searchButton.setVisible(false);
      restart.setVisible(true);
      }
    }
     
     public void reset(ActionEvent event) throws IOException  {
         info.setVisible(false);
         lnl.setVisible(true);
         fnl.setVisible(true);
         fn.setVisible(true);
         ln.setVisible(true);
         searchButton.setVisible(true);
         restart.setVisible(false);
         fn.setText("");
         ln.setText("");
     }
         public void openHOME (ActionEvent event) throws IOException {
        Stage stage = (Stage) homeButton.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("HOME.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    FileReaders s = new FileReaders();
   
}
