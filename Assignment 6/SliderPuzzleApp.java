/**
 * Created by abdul on 2017-03-02.
 * 101042166
 */
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class SliderPuzzleApp extends Application {
    private SliderPuzzleGame model;
    private SliderPuzzleView view;
    private GamePiece selectedPiece;
    private boolean justGrabbed;
    private int lastX;
    private int lastY;

    public void start(Stage primaryStage) {
        model = new SliderPuzzleGame();
        view = new SliderPuzzleView(model);
        // Add event handlers to the inner game board buttons
        for (int w=1; w<=(GameBoard.WIDTH); w++) {
            for (int h=1; h<=(GameBoard.HEIGHT); h++) {
                view.getGridSection(w, h).setOnMousePressed(new EventHandler<MouseEvent>() {
                    public void handle(MouseEvent mouseEvent) {
                        handleGridSectionSelection(mouseEvent);
                    }
                });
                view.getGridSection(w, h).setOnMouseDragged(new EventHandler<MouseEvent>() {
                    public void handle(MouseEvent mouseEvent) {
                        handleGridSectionMove(mouseEvent);
                    }
                });
            }
        }
        // Plug in the Start button and NextBoard button event handlers
        view.getStartButton().setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent actionEvent) {
                model.startBoard();
                view.update();
            }
        });
        view.getNextBoardButton().setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent actionEvent) {
                model.moveToNextBoard();
                view.update();
            }
        });
        primaryStage.setTitle("Slide Puzzle Application");
        primaryStage.setResizable(false);
        primaryStage.setScene(new Scene(view, -10+SliderPuzzleView.GRID_UNIT_SIZE *
                (GameBoard.WIDTH+2),45 + SliderPuzzleView.GRID_UNIT_SIZE *
                (GameBoard.HEIGHT+2)));
        primaryStage.show();
        // Update the view upon startup
        view.update();
    }
    //Grid Section Selection Handler
    private void handleGridSectionSelection(MouseEvent mouseEvent) {
        for (int w=1; w<7; w++){
            for (int h=1; h<7; h++){
                if (mouseEvent.getSource()==view.getGridSection(w,h)){
                    if(model.getCurrentBoard().pieceAt(w-1,h-1)!=null){//Checks to see if spot is null
                        selectedPiece=model.getCurrentBoard().pieceAt(w-1,h-1);
                        justGrabbed=true;
                        lastX=(int)mouseEvent.getX();
                        lastY=(int)mouseEvent.getY();
                    }
                }
            }
        }
    }
    //Grid Section Move Handler
    private void handleGridSectionMove(MouseEvent mouseEvent) {
        int currentGridX = (int) mouseEvent.getX();
        int currentGridY = (int) mouseEvent.getY();
        int slideCompleted=0;

        if (Math.abs(currentGridX-lastX)>Math.abs(currentGridY-lastY)){
            if ((currentGridX>lastX)&&(selectedPiece.canMoveRightIn(model.getCurrentBoard()))&&currentGridX-lastX>=40){
                selectedPiece.moveRight();
                lastX+=40;
                slideCompleted++;
                if(selectedPiece instanceof GoalPiece){
                    model.getCurrentBoard().checkCompletion(selectedPiece);
                    if (model.getCurrentBoard().isCompleted())
                        model.completeBoard();
                }
            }
            else if ((selectedPiece.canMoveLeftIn(model.getCurrentBoard()))&&(currentGridX<lastX)&&(currentGridX-lastX<=40)){
                selectedPiece.moveLeft();
                lastX-=40;
                slideCompleted++;

            }
        }
        //for verticle movements
        else if(Math.abs(currentGridX-lastX)<Math.abs(currentGridY-lastY)){
            if ((selectedPiece.canMoveUpIn(model.getCurrentBoard()))&&(currentGridY<lastY)&&(currentGridY-lastY<=40)){
                selectedPiece.moveUp();
                lastY-=40;
                slideCompleted++;

            }
            else if((selectedPiece.canMoveDownIn(model.getCurrentBoard()))&&(currentGridY>lastY)&&(currentGridY-lastY>=40)){
                selectedPiece.moveDown();
                lastY+=40;
                slideCompleted++;

            }
        }
        if((justGrabbed==true)&&(slideCompleted>0)){
            model.makeAMove();
            justGrabbed=false;
        }

        view.update();

    }
    public static void main(String[] args) { launch(args); }
}



