import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

import java.util.Optional;
/**
 * Created by abdul on 2017-03-18.
 */
public class DirectoryDialog extends Dialog {
    public  DirectoryDialog(Building model,FloorBuilderView view){
        setTitle("Directory Listing");
        setHeaderText(null);

        GridPane pane= new GridPane();
        pane.setVgap(10);
        pane.setHgap(10);
        pane.setPadding(new Insets(10,10,10,10));


        getDialogPane().setPrefSize(300,300);

        ObservableList<String> directorylist= FXCollections.observableArrayList();

        for(int i=0;i<model.getFloorPlans().length;i++){
            for(int j=0;j<model.getFloorPlans()[i].size();j++){
                for(int k=0;k<model.getFloorPlans()[i].size();k++){
                    if(model.getFloorPlans()[i].roomAt(j,k)!=null){
                        if(model.getFloorPlans()[i].roomAt(j,k).getOccupant()!= null&& model.getFloorPlans()[i].roomAt(j,k).getPosition()!= null&& model.getFloorPlans()[i].roomAt(j,k).getNumber()!=null){
                            directorylist.add(model.getFloorPlans()[i].roomAt(j, k).getNumber() + " - " + model.getFloorPlans()[i].roomAt(j, k).getOccupant() + " (" + model.getFloorPlans()[i].roomAt(j, k).getPosition() + ")");
                        }
                    }
                }
            }
        }

        Button search=new Button("Search");
        search.setPrefWidth(500);

        ListView<String> directorylisting= new ListView<>(directorylist);
        directorylisting.setPrefWidth(getDialogPane().getPrefWidth());

        pane.add(directorylisting,0,0);
        pane.add(search,0,1);
        getDialogPane().getButtonTypes().addAll((new ButtonType("OK", ButtonBar.ButtonData.CANCEL_CLOSE)));
        getDialogPane().setContent(pane);

        search.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                TextInputDialog searchAlert= new TextInputDialog();

                searchAlert.getDialogPane().setPrefSize(650,100);
                searchAlert.setTitle("Input Required");
                searchAlert.setHeaderText(null);
                searchAlert.setContentText("Please enter the full name of the person you are searching for: ");

                Optional<String> result = searchAlert.showAndWait();

                if (result.isPresent()){
                    String searchedName = result.get();

                    Alert searchResultsAlert = new Alert(Alert.AlertType.INFORMATION);
                    searchResultsAlert.setTitle("Search Result");
                    searchResultsAlert.setContentText("That name does not match anyone in our records, please try again.");

                    for (int i = 0; i < model.getFloorPlans().length; i++){
                        for (int j = 0; j < model.getFloorPlans()[i].size(); j++){
                            for (int k = 0; k < model.getFloorPlans()[i].size(); k++){
                                if (model.getFloorPlans()[i].roomAt(j,k) != null){
                                    if(model.getFloorPlans()[i].roomAt(j,k).getNumber() != null && model.getFloorPlans()[i].roomAt(j,k).getPosition() != null && model.getFloorPlans()[i].roomAt(j,k).getOccupant() != null) {
                                        if (model.getFloorPlans()[i].roomAt(j, k).getOccupant().trim().equalsIgnoreCase(searchedName.trim())) {
                                            searchResultsAlert.setContentText(model.getFloorPlans()[i].roomAt(j, k).getOccupant() + " is a " + model.getFloorPlans()[i].roomAt(j, k).getPosition() + " and can be located on the " + model.getFloorPlans()[i].getName() + " in room " + model.getFloorPlans()[i].roomAt(j, k).getNumber());
                                        }

                                    }
                                }
                            }
                        }
                    }

                    searchResultsAlert.show();

                }

            }
        });

    }

}
