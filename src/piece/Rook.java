package piece;

import board.Board;
import board.Square;

public class Rook extends ABRQ{
public Rook(PieceColor color){
    this.color=color;
}
    @Override
    public boolean isMoveValid(Square moveFrom, Square moveTo) {
        return MoveBehavior.moveVertically(moveFrom,moveTo);
    }
}
