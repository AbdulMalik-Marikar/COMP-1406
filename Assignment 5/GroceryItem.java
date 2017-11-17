/**
 * Created by abdul on 2017-02-09.
 * 101042166
 */
public class GroceryItem implements Carryable{
    private String      name;
    private float       price;
    private float       weight;


    public GroceryItem() {
        name = "?";
        price = 0;
        weight = 0;

    }
    public GroceryItem(String n, float p, float w) {
        name = n;
        price = p;
        weight = w;

    }
    public String getContents(){
        return "";
    }
    public String getDescription(){
        return name;
    }

    public String getName() { return name; }
    public float getPrice() { return price; }
    public float getWeight() { return weight; }


    public String toString () {
        return name + " weighing " + weight + "kg with price $" + price;
    }

}
