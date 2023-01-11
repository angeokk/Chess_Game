import board.Board;
import board.Square;
import piece.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import java.util.Scanner;

public class ChessGame {
    private int turn;
    private final int maxTurns;
    private Winner winner;
    public ChessGame(int maxT){
        maxTurns=maxT;
        turn=1;
        winner=Winner.NULL;
    }
    public int getTurn() {
        return turn;
    }
    public void setTurn(int turn) {
        this.turn = turn;
    }
    public Winner getWinner() {
        return winner;
    }
    public void setWinner(Winner winner) {
        this.winner = winner;
    }
    public int getMaxTurns() {
        return maxTurns;
    }
    public void start(){

        Board b = new Board();
        b.initializeBoard();

        // Main game loop
        while (getTurn()<=getMaxTurns() & getWinner()==Winner.NULL) {

            b.showBoard();
            printText();

            Pattern pattern = Pattern.compile("move [a-h][1-8] [a-h][1-8]");
            String move = getInput();
            Matcher matcher = pattern.matcher(move);
            boolean matchFound = matcher.find();
            if(!matchFound){
                System.out.println("Wrong input! Try again.");
                System.out.println();
                continue;
            }

            String[] moveParts = move.split(" ");
            int[] moveArray = new int[4];
            moveArray[0] = 8 - Integer.parseInt(moveParts[1].substring(1)); //from row
            moveArray[1] = moveParts[1].charAt(0) - 'a';                              //from col
            moveArray[2] = 8 - Integer.parseInt(moveParts[2].substring(1)); //to row
            moveArray[3] = moveParts[2].charAt(0) - 'a';                              //to col

            if((moveArray[0]==moveArray[2]) && (moveArray[1]==moveArray[3])){
                System.out.println("Illegal move! Cannot move from a square to the same. Try again.");
                continue;
            }

            Square[][] board = b.getBoardOfSquares();
            PieceType type = board[moveArray[0]][moveArray[1]].getPtype();
            if(type == PieceType.NULL){
                System.out.println("Illegal move! Square to move from is empty. Try again.");
                continue;
            }
            PieceColor color = board[moveArray[0]][moveArray[1]].getPiece().getColor();
            if(getTurn()%2==1 & color==PieceColor.DARK){
                System.out.println("Illegal move! It is the turn of the player with the light pieces. Try again.");
                continue;
            }
            else if(getTurn()%2==0 & color==PieceColor.LIGHT){
                System.out.println("Illegal move! It is the turn of the player with the dark pieces. Try again.");
                continue;
            }

            if(!board[moveArray[0]][moveArray[1]].getPiece().isMoveValid(board[moveArray[0]][moveArray[1]], board[moveArray[2]][moveArray[3]])){
                System.out.println("Illegal move! Piece " + type.toString().toLowerCase() + " cannot move that way. Try again.");
                continue;
            }

            if(board[moveArray[0]][moveArray[1]].getPiece().isBlocked(board[moveArray[0]][moveArray[1]], board[moveArray[2]][moveArray[3]], board)){
                System.out.println("Illegal move! Blocked path. Try again.");
                continue;
            }

            int[] revMoveArray = new int[4];
            revMoveArray[0]=moveArray[2];
            revMoveArray[1]=moveArray[3];
            revMoveArray[2]=moveArray[0];
            revMoveArray[3]=moveArray[1];
            char c = board[moveArray[2]][moveArray[3]].getChar();
            b.makeMove(moveArray, board);

            if(isKingChecked(b,color)){
                System.out.println("Illegal move! By making that move you left your king exposed. Try again.");
                b.makeMove(revMoveArray, board);    //make possible move
                board[moveArray[2]][moveArray[3]].setParam(c);
                continue;
            }
            b.makeMove(revMoveArray, board);    //undo the move
            board[moveArray[2]][moveArray[3]].setParam(c);

            // Make the move if everything is ok
            b.makeMove(moveArray, board);
            setTurn(getTurn()+1);

            //check if check mate
            if (color==PieceColor.DARK){
                color=PieceColor.LIGHT;
            }
            else{
                color=PieceColor.DARK;
            }
            if(isKingChecked(b,color)) {
                if (!checkEveryMove(b, color)) {
                    System.out.print("End of game.");
                    //winner output
                    if(color==PieceColor.DARK){
                        System.out.print(" Player with the light pieces is the winner.");
                        setWinner(Winner.LIGHT);
                    }
                    else{
                        System.out.print(" Player with the dark pieces is the winner.");
                        setWinner(Winner.DARK);
                    }
                }
            }
            if(getMaxTurns()+1==getTurn()){
                b.showBoard();
                System.out.println();
                System.out.print("The game ended in draw.");
            }
        }
    }

