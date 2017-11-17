import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;


/**
 * Created by abdul on 2017-03-18.
 */
public class BuildingDialog extends Dialog {
    public BuildingDialog (Building model, FloorBuilderView view){
        Button listingbutton;
        int NumRooms=0;
        int totalsize=0;

        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(10, 10, 10, 10));
        setTitle("Building Overview");
        setHeaderText(null);
        getDialogPane().getButtonTypes().addAll((new ButtonType("OK", ButtonBar.ButtonData.CANCEL_CLOSE)));

        getDialogPane().setContent(gridPane);
        getDialogPane().setPrefWidth(250);

        Label numFloors= new Label("Num Floors: ");
        numFloors.setPrefHeight(27);
        gridPane.add(numFloors,0,0);

        Label numExits= new Label("Num Exits: ");
        numExits.setPrefHeight(27);
        gridPane.add(numExits,0,1);

        Label numRooms= new Label("Num Rooms: ");
        numRooms.setPrefHeight(27);
        gridPane.add(numRooms,0,2);

        Label totalSize= new Label("Total Size: ");
        totalSize.setPrefHeight(27);
        gridPane.add(totalSize,0,3);

        TextField numFloorsField= new TextField("Num Floors: ");
        numFloorsField.setPrefSize(100,27);
        numFloorsField.setEditable(false);
        gridPane.add(numFloorsField,1,0);

        TextField numExitsField= new TextField("Num Exits: ");
        numExitsField.setPrefSize(100,27);
        numExitsField.setEditable(false);
        gridPane.add(numExitsField,1,1);

        TextField numRoomsField= new TextField("Num Rooms: ");
        numRoomsField.setPrefSize(100,27);
        numRoomsField.setEditable(false);
        gridPane.add(numRoomsField,1,2);

        TextField totalSizeField= new TextField("Total Size: ");
        totalSizeField.setPrefSize(100,27);
        totalSizeField.setEditable(false);
        gridPane.add(totalSizeField,1,3);

        listingbutton= new Button("Directory Listing");
        listingbutton.setPrefSize(300,27);
        gridPane.add(listingbutton,0,5,2,1);

        for(int i=0;i<model.getFloorPlans().length;i++){
            NumRooms+=model.getFloorPlan(i).getNumberOfRooms();
        }
        numRoomsField.setText(NumRooms+"");

        numExitsField.setText(model.getNumExits()+"");

        for(int i=0; i<model.getFloorPlans().length;i++){
            for(int j=0;j<model.getFloorPlan(i).size();j++){
                for(int k=0;k<model.getFloorPlan(i).size();k++){
                    if (!model.getFloorPlan(i).wallAt(j,k)){
                        totalsize++;
                    }
                }
            }
        }
        totalSizeField.setText(totalsize+" Sq. Ft.");
        numFloorsField.setText(model.getFloorPlans().length + "");


        listingbutton.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                DirectoryDialog directoryDialog = new DirectoryDialog(model, view);
                directoryDialog.showAndWait();
            }
        });

    }

}
