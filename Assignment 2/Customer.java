/**
 * Created by abdul on 2017-01-29.
 * 101042166
 */
public class Customer {
    private String name;
    private int callsMade,MinutesPurchased;
    private float balance,MonthlyCharges,Talkcharge,DataCharge;
    private PhonePlan planphone;
    private CellPhone phoney;

    //Constructor
    public Customer(String n,CellPhone c,PhonePlan p){
        this.name=n;
        this.phoney=c;
        this.planphone=p;
        this.callsMade=0;

        //calculating the price of minutes
        if (planphone.getplanType())
            balance = 0.4f* planphone.getMinutesAllowed() ;
        else
            balance = 0;
    }
    //get method
    public String getname(){return this.name;}
    public CellPhone getphone(){return this.phoney;}
    public PhonePlan getplanphone(){return this.planphone;}
    public int getCallsMade() {return this.callsMade;}

    //set method
    public void setname(String n){this.name=n;}
    public void setphone(CellPhone phone){this.phoney=phone;}
    public void setplanphone(PhonePlan planphone){this.planphone=planphone;}
    public void setCallsMade(int c) {this.callsMade=c;}

    //to string printing the info and appending the info from the previous class
    public String toString(){
        boolean type=planphone.getplanType();
        if (type==true)
            return (name+ " with a " + phoney.getManufacturer() +" " +phoney.getModel() + " on a " + planphone.toString());
        else
            return (name+ " with a " + phoney.getManufacturer() +" " +getphone().getModel() + " on a " + planphone.toString());
    }
    //calculating call length and amount of calls made
    public void Phone(Customer c,int callLength){
        if (((c.planphone.getplanType()) || (this.planphone.getplanType())) && ((callLength > c.planphone.getMinutesRemaining()) || (callLength > planphone.getMinutesRemaining()))){

        }
        else if (!c.planphone.getplanType()&& !this.planphone.getplanType()){
            c.planphone.setMinutesUsed(c.planphone.getMinutesUsed()+callLength);
            planphone.setMinutesUsed(planphone.getMinutesUsed()+callLength);
            c.callsMade ++;
            callsMade++;
        }
        else {
            c.planphone.setMinutesUsed(c.planphone.getMinutesUsed()+callLength) ;
            planphone.setMinutesUsed(planphone.getMinutesUsed()+callLength);
            c.callsMade ++;
            callsMade++;
        }
    }
    //Calculating cost of buying more minutes
    public void buyMinutes(int m) {
        if (planphone.getplanType()) {
            balance += (0.4f * m);
            this.planphone.setMinutesAllowed(this.planphone.getMinutesAllowed() +m );
        }
    }
    //recording how much darta the customer used
    public void accessInternet (int KB) {
        if (this.planphone.getplanType()) {
            if (this.planphone.getDataRemaining() >= KB) {
                this.planphone.setDataUsed(this.planphone.getDataUsed() + KB);
            } else
                this.planphone.setDataUsed(this.planphone.getDataAllowed());
        } else {
            this.planphone.setDataUsed(this.planphone.getDataUsed() + KB);

        }
    }
    //printing the monthly statement of each customer
    public void printMonthlyStatement(){
        String PlanType;
        if (!this.planphone.getplanType()){
            PlanType = "Regular (" + this.planphone.getMinutesAllowed() + " minute, " + (this.planphone.getDataAllowed()/1000000.00) + "GB Data)";
        }
        else{

            PlanType =("Pay-As-You-Go");
        }

        if (!this.planphone.getplanType()){
            int monthlycharge1 = 25;
            int monthlycharge2 = 15;
            int datacharge = 10;
            PlanType = "Regular (" + this.planphone.getMinutesAllowed() + " minute, " + (this.planphone.getDataAllowed()/1000000.00) + "GB Data)";

            if (this.planphone.getMinutesAllowed() == 200){
                balance+=monthlycharge1;
            }
            else
                balance+=monthlycharge2;

            if (this.planphone.getMinutesAllowed()<this.planphone.getMinutesUsed()){
                Talkcharge = (float) ((this.planphone.getMinutesUsed()-this.planphone.getMinutesAllowed())*0.15);
            }
            if (this.planphone.getDataUsed()>this.planphone.getDataAllowed()){
                DataCharge = (float) ((this.planphone.getDataUsed()-this.planphone.getDataAllowed())*0.00005);
            }
            MonthlyCharges = (float) (balance+((this.planphone.getDataAllowed()/1000000.00)*10));
            System.out.print(String.format("\n"+"%-24s%-35s", "Name: ", name));
            System.out.print(String.format("\n"+"%-24s%-35s", "Plan Type: ", PlanType));
            System.out.print(String.format("\n"+"%-24s%-35s", "Minutes Used: ", this.planphone.getMinutesUsed()+MinutesPurchased));
            System.out.print(String.format("\n"+"%-24s%-35s", "Data Used: ", this.planphone.getDataUsed()));
            System.out.print(String.format("\n"+"%-24s%-35s", "Calls Made: ", callsMade));
            System.out.print(String.format("\n"+"%-24s%-35.2f", "Monthly Charges: ", MonthlyCharges));
            System.out.print(String.format("\n"+"%-24s%-35.2f", "Voice Overtime Charges: ", Talkcharge));
            System.out.print(String.format("\n"+"%-24s%-35.2f", "Data Overuse Charges: ", DataCharge));
            System.out.print(String.format("\n"+"%-24s%-35.2f", "HST: ",(MonthlyCharges+Talkcharge+DataCharge)*0.13));
            System.out.print(String.format("\n"+"%-24s%-35.2f", "Total Due: ", MonthlyCharges+Talkcharge+DataCharge+(MonthlyCharges+Talkcharge+DataCharge)*0.13));
            System.out.println();

        }

        else{
            System.out.print(String.format("\n"+"%-24s%-35s", "Name: ", name));
            System.out.print(String.format("\n"+"%-24s%-35s", "Plan Type: ", PlanType));
            System.out.print(String.format("\n"+"%-24s%-35s", "Minutes Used: ", getplanphone().getMinutesUsed()+MinutesPurchased));
            System.out.print(String.format("\n"+"%-24s%-35s", "Minutes Remaining: ", this.planphone.getMinutesRemaining()));
            System.out.print(String.format("\n"+"%-24s%-35s", "Data Used: ", this.planphone.getDataUsed()));
            System.out.print(String.format("\n"+"%-24s%-35s", "Data Remaining: ", this.planphone.getDataRemaining()));
            System.out.print(String.format("\n"+"%-24s%-35s", "Calls Made: ", callsMade));
            System.out.print(String.format("\n"+"%-24s%-35.2f", "Monthly Charges: ", balance));
            System.out.print(String.format("\n"+"%-24s%-35.2f", "HST: ",balance*0.13));
            System.out.print(String.format("\n"+"%-24s%-35.2f", "Total Due: ", balance +(balance*0.13)));
        }

    }

}
