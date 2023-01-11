package piece;

import board.Board;
import board.Square;

public class Pawn extends Piece{
    private int direction;
    public Pawn(PieceColor color){
        this.color = color;
        if(color==PieceColor.DARK){
            direction=1;
        }
        else{
            direction=-1;
        }
    }
    @Override
    public boolean isMoveValid(Square moveFrom, Square moveTo) {
        if(((moveTo.getRow()==moveFrom.getRow()+direction) | (moveTo.getRow()==moveFrom.getRow()+2*direction & (moveFrom.getRow()==1 | moveFrom.getRow()==6))) & (moveFrom.getCol()==moveTo.getCol())
                | ((moveTo.getRow()==moveFrom.getRow()+direction) & ((moveTo.getCol()==moveFrom.getCol()-1) | (moveTo.getCol()==moveFrom.getCol()+1)))){
            return true;
        }
        return false;
    }
    @Override
    public boolean isBlocked(Square moveFrom, Square moveTo, Square[][] board) {
        if(moveTo.getCol()!=moveFrom.getCol()){
            if(moveTo.getPtype()==PieceType.NULL){
                return true;
            }
            if(moveTo.getPiece().getColor()==moveFrom.getPiece().getColor()){
                return true;
            }
            return false;
        } else if (board[moveFrom.getRow()+direction][moveFrom.getCol()].getPtype()!=PieceType.NULL) {
            return true;
        } else if (board[moveFrom.getRow()+2*direction][moveFrom.getCol()].getPtype()!=PieceType.NULL & moveFrom.getRow()+2*direction==moveTo.getRow()) {
            return true;
        }
        return false;
    }
}
