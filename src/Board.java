import java.util.ArrayList;
import java.util.Arrays;

public class Board {

    private final int[][] arrBoard;
    private int[] freeSpace;

    // create a board from an n-by-n array of tiles,
    // where tiles[row][col] = tile at (row, col)
    public Board(int[][] tiles) {
        arrBoard = new int[tiles.length][tiles[0].length];

        for (int i = 0; i < arrBoard.length; i++) {
            for (int j = 0; j < arrBoard.length; j++) {
                if(tiles[i][j] == 0){
                    freeSpace[0] = i;
                    freeSpace[1] = j;
                }
                arrBoard[i][j] = tiles[i][j];
            }
        }
    }

    // string representation of this board
    public String toString() {
        StringBuilder s = new StringBuilder();

        s.append(arrBoard.length + "\n");
        for (int i = 0; i < arrBoard.length; i++) {
            for (int j = 0; j < arrBoard.length; j++) {
                s.append(String.format("%2d ", arrBoard[i][j]));
            }
            s.append("\n");
        }

        return s.toString();
    }

    // board dimension n
    public int dimension() {
        return arrBoard.length;
    }

    // number of tiles out of place
    public int hamming() {
        int count = 0;
        for (int i = 0; i < arrBoard.length; i++) {
            for (int j = 0; j < arrBoard.length; j++) {
                if (!inPlace(i, j)) count++;
            }
        }

        return count;
    }

    // sum of Manhattan distances between tiles and goal
    public int manhattan() {
        int sumDist = 0;

        for (int i = 0; i < arrBoard.length; i++) {
            for (int j = 0; j < arrBoard.length; j++) {
                if (inPlace(i, j)) continue;
                else {
                    int[] idxs = twoIndex(arrBoard[i][j]);
                    sumDist += Math.abs(i - idxs[0]) + Math.abs(j - idxs[1]);
                }
            }
        }
        return sumDist;
    }

    private boolean inPlace(int i, int j) {
        if(arrBoard[i][j] == 0) return true;

        int idx = oneIndex(i, j);
        return idx == arrBoard[i][j];
    }

    private int oneIndex(int row, int col) {
        int idx = col + row * arrBoard.length;
        idx++;

        return idx;
    }

    private int[] twoIndex(int index) {
        int[] idxs = new int[2];

        index--;
        idxs[0] = index / arrBoard.length; //Row
        idxs[1] = index - (idxs[0] * arrBoard.length); //Column

        return idxs;
    }

    // is this board the goal board?
    public boolean isGoal(){
        return hamming() == 0;
    }

    // does this board equal y?
    public boolean equals(Board y){
        return Arrays.deepEquals(this.arrBoard, y.arrBoard);
    }

    // all neighboring boards
    public Iterable<Board> neighbors(){
        ArrayList<Board> nbors;

        
        return nbors.toArray();
    }

    // a board that is obtained by exchanging any pair of tiles
    public Board twin(){

    }

    private boolean chkIndex(int i, int j){
        return (i >= 0) && (i < arrBoard.length) && (j >= 0) && (j < arrBoard.length);
    }

    // unit testing (not graded)
    public static void main(String[] args) {
        int[][] arr ={
                {1, 3, 0},
                {4, 2, 5},
                {7, 8, 6}
        };

        Board test = new Board(arr);
        System.out.println(test.hamming());
        System.out.println(test.manhattan());
        System.out.println(test.toString());

    }

}