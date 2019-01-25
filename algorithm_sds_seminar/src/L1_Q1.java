import com.sun.deploy.util.StringUtils;

import java.util.*;

public class L1_Q1 {
    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);

        int testcase = scan.nextInt();
        int[] numOfCards = new int[testcase];

        List<String> hs = new ArrayList<String>();

        for (int i = 0; i <= testcase * 2; i++) {
            hs.add(scan.nextLine());

        }

        int size = hs.size();

        String[] token;
        String[] arrange;
        List<String> result = new ArrayList<>();
        int k = 1;
        for (int i = 0; i < size; i++) {
            if (i > 0 && i % 2 == 0) {
                token = hs.get(i).split(" ");
                for (int j = 0; j < token.length; j++) {
                    if (result.size() == 0) {

                        result.add(token[j]);
                    }
                    else if (result.get(0).compareTo(token[j]) <= 0) {

                        result.add(0, token[j]);
                    } else {

                        result.add(token[j]);
                    }


                }
                StringBuilder sb = new StringBuilder();
                for (String s : result)
                {
                    sb.append(s);

                }
                System.out.println("#" + k++ + " " + sb);
                result = new ArrayList<>();

            }
        }



    }
}
