
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.regex.Pattern;

public class Q1 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String pattern = br.readLine();

        boolean okPattern = false;
        String regex = null;

//        regex = "^\\+[0-9]-(?:[- ]?[0-9]){14}$";
        regex = "^\\+\\d{2}-1(?:0[6-9])-(?:\\d{3}|\\d{4})-\\d{4}$";
//        regex = "^[\\d\\+]{2}([\\- ]?)\\d{1,3}([\\- ]?)\\d{3}([\\- ]?)\\d{4}$";
//        regex = "^\\d{2,3}-\\d{3,4}-\\d{4}$";

//        regex = "^\\+(9[976]\\d|8[987530]\\d|6[987]\\d|5[90]\\d|42\\d|3[875]\\d|2[98654321]\\d|9[]|8[6421]|6[]|5[87654321]|4[987654310]|3[]|2[70]|7|1)\\d{1,14}$";
//        regex = "^01(?:0|1[6-9])-(?:\\d{3}|\\d{4})-\\d{4}$";

        okPattern = Pattern.matches(regex, pattern);

        Queue<String> q = new LinkedList<>();

        while (!q.isEmpty()) {
            
        }
        System.out.println(okPattern);

    }
}
