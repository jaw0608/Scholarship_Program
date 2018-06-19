
package scholarship;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Scanner;

public class FileReaders {
    
    public int totalHours;
    public String theFileName;
    public String fullName;
    public boolean failed=false;
  //function to create a new School File, pulls hours, name of volunteer, graduating year, and school district  
    public void createSchoolFile(int hours, String name,String year, String school) {
        //converts year and school to filename, and creates appropriate file path
        String fileName=year+school+".txt";
    	Path f=Paths.get(fileName);
        try {
        //tries to create the file, if fails OR passes, the addhours function is ran
	Files.createFile(f);
        System.out.println("File was made");
        File t= new File(fileName);
        PrintWriter p=new PrintWriter(t);
        p.print(fileName);
        addHours(hours/2,name,fileName);
        }
        catch(FileAlreadyExistsException a) {
            addHours(hours,name,fileName);
        }
        catch(IOException b) {}
    }
    
    //pulls hours, volunteers name, and previously created filename
    public int addHours(int hours, String name,String fileName) {
    //creates new file and scanner using the filename
    File file = new File(fileName);
    System.out.println("In ran hours");
    try {
    Scanner input = new Scanner(file);
    //lineNumber variable to track line-number, and boolean for if the name was found or not found in the next statement
    int lineNumber=0;
    boolean nameWasFound=false;
    //if first line of file, set lineNumber to 1 to account for this.
    if (input.hasNextLine()==false) {
        lineNumber=0;
    }
    while (input.hasNextLine()) {
        String line=input.nextLine();
        lineNumber++;
        //if the line contains the entered name...
        if (line.contains(name)==true) {
            System.out.println("Found name for hours");
            //Next three lines cut down line into just the total hours 
            String temp=line.substring(line.indexOf("\t")+1);
            temp=temp.substring(temp.indexOf("\t")+1);
            temp=temp.substring(temp.indexOf("\t")+1,temp.lastIndexOf("H"));
            //converts string to integer
            int totalHoursIn=Integer.parseInt(temp);
            //creates new hours instance, and runs the calculations for how many entries the volunteer should have total
            //The hours are returned in i[0], and entries in i[1]
            Hours h = new Hours();
            int[] i=h.addHoursBE(hours, totalHoursIn);
            int entries=i[1];
            totalHoursIn=i[0];
            totalHours=totalHoursIn;
            // a new temporary file is created for manipulating text without error
            File fileTemp= new File("temp"+fileName);
            input.close();
            // A new scanner is created to read from the original file, and a printwriter is made to write to the temp file
            Scanner in = new Scanner(file);
            PrintWriter pw= new PrintWriter(fileTemp);
            String write;
            //The while statement below copies all the lines ABOVE the volunteer who is receiving hours, if any lines are present
            while (lineNumber>1) {
            write= in.nextLine();
            pw.println(write);
            pw.flush();
            lineNumber--;
            }
            
            write=in.nextLine();
            //the next two lines remove the old hours and entries from the volunteer so they can be updated.
            write=write.substring(0,write.lastIndexOf("\t"));
            write=write.substring(0,write.lastIndexOf("\t"));
            //reprints linenumber and name, and then prints the updated hours and entries
            pw.println(write+"\t"+totalHours+"H\t"+entries+"E");
            nameWasFound=true;
            //the below while statement copies all the lines BELOW the volunteer who is receiving hours, if any lines are present
            while (in.hasNextLine()) {
            write=in.nextLine();
            pw.println(write);
            }
            pw.flush();
            //copies the temporary file to the actual file, updating it.
            copy(fileTemp,file);
            pw.flush();
            break;
            
        }
        }
   
    //if the name was not found in the file...
    if (nameWasFound==false) {
        System.out.println("Not found");
        PrintWriter pr= new PrintWriter(fileName);
        pr.print("");
        //a new hours instance is created to calculate updated totalhours and entries
        Hours t= new Hours();
        int[] q=t.addHoursBE(hours,0);
        File filen = new File(fileName);
        File tempn= new File("temp"+fileName);
        //temp file copies to actual file and adds the new volunteer at the end. This is then copied back to temp.
        copy(tempn,filen,(lineNumber+1)+"\t"+name+"\t"+q[0]+"H\t"+q[1]+"E");
        copy(filen,tempn);
        //if copying failed and went to the exception, it is tried again.
        if (failed==true) {
            addHours(totalHours,fullName,theFileName);
            failed=false;
        }
        pr.flush();
    }
    }
    catch(Exception possiblyParseIntError){System.out.println("Parse int error");}
    return totalHours;
}
    //f1 is to be copied to f2
    public void copy (File f1,File f2) {
       int cop=0;
        try {
        Scanner c= new Scanner(f1);
       PrintWriter p= new PrintWriter(f2);
       while (c.hasNextLine()) {
           String w=c.nextLine();
           cop++;
           if(c.hasNextLine()) {
               p.println(w);
           }
           else {
           p.print(w);
           }
           p.flush();
       }
       }
       catch(Exception a) {}
        
    }
    //f1 copies to f2, and line a is added to the end.
    public void copy (File f1,File f2,String a) {
        System.out.println("Running Copier");
        try {
            
        PrintWriter p= new PrintWriter(f2);
        Scanner c= new Scanner(f1);
        while (c.hasNextLine()) {
           System.out.println("Copied Line");
           String w=c.nextLine();
           p.println(w);
           p.flush();
       }
       p.print(a);
       p.flush();
       }
       
       catch(Exception b ) {
       //For some reason, the first name entered for each file causes an exception. So, I made this catch statement to re-run the program after file
       //creation in order for it to successfully add the first name. How it works is that it cuts the already made line into name, hours, and the
       //file-name. It then sets a boolean to false, and the boolean is used to re-run the program if it failed. The art of the bodge baby.
       String name=a.substring(a.indexOf("\t"),a.lastIndexOf("H"));
       name=name.substring(0,name.lastIndexOf("\t"));
       String hours=a.substring(0,a.lastIndexOf("\t"));
       hours=hours.substring(hours.lastIndexOf("\t")+1,hours.lastIndexOf("H"));
       int hour= Integer.parseInt(hours);
       String fName=f2.getName();
       theFileName=fName;
       totalHours=hour;
       fullName=name;
       failed=true;
       
       }
        
       
    }
    
