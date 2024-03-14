package LLD.TicTacToe;

public class Board {
    private final int size;
    private Cell[][] board;

    public Board(int size) {
        this.size = size;
        this.board = new Cell[size][size];
    }

    public boolean fillCell(int row, int col, Cell cell) {
        if (0 <= row && row < size && 0 <= col && col < size && board[row][col] == null) {
            board[row][col] = cell;
            return true;
        }
        return false;
    }

    public boolean hasStrike(int row, int col, Cell cell) {
        return Utils.isRowStrike(board, row, cell)
                || Utils.isColumnStrike(board, col, cell)
                || Utils.isForwardDiagonalStrike(board, cell)
                || Utils.isBackwardDiagonalStrike(board, cell);
    }

    public boolean hasEmptyCell() {
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                if (board[row][col] == null) return true;
            }
        }
        return false;
    }

    public void print() {
        for (int row = 0; row < size; row++) {
            StringBuilder sb = new StringBuilder(" ");
            for (int col = 0; col < size; col++) {
                if (board[row][col] == null) sb.append("  ");
                else sb.append(" ").append(board[row][col]);
                if (col < size - 1) sb.append(" | ");
            }
            System.out.println(sb);
        }
    }

    public void reset() {
        this.board = new Cell[size][size];
    }
}
