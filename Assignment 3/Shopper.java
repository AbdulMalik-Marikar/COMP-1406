/**
 * Created by abdul on 2017-02-06.
 * 101042166
 */
public class Shopper {
    public static final int MAX_CART_ITEMS=100;
    private GroceryItem[] cart;
    private int finalnumitems;

    public Shopper(){
        cart= new GroceryItem[MAX_CART_ITEMS];
        finalnumitems=0;
        }

        public GroceryItem[] getCart(){return this.cart;}
    public int getFinalnumitems(){return this.finalnumitems;}

    public String toString() {
        return "Shopper with a shopping cart containing "+finalnumitems+" items";
    }

    public void addItem(GroceryItem x){
        if(getFinalnumitems()<=MAX_CART_ITEMS){
            cart[finalnumitems]=x;
            finalnumitems=finalnumitems+1;
        }
    }

    public void removeItem(GroceryItem x){
        for (int i=0;i<finalnumitems;i++){
            if (cart[i]==x){
                cart[i]= cart[finalnumitems-1];
                cart[finalnumitems-1]=null;
                finalnumitems=finalnumitems-1;
                break;
            }
        }
    }
    //creates a new array for a new bag, adds items to the bag if there is enough space and then removes it from the cart
    public GroceryBag[] packBags(){
        int bagnumber=0;
        GroceryBag[]packBags=new GroceryBag[MAX_CART_ITEMS];
        packBags[bagnumber]=new GroceryBag();
        for (int i=0;i<finalnumitems;i++){
            if (cart[i].getWeight()+packBags[bagnumber].getweight()<=GroceryBag.MAX_WEIGHT){
                packBags[bagnumber].addItem(cart[i]);
                removeItem(cart[i]);
                i=i-1;

            //if the item doesn't fit in the other bag it calculates one more bag and then adds it,it then will add items to the bag and remove it from the cart until no more bags are required
            }else{
                if (cart[i].getWeight()<=GroceryBag.MAX_WEIGHT){
                    bagnumber=bagnumber+1;
                    packBags[bagnumber]=new GroceryBag();
                    packBags[bagnumber].addItem(cart[i]);
                    removeItem(cart[i]);
                    i=i-1;
                }
            }
        }//creates array adding all the items of the bag into one array
        GroceryBag[] packbagsnew=new GroceryBag[bagnumber+1];
        for (int i=0;i<bagnumber+1;i++){
            packbagsnew[i]=packBags[i];
        }
        return packbagsnew;
    }
}
