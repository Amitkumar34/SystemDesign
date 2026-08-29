package LLD.TicTacToe;

import java.util.Scanner;

public class MainClass {
    public static void main(String[] args) {
        try (Scanner input = new Scanner(System.in)) {
            new TicTacToeGame.Builder(input).takeGameConfiguration().build().startGame();
        }
    }
}
