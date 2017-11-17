/**
 * Created by abdul on 2017-02-09.
 * 101042166
 */
public class GroceryBag implements Carryable {
    public static final float MAX_WEIGHT = 5;  // max weight allowed (kg)
    public static final int   MAX_ITEMS = 25;  // max # items allowed

    private GroceryItem[]   items;      // actual GroceryItems in bag
    private int             numItems;   // # of GroceryItems in bag
    private float           weight;     // current weight of bag

    public GroceryBag() {
        items = new GroceryItem[MAX_ITEMS];
        numItems = 0;
        weight = 0;
    }

    public GroceryItem[] getItems() { return items; }
    public int getNumItems() { return numItems; }
    public float getWeight() { return weight; }

    public String toString() {
        if (weight == 0)
            return "An empty grocery bag";
        return ("A " + weight + "kg grocery bag with " + numItems + " items");
    }
    public String getContents(){
        String n="";
        for (int i=0;i<numItems;i++)
            n+=String.format("%3s%1s","",items[i]);
        return n;
    }
    public String getDescription(){
        return "GROCERY BAG ("+getWeight()+" KG)";
    }
    public float getPrice(){
        float total = 0;
        for (int i=0; i<numItems; i++) {
            total += items[i].getPrice();
        }
        return total;
    }

    public boolean canHold(GroceryItem g) {
        return (((weight + g.getWeight()) <= MAX_WEIGHT) && (numItems <= MAX_ITEMS));
    }

    public void addItem(GroceryItem g) {
        if (canHold(g)) {
            items[numItems++] = g;
            weight += g.getWeight();
        }
    }

    public void removeItem(GroceryItem item) {
        for (int i = 0; i < numItems; i++) {
            if (items[i] == item) {
                weight -= items[i].getWeight();
                items[i] = items[numItems - 1];
                numItems -= 1;
                return;
            }
        }
    }

    // Finds and returns the heaviest item in the shopping cart
    public GroceryItem heaviestItem() {
        if (numItems == 0)
            return null;
        GroceryItem heaviest = items[0];
        for (int i=0; i<numItems; i++) {
            if (items[i].getWeight() > heaviest.getWeight()) {
                heaviest = items[i];
            }
        }
        return heaviest;
    }

    // Determines whether or not the given item in the shopping cart
    public boolean has(GroceryItem item) {
        for (int i = 0; i < numItems; i++) {
            if (items[i] == item) {
                return true;
            }
        }
        return false;
    }

    // Remove all perishables from the bag and return an array of them
    public GroceryItem[] unpackPerishables() {
        int perishableCount = 0;
        for (int i=0; i<numItems; i++) {
            if (items[i] instanceof PerishableItem)
                perishableCount++;
        }
        GroceryItem[] perishables = new GroceryItem[perishableCount];
        perishableCount = 0;
        for (int i=0; i<numItems; i++) {
            if (items[i]instanceof PerishableItem) {
                perishables[perishableCount++] = items[i];
                removeItem(items[i]);
                i--;
            }
        }
        return perishables;
    }
}