/**
 * Created by abdul on 2017-02-26.
 * 101042166
 */
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class ShoppingListApp extends Application {
    private Shopper Transfer;
    private ShoppingListView view;

    //Creating the window
    public void start(Stage primaryStage) {
        Transfer = new Shopper();
        view = new ShoppingListView(Transfer);

        //Event handlers
        view.getBuy().setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent actionEvent) {
                BuyButton();
            }
        });

        view.getProducts().setOnMousePressed(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent mouseEvent) {
                ListSelection();
            }
        });

        view.getButtonreturn().setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent actionEvent) {
                ReturnButton();
            }
        });

        view.getCheckout().setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent actionEvent) {
                CheckoutButton();
            }
        });

        view.getShoppingcart().setOnMousePressed(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent mouseEvent) {
                ListSelection();
            }
        });

        primaryStage.setTitle("Grocery Store Application"); // Set title of window
        primaryStage.setResizable(false);
        primaryStage.setScene(new Scene(view, 740, 390)); // Set size of window
        primaryStage.show();
    }

    private void BuyButton() {
        Carryable selection = view.getProducts().getSelectionModel().getSelectedItem();
        Transfer.addItem(selection);
        view.update();
    }

    private void ListSelection() {
        view.update();
    }

    private void ReturnButton() {
        int selection = view.getShoppingcart().getSelectionModel().getSelectedIndex();
        Transfer.removeItem(Transfer.getCart()[selection]);
        view.update();
    }

    private void CheckoutButton() {
        if (view.getCheckout().getText() == "Restart Shopping") {
            for (int i = 0; i < Transfer.getNumItems(); i++) {
                Transfer.removeItem(Transfer.getCart()[i]);
                i = i - 1;
            }
            view.getCheckout().setDisable(true);
            view.getProducts().setDisable(false);
            view.getShoppingcart().setItems(FXCollections.observableArrayList(""));
            view.getContents().setItems(FXCollections.observableArrayList());
            view.getCheckout().setText("Checkout");
            view.getText().setText(String.format("$%1.2f", 0.00));


        } else {
            for (int i = 0; i < Transfer.getNumItems(); i++) {
                System.out.println(String.format("%-30s%10.2f", Transfer.getCart()[i].getDescription(), Transfer.getCart()[i].getPrice()));
            }
            System.out.println("----------------------------------------");
            System.out.println(String.format("%-30s%10.2f", "Total", Transfer.computeTotalCost()));
            Transfer.packBags();
            view.update();
        }
    }
    public static void main(String[] args){ launch(args);}
}
