/**
 * Created by abdul on 2017-02-13.
 * 101042166
 */
public class PerishableItem extends GroceryItem{
    public PerishableItem(){
        super();
    }
    public PerishableItem(String a, float b, float c){
        super(a,b,c);
    }

    public String toString(){
        return  super.toString()+" (perishable)";
    }

}