import java.util.Arrays;

public class Q2261 {
    /**
     * BOJ
     */
    public static void main(String[] args) {
        int[] coffe_times = {4, 2, 2, 5, 3};
        Arrays.sort(coffe_times);

        int[] answer = {};
        for (int i = 0; i < coffe_times.length; i++)
            System.out.println(coffe_times[i]);
    }
}
