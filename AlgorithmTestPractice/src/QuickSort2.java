import java.util.Scanner;

public class QuickSort2 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int[] inputs = new int[scan.nextInt()];

        for (int i = 0; i < inputs.length; i++) {
            inputs[i] = scan.nextInt();
        }

        quickSort(inputs, 0, inputs.length-1);
        printArr(inputs);

    }

   static void quickSort(int[] arr, int l, int r) {
        if (l < r) {
            int partition = partition(arr, l, r);
            quickSort(arr, l, partition-1);
            quickSort(arr, partition + 1, r);
        }
   }
    static int partition(int[] arr, int left, int right) {
        int pivot = arr[(left + right) /2];

        while(left < right){
            while(arr[left] < pivot && (left < right)){
                left++;
            }
            while(arr[right] > pivot && (left < right)){
                right--;
            }

            if (left < right) {
                int temp = arr[left];
                arr[left] = arr[right];
                arr[right] = temp;
            }
        }
        return left;
    }
    public static void printArr(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }
}
