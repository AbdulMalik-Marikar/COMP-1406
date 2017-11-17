/**
 * Created by abdul on 2017-01-31.
 * 101042166
 */
public class GroceryBag {
    public static final int MAX_WEIGHT=5;
    public static final int MAX_ITEMS=25;
    private GroceryItem[] items;
    private int numitems;
    private float weight;

    //zero constructor
    public GroceryBag(){
        weight=0;
        numitems=0;
        items=new GroceryItem[MAX_ITEMS];
    }
    //get methods
    public float getweight(){return this.weight;}
    public int getNumItems(){return this.numitems;}
    public GroceryItem[] getitems(){return this.items;}

    //to string indiacting weight,and number of items in the bag or indicating if the bag is empty
    public String toString(){
        if (this.numitems>0&& this.weight>0){
            return("There are "+ getNumItems()+" items in the bag and it weighs "+getweight()+"KG");
        }else
            return("An Empty grocery bag ");
    }
    //adding items to the bag
    public void addItem(GroceryItem x) {
        if ((x.getWeight()+getweight() <= MAX_WEIGHT)&&(getNumItems()<=MAX_ITEMS)){
            items[numitems] = x;
            weight=x.getWeight()+getweight();
            numitems=numitems+1;
        }
    }
    //removing items from the bag
    public void removeItem(GroceryItem y){
        for (int i=0;i<numitems;i++){
            if (items[i]==y){
                items[i]=items[numitems-1];
                items[numitems-1]=null;
                numitems=numitems-1;
                weight=getweight()-y.getWeight();
            }
        }
    }
    //finidng the heaviest item in the bag
    public GroceryItem heaviestItem(){
        float notlightestitem=0;
        GroceryItem heaviest_name=null;
        if (numitems>0){
            for(int i=0;i<numitems;i++){
                if(items[i].getWeight()>notlightestitem){
                    notlightestitem=items[i].getWeight();
                    heaviest_name=items[i];
                }
            }
            return heaviest_name;
        }else
            return null;
    }
    //indicating if a certain item is in the bag
    public boolean has(GroceryItem item){
        for (int i=0;i<numitems;i++){
            if(items[i]==item){
                return true;
            }
        }
        return false;
    }
    //creating a new array by checking how many items are perishables, taking the number and
    //adding using it to create the length of the array. then removing the perishables and adding it to the new array
    public GroceryItem[] unpackPerishables(){
        int num_perishables=0;
        int arrayspot=0;
        for(int i=0;i<numitems;i++){
            if(items[i].isPerishable())
                num_perishables=num_perishables+1;
        }
        GroceryItem[] perishables=new GroceryItem[num_perishables];
        for (int i=0;i<numitems;i++){
            if (items[i].isPerishable()){
                perishables[arrayspot]=items[i];
                removeItem(items[i]);
                arrayspot=arrayspot+1;
                i=i-1;
            }
        }
        return perishables;
    }
}
