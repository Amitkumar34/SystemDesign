package LLD.TicTacToe.model;

import LLD.TicTacToe.enums.Symbol;
import lombok.Getter;

@Getter
public class Player {
    // Once values are assigned can't be changed.
    private final String name;
    private final Symbol assignedCell;

    public Player(String name, Symbol assignedCell) {
        this.name = name;
        this.assignedCell = assignedCell;
    }
}
