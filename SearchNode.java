package Laboratore.Lab2;

public class SearchNode implements Comparable<SearchNode>{
    final Board board;
    final int moves;
    final SearchNode previous;

    public SearchNode(Board board, int moves, SearchNode previous) {
        this.board = board;
        this.moves = moves;
        this.previous = previous;
    }

    public int compareTo(SearchNode that) {
        return (this.board.manhattan() + this.moves) - (that.board.manhattan() + that.moves);
    }
}
