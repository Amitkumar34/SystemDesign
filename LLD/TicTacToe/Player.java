package LLD.TicTacToe;

public class Player {
    // Once values are assigned can't be changed.
    private final String name;
    private final Cell assigedCell;

    public Player(String name, Cell assigedCell) {
        this.name = name;
        this.assigedCell = assigedCell;
    }

    public String getName() {
        return name;
    }

    public Cell getAssigedCell() {
        return assigedCell;
    }
}
