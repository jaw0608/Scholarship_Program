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
public class LOTTERYController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    Button BACK;
    @FXML
    Button DRAW;
    
    //CONTINUE HERE!!!! VVVVV ADD ITEMS
    //SCHOOLS.setItems(FXCollections.observableArrayList("Select a school", "SHOREHAM", "MOUNTSINAI", "MILLERPLACE", "ROCKYPOINT"));
 
    @FXML
    ChoiceBox SCHOOLS;
    
    @FXML
    TextField YEAR;
    
    
    public void openHOME (ActionEvent event) throws IOException {
        Stage stage = (Stage) BACK.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("HOME.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void openINFO (ActionEvent event) throws IOException {
        Stage stage = (Stage) BACK.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("VOLUNTEERINFORMATION.fxml"));
        VOLUNTEERINFORMATIONController x = new VOLUNTEERINFORMATIONController();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        
    }
public void confirm (ActionEvent event) throws IOException {
        String school = (String) SCHOOLS.getValue();
        String year = YEAR.getText();
        school=school.replace("\t","");
        System.out.println(school+""+year);
        int input = JOptionPane.showOptionDialog(null, "Are you sure you want to draw for " + school + " graduating class of "+year+"?\nClick OK to continue.",
                "Message", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null);
        if (input == JOptionPane.OK_OPTION) {
            lottery l= new lottery();
            String[] winner = l.buildEntries(year+school+".txt");
            if (winner[0]!="NOFILE") {
            winnerInfo[0]=winner[3];
            winnerInfo[1]= winner[0];
            winnerInfo[2]= winner[1];
            winnerInfo[3]=winner[6];
            winnerInfo[4]=winner[5];
            openINFO(event);
        }
            else
                 JOptionPane.showMessageDialog(null, "No entries found for this school and year");
        }
    }
public String[] getWinner() {
    return winnerInfo;
}
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        SCHOOLS.getItems().addAll("\t\tMILLERPLACE\t\t", "\t\tMOUNTSINAI\t\t", "\t\tROCKYPOINT\t\t", "\t\tSHOREHAM\t\t");
    }    
    public static String[] winnerInfo = new String[5];
}
