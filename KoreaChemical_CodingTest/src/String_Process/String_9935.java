package String_Process;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class String_9935 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();

        StringBuffer sf = new StringBuffer();

        char ch[] = s.toCharArray();
        String bomb = br.readLine();

        for (int i = 0; i < s.length(); i++) {
            for (int j = 0; j < bomb.length(); j++) {
                if (s.charAt(i) == bomb.charAt(j)) {
                    ch[i] = ' ';
                }
            }
        }


        for (int i = 0; i < ch.length; i++) {
            if (ch[i] != ' ') {
                sf.append(ch[i]);
            }
        }

        if (sf.length() == 0) {
            System.out.println("FRULA");
        }
        else {
            System.out.println(sf.toString());
        }


    }
}