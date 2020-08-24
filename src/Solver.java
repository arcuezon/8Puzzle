import java.util.Comparator;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.MinPQ;

public class Solver {

    private final Board initial;
    private final node goal;

    private static class node {
        public final Board board;
        public final int moves;
        public final node prevBoard;

        public node(Board board, int moves, node prevBoard) {
            this.board = board;
            this.moves = moves;
            this.prevBoard = prevBoard;
        }
    }

    // find a solution to the initial board (using the A* algorithm)
    public Solver(Board initial) {
        priorityOrder order = new priorityOrder();
        this.initial = initial;

        node current = new node(initial, 0, null);
        MinPQ<node> pQ = new MinPQ<>(order);
        pQ.insert(current);

        while (!pQ.min().board.isGoal()) {
            node prev = pQ.delMin();
            int moves = prev.moves;
            moves++;

            for(Board board: prev.board.neighbors()){
                node nborNode = new node(board, moves, prev);
                pQ.insert(nborNode);
            }
            //System.out.printf("Move: %d \n", moves);
        }

        goal = pQ.delMin();
        System.out.printf("Solved in %d moves.\n", goal.moves);
    }

    // is the initial board solvable? (see below)
    public boolean isSolvable() {
        return true;
    }

    // min number of moves to solve initial board; -1 if unsolvable
    public int moves() {
        return initial.manhattan();
    }

    // sequence of boards in a shortest solution; null if unsolvable
    public Iterable<Board> solution() {
        Stack<Board> sols = new Stack<>();

        for(node curr = goal; curr != null; curr = curr.prevBoard){
            sols.push(curr.board);
        }

        return sols;
    }

    private static class priorityOrder implements Comparator<node> {
        public int compare(node a, node b) {
            int pA = a.board.manhattan() + a.moves;
            int pB = b.board.manhattan() + b.moves;

            if (pA > pB) return 1;
            else if (pB > pA) return -1;
            else return 0;
        }
    }

    // test client (see below)
    public static void main(String[] args) {
        int[][] test = {
            {6, 1, 3},
            {4, 2, 5},
            {7, 0, 8}
        };

        Board testCase = new Board(test);
        Solver testSolve = new Solver(testCase);

        for(Board steps: testSolve.solution()){
            System.out.println(steps.toString());
        }
    }

}