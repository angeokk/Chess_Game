package piece;

import board.Board;
import board.Square;

public class Queen extends ABRQ{
public Queen(PieceColor color){
    this.color=color;
}
    @Override
    public boolean isMoveValid(Square moveFrom, Square moveTo) {
        return MoveBehavior.moveVertically(moveFrom,moveTo) | MoveBehavior.moveDiagonally(moveFrom,moveTo);
    }
}
