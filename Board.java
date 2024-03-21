package Laboratore.Lab2;
import java.lang.IllegalArgumentException;
import java.util.ArrayList;
import java.util.List;

public class Board {
    private final int[][] tiles;
    private final int n;

    public Board(int[][] tiles){
        this.n = tiles.length;
        this.tiles = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                this.tiles[i][j] = tiles[i][j];
        int manhattan = manhattan();
    }

    public String toString() {
        String toString = n + "\n";
        for(int i=0; i<n; i++) {
            for (int j = 0; j < n; j++)
                toString += String.format("%2d", tiles[i][j]);
            toString += "\n";
        }
        return toString;
    }

    public int tileAt(int row, int col) {
        if (row < 0 || col < 0 || row > n-1 || col > n-1)
            throw new IllegalArgumentException("Rresht ose kolone jashte madhesise se board");
        return tiles[row][col];
    }

    public int size() {
        return n;
    }

    //kthen numrin e elementeve qe nuk jane ne pozicionet e duhura
    public int hamming() {
        int hamming=0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (tiles[i][j] != 0 && tiles[i][j] != i * n + j + 1) { //kontrollon nese elementet jane ne pozicionin perfundimtar
                    hamming++;
                }
            }
        }
        return hamming;
    }

    //kthen distancen e pozicionit aktual te elementit nga pozicioni ku duhet te jete
    public int manhattan() {
        int manhattan = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (tiles[i][j] != 0 && tiles[i][j] != i * n + j + 1) {
                    manhattan += Math.abs((tiles[i][j] - 1) / n - i) + Math.abs((tiles[i][j] - 1) % n - j);
                }
            }
        }
        return manhattan;
    }

    public boolean isGoal() {
        if(hamming()==0) //puzzle i zgjidhur nese nuk ka asnje element jo ne pozicionin e duhur
            return true;
        return false;
    }

    public boolean equals(Object y) {
        Board board = (Board) y;
        if(n!=board.n)
            return false;
        for(int i=0; i<n; i++)
            for(int j=0; j<n; j++)
                if(board.tiles[i][j]!=tiles[i][j])
                    return false;
        return true;
    }

    //kthen gjithe fqinjet e mundshem
    public Iterable<Board> neighbors() {
        List<Board> neighbors = new ArrayList<>();
        int blankRow = -1, blankCol = -1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (tiles[i][j] == 0) {
                    blankRow = i;
                    blankCol = j;
                    break;
                }
            }
        }
        //krijon boards fqinje te mundshme
        int[][] directions = {{-1, 0}, //leviz nje kuti lart
                              {1, 0},
                              {0, -1}, //leviz nje kuti majtas
                              {0, 1}};
        for (int[] direction : directions) {  //4 directions => maksimalisht 4 fqinje te mundshem kur blank tile ndodhet ne qender
            int newRow = blankRow + direction[0];
            int newCol = blankCol + direction[1];
            if (newRow >= 0 && newRow < n && newCol >= 0 && newCol < n) {
                int[][] newTiles = new int[n][n];
                for (int i = 0; i < n; i++) {
                    System.arraycopy(tiles[i], 0, newTiles[i], 0, n);
                }
                newTiles[blankRow][blankCol] = newTiles[newRow][newCol];
                newTiles[newRow][newCol] = 0;
                neighbors.add(new Board(newTiles));
            }
        }
        return neighbors;
    }

    // is this board solvable?
    public boolean isSolvable(){
        int inversions = 0;
        int index = 0;
        int[] arr = new int[n*n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (tiles[i][j] != 0)
                    arr[index++] = tiles[i][j];
            }
        }
        for(int i=0; i<n*n; i++){
            int min=i;
            for(int j=i+1; j< n*n; j++)
                if(arr[j]< arr[min]) {
                    int swap = arr[j];
                    arr[j] = arr[min];
                    arr[min] = swap;
                    inversions++;
                }
        }

        if((n%2==0 && inversions%2!=0) || (n%2!=0 && inversions%2==0))
            return true;
        return false;
    }

    public static void main(String[] args) {
        int[][] tilesUnsolv = {{8, 6, 7}, {2, 5, 4}, {1, 3, 0}}; //puzzle i pazgjidhshem
        Board boardUnsolv = new Board(tilesUnsolv);
        System.out.println(boardUnsolv);
        System.out.println("Zgjidhet puzzle? " + boardUnsolv.isSolvable());

        System.out.println();

        int[][] tilesSolv = {{6, 5, 3}, {4, 1, 7}, {0, 2, 8}};  //puzzle i zgjidhshem
        Board boardSolv = new Board(tilesSolv);
        System.out.println(boardSolv);
        System.out.println("Zgjidhet puzzle? " + boardSolv.isSolvable());
    }
}
