// you can also use imports, for example:
// import java.util.*;

// you can write to stdout for debugging purposes, e.g.
// System.out.println("this is a debug message");

class Solution {
    public int[] dx = {-1,-1};
    public int[] dy = {+1,-1};
    public int[][] matrix;
    public boolean[][] visited;
    public int counts = 0;


    public int solution(String[] B) {
        // write your code in Java SE 8
        matrix = new int[B.length][B.length];
        visited = new boolean[B.length][B.length];
        DOT dot;

        for (int i = 0; i < B.length; i++) {
            String pawns = B[i];

            for (int j = 0; j < B.length; j++) {
                char a = pawns.charAt(i);
                if (a == 'X') {
                    matrix[i][j] = 1;
                }
                if (a == 'O') {
                    dot = new DOT(i, j);
                    DFS(dot);
                }
            }
        }

        return counts;
    }
    void DFS(DOT d) {

        if (check(d.x, d.y)) {
            counts++;
            DFS(new DOT(row, col));
        }

    }
    boolean check(int row, int col) {

        for (int i = 0; i < dx.length; i++) {
            int nextX = row + dx[i];
            int nextY = col + dy[i];

            if (row < 0 || row >= matrix.length || col < 0 || col >= matrix.length) continue;

            int nextnextX = row + dx[i];
            int nextnextY = col + dy[i];

            if (nextnextX < 0 || nextnextX >= matrix.length || nextnextY < 0 || nextnextY >= matrix.length) continue;

            if (matrix[nextnextX][nextnextY] == 1) continue;

            if (matrix[row][col] == 1) return true;
        }

        return true;
    }
}

class DOT {
    int x;
    int y;

    public DOT(int x, int y) {
        this.x = x;
        this.y = y;
    }
}