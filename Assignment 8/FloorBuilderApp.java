/**
 * Created by abdul on 2017-03-14.
 */
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;



public class FloorBuilderApp extends Application  {
    private FloorBuilderView   view;
    private Building           model;
    private int                currentFloor;    // Index of the floor being displayed
    private int                currentColor;    // Index of the current room color
    private VBox aPane;

    public void start(Stage primaryStage) {
        model = Building.example();
        currentFloor = 0;
        currentColor = 0;

        aPane = new VBox();
        view = new FloorBuilderView(model);
        view.setPrefWidth(Integer.MAX_VALUE);
        view.setPrefHeight(Integer.MAX_VALUE);

        Menu selectMenu= new Menu("_Select Floor");


        MenuBar menuBar= new MenuBar();
        menuBar.getMenus().addAll(selectMenu);
        aPane.getChildren().add(menuBar);

        MenuItem[] floors= new MenuItem[model.getFloorPlans().length];
        ToggleGroup toggleGroup=new ToggleGroup();
        for (int i=0; i<model.getFloorPlans().length;i++){
            if(model.getFloorPlan(i).getName().equals("Basement")){
                floors[i]=new MenuItem(model.getFloorPlan(i).getName());
            }
            else{
                floors[i]=new MenuItem(model.getFloorPlan(model.getFloorPlans().length-i-2).getName());
            }
        }
        selectMenu.getItems().addAll(floors[0],floors[1],floors[2],floors[3],new SeparatorMenuItem(),floors[4]);

        for(int i=0;i<model.getFloorPlans().length;i++){
           final int j=i;
           floors[j].setOnAction(new EventHandler<ActionEvent>() {
               @Override
               public void handle(ActionEvent event) {
                   currentFloor=j;
                   view.update(currentFloor,currentColor);
               }
           });

        }

        aPane.getChildren().add(view);
        primaryStage.setTitle("Floor Plan Builder");
        primaryStage.setScene(new Scene(aPane, 370,340));
        primaryStage.show();



        // Plug in the floor panel event handlers:
        for (int r=0; r<model.getFloorPlan(0).size(); r++) {
            for (int c=0; c<model.getFloorPlan(0).size(); c++) {
                view.getFloorTileButton(r, c).setOnAction(new EventHandler<ActionEvent>() {
                    public void handle(ActionEvent event) {
                        handleTileSelection(event);
                    }});
            }
        }

        // Plug in the radioButton event handlers
        view.getEditWallsButton().setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                view.update(currentFloor, currentColor);
            }});
        view.getSelectExitsButton().setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                view.update(currentFloor, currentColor);
            }});
        view.getEditRoomsButton().setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                view.update(currentFloor, currentColor);
            }});
        view.getDefineRoomsButton().setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                view.update(currentFloor, currentColor);
            }});

        // Plug in the office color button
        view.getRoomColorButton().setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                currentColor = (currentColor + 1)%view.ROOM_COLORS.length;
                view.update(currentFloor, currentColor);
            }});

        view.getBuildingOverviewButton().setOnMousePressed(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                BuildingDialog buildingDialog = new BuildingDialog(model, view);

                buildingDialog.showAndWait();
                // Plug in Event Handler for Directory Listing Button


            }});
        view.update(currentFloor, currentColor);
    }

    // Handle a Floor Tile Selection
    private void handleTileSelection(ActionEvent e) {
        // Determine which row and column was selected
        int r=0, c=0;
        OUTER:
        for (r=0; r<model.getFloorPlan(0).size(); r++) {
            for (c=0; c<model.getFloorPlan(0).size(); c++) {
                if (e.getSource() == view.getFloorTileButton(r, c))
                    break OUTER;
            }
        }

        // Check if we are in edit wall mode, then toggle the wall
        if (view.getEditWallsButton().isSelected()) {
            model.getFloorPlan(currentFloor).setWallAt(r, c, !model.getFloorPlan(currentFloor).wallAt(r, c));
            // Remove this tile from the room if it is on one, because it is now a wall
            Room room = model.getFloorPlan(currentFloor).roomAt(r, c);
            if (room != null)
                room.removeTile(r, c);
        }

        // Otherwise check if we are in edit exits mode
        else if (view.getSelectExitsButton().isSelected()) {
            if (model.exitAt(r, c) != null)
                model.removeExit(r, c);
            else {
                model.addExit(r, c);
                // Remove this tile from the room if it is on one, because it is now an exit
                Room off = model.getFloorPlan(currentFloor).roomAt(r, c);
                if (off != null)
                    off.removeTile(r, c);
            }
        }

        // Otherwise check if we are selecting a room tile
        else if (view.getEditRoomsButton().isSelected()) {
            if (!model.getFloorPlan(currentFloor).wallAt(r, c) && !model.hasExitAt(r, c)) {
                Room room = model.getFloorPlan(currentFloor).roomAt(r, c);
                if (room != null) {
                    room.removeTile(r, c);
                    if (room.getNumberOfTiles() == 0)
                        model.getFloorPlan(currentFloor).removeRoom(room);
                }
                else {
                    room = model.getFloorPlan(currentFloor).roomWithColor(currentColor);
                    if (room == null) {
                        room = model.getFloorPlan(currentFloor).addRoomAt(r, c);
                        room.setColorIndex(currentColor);
                    }
                    else {
                        room.addTile(r, c);
                    }
                }
            }
        }
        // Otherwise do nothing
        else if (view.getDefineRoomsButton().isSelected()) {
            if (model.getFloorPlan(currentFloor).roomAt(r, c) != null) {
                RoomInfoDialog roomDetailsDialog = new RoomInfoDialog(view, model, r, c, currentFloor);
            }
            else{
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("INVALID SELECTION");
                alert.setHeaderText(null);
                alert.setContentText("You must select a tile that belongs to a room");
                alert.showAndWait();
            }
        }

        view.update(currentFloor, currentColor);
    }

    public static void main(String[] args) {
        launch(args);
    }
}