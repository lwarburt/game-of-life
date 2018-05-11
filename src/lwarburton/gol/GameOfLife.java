package lwarburton.gol;

public class GameOfLife {
    private final int boardSize;
    private final double cellStartDensity;
    private boolean oldBoard[][];

    public GameOfLife(int boardSize, double cellStartDensity) {
        this.boardSize = boardSize;
        this.cellStartDensity = cellStartDensity;
        oldBoard = randomBoard();
    }

    private boolean[][] randomBoard() {
        boolean[][] startBoard = new boolean[boardSize][boardSize];
        for (int y = 0; y < boardSize; y++) {
            for (int x = 0; x < boardSize; x++) {
                double random = Math.random();
                if (random <= cellStartDensity) {
                    startBoard[y][x] = true;
                } else {
                    startBoard[y][x] = false;
                }
            }
        }
        return startBoard;
    }

    private int countLiveCells(int y, int x) {
        int liveCells = 0;
        for (int j = -1; j <= 1; j++) {                     //j,i represents relative coordinate position
            for (int i = -1; i <= 1; i++) {
                if (i == 0 && j == 0) {
                    continue;
                }
                int testX = x + i;
                int testY = y + j;
                if (testX >= 0 && testX < boardSize && testY >= 0 && testY < boardSize && oldBoard[testY][testX]) {
                    liveCells++;
                }
            }
        }
        return liveCells;
    }

    private boolean cellTest(int y, int x, int liveCells) {
        boolean cellIsAlive = oldBoard[y][x];
        if (cellIsAlive) {
            if (liveCells < 2) {                     //under population
                return false;
            }
            if (2 <= liveCells && liveCells <= 3) {  //goldilocks zone
                return true;
            }
            if (4 <= liveCells) {                    //over population
                return false;
            }
        } else {
            if (liveCells == 3) {                    //born cell
                return true;
            }
        }
        return false;
    }

    public void simulate(int iterations) {
        for (int n = 0; n < iterations; n++) {
            boolean[][] newBoard = new boolean[boardSize][boardSize];
            for (int y = 0; y < boardSize; y++) {          //x,y represents absolute coordinate position
                for (int x = 0; x < boardSize; x++) {
                    int liveCells = countLiveCells(y, x);
                    newBoard[y][x] = cellTest(y, x, liveCells);
                }
            }
            oldBoard = newBoard;
        }
    }

    public void simulate() {
        simulate(1);
    }

    @Override
    public String toString() {
        StringBuilder board = new StringBuilder();
        for (int y = 0; y < boardSize; y++) {
            for (int x = 0; x < boardSize; x++) {
                if (oldBoard[y][x]) {
                    board.append("#");
                } else {
                    board.append(".");
                }
            }
            board.append("\n");
        }
        return board.toString();
    }
}
