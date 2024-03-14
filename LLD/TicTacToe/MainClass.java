package LLD.TicTacToe;

import java.util.Scanner;

public class MainClass {
    public static void main(String[] args) {
        TicTacToeGame.Builder gameBuilder = new TicTacToeGame.Builder();
        System.out.println("Set Game Configurations..");
        System.out.print("Enter board size: ");
        Scanner sc = new Scanner(System.in);
        int size = sc.nextInt();sc.nextLine();
        gameBuilder.setSize(size);
        System.out.print("Enter player1 name for X: ");
        gameBuilder.addPlayer(new Player(sc.nextLine(), Cell.X));
        System.out.print("Enter player2 name for O: ");
        gameBuilder.addPlayer(new Player(sc.nextLine(), Cell.O));

        gameBuilder.build().startGame();
        sc.close();
    }
}
