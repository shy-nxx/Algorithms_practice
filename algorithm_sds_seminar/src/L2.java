import java.util.Scanner;

public class L2 {
    public static void main(String args[]) {
        Scanner scan = new Scanner(System.in);

        int testcase = scan.nextInt();

        for (int i = 0; i < testcase; i++) {
            int n = scan.nextInt(); //우주선 수
            int m = scan.nextInt(); //미사일 수
            int b = scan.nextInt(); //방어막 세기

            int[] level = new int[m];
            int[] count = new int[m];
            for (int j = 0; j < m; j++) {
                level[j] = scan.nextInt();
                count[j] = scan.nextInt();
            }
            int local_min = 10000;
            int local_sum = 0;
            for (int k = 0; k < m; k++ ) {
                if (count[k] > 0) {
                    local_sum += level[k];
                    count[k]--;
                    if (local_sum >= n * b) {
                        if(local_min > local_sum) local_min = local_sum;
                        break;
                    }
                }

            }
            if (local_min < 10000)
                System.out.println("#" + (i + 1) + " " + local_min);
            else
                System.out.println("#" + (i + 1) + " " + "-1");

        }


        
    }


}
