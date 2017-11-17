/**
 * Created by abdul on 2017-01-27.
 * 101042166
 */
public class PhonePlan {
    private int minutesAllowed,minutesUsed,dataAllowed,dataUsed;
    private boolean planType;

    //Constructor
    public PhonePlan(int m,int d,boolean p){
        minutesAllowed=m;
        dataAllowed=d;
        planType=p;
        minutesUsed=0;
        dataUsed=0;
    }
    //get methods
    public int getMinutesAllowed(){return this.minutesAllowed;}
    public int getMinutesUsed(){return this.minutesUsed;}
    public int getDataAllowed(){return this.dataAllowed;}
    public int getDataUsed() {return this.dataUsed;}
    public boolean getplanType() {return this.planType;}
    public int getMinutesRemaining() {return this.minutesAllowed - this.minutesUsed;}
    public int getDataRemaining() {return this.dataAllowed - this.dataUsed;}

    //set Methods
    public void setMinutesAllowed(int m){this.minutesAllowed=m;}
    public void setMinutesUsed(int u){this.minutesUsed=u;}
    public void setDataAllowed(int d){this.dataAllowed=d;}
    public void setDataUsed(int q){this.dataUsed=q;}
    public void setPlanType(boolean p){this.planType=p;}

    // to string printing out the info
 public String toString(){
        if (planType)
            return ("Pay-As-You-Go Plan with "+ getMinutesRemaining()+" minutes and "+getDataRemaining()+"KB remaining");
        else
            return ("Regular("+minutesAllowed+" minutes, "+(dataAllowed/1000000.00)+"GB data) Monthly Plan with "+getMinutesRemaining()+" minutes and "+getDataRemaining()+"KB remaining");

    }

}
