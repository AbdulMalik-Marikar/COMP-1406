import javafx.collections.FXCollections;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;

/**
 * Created by abdul on 2017-02-26.
 * 101042166
 */
public class ShoppingListView extends Pane {
    private Shopper transfer;
    private Label totalpricetitle,productstitle, shoppingcarttitle, itemstitle;
    private Button checkout,buttonreturn,buy;
    private ListView<GroceryItem> contents, product;
    private ListView<String> shoppingcart;
    private TextField cost;

    //get methods
    public Label getTotalpricetitle(){return totalpricetitle;}
    public Label getShoppingcarttitle(){return shoppingcarttitle;}
    public Label getProductstitle(){return productstitle;}
    public Label getItemstitle(){return itemstitle;}
    public Button getCheckout(){return checkout;}
    public Button getButtonreturn(){return buttonreturn;}
    public Button getBuy(){return buy;}
    public ListView<GroceryItem> getContents(){return contents;}
    public ListView<GroceryItem> getProducts(){return product;}
    public ListView<String> getShoppingcart(){return shoppingcart;}
    public TextField getText(){return cost;}

    //Constructor
    public ShoppingListView(Shopper s){
        transfer=s;

        //Creating a spot for the products title
        productstitle= new Label("Products");
        productstitle.relocate(10,10);
        productstitle.setPrefSize(200,35);

        //Creating a spot for the shopping cart title
        shoppingcarttitle= new Label("Shopping Cart");
        shoppingcarttitle.relocate(220,10);
        shoppingcarttitle.setPrefSize(200,35);

        //Creating a spot for the contents title
        itemstitle=new Label("Contents");
        itemstitle.relocate(430,10);
        itemstitle.setPrefSize(120,35);

        //Crating a spot for the buy/add to cart button
        buy=new Button ("Buy");
        buy.relocate(10,355);
        buy.setPrefSize(200,25);

        //Crating a spot for the return/remove from cart button
        buttonreturn= new Button ("Return");
        buttonreturn.relocate(220,355);
        buttonreturn.setPrefSize(200,25);

        //creating a spot for the checkout button
        checkout= new Button("Checkout");
        checkout.relocate(430,355);
        checkout.setPrefSize(120,25);

        //Creating an array of groceryitem products
        //creating a spot for products list
        product= new ListView<GroceryItem>();
        GroceryItem[] products = {
                new FreezerItem("Smart-Ones Frozen Entrees", 1.99f, 0.311f),
                new GroceryItem("SnackPack Pudding", 0.99f, 0.396f),
                new FreezerItem("Breyers Chocolate Icecream",2.99f,2.27f),
                new GroceryItem("Nabob Coffee", 3.99f, 0.326f),
                new GroceryItem("Gold Seal Salmon", 1.99f, 0.213f),
                new GroceryItem("Ocean Spray Cranberry Cocktail",2.99f,2.26f),
                new GroceryItem("Heinz Beans Original", 0.79f, 0.477f),
                new RefrigeratorItem("Lean Ground Beef", 4.94f, 0.75f),
                new FreezerItem("5-Alive Frozen Juice",0.75f,0.426f),
                new GroceryItem("Coca-Cola 12-pack", 3.49f, 5.112f),
                new GroceryItem("Toilet Paper - 48 pack", 40.96f, 10.89f),
                new RefrigeratorItem("2L Sealtest Milk",2.99f,2.06f),
                new RefrigeratorItem("Extra-Large Eggs",1.79f,0.77f),
                new RefrigeratorItem("Yoplait Yogurt 6-pack",4.74f,1.02f),
                new FreezerItem("Mega-Sized Chocolate Icecream",67.93f,15.03f)};
        product.setItems(FXCollections.observableArrayList(products));
        product.relocate(10,45);
        product.setPrefSize(200,300);


        //creating a spot for shopping cart items list
        shoppingcart= new ListView<String>();
        shoppingcart.setItems(FXCollections.observableArrayList(""));
        shoppingcart.relocate(220,45);
        shoppingcart.setPrefSize(200,300);

        //creating a spot for contents list
        contents= new ListView<GroceryItem>();
        contents.relocate(430,45);
        contents.setPrefSize(300,300);

        //Create spot for total price
        cost= new TextField();
        cost.relocate(630,355);
        cost.setPrefSize(100,25);
        cost.setEditable(false);
        cost.setText(String.format("$%1.2f",transfer.computeTotalCost()));
        cost.setAlignment(Pos.BASELINE_CENTER);

        getChildren().addAll(productstitle,product,shoppingcarttitle,itemstitle,contents,buy,buttonreturn,checkout,shoppingcart,cost);

        update();
    }

    public void update(){
        String[] Length= new String[transfer.getNumItems()];
        for (int i=0;i<transfer.getNumItems();i++)
            Length[i]=transfer.getCart()[i].getDescription();
        int index= shoppingcart.getSelectionModel().getSelectedIndex();
        shoppingcart.setItems(FXCollections.observableArrayList(Length));

        //computes total cost
        cost.setText(String.format("$%1.2f",transfer.computeTotalCost()));
        cost.setAlignment(Pos.BASELINE_CENTER);

        //makes sure that the right buttos are available for the right task
        buy.setDisable(product.getSelectionModel().getSelectedIndex() < 0);
        buttonreturn.setDisable(shoppingcart.getSelectionModel().getSelectedIndex() < 0);
        checkout.setDisable(transfer.getNumItems()<=0);

        for (int i=0; i<transfer.getNumItems();i++){
            if(transfer.getCart()[i] instanceof GroceryBag){
                buy.setDisable(true);
                buttonreturn.setDisable(true);
                product.setDisable(true);
                checkout.setText("Restart Shopping");
                checkout.setStyle("-fx-font: 11 arial");
            }
        }

        if(shoppingcart.getSelectionModel().getSelectedIndex() >=0){
            if(transfer.getCart()[shoppingcart.getSelectionModel().getSelectedIndex()] instanceof GroceryBag) {
                GroceryItem[] contentsOfBag = new GroceryItem[((GroceryBag)transfer.getCart()[shoppingcart.getSelectionModel().getSelectedIndex()]).getNumItems()];
                GroceryItem[] nullBagContent = ((GroceryBag)transfer.getCart()[shoppingcart.getSelectionModel().getSelectedIndex()]).getItems();
                for (int i=0; i<((GroceryBag)transfer.getCart()[shoppingcart.getSelectionModel().getSelectedIndex()]).getNumItems(); i++){
                    contentsOfBag[i]=nullBagContent[i];
                }contents.setItems(FXCollections.observableArrayList(contentsOfBag));
            }else
                contents.setItems(FXCollections.observableArrayList());
        }
    }
}
