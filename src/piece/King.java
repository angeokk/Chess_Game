package piece;

import board.Board;
import board.Square;

public class King extends ABRQ {
public King(PieceColor color){
    this.color=color;
}
    @Override
    public boolean isMoveValid(Square moveFrom, Square moveTo) {
        if(((moveFrom.getRow()==moveTo.getRow()) & ((moveFrom.getCol()==moveTo.getCol()+1) | (moveFrom.getCol()==moveTo.getCol()-1)))
            | ((moveFrom.getCol()==moveTo.getCol()) &  ((moveFrom.getRow()==moveTo.getRow()+1) | (moveFrom.getRow()==moveTo.getRow()-1)))
            | (((moveFrom.getRow()==moveTo.getRow()+1) | (moveFrom.getRow()==moveTo.getRow()-1)) & ((moveFrom.getCol()==moveTo.getCol()+1) | (moveFrom.getCol()==moveTo.getCol()-1)))){
            return true;
        }
        return false;
    }
}
