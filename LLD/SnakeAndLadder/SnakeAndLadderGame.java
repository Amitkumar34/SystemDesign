package LLD.SnakeAndLadder;

import LLD.TicTacToe.GameInt;

import java.util.*;

public class SnakeAndLadderGame implements GameInt {
    final Deque<Player> players;
    final Board board;
    final List<Dice> dices;

    private SnakeAndLadderGame(Deque<Player> players, Board board, List<Dice> dices) {
        this.players = players;
        this.board = board;
        this.dices = dices;
    }

    public Deque<Player> getPlayers() {
        return players;
    }

    public Board getBoard() {
        return board;
    }

    public List<Dice> getDices() {
        return dices;
    }

    @Override
    public void startGame() {
        System.out.println("Game Started");
        while (true) {
            Player curPlayer = players.removeFirst();
            if (players.isEmpty()) {
                System.out.println("One Player Left: " + curPlayer.getName() + " wins!");
                endGame();
                return;
            }
            int res = Util.rollDices(dices.toArray(new Dice[0]));
            int curP = curPlayer.getCurPosition();
            int newP = (curP + res > board.getSize() ? curP : curP + res);
            if (newP < board.getSize()) {
                CellShifter cs = board.getCells()[newP].getCellShifter();
                if (cs != null) {
                    newP = cs.getEnd();
                    if (cs instanceof Snake) {
                        System.out.print("Snake Bites!!! ");
                    } else {
                        System.out.print("Climbing Ladder!!! ");
                    }
                }
            }
            System.out.println(curPlayer.getName() + " rolled " + res + " and moved from " + curP + " to " + newP);
            curPlayer.setCurPosition(newP);
            if (newP == board.getWinPos()) {
                System.out.println(curPlayer.getName() + " wins!");
                endGame();
                return;
            }
            players.addLast(curPlayer);
        }
    }

    @Override
    public void reStartGame() {
        endGame();
        players.forEach(player -> player.setCurPosition(0));
        startGame();
    }

    @Override
    public void endGame() {
        System.out.println("Game ends!");
    }

    public static class Builder {
        private int boardSize;
        private List<Player> players;
        private List<int[]> snakes, ladders;
        private List<Dice> dices;

        public Builder() {
            dices = new ArrayList<>();
            snakes = new ArrayList<>();
            ladders = new ArrayList<>();
            players = new ArrayList<>();
            boardSize = 100;
        }

        public int getBoardSize() {
            return boardSize;
        }

        public void setBoardSize(int boardSize) {
            this.boardSize = boardSize;
        }

        public List<Player> getPlayers() {
            return players;
        }

        public void addPlayer(String name) {
            players.add(new Player(name));
        }

        public void setPlayers(List<Player> players) {
            this.players = players;
        }

        public List<int[]> getSnakes() {
            return snakes;
        }

        public void addSnakes(int start, int end) {
            this.snakes.add(new int[]{start, end});
        }

        public List<int[]> getLadders() {
            return ladders;
        }

        public void addLadders(int start, int end) {
            this.ladders.add(new int[]{start, end});
        }

        public void addDice(int min, int max) {
            this.dices.add(new Dice(min, max));
        }

        public SnakeAndLadderGame build() {
            Board brd = Board.createBoard(boardSize, snakes, ladders);
            SnakeAndLadderGame gm = new SnakeAndLadderGame(new ArrayDeque<>(players), brd, dices);
            return gm;
        }
    }

}
