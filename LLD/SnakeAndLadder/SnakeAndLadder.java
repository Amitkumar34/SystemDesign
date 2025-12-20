package LLD.SnakeAndLadder;

public class SnakeAndLadder {
    public static void main(String[] args) {
        System.out.println("!!Snake & Ladders!!");
        SnakeAndLadderGame.Builder builder = new SnakeAndLadderGame.Builder();
        builder.addDice(1, 6);
        builder.addDice(1, 6);
        builder.addPlayer("Amit");
        builder.addPlayer("Sagar");
        builder.addSnakes(62, 5);
        builder.addSnakes(33, 6);
        builder.addSnakes(49, 9);
        builder.addSnakes(88, 16);
        builder.addSnakes(41, 20);
        builder.addSnakes(56, 53);
        builder.addSnakes(98, 64);
        builder.addSnakes(93, 73);
        builder.addSnakes(95, 75);
        builder.addLadders(2, 37);
        builder.addLadders(27, 46);
        builder.addLadders(10, 32);
        builder.addLadders(51, 68);
        builder.addLadders(61, 79);
        builder.addLadders(65, 84);
        builder.addLadders(71, 91);
        builder.addLadders(81, 10);
        builder.build().startGame();
    }

}
