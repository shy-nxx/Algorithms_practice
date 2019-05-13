package String_Process;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class String_1316 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());

        int count = 0;

        for (int i = 0; i < N; i++) {
            String s = br.readLine();

            if (wordCheck(s)) {
                count++;
            }

        }
        System.out.println(count);
    }
    static boolean wordCheck(String s) {

        boolean[] alpa = new boolean[26];

        char[] ch = s.toCharArray();
        for (int j = 0; j < ch.length-1; j++) {

            if (alpa[ch[j] - 'a']) { //이미 체크한 단어
                return false;
            }
            else{ //처음 오는 단어
                alpa[ch[j] - 'a'] = true;

                char tmp = ch[j];
                while (true) {
                    if (tmp != ch[j+1]) { //현재 알파벳과 다른 알파벳이 올때까지 탐색
                        j--;
                        break;
                    }
                }
            }
        }
        return true;
    }
}