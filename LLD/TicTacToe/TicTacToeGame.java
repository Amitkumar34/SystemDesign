package LLD.TicTacToe;

import java.util.*;

public class TicTacToeGame implements GameInt {
    final Deque<Player> players;
    final Board board;

    private TicTacToeGame(Map<Cell, Player> players, int boardSize) {
        this.players = new LinkedList<>();
        for (Map.Entry<Cell, Player> entry : players.entrySet()) {
            if (entry.getValue() != null)
                this.players.add(entry.getValue());
        }
        this.board = new Board(boardSize);
    }

    @Override
    public void startGame() {
        printText("Game Started!!\n");
        showGameOptions();

        Scanner sc = new Scanner(System.in);
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
            String input = sc.nextLine().trim();
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
            if (!board.fillCell(row, col, cur.getAssigedCell())) {
                printText("Invalid position...\n");
                continue;
            }

            // shifting to next player and cur player is moved to last
            players.addLast(cur);
            players.removeFirst();

            // checks if the turn played by the current player ends the game
            if (board.hasStrike(row, col, cur.getAssigedCell())) {
                board.print();
                printText(cur.getName() + " WINS\n");
                endGame();
                break;
            }
        }
        //checking if user still wants to play the game after previous game ends
        showGameOptions();
        while (true) {
            String input = sc.nextLine().trim();
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
            case "END":
                endGame();
                return true;
            case "RESTART":
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

    public void printText(String s) {
        System.out.print(s);
    }

    /**
     * Builder for {@link TicTacToeGame}, set configurations for the Game
     */
    public static class Builder {
        int size;
        Map<Cell, Player> playerMap;

        public Builder() {
            playerMap = new HashMap<>();
        }

        public int getSize() {
            return size;
        }

        public Builder setSize(int size) {
            this.size = size;
            return this;
        }

        public Map<Cell, Player> getPlayerMap() {
            return playerMap;
        }

        public boolean addPlayer(Player player) {
            if (playerMap.containsKey(player.getAssigedCell())) {
                return false;
            }
            playerMap.put(player.getAssigedCell(), player);
            return true;
        }

        public TicTacToeGame build() {
            return new TicTacToeGame(playerMap, size);
        }
    }
}
