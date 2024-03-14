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
        boolean noWinner = true;
        println("Game Started!!\n");
        Scanner sc = new Scanner(System.in);
        while (noWinner) {
            board.print();
            if (!board.hasEmptyCell()) {
                println("Tie!!\n");
                println("!!Game Ends!!\n");
                return;
            }
            Player cur = players.getFirst();
            println(cur.getName() + " turn: Enter the cell position:  ");
            int row = sc.nextInt();
            int col = sc.nextInt();
            if (!board.fillCell(row, col, cur.getAssigedCell())) {
                println("Invalid position...\n");
                continue;
            }
            players.addLast(cur);
            players.removeFirst();
            if (board.hasStrike(row, col, cur.getAssigedCell())) {
                board.print();
                println(cur.getName() + " WINS\n");
                println("Game Ends!!\n");
                noWinner = false;
            }
        }
    }

    public void println(String s) {
        System.out.print(s);
    }

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
