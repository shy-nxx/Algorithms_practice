import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class bead_BJ {
    /**
     * 구슬탈출 - 삼성 SW 기출
     */
    static int N;
    static int M;
    final static int HOLE = 0;
    final static int POSSIBLE = 1;
    final static int IMPOSSIBLE = 2;
    final static int[] xPos = {-1,1,0,0};
    final static int[] yPos = {0,0,-1,1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());


    }
}
