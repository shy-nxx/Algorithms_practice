package String_Process;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class String_1100 {
    static final int WHITE = 1;
    static final int BLACK = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        char[][] chase = new char[8][8];
        int flag= 0;

        for (int i = 0; i < 8; i++) {
            String s = br.readLine();
            for (int j = 0; j < 8; j++) {
                chase[i][j] = s.charAt(j);

                if (chase[i][j] == 'F' && (i + j) % 2 == 0)
                    flag++;
            }
        }

        System.out.println(flag);
    }
}
