import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * Created by abdul on 2017-03-07.
 */
public class FloorBuilderApp extends Application{
    public static final String[] ROOM_COLORS =
            {"ORANGE", "YELLOW", "LIGHTGREEN", "DARKGREEN",
                    "LIGHTBLUE", "BLUE", "CYAN", "DARKCYAN",
                    "PINK", "DARKRED", "PURPLE", "GRAY"};
    private int selectedcolour=0;
    private Building model=Building.example();
    private FloorBuilderView view;
    private int exit=3;
    private int column=0;
    private int row=0;

    public void start(Stage primaryStage){
        view=new FloorBuilderView(model);

        for(int i=0;i<model.getFloorPlan(0).size();i++){
            for(int j=0;j<model.getFloorPlan(0).size();j++){
                view.getSquares(i,j).setOnMousePressed(new EventHandler<MouseEvent>() {
                    public void handle(MouseEvent mouseEvent) {
                        for (int x=0; x<model.getFloorPlan(0).size(); x++) {
                            for (int y = 0; y < model.getFloorPlan(0).size(); y++) {
                                if (view.getSquares(x, y).isPressed()) {
                                    column = x;
                                    row = y;
                                }
                            }
                        }
                        handlesquaresSelection(mouseEvent);
                    }
                });
            }
        }
        view.getPaintButton().setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                handlepaintbutton(mouseEvent);
            }
        });

        view.getRadiobutton1().setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                handleradiobutton1(mouseEvent);
            }
        });
        view.getRadiobutton2().setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                handleradiobutton2(mouseEvent);
            }
        });
        view.getRadiobutton3().setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                handleradiobutton3(mouseEvent);
            }
        });

        primaryStage.setTitle("Floor Plan Builder"); // Set title of window
        primaryStage.setResizable(true);
        primaryStage.setScene(new Scene(view, 500,500)); // Set size of window
        primaryStage.show();
    }

    public void handlepaintbutton (MouseEvent mouseEvent){
        if (selectedcolour<=11){
            view.getPaintButton().setStyle("-fx-base:"+ROOM_COLORS[selectedcolour++]);
        }
        else{
            selectedcolour=0;
        }
        view.getPaintButton().setDisable(false);
    }

    public void handleradiobutton3 (MouseEvent mouseEvent){
        view.getPaintButton().setDisable(false);
    }
    public void handleradiobutton1 (MouseEvent mouseEvent){
        view.getPaintButton().setDisable(true);
    }
    public void handleradiobutton2 (MouseEvent mouseEvent){
        view.getPaintButton().setDisable(true);
    }
    public void handleradiobutton4 (MouseEvent mouseEvent){
        view.getRadiobutton4().setDisable(true);
    }
    public void handlesquaresSelection (MouseEvent mouseEvent){
        if (view.getRadiobutton1().isSelected()) {
            if (model.getFloorPlan(0).wallAt(column, row)) {
                model.getFloorPlan(0).setWallAt(column, row, false);
                view.getSquares(column, row).setStyle("-fx-base:WHITE");
            } else {
                model.getFloorPlan(0).setWallAt(column, row, true);
                view.getSquares(column, row).setStyle("-fx-base:BLACK");
            }
        }else if (view.getRadiobutton2().isSelected()){
            if (model.hasExitAt(column,row)) {
                model.removeExit(column, row);
                view.getSquares(column, row).setStyle("-fx-base: WHITE;");
                exit--;
            }else{
                if(exit<model.MAXIMUM_EXITS) {
                    model.addExit(column, row);
                    view.getSquares(column, row).setStyle("-fx-base: RED;");
                    view.getSquares(column, row).setText("EXIT");
                    exit++;
                }
            }
        }else if (view.getRadiobutton3().isSelected()){
            if (!model.hasExitAt(row,column)&&!model.getFloorPlan(0).wallAt(row,column)) {
                if (model.getFloorPlan(0).roomAt(row, column) == null) {
                    if(model.getFloorPlan(0).roomWithColor(selectedcolour)!=null){
                        model.getFloorPlan(0).roomWithColor(selectedcolour).addTile(row,column);
                        view.getSquares(column,row).setStyle("-fx-base:"+ ROOM_COLORS[selectedcolour]);
                    }
                    else{
                        model.getFloorPlan(0).addRoomAt(row, column).setColorIndex(selectedcolour);
                        view.getSquares(column,row).setStyle("-fx-base:"+ ROOM_COLORS[selectedcolour]);
                        view.getTextField().setText(view.getBuilding().getFloorPlan(0).getName()+" with" +view.getBuilding().getFloorPlan(0).getNumberOfRooms()+" rooms");
                    }
                }else{
                    if(model.getFloorPlan(0).roomAt(row,column).getColorIndex()==selectedcolour){
                        model.getFloorPlan(0).roomAt(row,column).removeTile(column,row);
                        view.getSquares(column,row).setStyle("-fx-base:WHITE");
                        if (model.getFloorPlan(0).roomWithColor(selectedcolour).getNumberOfTiles()==0){
                            model.getFloorPlan(0).removeRoom(model.getFloorPlan(0).roomWithColor(selectedcolour));
                            view.getSquares(column,row).setStyle("-fx-base:WHITE");
                        }
                    }else{
                        Room room = model.getFloorPlan(0).roomAt(row,column);
                        model.getFloorPlan(0).roomAt(row,column).removeTile(row,column);
                        if (room.getNumberOfTiles()==0){
                            model.getFloorPlan(0).removeRoom(room);
                            view.getSquares(column,row).setStyle("-fx-base:WHITE");
                        }if (model.getFloorPlan(0).roomWithColor(selectedcolour)==null){
                            model.getFloorPlan(0).addRoomAt(row,column).setColorIndex(selectedcolour);
                            view.getSquares(column,row).setStyle("-fx-base:"+ ROOM_COLORS[selectedcolour]);
                        }else{
                            model.getFloorPlan(0).roomWithColor(selectedcolour).addTile(row,column);
                            view.getSquares(column,row).setStyle("-fx-base:"+ ROOM_COLORS[selectedcolour]);
                            view.getTextField().setText(view.getBuilding().getFloorPlan(0).getName()+" with" +view.getBuilding().getFloorPlan(0).getNumberOfRooms()+" rooms");
                        }
                    }
                }
            }
        }
    }
    public static void main(String[] args) {
        launch(args);
    }

}
