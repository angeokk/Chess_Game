package board;


public class Board {
    private Square[][] boardOfSquares = new Square[8][8];
    public Square[][] getBoardOfSquares() {
        return boardOfSquares;
    }
    public void initializeBoard(){

        char[] one = {'R','K','B','Q','A','B','K','R'};
        char[] two = {'P','P','P','P','P','P','P','P'};
        char[] eight = {'r','k','b','q','a','b','k','r'};
        char[] seven = {'p','p','p','p','p','p','p','p'};

        for (int j = 0; j < 8; j++) {
            boardOfSquares[7][j] = new Square(one[j], 7, j);
        }
        for (int j = 0; j < 8; j++) {
            boardOfSquares[6][j] = new Square(two[j], 6, j);
        }
        for (int j = 0; j < 8; j++) {
            boardOfSquares[1][j] = new Square(seven[j], 1, j);
        }
        for (int j = 0; j < 8; j++) {
            boardOfSquares[0][j] = new Square(eight[j], 0, j);
        }
        for(int i = 2; i < 6; i++){
            for (int j = 0; j < 8; j++) {
                boardOfSquares[i][j] = new Square('-', i, j);
            }
        }
    }
    public void showBoard(){
        System.out.println("   a b c d e f g h");
        System.out.println();
        for (int i = 0; i < 8; i++) {
            System.out.print(8-i + "  ");
            for (int j = 0; j < 8; j++) {
                System.out.print(boardOfSquares[i][j].getChar() + " ");
            }
            System.out.print(" " + (8-i));
            System.out.println();
        }
        System.out.println();
        System.out.println("   a b c d e f g h");
    }
    public void makeMove(int[] move, Square[][] board){
        board[move[2]][move[3]].setParam(board[move[0]][move[1]].getChar());
        board[move[0]][move[1]].setParam('-');
    }
}
