package LLD.TicTacToe;

public class Utils {
    public static boolean isRowStrike(Cell[][] board, int row, Cell cell) {
        int size = board.length;
        if (0 <= row && row < size) for (int col = 0; col < size; col++) {
            if (board[row][col] != cell) return false;
        }
        return true;
    }

    public static boolean isColumnStrike(Cell[][] board, int col, Cell cell) {
        int size = board.length;
        if (0 <= col && col < size) for (int row = 0; row < size; row++) {
            if (board[row][col] != cell) return false;
        }
        return true;
    }

    public static boolean isForwardDiagonalStrike(Cell[][] board, Cell cell) {
        int size = board.length;
        for (int row = 0; row < size; row++) {
            int col = size - row - 1;
            if (board[row][col] != cell) return false;
        }
        return true;
    }

    public static boolean isBackwardDiagonalStrike(Cell[][] board, Cell cell) {
        int size = board.length;
        for (int row = 0; row < size; row++) {
            int col = row;
            if (board[row][col] != cell) return false;
        }
        return true;
    }
}
