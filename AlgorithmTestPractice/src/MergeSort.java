import java.util.Scanner;

public class MergeSort {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int[] inputs = new int[scan.nextInt()];

        for (int i = 0; i < inputs.length; i++) {
            inputs[i] = scan.nextInt();
        }

        mergeSort(inputs, 0, inputs.length-1);

    }
    static void mergeSort(int[] arr, int l, int r) {
        if (l < r) {
            int mid = (l + r) /2;
            mergeSort(arr, l, mid);
            mergeSort(arr, mid + 1, r);
            merge(arr, l, mid, r);
        }
    }

    static void merge_02(int[] arr, int low, int mid, int high) {

        int[] sorted = new int[arr.length]; //정렬할 배열과 똑같은 사이즈의 배열이 필요
        for (int i = low; i <= high; i++) {
            sorted[i] = arr[i];
        }

        int Left = low; //sorted의 left
        int Right = mid+1; // sorted의 right
        int current = low;

        while (Left <= mid && Right <=high) {
            if(sorted[Left] <= sorted[Right]){
                arr[current] = sorted[Left];
                Left++;

            } else{
                arr[current] = sorted[Right];
                Right++;
            }
            current++;
        }

        int remaining = mid - Left;
        for (int i = 0; i <= remaining; i++) {
            arr[current+i] = sorted[Left+ i];
        }
        printArr(arr);
    }

    //이렇게도 사용
    public static void merge(int[] arr, int m, int middle, int n) {
        int i, j, k, t;

        i = m;
        j = middle + 1;
        k = m;

        int[] sorted = new int[arr.length];

        while (i <= middle && j <= n) {
            if (arr[i] <= arr[j])
                sorted[k] = arr[i++];
            else
                sorted[k] = arr[j++];
            k++;
        }

        if (i > middle) {
            for (t = j; t <= n; t++, k++)
                sorted[k] = arr[t];
        } else {
            for (t = i; t <= middle; t++, k++)
                sorted[k] = arr[t];
        }

        for (t = m; t <= n; t++)
            arr[t] = sorted[t];

        System.out.println("\n 합병 정렬 >> ");
        printArr(arr);

    }

    public static void printArr(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }
}
