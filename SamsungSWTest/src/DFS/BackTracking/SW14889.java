package DFS.BackTracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class SW14889 {
    /**
     * 예를 들어, 1, 2번이 스타트 팀, 3, 4번이 링크 팀에 속한 경우에 두 팀의 능력치는 아래와 같다.
     *
     * 스타트 팀: S12 + S21 = 1 + 4 = 5
     * 링크 팀: S34 + S43 = 2 + 5 = 7
     * 1, 3번이 스타트 팀, 2, 4번이 링크 팀에 속하면, 두 팀의 능력치는 아래와 같다.
     *
     * 스타트 팀: S13 + S31 = 2 + 7 = 9
     * 링크 팀: S24 + S42 = 6 + 4 = 10
     * 축구를 재미있게 하기 위해서 스타트 팀의 능력치와 링크 팀의 능력치의 차이를 최소로 하려고 한다.
     * 위의 예제와 같은 경우에는 1, 4번이 스타트 팀, 2, 3번 팀이 링크 팀에 속하면 스타트 팀의 능력치는 6, 링크 팀의 능력치는 6이 되어서 차이가 0이 되고 이 값이 최소이다.
     *
     * 예시
     * 4
     * 0 1 2 3
     * 4 0 5 6
     * 7 1 0 2
     * 3 4 5 0
     *
     * N명을 두 팀으로 나누는 모든 경우를 탐색해야하는 완전 탐색 문제가 된다.
     * DFS를 통해 두 팀을 나누는 경우를 구한다.
     * 구해진 경우에서의 두 팀을 배열로 나타낸다.
     * 두 팀의 능력 차이를 구한다.
     *
     *
     */
    static int N;
    static int[][] matrix;
    static boolean[] visited;
    static int min = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());

        matrix = new int[N+10][N+10];
        visited = new boolean[N+10];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++)
                matrix[i][j] = Integer.parseInt(st.nextToken());
        }

        DFS(0,0);

        System.out.println(min);
    }

    //dfs 백트래킹 - DFS를 통해 두 팀을 나누는 경우를 구한다.
    public static void DFS(int v, int len) {
        if (N/2 == len) { //팀나누기
            divideTeam();
        } else {
            for (int i = v + 1; i <= N; i++) {
                if (!visited[i]) {
                    visited[i] = true;
                    DFS(i, len+1);
                }
            }

        }
        visited[v] = false;

    }

    //구해진 경우에서의 두 팀을 배열로 나타낸다.
    public static void divideTeam(){
        int[] a = new int[N/2+1];
        int[] b = new int[N/2+1];

        int ai = 1, bi = 1;

        for (int i = 1; i <= N; i++) {
            if (visited[i]) {
                a[ai++] = i;
            }
            else {
                b[bi++] = i;
            }
        }

        for (int i = 1; i <= N/2; i++) {
            System.out.print(a[i] + " ");
        }
        System.out.println();
        for (int i = 1; i <= N/2; i++) {
            System.out.print(b[i] + " ");
        }
        System.out.println();

        int aStat = getState(a);
        int bStat = getState(b);
        int diff = Math.abs(aStat - bStat);

        if (min > diff)
            min = diff;

        System.out.println(aStat + " " + bStat + " " + diff);
    }

    static int getState(int[] team) {
        int result = 0;
        int len = N/2;

        for (int i = 1; i <= len; i++) {
            for (int j = 1; j <= len; j++) {
                result += matrix[team[i]][team[j]];
                result += matrix[team[j]][team[i]];
            }
        }

        return result;
    }
}