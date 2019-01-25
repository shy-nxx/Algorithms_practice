import java.util.Scanner;

public class insertionSort_prac {
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
            int compareIdx = i - 1;

            while(compareIdx >= 0 && standard < arr[compareIdx]){
                arr[compareIdx + 1] = arr[compareIdx];
                compareIdx--;
            }
            arr[compareIdx + 1] = standard;
        }
        printarr(arr);
    }
    static void printarr(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }
}
