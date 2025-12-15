package LLD.SnakeAndLadder;

import java.util.UUID;

public class Player {
    private String pId;
    private String name;
    private int curPosition;

    public Player(String name) {
        this.pId = UUID.randomUUID().toString();
        this.name = name;
    }

    public String getpId() {
        return pId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCurPosition() {
        return curPosition;
    }

    public void setCurPosition(int curPosition) {
        this.curPosition = curPosition;
    }
}
