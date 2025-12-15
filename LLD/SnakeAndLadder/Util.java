package LLD.SnakeAndLadder;

public class Util {
    public static int rollDices(Dice... dices) {
        int result = 0;
        for (Dice dice : dices) {
            result += dice.rollDice();
        }
        return result;
    }

}
