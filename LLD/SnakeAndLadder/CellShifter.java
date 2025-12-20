package LLD.SnakeAndLadder;

public abstract class CellShifter {
    int start;
    int end;

    public CellShifter(int start, int end) {
        this.start = start;
        this.end = end;
    }

    public int getStart() {
        return start;
    }

    public int getEnd() {
        return end;
    }
}
