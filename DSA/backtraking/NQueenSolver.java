import java.util.ArrayList;
import java.util.List;

class NQueenSolver {

    // Check if placement is safe
    static boolean isSafe(List<Integer> board, 
                          int currRow, int currCol) {
      
        for(int i = 0; i < board.size(); i++) {
            int placedRow = board.get(i);
            int placedCol = i + 1;

            // Check diagonals
            if(Math.abs(placedRow - currRow) == 
               Math.abs(placedCol - currCol)) {
                return false; // Not safe
            }
        }
        return true; // Safe to place
    }

    // Recursive utility to solve
    static void nQueenUtil(int col, int n, 
                           List<Integer> board, 
                           List<List<Integer>> result, 
                           boolean[] visited) {

        // If all queens placed, add to result
        if(col > n) {
            result.add(new ArrayList<>(board));
            return;
        }

        // Try each row in column
        for(int row = 1; row <= n; row++) {

            // If row not used
            if(!visited[row]) {

                // Check safety
                if(isSafe(board, row, col)) {

                    // Mark row
                    visited[row] = true;

                    // Place queen
                    board.add(row);

                    // Recur for next column
                    nQueenUtil(col + 1, n, board, 
                              result, visited);

                    // Backtrack
                    board.remove(board.size()-1);
                    visited[row] = false;
                }
            }
        }
    }

    // Main N-Queen solver
    static List<List<Integer>> solveNQueen(int n) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> board = new ArrayList<>();
        boolean[] visited = new boolean[n + 1];
        nQueenUtil(1, n, board, result, visited);
        return result;
    }

    // Main method
    public static void main(String[] args) {
        int n = 4;
        List<List<Integer>> result = solveNQueen(n);
      
        for(List<Integer> res : result) {
            System.out.print("[");
            for(int i = 0; i < res.size(); i++) {
              
                System.out.print(res.get(i));
                if(i != res.size()-1)
                  System.out.print(", ");
            }
            System.out.println("]");
        }
    }
}
