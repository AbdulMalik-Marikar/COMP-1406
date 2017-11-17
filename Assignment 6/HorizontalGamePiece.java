/**
 * Created by abdul on 2017-03-02.
 * 101042166
 */
import javafx.scene.paint.Color;

public class HorizontalGamePiece extends GamePiece {
    public HorizontalGamePiece(int w, Color c, int x, int y) {
        super(w, 1, c, x, y);
    }
    public boolean canMoveLeftIn(GameBoard b) {
        if (this.topLeftX-1 >= 0){//checks for walls
            for (int i=0;i<b.getNumGamePieces();i++){
                if (b.getGamePieces()[i] instanceof HorizontalGamePiece) {
                    if (this.topLeftY==b.getGamePieces()[i].topLeftY&&this.topLeftX == b.getGamePieces()[i].topLeftX + b.getGamePieces()[i].width)
                        return false;
                }
                if (b.getGamePieces()[i] instanceof VerticalGamePiece) {
                    for (int j = 0; j < b.getGamePieces()[i].height; j++) {
                        if (this.topLeftY==b.getGamePieces()[i].topLeftY+j&&this.topLeftX == b.getGamePieces()[i].topLeftX+b.getGamePieces()[i].width)
                            return false;
                    }
                }
            }return true;
        }return false;
    }
    public boolean canMoveRightIn(GameBoard b) {
        if (this.topLeftX+this.width < 6){
            for (int k=0;k<b.getNumGamePieces();k++) {
                if (b.getGamePieces()[k] instanceof HorizontalGamePiece) {
                    if (this.topLeftY==b.getGamePieces()[k].topLeftY&&this.topLeftX + this.width == b.getGamePieces()[k].topLeftX)
                        return false;
                }
                if (b.getGamePieces()[k] instanceof VerticalGamePiece) {
                    for (int x = 0; x < b.getGamePieces()[k].height; x++) {
                        if (this.topLeftY==b.getGamePieces()[k].topLeftY+x&&this.topLeftX+this.width == b.getGamePieces()[k].topLeftX)
                            return false;
                    }
                }
            }return true;
        }return false;
    }
}