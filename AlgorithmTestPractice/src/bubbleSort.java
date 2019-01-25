import java.util.Scanner;

public class bubbleSort {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int[] inputs = new int[scan.nextInt()];

        for (int i = 0; i < inputs.length; i++) {
            inputs[i] = scan.nextInt();
        }

        bubbleSort(inputs);
    }

    static void bubbleSort(int[] arr) {
        int n = arr.length;
        int temp;
        for(int i=0; i < n; i++){
            for(int j=1; j < (n-i); j++){
                if(arr[j-1] > arr[j]){
                    //swap elements
                    temp = arr[j-1];
                    arr[j-1] = arr[j];
                    arr[j] = temp;
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
