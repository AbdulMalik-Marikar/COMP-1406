/**
 * Created by abdul on 2017-01-27.
 * 101042166
 */
public class CellPhone {
    private String Model, Manufacturer;
    private int MonthsWarranty;
    private float Price;

    //Consructor with all fields
    public CellPhone(String n, String m, int w, float p){
        Model=n;
        Manufacturer=m;
        MonthsWarranty=w;
        Price=p;
    }
    //Constructor able to take no parameters
    public CellPhone(){
        Model= "Unknown";
        Manufacturer="Unknown";
        MonthsWarranty=0;
        Price=0;
    }

    //get methods
    public String getModel(){return this.Model;}
    public String getManufacturer() {return this.Manufacturer;}
    public int getMonthsWarranty() {return this. MonthsWarranty;}
    public float getPrice() {return this.Price;}

    //set methods
    public void setModel(String n){this.Model=n;}
    public void setPrice(float p){this.Price=p;}



}
