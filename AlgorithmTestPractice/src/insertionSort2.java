import java.util.Scanner;

public class insertionSort2 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int[] inputs = new int[scan.nextInt()];

        for (int i = 0; i < inputs.length; i++) {
            inputs[i] = scan.nextInt();
        }

        insertionSort(inputs);
    }
    static void insertionSort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int standard = arr[i];
            int compardIdx = i - 1;

            while (compardIdx >= 0 && standard < arr[compardIdx]) {
                arr[compardIdx + 1] = arr[compardIdx];
                compardIdx --;
            }
            arr[compardIdx + 1] = standard;
        }
    }
    static void printarr(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]);
        }
        System.out.println();
    }
}
