package String_Process.Practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class String_1032_prompt {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());

        String result = br.readLine();

        char[] ch_result = result.toCharArray();

        for (int i = 1; i < N; i++) {
            String s = br.readLine();

            for (int j = 0; j < s.length(); j++) {
                if (ch_result[j] != s.charAt(j)){
                    ch_result[j] = '?';
                }
            }
        }

        for (int i = 0; i < ch_result.length; i++) {
            System.out.print(ch_result[i]);
        }
    }
}