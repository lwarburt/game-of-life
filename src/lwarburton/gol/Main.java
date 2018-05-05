package lwarburton.gol;

public class Main {
    public static final int BOARD_SIZE = 20;
    public static final double CELL_START_DENSITY = .375;

    public static void main(String[] args) {
        boolean[][] oldBoard = randomBoard();
        for (int n = 0; n < 100; n++) {
            boolean[][] newBoard = new boolean[BOARD_SIZE][BOARD_SIZE];
            printBoard(oldBoard);
            System.out.println();
            for (int y = 0; y < BOARD_SIZE; y++) {
                for (int x = 0; x < BOARD_SIZE; x++) {
                    int liveCells = countLiveCells(y, x, oldBoard);
                    newBoard[y][x] = cellTest(y, x, liveCells, oldBoard);
                }
            }
            oldBoard = newBoard;
        }
    }

    public static boolean[][] randomBoard() {
        boolean[][] startBoard = new boolean[BOARD_SIZE][BOARD_SIZE];
        for (int y = 0; y < BOARD_SIZE; y++) {
            for (int x = 0; x < BOARD_SIZE; x++) {
                double random = Math.random();
                if (random <= CELL_START_DENSITY) {
                    startBoard[y][x] = true;
                } else {
                    startBoard[y][x] = false;
                }
            }
        }
        return startBoard;
    }

    public static void printBoard(boolean[][] oldBoard) {
        for (int y = 0; y < BOARD_SIZE; y++) {
            for (int x = 0; x < BOARD_SIZE; x++) {
                if (oldBoard[y][x]) {
                    System.out.print("O");
                } else {
                    System.out.print(".");
                }
            }
            System.out.println();
        }
    }

    public static int countLiveCells(int y, int x, boolean[][] oldBoard) {
        int liveCells = 0;
        for (int j = -1; j <= 1; j++) {
            for (int i = -1; i <= 1; i++) {
                if (i == 0 && j == 0) {
                    continue;
                }
                int testX = x + i;
                int testY = y + j;
                if (testX >= 0 && testX < BOARD_SIZE && testY >= 0 && testY < BOARD_SIZE && oldBoard[testY][testX]) {
                    liveCells++;
                }
            }
        }
        return liveCells;
    }

    public static boolean cellTest(int y, int x, int liveCells, boolean[][] oldBoard) {
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
}