    public String getInput(){
        Scanner input = new Scanner(System.in);
        System.out.print("Enter move (e.g. move a2 a4): ");
        return input.nextLine();
    }
    public boolean isKingChecked(Board b, PieceColor color){

        Square[][] board = b.getBoardOfSquares();
        //find kings position
        int[] kingsPos = new int[2];
        for(int i=0; i<8; i++){
            for(int j=0; j<8; j++){
                if(board[i][j].getPtype()==PieceType.KING){
                    if(board[i][j].getPiece().getColor()==color) {
                        kingsPos[0] = i;
                        kingsPos[1] = j;
                        break;
                    }
                }
            }
        }
        //check if any opponent's piece is checking the king
        boolean check = false;
        int[] opPos = new int[2];
        for(int i=0; i<8; i++) {
            for (int j = 0; j < 8; j++) {
                if(board[i][j].getPtype()!=PieceType.NULL){
                    if(board[i][j].getPiece().getColor()!=color){
                        opPos[0]=i;
                        opPos[1]=j;
                        if(board[opPos[0]][opPos[1]].getPiece().isMoveValid(board[opPos[0]][opPos[1]],board[kingsPos[0]][kingsPos[1]])){
                            if(!board[opPos[0]][opPos[1]].getPiece().isBlocked(board[opPos[0]][opPos[1]],board[kingsPos[0]][kingsPos[1]],board)) {
                                check = true;
                                return check;
                            }
                        }
                    }
                }
            }
        }
        return check;
    }
    public boolean checkEveryMove(Board b, PieceColor color){
        Square[][] board = b.getBoardOfSquares();
        int[] moveArray = new int[4];
        int[] revMoveArray = new int[4];
        char c;
        for(int i=0; i<8; i++){
            moveArray[0]=i;
            for(int j=0; j<8; j++){
                moveArray[1]=j;
                if(board[i][j].getPtype()!=PieceType.NULL){     //find a not NULL piece
                    if(board[i][j].getPiece().getColor()==color){       //find a  piece with the same color as the checked king
                        for(int k=0; k<8; k++){
                            moveArray[2]=k;
                            for (int l=0; l<8; l++){
                                moveArray[3]=l;
                                if(board[i][j].getPiece().isMoveValid(board[i][j],board[k][l])){
                                    if(!board[i][j].getPiece().isBlocked(board[i][j], board[k][l], board)){     //check every possible move
                                        revMoveArray[0]=moveArray[2];
                                        revMoveArray[1]=moveArray[3];
                                        revMoveArray[2]=moveArray[0];
                                        revMoveArray[3]=moveArray[1];
                                        c = board[moveArray[2]][moveArray[3]].getChar();
                                        b.makeMove(moveArray, board);
                                        if(!isKingChecked(b,color)){    //if check is gone return true
                                            b.makeMove(revMoveArray, board);
                                            board[moveArray[2]][moveArray[3]].setParam(c);
                                            return true;
                                        }
                                        b.makeMove(revMoveArray, board);
                                        board[moveArray[2]][moveArray[3]].setParam(c);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    public void printText(){
        System.out.println();
        System.out.print("Number of turn: " + turn + ". Max turns: " + maxTurns);
        if(turn%2==1){
            System.out.println(". Turn of player with the light pieces.");
        }
        else{
            System.out.println(". Turn of player with the dark pieces.");
        }
        System.out.println();
    }

}



