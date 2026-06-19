package LLD.TicTacToe;

import LLD.TicTacToe.enums.Symbol;
import LLD.TicTacToe.model.Board;
import LLD.TicTacToe.model.Player;
import lombok.Getter;

import java.util.*;

public class TicTacToeGame implements GameInt {
    private final Scanner input;
    final Deque<Player> players;
    final Board board;
    final int noOfPlayers;

    private TicTacToeGame(Map<Symbol, Player> symbolPlayerMap, int boardSize, Scanner input) {
        this.input = input;
        this.players = new LinkedList<>();
        symbolPlayerMap.forEach((key, value) -> {
            if (value != null)
                this.players.add(value);
        });
        this.board = new Board(boardSize);
        this.noOfPlayers = this.players.size();
    }

    @Override
    public void startGame() {
        printText("Game Started!!\n");
        showGameOptions();

        while (true) {
            board.print();

            // if board is left with any empty cell so that next player can play the turn
            if (!board.hasEmptyCell()) {
                printText("Tie!!\n");
                endGame();
                break;
            }

            Player cur = players.getFirst();
            printText(cur.getName() + " turn: Enter the cell position:  ");
            String input = this.input.nextLine().trim();
            if (handleOption(input)) return;
            String[] inputs = input.split(" ");
            int row, col;
            try {
                row = Integer.parseInt(inputs[0]);
                col = Integer.parseInt(inputs[1]);
            } catch (Exception e) {
                printText("Invalid input...\n");
                continue;
            }
            if (!board.fillCell(row, col, cur.getAssignedCell())) {
                printText("Invalid position...\n");
                continue;
            }

            // shifting to next player and cur player is moved to last
            players.addLast(cur);
            players.removeFirst();

            // checks if the turn played by the current player ends the game
            if (board.hasStrike(row, col, cur.getAssignedCell())) {
                board.print();
                printText(cur.getName() + " WINS\n");
                endGame();
                break;
            }
        }
        //checking if user still wants to play the game after previous game ends
        showGameOptions();
        while (true) {
            String input = this.input.nextLine().trim();
            if (handleOption(input)) return;
            printText("Invalid input...\n");
        }
    }

    private void showGameOptions() {
        printText("In Game Options: Enter 'END' - to end the game, 'RESTART' - to restart the game\n");
    }

    /**
     * @param option Selected option is being handled if it is a valid option
     * @return true if valid option is selected
     */
    private boolean handleOption(String option) {
        switch (option) {
            case "END", "end":
                endGame();
                return true;
            case "RESTART", "restart":
                reStartGame();
                return true;
        }
        return false;
    }

    @Override
    public void reStartGame() {
        endGame();
        startGame();
    }

    @Override
    public void endGame() {
        board.reset();
        System.out.println("!!Game Ends!!");
    }

    public static void printText(String... texts) {
        for (String s : texts)
            System.out.print(s);
    }

    /**
     * Builder for {@link TicTacToeGame}, set configurations for the Game
     */
    @Getter
    public static class Builder {
        private final Scanner input;
        int size;
        Map<Symbol, Player> playerMap;

        public Builder(Scanner input) {
            this.input = input;
            playerMap = new HashMap<>();
        }


        public boolean addPlayer(Player player) {
            if (playerMap.containsKey(player.getAssignedCell())) {
                return false;
            }
            playerMap.put(player.getAssignedCell(), player);
            return true;
        }

        public Builder takeGameConfiguration() {
            printText("Set Game Configurations..\n");
            while (true) {
                try {
                    printText("Enter board size: ");
                    this.size = input.nextInt();
                    break;
                } catch (Exception e) {
                    printText("Invalid Number!!!\n");
                } finally {
                    input.nextLine();
                }
            }
            Symbol[] symbols = Symbol.values();
            int noOfPlayers;
            while (true) {
                try {
                    printText("Enter no of Players b/w 2 to " + symbols.length, ": ");
                    noOfPlayers = input.nextInt();
                    if (noOfPlayers < 2 || symbols.length < noOfPlayers) throw new Exception();
                    break;
                } catch (Exception e) {
                    printText("Invalid Number!!!\n");
                } finally {
                    input.nextLine();
                }
            }
            for (int i = 0; i < noOfPlayers; i++) {
                while (true) {
                    try {
                        printText("Enter player" + (i + 1) + " name for " + symbols[i] + " : ");
                        String name = input.nextLine();
                        if (this.addPlayer(new Player(name, symbols[i]))) break;
                        else printText("Player Already Exists - ", name, "\n");
                    } catch (Exception e) {
                        printText(e.getMessage());
                    }
                }
            }
            return this;
        }

        public TicTacToeGame build() {
            return new TicTacToeGame(playerMap, size, input);
        }
    }
}
