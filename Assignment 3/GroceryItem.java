/**
 * Created by abdul on 2017-01-31.
 * 101042166
 */
public class GroceryItem {
    private String name;
    private float price;
    private float weight;
    private boolean perishable;

    //constructor taking in 3 properties
    public GroceryItem(String n,float p,float w){
        name=n;
        price=p;
        weight=w;
        perishable=false;
    }
    //zero constructor
    public GroceryItem(){
        name="UNKNOWN";
        price=0;
        weight=0;
        perishable=false;
    }
    //constructor taking in 4 properties
    public GroceryItem(String n,float p,float w,boolean r){
        name=n;
        price=p;
        weight=w;
        perishable=r;
    }
    //get methods
    public String getName() {return name;}
    public float getPrice() {return price;}
    public float getWeight() {return weight;}
    public boolean isPerishable() {return perishable;}

    //to string
    public String toString(){
        return (getName()+" weighing "+getWeight()+"KG with a price of $"+getPrice());
    }



}
