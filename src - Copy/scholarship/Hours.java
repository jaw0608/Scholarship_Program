/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scholarship;

/**
 *
 * @author Owner
 */
public class Hours {
    public int totalHours;
    public int currentHours;
    public int entries;
    public static int hoursToEntry=5;
    
        public int[] addHoursBE(int hours, int total) {
        totalHours=total;
        totalHours+=hours;
        entries=Math.floorDiv(totalHours, hoursToEntry);
	currentHours-=(Math.floorDiv(hours, hoursToEntry)*hoursToEntry);
        int[] i= new int[2];
        i[0]=totalHours;
        i[1]=entries;
        return i;
    }

    public String toString() {
        String ret="";
        ret+="Hours Until Next Entry: "+(hoursToEntry-currentHours)+"\n";
        ret+="Total Hours: "+totalHours+"\n";
        ret+="Entries: "+entries+"\n";
        return ret;
    }
}
