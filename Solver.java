package Laboratore.Lab2;

import edu.princeton.cs.algs4.MinPQ;
import java.util.ArrayList;
import java.util.Collections;

public class Solver {
    private final ArrayList<Board> solutionBoards;
    private final int moves;
    public Solver(Board initial) {
        if(initial==null)
            throw new IllegalArgumentException("Puzzle fillestar nuk mund te jete bosh");
        if(!initial.isSolvable())
            throw new IllegalArgumentException("Puzzle eshte i pazgjidhshem");

        MinPQ<SearchNode> pq = new MinPQ<>();
        pq.insert(new SearchNode(initial, 0, null));

        while (true) {
            SearchNode node = pq.delMin();
            if (node.board.isGoal()) {
                moves = node.moves;
                solutionBoards = new ArrayList<>();
                while (node != null) {
                    solutionBoards.add(node.board);
                    node = node.previous;
                }
                Collections.reverse(solutionBoards);
                break;
            }
            for (Board neighbor : node.board.neighbors()) {
                if (node.previous == null || !neighbor.equals(node.previous.board)) {
                    pq.insert(new SearchNode(neighbor, node.moves + 1, node));
                }
            }
        }
    }
    public int moves(){
        return moves;
    }

    public Iterable<Board> solution() { //sekuenca e boardeve ne rrugezgjidhjen me te shkurter
        return solutionBoards;
    }

    public static void main(String[] args){
        PuzzleChecker.main(args);
    }

}
