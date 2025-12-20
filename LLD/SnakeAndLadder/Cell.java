package LLD.SnakeAndLadder;

public class Cell {
    private int position;
    private CellShifter cellShifter;

    public Cell(int position, CellShifter cellShifter) {
        this.position = position;
        this.cellShifter = cellShifter;
    }

    public int getPosition() {
        return position;
    }

    public CellShifter getCellShifter() {
        return cellShifter;
    }
}
