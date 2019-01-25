import java.util.Scanner;

public class selectionSort2 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int[] inputs = new int[scan.nextInt()];

        for (int i = 0; i < inputs.length; i++) {
            inputs[i] = scan.nextInt();
        }

        selectionSort(inputs);
    }
    static void selectionSort(int[] arr) {
        int temp;

        for (int i = 0; i < arr.length -1; i++) {
            for (int j = i + 1; j < arr.length; j++ ){
                if (arr[j] < arr[i]) {
                    temp = arr[j];
                    arr[j] = arr[i];
                    arr[i] = temp;
                }
            }
        }
        printArr(arr);
    }

    public static void printArr(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
    }
}
