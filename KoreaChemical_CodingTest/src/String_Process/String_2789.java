package String_Process;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class String_2789 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        StringBuffer origin = new StringBuffer(s);

        //CAMBRIDGE
        String cam = "CAMBRIDGE";
        int i = 0;
        while(i < origin.length()) {
            for (int j = 0; j < cam.length(); j++) {
                if (origin.charAt(i) == cam.charAt(j)) {
                    origin.deleteCharAt(i);
                }
            }
            i++;

        }


        System.out.println(origin);
    }
}