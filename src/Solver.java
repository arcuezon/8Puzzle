import java.util.Comparator;

import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class Solver {

    private final node goal;


    private static class node {
        public final Board board;
        public final int moves;
        public final node prevBoard;
        public final int priority;

        public node(Board board, int moves, node prevBoard) {
            this.board = board;
            this.moves = moves;
            this.prevBoard = prevBoard;
            this.priority = moves + board.manhattan();
        }
    }

    // find a solution to the initial board (using the A* algorithm)
    public Solver(Board initial) {
        if (initial == null) throw new IllegalArgumentException();
        priorityOrder order = new priorityOrder();

        MinPQ<node> pQ = new MinPQ<>(order);
        MinPQ<node> twinPQ = new MinPQ<>(order);
        pQ.insert(new node(initial, 0, null));
        Board twinBoard = initial.twin();
        twinPQ.insert(new node(twinBoard, 0, null));

        while (!pQ.min().board.isGoal() && !twinPQ.min().board.isGoal()) {

            node prev = pQ.delMin(); //Actual
            for (Board board : prev.board.neighbors()) {
                if (prev.prevBoard == null) {
                    node nborNode = new node(board, prev.moves + 1, prev);
                    pQ.insert(nborNode);
                } else if (!board.equals(prev.prevBoard.board)) {
                    node nborNode = new node(board, prev.moves + 1, prev);
                    pQ.insert(nborNode);
                }
            }

            node twinPrev = twinPQ.delMin(); //Twin
            for (Board twinNbor : twinPrev.board.neighbors()) {
                if (twinPrev.prevBoard == null) {
                    node nborNode = new node(twinNbor, twinPrev.moves + 1, twinPrev);
                    twinPQ.insert(nborNode);
                } else if (!twinNbor.equals(twinPrev.prevBoard.board)) {
                    node nborNode = new node(twinNbor, twinPrev.moves + 1, twinPrev);
                    twinPQ.insert(nborNode);
                }
            }
        }

        if (pQ.min().board.isGoal()) {
            goal = pQ.delMin();
        } else goal = null;
    }

    // is the initial board solvable? (see below)
    public boolean isSolvable() {
        if (goal == null) return false;
        if (goal.board.isGoal()) return true;
        return false;
    }

    // min number of moves to solve initial board; -1 if unsolvable
    public int moves() {
        if (!isSolvable()) return -1;
        return goal.moves;
    }

    // sequence of boards in a shortest solution; null if unsolvable
    public Iterable<Board> solution() {
        if (!isSolvable()) return null;

        Stack<Board> sols = new Stack<>();

        for (node curr = goal; curr != null; curr = curr.prevBoard) {
            sols.push(curr.board);
        }

        return sols;
    }

    private static class priorityOrder implements Comparator<node> {
        public int compare(node a, node b) {

            if (a.priority > b.priority) return 1;
            else if (b.priority > a.priority) return -1;
            else return 0;

        }
    }

    // test client (see below)
    public static void main(String[] args) {
        In in = new In(args[0]);
        int N = in.readInt();
        int[][] blocks = new int[N][N];
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                blocks[i][j] = in.readInt();
        Board initial = new Board(blocks);

        // solve the puzzle
        Solver solver = new Solver(initial);

        // print solution to standard output
        if (!solver.isSolvable())
            StdOut.println("No solution possible");
        else {
            StdOut.println("Minimum number of moves = " + solver.moves());
            for (Board board : solver.solution())
                StdOut.println(board);
        }
    }
}