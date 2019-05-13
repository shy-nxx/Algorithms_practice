package String_Process.Practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class String_1316_group_words {
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
        boolean[] alpabet = new boolean[26];

        char[] ch = s.toCharArray();
        for (int i = 0; i < ch.length-1; i++) {
            if (alpabet[ch[i] - 'a']) { //이미 처리된 단어
                return false;
            }

            else { //처음 처리하는 단어
                alpabet[ch[i] - 'a'] = true; //true로 바꿔주고
                char temp = ch[i];

                while (true) {
                    if (temp != ch[i+1]) { //같은 단어나 나오지 않을 때까지 뒤로 가줌
                        i--;
                        break;
                    }
                }
            }
        }
        return true;
    }
}