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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author bflor
 */
public class ADDHOURSController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML
    Button BACK;
    @FXML
    Button ADD;
    
    @FXML
    TextField FIRST;
    @FXML
    TextField LAST;
    @FXML
    ChoiceBox PROGRAM;
    @FXML
    TextField HOURS;
    
    public void openHOME (ActionEvent event) throws IOException {
        Stage stage = (Stage) BACK.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("HOME.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void addHours (ActionEvent event) throws IOException {
        String first, last, program;
        String hours;
        if (hasText(FIRST)) {
            first=FIRST.getText();
            if (hasText(LAST)) {
                last = LAST.getText();
                if (PROGRAM.getValue() != null) {
                    program = (String) PROGRAM.getValue();
                    if (hasText(HOURS)){ hours = HOURS.getText();
                    int hour=Integer.parseInt(hours);
                    FileReaders fr= new FileReaders();
                    String[] info=fr.search(first,last);
                    if (info[0].equals("NOTFOUND")) {
                        System.out.println("Name not found");
                        JOptionPane.showMessageDialog(null, "Volunteer not found."); 
                    }
                    else {
                        if (!info[2].equals("")) {
                        String name=last.toUpperCase()+", "+first.toUpperCase()+" "+info[2].toUpperCase()+".";
                        fr.createSchoolFile(hour,name,info[0],info[1]);
                        }
                        else {
                            String name=last.toUpperCase()+", "+first.toUpperCase();
                            fr.createSchoolFile(hour,name,info[0],info[1]);
                        }
                    }
                } else { 
                    JOptionPane.showOptionDialog(null, "ERROR: Missing Required Information",
                        "ERROR", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null); 
                }
            }
        }
    }
    }
    public boolean hasText(TextField a) {
        if (a.getText() == null || a.getText().equals("")) {
        int input = JOptionPane.showOptionDialog(null, "ERROR: Missing Required Information",
                "ERROR", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null);
        return false;
        }
        else return true;
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        PROGRAM.getItems().addAll("DROPIN", "OPENMIC", "OPENGYM", "SACC", "SUMMERBUDDIES", "TUTORING", "OTHER");
    }    
    
}
