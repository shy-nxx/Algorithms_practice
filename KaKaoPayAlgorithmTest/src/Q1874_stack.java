import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

public class Q1874_stack {
    public static void main(String[] args) {
        // TODO Auto-generated method stub

        Scanner scan = new Scanner(System.in);
        Stack st = new Stack();
        ArrayList array = new ArrayList();
        int index = 0;

        int n = scan.nextInt();
        int[] arr;
        arr= new int[n + 1];

        for (int i = 0; i < n; i++) {

            arr[i] = scan.nextInt();
        }

        for(int i =1; i <= n; i++) {
            st.add(i);
            array.add("+");

            while(!st.empty() && (int)st.peek() == arr[index]) {
                index++;
                st.pop();
                array.add("-");
            }

        }
        if (!st.empty()) {
            System.out.println("NO");
        }
        else {
            for (int i = 0; i < array.size(); i++) {
                System.out.println(array.get(i));
            }
        }
    }
}
