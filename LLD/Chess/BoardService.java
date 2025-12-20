package LLD.Chess;

public class BoardService {
    private Board board;
    public static final int DEFAULT_BOARD_SIZE = 8;

    public BoardService() {
        board = new Board(DEFAULT_BOARD_SIZE, DEFAULT_BOARD_SIZE);
    }

    public void setBoardAsDefault() {
        Cell[][] cells = board.getCells();
        for (int j = 0; j < cells[0].length; j++) {
            cells[1][j].setPieceType(PieceType.PAWN, Color.WHITE);
            cells[cells.length - 2][j].setPieceType(PieceType.PAWN, Color.BLACK);
        }
        cells[0][0].setPieceType(PieceType.ROOK, Color.WHITE);
        cells[0][1].setPieceType(PieceType.KNIGHT, Color.WHITE);
        cells[0][2].setPieceType(PieceType.BISHOP, Color.WHITE);
        cells[0][3].setPieceType(PieceType.QUEEN, Color.WHITE);
        cells[0][4].setPieceType(PieceType.KING, Color.WHITE);
        cells[0][5].setPieceType(PieceType.BISHOP, Color.WHITE);
        cells[0][6].setPieceType(PieceType.KNIGHT, Color.WHITE);
        cells[0][7].setPieceType(PieceType.ROOK, Color.WHITE);

        cells[cells.length - 1][0].setPieceType(PieceType.ROOK, Color.BLACK);
        cells[cells.length - 1][1].setPieceType(PieceType.KNIGHT, Color.BLACK);
        cells[cells.length - 1][2].setPieceType(PieceType.BISHOP, Color.BLACK);
        cells[cells.length - 1][3].setPieceType(PieceType.QUEEN, Color.BLACK);
        cells[cells.length - 1][4].setPieceType(PieceType.KING, Color.BLACK);
        cells[cells.length - 1][5].setPieceType(PieceType.BISHOP, Color.BLACK);
        cells[cells.length - 1][6].setPieceType(PieceType.KNIGHT, Color.BLACK);
        cells[cells.length - 1][7].setPieceType(PieceType.ROOK, Color.BLACK);
    }

    public void printBoard() {
        if (board != null) board.print();
    }

    public boolean movePiece() {
        return false;
    }
}
