import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class L1_Q2 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int testcase = scan.nextInt();

        int[] cars;
        int[] colors;
        int[] result  = new int[testcase];

        for (int z = 0; z < result.length; z++) {
            result[z] = -1;
        }
        for (int i = 0; i < testcase; i++) {
            int n = scan.nextInt();
            int m = scan.nextInt();

            cars = new int[n]; //지나가는 차량의 색상
            colors = new int[m]; //색상에 따른 차량의 최대 개수
            for (int j = 0; j < m; j++) {
                colors[j] = scan.nextInt();

            }

            for (int j = 0; j < n; j++) {
                cars[j] = scan.nextInt();

            }


            for (int j = 0; j < n; j++) {

                int[] temp = new int[m];
                System.arraycopy(colors, 0, temp, 0, colors.length);
                int count = 0;

                temp[cars[j] - 1] -= 1;

                for (int z = j + 1; z < n; z++) {

                    if ((temp[cars[z] - 1] -1) < 0) break;
                    temp[cars[z] - 1] -= 1;

                }

                for (int z = 0; z < m; z++) {
//                    System.out.println(j + "temp = " +  temp[z]);
                    if (temp[z] == 0) {
                        count++;
//                        System.out.println(j + "count = " + count);
                    }
                }

                if (count == m) {
                    result[i] = i;
                    System.out.println("#" + (i + 1) + " " + (j + 1));
                }


            }


        }
        for (int z = 0; z <result.length; z++) {
            if (result[z] == -1) {
                System.out.println("#" + (z + 1) + " 0");
            }
        }

    }
}
