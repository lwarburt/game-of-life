package lwarburton.gol;

public class Main {

    public static void main(String[] args) {
        GameOfLife g = new GameOfLife(30, .375);
        System.out.println(g);
        g.simulate(5);
        System.out.println(g);
    }
}