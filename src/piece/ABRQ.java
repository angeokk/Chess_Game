package piece;

import board.Square;

public abstract class ABRQ extends Piece {
    @Override
    public boolean isBlocked(Square moveFrom, Square moveTo, Square[][] board) {
        int up, left;
        if(moveFrom.getRow()-moveTo.getRow()==0){
            up=0;
        }
        else{
            up=-(moveFrom.getRow()-moveTo.getRow())/Math.abs(moveFrom.getRow()-moveTo.getRow());
        }
        if(moveFrom.getCol()-moveTo.getCol()==0){
            left=0;
        }
        else{
            left=-(moveFrom.getCol()-moveTo.getCol())/Math.abs(moveFrom.getCol()-moveTo.getCol());
        }

        int steps;
        if(moveFrom.getRow()-moveTo.getRow()!=0){
            steps=Math.abs(moveFrom.getRow()-moveTo.getRow());
        }
        else{
            steps=Math.abs(moveFrom.getCol()-moveTo.getCol());
        }

        for(int i=1; i<steps; i++){
            if(board[moveFrom.getRow()+i*up][moveFrom.getCol()+i*left].getPtype()!=PieceType.NULL){
                return true;
            }
        }
        if(moveTo.getPtype()!=PieceType.NULL) {
            if (moveTo.getPiece().getColor() == moveFrom.getPiece().getColor()) {
                return true;
            }
        }
        return false;
    }
}
