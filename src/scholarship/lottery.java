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
    public String[] buildEntries(String a) {
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
    return drawBE(entryList,a);
    }
    catch(FileNotFoundException b) {
        System.out.println("Error, no entries"); 
        String ret[] = new String[6];
        ret[0]="NOFILE";
        return ret;
    }
    }
public String[] drawBE (ArrayList<String> a, String fileName) {
       double winner = Math.random();
       winner=winner*a.size();
       
    String name=a.get((int)winner);
    String keepName = name;
    String fn = name.substring(0, name.indexOf(" "));
    name = name.substring(0, name.indexOf(" "));
    int mnIndex = name.indexOf("\t");
    int lnIndex = name.lastIndexOf("\t");
    String ln ="";
    String mn= "";
    
    if (mnIndex==lnIndex) {
        ln = name.substring(0, lnIndex);
    }
    else {
        ln =name.substring(lnIndex);
    }
    FileReaders s = new FileReaders();
    String[] ret = new String[7];
    fn= fn.replace(",","");
    ln= ln.replace(",","");
    fn= fn.replace("\t","");
    ln= ln.replace("\t","");
    System.out.println(fn+" "+ln);
    String[] search = s.search(fn,ln);
    
    ret[0]=search[0];
    ret[1]= search[1];
    ret[2]= search[2];
    ret[3]= search[3];
    ret[4]= search[4];
    ret[5] = search[5];
    ret[6] = ""+s.addHours(0, keepName, fileName);
    return ret;
    }
}
