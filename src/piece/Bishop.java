package piece;

import board.Board;
import board.Square;

public class Bishop extends ABRQ{
    public Bishop(PieceColor color){
        this.color=color;
    }
    @Override
    public boolean isMoveValid(Square moveFrom, Square moveTo) {
        return MoveBehavior.moveDiagonally(moveFrom,moveTo);
    }
}
