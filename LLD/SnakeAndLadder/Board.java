package LLD.SnakeAndLadder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Board {
    private int size;
    private Cell[] cells;
    private int startPos, winPos;

    private Board(int size, Cell[] cells) {
        this.size = size;
        this.cells = cells;
        startPos = 0;
        winPos = size;
    }

    public int getSize() {
        return size;
    }

    public Cell[] getCells() {
        return cells;
    }

    public int getStartPos() {
        return startPos;
    }

    public int getWinPos() {
        return winPos;
    }

    public static Board createBoard(int size, List<int[]> snakes, List<int[]> ladders) {
        Map<Integer, CellShifter> mp = new HashMap<>();
        for (int[] snake : snakes) {
            mp.put(snake[0], new Snake(snake[0], snake[1]));
        }
        for (int[] ladder : ladders) {
            mp.put(ladder[0], new Ladder(ladder[0], ladder[1]));
        }
        Cell[] c = new Cell[size + 1];
        for (int i = 0; i <= size; i++) {
            c[i] = new Cell(i, mp.getOrDefault(i, null));
        }
        return new Board(size, c);
    }
}
