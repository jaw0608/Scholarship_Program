/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scholarship;

import java.util.ArrayList;

/**
 *
 * @author Owner
 */
//Volunteer Class
public class Volunteer {
    //Instance variables
    private int currentHours=0;
    private int totalHours=0;
    private int entries=0;
    private static int hoursToEntry=5;
    private String firstName="";
    private String lastName="";
    private String middleName="";
    private String school;
    private int graduationYear;
    private String phoneNum;
    private String zipCode;
    private String addressOne;
    private String city;
    private String state;
    
    
    public Volunteer(String first, String last, String middle, String sch, int year,String zip, String add1,String cit,String stat, String phone){
        firstName=first;
        lastName=last;
        middleName=middle;
        school=sch;
        graduationYear=year;
        zipCode=zip;
        addressOne=add1;
        city=cit;
        state=stat;
        phoneNum=phone;
    } 
   
    //get methods
    public String getAddress(){
        return "Primary Address: "+addressOne+" , "+city+" "+state+" ,"+zipCode+" Phone Number: "+phoneNum;
    }
    public int getCurrentHoursBE() {
        return currentHours;
    }
    public int getTotalHoursBE() {
        return totalHours;
    }
    public String getNameBE() {
        return firstName+" "+middleName+" "+lastName;
    }
    public int getEntriesBE() {
        return entries;
    }
    public String getSchoolBE() {
        return school;
    }
    public int getGraduationYearBE() {
        return graduationYear;
    }
    public String toString() {
        String ret= "Name: "+getNameBE()+"\n";
        ret+="School: "+getSchoolBE()+"\n";
        ret+="Graduating Year: "+getGraduationYearBE()+"\n";
        ret+=getAddress();
        return ret;
    }
    public void changeSchoolBE(String sch) {
        school=sch;
    }
    public void changeGraduationYearBE(int grad) {
        graduationYear=grad;
    }
    public void changeContactBE (String phone, String add1,String cit, String stat, String zip) {
        phoneNum=phone;
        addressOne=add1;
        city=cit;
        state=stat;
        zipCode=zip;
    }


}
