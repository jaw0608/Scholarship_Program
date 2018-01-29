/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scholarship;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Owner
 */
public class lottery {
    //Creates arraylist that will be used to hold entry names
    private ArrayList<String> entryList= new ArrayList<String>(15);
    
    
    //function that reads entry from file, and 
    public void buildEntries(String a) {
    try {
    File file = new File(a);
    Scanner input = new Scanner(file);
    while (input.hasNextLine()) {
    String line=input.nextLine();
    String name=line.substring(line.indexOf("\t"));
    name=name.substring(0,name.lastIndexOf("H"));
    name=name.substring(0,name.lastIndexOf("\t"));
    int entries=Integer.parseInt(line.substring(line.lastIndexOf("\t")+1,line.lastIndexOf("E")));
    while (entries>0) {
        entryList.add(name);
        entries--;
        System.out.println("Added: "+name);
    }
}
System.out.println(drawBE(entryList));
    }
    catch(FileNotFoundException b) {System.out.println("Error, file not found");}
    }
public String drawBE (ArrayList<String> a) {
       double winner = Math.random();
       winner=winner*a.size();
       
    return "And the winner is: "+a.get((int)winner);
    }
}
