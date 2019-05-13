

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class SW5686 {
    static int MAG = 4;

    static LinkedList<Integer>[] list = (LinkedList<Integer>[])new LinkedList[5];

    public static void main(String[] args) throws IOException {
        // TODO Auto-generated method stub
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        StringBuffer sf = new StringBuffer();

        int T = Integer.parseInt(st.nextToken());

        for (int k = 0; k < T; k++) {

            sf.append("#" + (k+1) + " ");
            st = new StringTokenizer(br.readLine());
            int K = Integer.parseInt(st.nextToken());//자석 돌리는 횟수

            for (int i = 1; i <= MAG; i++) {
                list[i] = new LinkedList<>();
                st = new StringTokenizer(br.readLine());
                while(st.hasMoreTokens()) {
                    list[i].add(Integer.parseInt(st.nextToken()));
                }

            }

            for (int i = 0; i < K; i++) {
                st = new StringTokenizer(br.readLine());
                int n = Integer.parseInt(st.nextToken());
                int d = Integer.parseInt(st.nextToken());

                left(n-1, -d);
                right(n+1, -d);
                rotate(n, d);

            }

            int ans = 0;

            for (int i = 1; i <= MAG; i++) {
                if (list[i].get(0) == 1) {
                    ans += (int)Math.pow(2, i-1);
                }
            }

            sf.append(ans + "\n");
            /**
             * 2, 6번 인덱스는 각각 다른 자석과 맞물린다.
             * 1번 자석 :  2번
             * 2번 자석 : 2,6번
             * 3번 자석 : 2,6번
             * 4번 자석 : 6번
             *
             * 시계 방향 회전 : 마지막 인덱스가 첫번째 인덱스로 이동
             * 반시계 :  첫번째 인덱스가 마지막 인덱스로 들어감
             */

        }
        System.out.println(sf.toString());
    }
    static void left(int n, int d) {
        if (!(1<= n && n <= MAG)) return;
        if (check(n,n+1)) {
            left(n-1, -d);
            rotate(n,d);
        }
    }

    static void right(int n, int d) {
        if (!(1<= n && n <= MAG)) return;
        if (check(n, n-1)) {
            right(n+1, -d);
            rotate(n,d);
        }
    }
    static void rotate(int n, int d) {
        if (d == 1) {
            list[n].addFirst(list[n].pollLast());
        }
        else {
            list[n].addLast(list[n].pollFirst());
        }
    }
    static boolean check(int a, int b) {

        if (a > b) {
            if (list[a].get(6) == list[b].get(2)) return false;
            else return true;
        }
        else {
            if (list[a].get(2) == list[b].get(6)) return false;
            else return true;
        }
    }
}