    //Idea for search in Drawing and Volunteer info tabs: To get hours for volunteer, run the add function with 0 hours being added. 
    //Then, call FileReaders "totalHours" variable (public) to get the hours that person has, since this will be the last stored variable.
    
    
    
    
    
    
    public String [] search(String firstName, String lastName) {
        File g= new File("VolunteerList.txt");
        String [] info= new String[6];
        try {
        Scanner readFromFile= new Scanner(g);
        firstName=firstName.toUpperCase();
        lastName=lastName.toUpperCase();
        String school="";
        String year="";
        String line="";
        
        try {
           
        while(readFromFile.hasNextLine()) {
            line=readFromFile.nextLine();
            System.out.println("Read in line");
            if (line.indexOf(firstName)>=0&&line.indexOf(lastName)>=0) {
               System.out.println("Found name");
               info[3]=line.substring(6,line.indexOf("School"));
          
                if (line.contains(".")) {
                   System.out.println("Found middle name");
                    info[2]=""+line.charAt(line.indexOf(".")-1);
               }
               else {
                    System.out.println("No middle name found");
                   info[2]="";
               }
                line=line.substring(line.indexOf("School: ")+8);
                school=line.substring(0,line.indexOf("\t"));
                line=line.substring(line.indexOf("Year: ")+6);
                year=line.substring(0,line.indexOf("\t"));
                line = line.substring(line.indexOf("\t")+1);
                info[5]=line;
                info[0]=year;
                info[1]=school;
                System.out.println(Arrays.toString(info));
                return info;
            }
            
        }
        }
        catch(Exception a) {
        //Add J-Option Pane Here Saying "You fam no"
        info[0]="NOTFOUND";
        info[1]="NOTFOUND";
        info[2]="NOTFOUND";
        info[3]="NOTFOUND";
        info[4]="NOTFOUND";
        info[5]="NOTFOUND";
        return info;}
              }
        catch(FileNotFoundException notgonnahappen) {}
        info[0]="NOTFOUND";
        info[1]="NOTFOUND";
        info[2]="NOTFOUND";
        info[3]="NOTFOUND";
        info[4]="NOTFOUND";
        info[5]="NOTFOUND";
        return info;
    }

    
}
    

