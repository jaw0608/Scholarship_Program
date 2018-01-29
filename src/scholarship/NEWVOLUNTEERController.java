/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scholarship;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ResourceBundle;
import java.util.Scanner;
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
public class NEWVOLUNTEERController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML
    Button ADD;
    @FXML
    Button BACK;
    
    @FXML
    ChoiceBox SCHOOLS;
    @FXML
    TextField FIRST;
    @FXML
    TextField LAST;
    @FXML
    TextField MI;
    @FXML
    TextField ADDRESS;
    @FXML
    TextField CITY;
    @FXML
    TextField STATE;
    @FXML
    TextField ZIP;
    @FXML
    TextField GRADYEAR;
    
    public void openHOME (ActionEvent event) throws IOException {
        Stage stage = (Stage) BACK.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("HOME.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
    public void addVolunteer (ActionEvent event) throws IOException {
        String first, mi, last, address, city, state, zip, school, year;
        if(MI.getText()==null||MI.getText().equals("")) {
            mi="";  
        }
        else {
            mi=MI.getText();
        }
        if (hasText(FIRST)) {
            first = FIRST.getText(); System.out.println(first);
            if (hasText(LAST)) {
                last = LAST.getText(); System.out.println(last);
                if (hasText(ADDRESS)) {
                    address=ADDRESS.getText(); System.out.println(address);
                    if (hasText(CITY)) {
                        city = CITY.getText(); System.out.println(city);
                        if (hasText(STATE)) {
                            state = STATE.getText(); System.out.println(state);
                            if (hasText(ZIP)) {
                                zip = ZIP.getText(); System.out.println(zip);
                                if (SCHOOLS.getValue() != null)
                                {
                                    school = (String) SCHOOLS.getValue(); System.out.println(school);
                                    if (hasText(GRADYEAR)) {
                                        year = GRADYEAR.getText(); System.out.println(year);
                                        addBE(first,last,mi,address,city,state,zip,school,year);
                                        //SEARCH IF VOLUNTEER EXISTS WITH INFORMATION!
                                        //IF YES: int input = JOptionPane.showOptionDialog(null, "ERROR: Volunteer Already Exists", "ERROR", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null);
                                        //IF NO: int input = JOptionPane.showOptionDialog(null, "Volunteer added successfully", "Message", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null);
                                    }
                                }
                                else
                                {
                                    int input = JOptionPane.showOptionDialog(null, "ERROR: Missing Required Information",
                                        "ERROR", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null);
                                }
                            }
                        }
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
public void addBE(String fn, String ln, String mn, String address, String cit, String stat,String zip, String school, String gradyear) {
        ln=ln.toUpperCase();
        mn=mn.toUpperCase();
        fn=fn.toUpperCase();
        String ret= "Name: "+ln+", "+fn+" "+mn+".\t";
        ret+="School: "+school+"\t";
        ret+="Graduating Year: "+gradyear+"\t";
        ret+=address+", "+cit+", "+stat+" "+zip+"\t";
        String fileName="VolunteerList.txt";
    	Path f=Paths.get(fileName);
        try {
        //tries to create the file, if fails OR passes, the writer method is ran.
	Files.createFile(f);
        System.out.println("File was created");
        writer(ret,fileName);
        
        }
        catch(FileAlreadyExistsException a) {
            writer(ret,fileName);
        }
        catch(IOException b) {}
          
    }
    
    public void writer (String stringToWrite,String nameOfFile) {
         File original= new File(nameOfFile);
         int lineNum=1;
         try {
         Scanner lines= new Scanner(original);
         while(lines.hasNextLine()) {
             lineNum++;
             lines.nextLine();
         }
         }
         catch(Exception notFound) {}
         File temp= new File("temp"+nameOfFile);
         FileReaders f= new FileReaders();
         f.copy(original,temp,lineNum+"\t"+stringToWrite);
         f.copy(temp,original);
       }    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        SCHOOLS.getItems().addAll("MILLERPLACE", "MOUNTSINAI", "ROCKYPOINT", "SHOREHAM");
    }    
    
}
