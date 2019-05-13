
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class SWEA_5658 {

    public static void main(String[] args) throws IOException {
        // TODO Auto-generated method stub
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int T =  Integer.parseInt(st.nextToken());

        StringBuffer sf = new StringBuffer();
        for (int k=0; k < T; k++) {
            sf.append("#" + (k+1) + " ");
            st = new StringTokenizer(br.readLine());

            int N = Integer.parseInt(st.nextToken()); //N개의 16진수 숫자
            int K = Integer.parseInt(st.nextToken()); //K번째 큰 수를 찾아라

//			int[][] hex_n = new int[4][N];
            LinkedList<String> list = new LinkedList<>();

            String s = br.readLine();

//            for (int i = 0; i < s.length(); i++) {
//                list.add(String.valueOf(s.charAt(i)));
//            }

            char[] ch = s.toCharArray();

            int size = N / 4;

            int cnt = size;
            int sum = 0;

            while(cnt-- != 0) {
                for (int i = 0; i < 4; i++) { //네 변
                   for (int j = 0; j < size; j++) {
                        
                   }
                }

            }




        }
    }
    //문자열을 hex값으로
    static String stringToHex(String s) {
        String result = "";

        for (int i = 0; i < s.length(); i++) {
            result += String.format("%02X", (int)s.charAt(i));

        }
        return result;
    }
}

