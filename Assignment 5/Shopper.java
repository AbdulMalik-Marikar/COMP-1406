/**
 * Created by abdul on 2017-02-09.
 * 101042166
 */
public class Shopper {
    public static final int MAX_CART_ITEMS = 100;  // max # items allowed

    private Carryable[] cart;       // items to be purchased
    private int numItems;   // #items to be purchased

    public Shopper() {
        cart = new Carryable[MAX_CART_ITEMS];
        numItems = 0;
    }

    public Carryable[] getCart() {
        return cart;
    }

    public int getNumItems() {
        return numItems;
    }

    public String toString() {
        return "Shopper with shopping cart containing " + numItems + " items";
    }

    // Return the total cost of the items in the cart
    public float totalCost() {
        float total = 0;
        for (int i = 0; i < numItems; i++) {
            total += cart[i].getPrice();
        }
        return total;
    }

    // Add an item to the shopper's shopping cart
    public void addItem(Carryable item) {
        if (this.getNumItems() <= MAX_CART_ITEMS) {
            cart[numItems] = item;
            numItems++;
        }


    }

    // Removes the given item from the shopping cart
    public void removeItem(Carryable item) {
        for (int i = 0; i < numItems; i++) {
            if (cart[i] == item) {
                cart[i] = cart[numItems - 1];
                numItems -= 1;
                return;
            }
        }
    }

    // Go through the shopping cart and pack all packable items into bags
    public void packBags() {
        GroceryBag[] packedBags = new GroceryBag[numItems];
        int bagCount = 0;

        GroceryBag currentBag = new GroceryBag();
        for (int i = 0; i < numItems; i++) {
            GroceryItem item = (GroceryItem) cart[i];
            if (item.getWeight() <= GroceryBag.MAX_WEIGHT) {
                if (!currentBag.canHold(item)) {
                    packedBags[bagCount++] = currentBag;
                    currentBag = new GroceryBag();
                }
                currentBag.addItem(item);
                removeItem(item);
                i--;
            }
        }
        // Check this in case there were no bagged items
        if (currentBag.getWeight() > 0)
            packedBags[bagCount++] = currentBag;

        for (int i = 0; i < bagCount; i++)
            addItem(packedBags[i]);
    }

    public void displayCartContents() {
        for (int i = 0; i < numItems; i++) {
            if (cart[i].getContents()=="") {
                System.out.println(cart[i].getDescription());
            } else {
                System.out.println(cart[i].getDescription());
                System.out.println(cart[i].getContents());
            }

        }
    }
    //makes an array of removed perishable items
    public PerishableItem[] removePerishables(){
        int Perishableitems=0;
        for (int i=0;i<numItems;i++){
            if (cart[i] instanceof GroceryItem) {//checks the grocerybag to see if the item is there
                if (cart[i] instanceof PerishableItem) {
                    Perishableitems++;
                }
            } else {
                GroceryBag bags = (GroceryBag) cart[i];
                for (int x= 0; x < bags.getNumItems(); x++) {
                    if (bags.getItems()[x] instanceof PerishableItem) {//checks for item in the cart
                        Perishableitems++;
                    }
                }
            }
        }
        PerishableItem[] result = new PerishableItem[Perishableitems]; // make an array of the perishable items large enough to hold all the items
        int spot= 0;
        for (int i = 0; i < numItems; i++) {
            if (cart[i] instanceof GroceryItem) {
                if (cart[i] instanceof PerishableItem) {
                    result[spot] = (PerishableItem) cart[i];
                    spot++;
                    removeItem(cart[i]);
                    i--;
                }
            } else {
                GroceryBag bags = (GroceryBag) cart[i];
                for (int x = 0; x < bags.getNumItems(); x++) {
                    if (bags.getItems()[x] instanceof PerishableItem) {
                        result[spot] = (PerishableItem) bags.getItems()[x];
                        spot++;
                        bags.removeItem(bags.getItems()[x]);
                        x--;
                    }
                }
            }
        }
        return result;
    }
    // calculates the cost of all the items from the freezer
    public float computeFreezerItemCost() {
        float newprice = 0.0f;
        for (int i = 0; i < numItems; i++) { //scan through each item in cart
            if (cart[i] instanceof GroceryItem) { //if the item is a grocery item then check if the item is a freezeritem
                if (cart[i] instanceof FreezerItem) { // if item is freezer item then add the price
                    newprice += (cart[i]).getPrice();

                }
            } else { //if the item in cart is a bag then carry out this procedure
                if (cart[i] instanceof GroceryBag) {
                    GroceryBag Bagsnew = (GroceryBag) cart[i]; // make a GroceryBag object which will scan the bag
                    for (int x = 0; x< Bagsnew.getNumItems(); x++) {
                        if (Bagsnew.getItems()[x] instanceof FreezerItem) { //if item inside the bag is a freezeritem then add that price
                            newprice += Bagsnew.getItems()[x].getPrice();

                        }
                    }
                }
            }
        }
        return newprice;
    }
    //using the totalcost method from before we compute the final cost
    public float computeTotalCost(){
        return totalCost();
    }
}
