import java.util.Comparator;

import edu.princeton.cs.algs4.MinPQ;

public class Solver {

    private Board initial;

    private final class node{
        public Board board;
        public int moves;
        public Board prevBoard;

        public node(Board board, int moves, Board prevBoard){
            this.board = board;
            this.moves = moves;
            this.prevBoard = prevBoard;
        }   

        
        public Comparator<Board> nodeComp(){
            return new boardComparator();
        }
    }

   // find a solution to the initial board (using the A* algorithm)
    public Solver(Board initial){
        this.initial = initial;
        
        node current = new node(initial, 0, null);
        MinPQ<Board> pQ = new MinPQ<Board>();
        int curMoves = 0;

        while(!pQ.min().isGoal()){
            curMoves++;
            Iterable<Board> nbors = pQ.min().neighbors();
        }
    }

    // is the initial board solvable? (see below)
    public boolean isSolvable(){

    }

    // min number of moves to solve initial board; -1 if unsolvable
    public int moves(){
        return initial.manhattan();
    }

    // sequence of boards in a shortest solution; null if unsolvable
    public Iterable<Board> solution(){

    }

    // test client (see below)
    public static void main(String[] args){

    }

}