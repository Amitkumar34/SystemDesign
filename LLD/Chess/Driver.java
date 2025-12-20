package LLD.Chess;

public class Driver {
    public static void main(String[] args) {
       BoardService b =  new BoardService();
       b.setBoardAsDefault();
       b.printBoard();
    }
}
