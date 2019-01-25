import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Q11004 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int index = Integer.parseInt(st.nextToken());

        int[] numbers = new int[n];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(numbers);
//        quickSort(numbers, 0, n-1);

        System.out.println(numbers[index-1]);
    }

    static void quickSort(int[] arr, int l, int r ) {
        if (l < r) {
            int partition = partition(arr, l, r);

            quickSort(arr, l, partition -1);
            quickSort(arr, partition + 1, r);
        }
    }

    static int partition(int[] arr, int left, int right) {
        int pivot = arr[(left + right) /2];

        while (left < right) {
            while (arr[left] < pivot && (left < right)) left ++;
            while (arr[right] > pivot && (left < right)) right --;

            if (left < right){
                int temp = arr[right];
                arr[right] = arr[left];
                arr[left] = temp;
            }
        }
        return left;
    }
}
