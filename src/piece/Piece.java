package piece;

import board.Board;
import board.Square;

public abstract class Piece {
    protected PieceColor color;
    public abstract boolean isMoveValid(Square moveFrom, Square moveTo);
    public abstract boolean isBlocked(Square moveFrom, Square moveTo, Square[][] board);
    public PieceColor getColor() {
        return color;
    }

}
