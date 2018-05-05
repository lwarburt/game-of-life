package lwarburton.gol;

public class Main {
    public static final int BOARD_SIZE = 5;

    public static void main(String[] args) {
        boolean[][] oldCells = new boolean[][]{{false, false, false, false, false}, {false, true, false, false, false}, {false, true, false, false, false}, {false, true, false, false, false}, {false, false, false, false, false}};
        int n = -4;
        while (n < 3) {
            boolean[][] newCells = new boolean[BOARD_SIZE][BOARD_SIZE];
            //boolean[][] newCells = oldCells;
            for (int y = 0; y < BOARD_SIZE; y++) {
                for (int x = 0; x < BOARD_SIZE; x++) {
                    if (oldCells[y][x]) {
                        System.out.print("#");
                    } else {
                        System.out.print(".");
                    }
                }
                System.out.println();
            }
            System.out.println();
            for (int y = 0; y < BOARD_SIZE; y++) {
                for (int x = 0; x < BOARD_SIZE; x++) {
                    int liveCells = 0;
                    for (int j = -1; j <= 1; j++) {
                        for (int i = -1; i <= 1; i++) {
                            if (i == 0 && j == 0) {
                                continue;
                            }
                            int testX = x + i;
                            int testY = y + j;
                            if (testX >= 0 && testX < BOARD_SIZE && testY >= 0 && testY < BOARD_SIZE && oldCells[testY][testX]) {
                                liveCells++;
                            }
                        }
                    }
                    //System.out.println(liveCells + " cells are alive around y=" + y + " x=" + x);
                    boolean cellIsAlive = oldCells[y][x];
                    if (cellIsAlive) {
                        if (liveCells < 2) {                     //under population
                            newCells[y][x] = false;
                        }
                        if (2 <= liveCells && liveCells <= 3) {  //goldilocks zone
                            newCells[y][x] = true;
                        }
                        if (4 <= liveCells) {                    //over population
                            newCells[y][x] = false;
                        }
                    } else {
                        if (liveCells == 3) {                    //born cell
                            newCells[y][x] = true;
                        }
                    }
                }
            }
            oldCells = newCells;
            n++;
        }

    }
}
