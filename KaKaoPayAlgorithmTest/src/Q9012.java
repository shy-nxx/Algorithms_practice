import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Q9012 {
    //백준알고리즘 스택 9012 - 괄호 (VPS)
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());

        for (int i = 0; i < n; i++) {
            String inputVPS = br.readLine();
            Stack st = new Stack();
            String result = "YES";
            for (int j = 0; j < inputVPS.length(); j++) {
                if (inputVPS.charAt(j) != ')') {
                    st.push(1);
                } else {
                    if (st.empty()) {
                        result = "NO";
                        break;
                    }
                    st.pop();
                }
            }
            if (!st.empty())
                result = "NO";

            sb.append(result+"\n");
        }

        System.out.println(sb.toString());
    }

}
