package LLD.Chess;

public class Board {
    private Cell[][] cells;

    public Board(int xSize, int ySize) {
        cells = new Cell[xSize][ySize];
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                cells[i][j] = new Cell(i, j);
            }
        }
    }

    public Cell[][] getCells() {
        return cells;
    }

    public void print() {
        for (int i = cells.length - 1; i >= 0; i--) {
            for (int j = 0; j < cells[i].length; j++) {
                System.out.print(cells[i][j]);
            }
            System.out.println();
        }
    }
}
