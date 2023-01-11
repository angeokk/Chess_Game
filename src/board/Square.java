package board;

import piece.*;

public class Square {
    private Piece piece;
    private PieceType ptype;
    private int row;
    private int col;
    private char c;

    public Square(char c, int row, int col){
        this.c = c;
        this.row = row;
        this.col = col;
        if(c=='-'){
            ptype = PieceType.NULL;
        } else if (Character.isUpperCase(c)) {
            setType(c,PieceColor.LIGHT);
        }
        else{
            setType(c,PieceColor.DARK);
        }
    }

    public Piece getPiece(){return piece;}
    public PieceType getPtype() {
        return ptype;
    }
    public int getRow() {
        return row;
    }
    public int getCol() {
        return col;
    }
    public char getChar(){
        return c;
    }
    public void setParam(char c){
        this.c = c;
        if(c=='-'){
            ptype = PieceType.NULL;
        } else if (Character.isUpperCase(c)) {
            setType(c,PieceColor.LIGHT);
        }
        else{
            setType(c,PieceColor.DARK);
        }
    }
    private void setType(char c, PieceColor color){
        c = Character.toLowerCase(c);
        switch (c){
            case 'r':
                ptype = PieceType.ROOK;
                piece = new Rook(color);
                break;
            case 'k':
                ptype = PieceType.KNIGHT;
                piece = new Knight(color);
                break;
            case 'b':
                ptype = PieceType.BISHOP;
                piece = new Bishop(color);
                break;
            case 'p':
                ptype = PieceType.PAWN;
                piece = new Pawn(color);
                break;
            case 'q':
                ptype = PieceType.QUEEN;
                piece = new Queen(color);
                break;
            case 'a':
                ptype = PieceType.KING;
                piece = new King(color);
                break;
        }
    }
}


