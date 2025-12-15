package LLD.SnakeAndLadder;

public class Dice {
    private int min;
    private int max;

    public Dice(int min, int max) {
        this.min = min;
        this.max = max;
    }

    public int getMin() {
        return min;
    }

    public int getMax() {
        return max;
    }

    public int rollDice() {
        return (int) (Math.random() * (max - min) + min);
    }

}
