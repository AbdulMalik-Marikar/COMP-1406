/**
 * Created by abdul on 2017-03-02.
 * 101042166
 */
import javafx.scene.paint.Color;

public class VerticalGamePiece extends GamePiece {
    public VerticalGamePiece(int h, Color c, int x, int y) {
        super(1, h, c, x, y);
    }

    public boolean canMoveDownIn(GameBoard b) {
        if (this.topLeftY+this.height < 6)  {//checks for walls
            for (int k=0;k<b.getNumGamePieces();k++){
                if (b.getGamePieces()[k] instanceof VerticalGamePiece) {
                    if (this.topLeftX==b.getGamePieces()[k].topLeftX&&this.topLeftX + this.height == b.getGamePieces()[k].topLeftX)
                        return false;
                }
                if (b.getGamePieces()[k] instanceof HorizontalGamePiece) {//horizontal pieces need to check the whole width of piece
                    for (int x = 0; x < b.getGamePieces()[k].width; x++) {//loops through width
                        if (this.topLeftX==b.getGamePieces()[k].topLeftX+x&&this.topLeftY+this.height == b.getGamePieces()[k].topLeftY)
                            return false;
                    }
                }
            }return true;
        }return false;
    }
    public boolean canMoveUpIn(GameBoard b) {
        if (this.topLeftY-1 >= 0){
            for (int i=0;i<b.getNumGamePieces();i++) {
                if (b.getGamePieces()[i] instanceof VerticalGamePiece) {
                    if (this.topLeftX==b.getGamePieces()[i].topLeftX&&this.topLeftY == b.getGamePieces()[i].topLeftY + b.getGamePieces()[i].height)
                        return false;
                }
                if (b.getGamePieces()[i] instanceof HorizontalGamePiece) {
                    for (int j = 0; j < b.getGamePieces()[i].width; j++) {
                        if (this.topLeftX==b.getGamePieces()[i].topLeftX+j&&this.topLeftY == b.getGamePieces()[i].topLeftY+b.getGamePieces()[i].height)
                            return false;
                    }
                }
            }return true;
        }return false;
    }
}