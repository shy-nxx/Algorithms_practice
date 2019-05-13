package String_Process;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class String_1032 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        /**
         * 패턴에는 알파벳과 "." 그리고 "?"만 넣을 수 있다. 가능하면 ?을 적게 써야 한다.
         * 그 디렉토리에는 검색 결과에 나온 파일만 있다고 가정하고, 파일 이름의 길이는 모두 같다.
         */
        int N = Integer.parseInt(st.nextToken());

        String result = br.readLine();
        char[] ch_result = result.toCharArray();

        for (int i = 1; i < N; i++) {
           String s = br.readLine();

           for (int j = 0; j < s.length(); j++) {
               if (result.charAt(j) != s.charAt(j)) {
                   ch_result[j] = '?';
               }
           }
        }

        for (int i = 0; i < ch_result.length; i++) {
            System.out.print(ch_result[i]);
        }
    }
}