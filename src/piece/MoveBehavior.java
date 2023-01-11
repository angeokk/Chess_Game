package piece;

import board.Square;

public class MoveBehavior {

    public static boolean moveDiagonally(Square moveFrom, Square moveTo){
        return (moveFrom.getCol()-moveFrom.getRow()==moveTo.getCol()-moveTo.getRow()) | (moveFrom.getCol()+moveFrom.getRow()==moveTo.getCol()+moveTo.getRow());
    }

    public static boolean moveVertically(Square moveFrom, Square moveTo){
        return (moveFrom.getRow()==moveTo.getRow()) | (moveFrom.getCol()==moveTo.getCol());
    }
}
