import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

import java.util.Optional;

/**
 * Created by abdul on 2017-03-18.
 */
public class RoomInfoDialog {

    public RoomInfoDialog(FloorBuilderView view,Building model,int r,int c, int currentFloor){
        Dialog roomDetailDialog = new Dialog();
        roomDetailDialog.setTitle("Room Detail");
        roomDetailDialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

        GridPane roompane= new GridPane();
        roompane.setPadding(new Insets(10,10,10,10));
        roompane.setHgap(10);
        roompane.setVgap(10);

        Label Occupant= new Label("Occupant: ");
        Occupant.setPrefHeight(27);
        roompane.add(Occupant,0,0);

        Label Position= new Label("Position: ");
        Position.setPrefHeight(27);
        roompane.add(Position,0,1);

        Label Number=new Label("Number: ");
        Number.setPrefHeight(27);
        roompane.add(Number,0,2);

        Label Floor=new Label("Floor: ");
        Floor.setPrefHeight(27);
        roompane.add(Floor,0,3);

        Label Size= new Label("Size: ");
        Size.setPrefHeight(27);
        roompane.add(Size,0,4);


        TextField occupantFeild= new TextField();
        occupantFeild.setPrefSize(300,27);
        occupantFeild.setPromptText("Person who occupies this room");
        roompane.add(occupantFeild, 1, 0, 2, 1);

        TextField positionFeild= new TextField();
        positionFeild.setPrefSize(300,27);
        positionFeild.setPromptText("Job position/title of this person");
        roompane.add(positionFeild, 1, 1, 2, 1);

        TextField numberFeild= new TextField();
        numberFeild.setPrefSize(300,27);
        numberFeild.setPromptText("The room number");
        roompane.add(numberFeild, 1, 2);

        TextField floorFeild = new TextField();
        floorFeild.setPrefSize(300, 27);
        floorFeild.setEditable(false);
        floorFeild.setText(model.getFloorPlan(currentFloor).getName());
        roompane.add(floorFeild, 1, 3, 2, 1);

        TextField sizeFeild = new TextField();
        sizeFeild.setPrefSize(300, 27);
        sizeFeild.setEditable(false);
        sizeFeild.setText(model.getFloorPlan(currentFloor).roomAt(r, c).getNumberOfTiles() + " Sq. Ft.");
        roompane.add(sizeFeild, 1, 4, 2, 1);

        Button colourButton= new Button();
        colourButton.setPrefSize(150,27);
        colourButton.setFocusTraversable(false);
        colourButton.setStyle(view.getFloorTileButton(r,c).getStyle());
        roompane.add(colourButton, 2, 2);

        if (model.getFloorPlan(currentFloor).roomAt(r,c).getOccupant()!=null){
            occupantFeild.setText(model.getFloorPlan(currentFloor).roomAt(r,c).getNumber());
        }
        if (model.getFloorPlan(currentFloor).roomAt(r,c).getPosition()!=null){
            positionFeild.setText(model.getFloorPlan(currentFloor).roomAt(r,c).getPosition());
        }
        if (model.getFloorPlan(currentFloor).roomAt(r,c).getNumber()!=null){
            numberFeild.setText(model.getFloorPlan(currentFloor).roomAt(r,c).getNumber());
        }

        roomDetailDialog.getDialogPane().setContent(roompane);

        Optional result= roomDetailDialog.showAndWait();

        if(result.get()==ButtonType.OK){
            model.getFloorPlan(currentFloor).roomAt(r,c).setOccupant(occupantFeild.getText());
            model.getFloorPlan(currentFloor).roomAt(r,c).setPosition(positionFeild.getText());
            model.getFloorPlan(currentFloor).roomAt(r,c).setNumber(numberFeild.getText());

        }








    }
}
