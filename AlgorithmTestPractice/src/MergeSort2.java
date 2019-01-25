import java.util.Scanner;

public class MergeSort2 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int[] inputs = new int[scan.nextInt()];

        for (int i = 0; i < inputs.length; i++) {
            inputs[i] = scan.nextInt();
        }

        mergeSort(inputs, 0, inputs.length-1);
        printArr(inputs);
    }

    static void mergeSort(int[] arr, int l, int r) {
        if (l < r) {
            int mid = (l + r) / 2;
            mergeSort(arr, l, mid);
            mergeSort(arr, mid + 1, r);
            merge(arr, l, mid, r);
        }
    }
    static void merge(int[] arr, int left, int mid, int right) {
        int i, j, k, t;

        i = left;
        j = mid + 1;
        k = left;

        int[] sorted = new int[arr.length];

        while(i <= mid && j <= right) {
            if(arr[i] <= arr[j]) {
                sorted[k++] = arr[i++];
            }
            else {
                sorted[k++] = arr[j++];
            }
        }
        if (i > mid) {
            for (t = j; t <= right; t++) {
                sorted[k++] = arr[t];
            }
        } else {
            for (t = i; t <= mid; t++) {
                sorted[k++] = arr[t];
            }
        }

        for (t = left; t <= right; t++)
            arr[t] = sorted[t];
    }
    public static void printArr(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }
}
