package piece;

import board.Board;
import board.Square;

public class Knight extends Piece{
//    public Knight(PieceType k){
//        super(k);
//    }
public Knight(PieceColor color){
    this.color=color;
}
    @Override
    public boolean isMoveValid(Square moveFrom, Square moveTo) {
        if((((moveFrom.getRow()==moveTo.getRow()+2) | (moveFrom.getRow()==moveTo.getRow()-2)) & ((moveFrom.getCol()==moveTo.getCol()+1) | (moveFrom.getCol()==moveTo.getCol()-1)))
            | (((moveFrom.getRow()==moveTo.getRow()+1) | (moveFrom.getRow()==moveTo.getRow()-1)) & ((moveFrom.getCol()==moveTo.getCol()+2) | (moveFrom.getCol()==moveTo.getCol()-2)))){
            return true;
        }
        return false;
    }
    @Override
    public boolean isBlocked(Square moveFrom, Square moveTo, Square[][] board) {
        if(moveTo.getPtype()==PieceType.NULL){
            return false;
        }
        if(moveFrom.getPiece().getColor()==moveTo.getPiece().getColor()){
            return true;
        }
        return false;
    }
}
