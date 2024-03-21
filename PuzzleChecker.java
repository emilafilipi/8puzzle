package Laboratore.Lab2;

public class PuzzleChecker {
    public static void main(String[] args) {
        // for each command-line argument
        int[][] tilesUnsolv = {{8, 6, 7}, {2, 5, 4}, {1, 3, 0}}; //puzzle i pazgjidhshem
        //int[][] tilesSolv = {{6, 5, 3}, {4, 1, 7}, {0, 2, 8}};  //puzzle i zgjidhshem
        int[][] tilesSolv = {{6, 7, 4}, {5, 2, 3}, {1, 8, 0}};
        Board boardInitial = new Board(tilesSolv);
        //Board boardInitial = new Board(tilesUnsolv);
        System.out.println(boardInitial);
        Solver solver = new Solver(boardInitial);

        if (!boardInitial.isSolvable())
            System.out.println("No solution possible");
        else {
            for (Board board : solver.solution())
                System.out.println(board);
            System.out.println("Minimum number of moves = " + solver.moves());
        }
    }
}

