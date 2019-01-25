import java.util.Scanner;

public class selectionSort {
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
        for(int i = 0 ; i <  arr.length -1 ; i ++) {
            for(int j = i+1 ; j < arr.length ; j ++) {
                if(arr[i] > arr[j]) {
                    temp = arr[j];
                    arr[j] = arr[i];
                    arr[i] = temp;
                }
                printArr(arr);
            }
        }
//        printArr(arr);
    }
    public static void printArr(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

}
