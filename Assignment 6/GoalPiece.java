/**
 * Created by abdul on 2017-03-02.
 * 101042166
 */
import javafx.scene.paint.Color;

public class GoalPiece extends HorizontalGamePiece {
    public GoalPiece(int x, int y) {
        super(2, Color.RED, x, y);
    }
    public boolean canMoveRightIn(GameBoard b) {
        for (int r=0;r<b.getNumGamePieces();r++) {
            if (this.topLeftX+this.width < 7) {
                if (b.getGamePieces()[r] instanceof HorizontalGamePiece) {
                    if (this.topLeftY==b.getGamePieces()[r].topLeftY&&this.topLeftX + this.width == b.getGamePieces()[r].topLeftX)
                        return false;
                }
                if (b.getGamePieces()[r] instanceof VerticalGamePiece) {
                    for (int x = 0; x < b.getGamePieces()[r].height; x++) {
                        if (this.topLeftY==b.getGamePieces()[r].topLeftY+x&&this.topLeftX+this.width == b.getGamePieces()[r].topLeftX)
                            return false;
                    }
                }
            }
        }return true;
    }
}
