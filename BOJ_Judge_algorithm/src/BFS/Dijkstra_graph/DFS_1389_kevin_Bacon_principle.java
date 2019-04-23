package BFS.Dijkstra_graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class DFS_1389_kevin_Bacon_principle {
    /**
     * 케빈 베이컨의 6단계 법칙에 의하면 지구에 있는 모든 사람들은 최대 6단계 이내에서 서로 아는 사람으로 연결될 수 있다. 케빈 베이컨 게임은 임의의 두 사람이 최소 몇 단계 만에 이어질 수 있는지 계산하는 게임이다.
     * <p>
     * 예를 들면, 전혀 상관없을 것 같은 인하대학교의 이강호와 서강대학교의 민세희는 몇 단계만에 이어질 수 있을까?
     * <p>
     * 천민호는 이강호와 같은 학교에 다니는 사이이다. 천민호와 최백준은 Baekjoon Online Judge를 통해 알게 되었다. 최백준과 김선영은 같이 Startlink를 창업했다. 김선영과 김도현은 같은 학교 동아리 소속이다. 김도현과 민세희는 같은 학교에 다니는 사이로 서로 알고 있다. 즉, 이강호-천민호-최백준-김선영-김도현-민세희 와 같이 5단계만 거치면 된다.
     * <p>
     * 케빈 베이컨은 미국 헐리우드 영화배우들 끼리 케빈 베이컨 게임을 했을때 나오는 단계의 총 합이 가장 적은 사람이라고 한다.
     * <p>
     * 오늘은 Baekjoon Online Judge의 유저 중에서 케빈 베이컨의 수가 가장 작은 사람을 찾으려고 한다. 케빈 베이컨 수는 모든 사람과 케빈 베이컨 게임을 했을 때, 나오는 단계의 합이다.
     * <p>
     * 예를 들어, BOJ의 유저가 5명이고, 1과 3, 1과 4, 2와 3, 3과 4, 4와 5가 친구인 경우를 생각해보자.
     * <p>
     * 1은 2까지 3을 통해 2단계 만에, 3까지 1단계, 4까지 1단계, 5까지 4를 통해서 2단계 만에 알 수 있다. 따라서, 케빈 베이컨의 수는 2+1+1+2 = 6이다.
     * <p>
     * 2는 1까지 3을 통해서 2단계 만에, 3까지 1단계 만에, 4까지 3을 통해서 2단계 만에, 5까지 3과 4를 통해서 3단계 만에 알 수 있다. 따라서, 케빈 베이컨의 수는 2+1+2+3 = 8이다.
     * <p>
     * 3은 1까지 1단계, 2까지 1단계, 4까지 1단계, 5까지 4를 통해 2단계 만에 알 수 있다. 따라서, 케빈 베이컨의 수는 1+1+1+2 = 5이다.
     * <p>
     * 4는 1까지 1단계, 2까지 3을 통해 2단계, 3까지 1단계, 5까지 1단계 만에 알 수 있다. 4의 케빈 베이컨의 수는 1+2+1+1 = 5가 된다.
     * <p>
     * 마지막으로 5는 1까지 4를 통해 2단계, 2까지 4와 3을 통해 3단계, 3까지 4를 통해 2단계, 4까지 1단계 만에 알 수 있다. 5의 케빈 베이컨의 수는 2+3+2+1 = 8이다.
     * <p>
     * 5명의 유저 중에서 케빈 베이컨의 수가 가장 작은 사람은 3과 4이다.
     * <p>
     * BOJ 유저의 수와 친구 관계가 입력으로 주어졌을 때, 케빈 베이컨의 수가 가장 작은 사람을 구하는 프로그램을 작성하시오.
     */

    static int N, M;

    static int[][] link;
    static boolean[][] visited;

    static int[][] dp;

    static int min = Integer.MAX_VALUE;
    static int min_p = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); //유저의 수
        M = Integer.parseInt(st.nextToken()); //관계의수

        link = new int[N+1][N+1];

        for (int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            link[a][b] = link[b][a] = 1;

        }

        dp = new int[N+1][N+1];

        for (int i = 1; i <= N; i++) { //다익스트라 알고리즘
            Arrays.fill(dp[i], -1);
        }

        for (int i = N; i >=1; i-- ) { //관계의 수가 같으면 번호가 작은 사람을 출력해야한다.
//            visited = new boolean[N+1][N+1];
            bfs(i);
        }

        System.out.println(min_p);
    }
    static void bfs(int start) {
        Queue<Integer> q = new LinkedList<>();

        q.add(start);

        dp[start][start] = 0;

        int result = 0;
        while (!q.isEmpty()){
            int now = q.poll();

            for (int i = 1; i <= N; i++) {
                if (link[now][i] == 0 || dp[start][i] != -1) continue; //이미 처리한 간선은 처리하지 않고 넘어간다. (다익스트라 알고리즘 기본 설정)v

                dp[start][i] = dp[start][now] + 1;

//                visited[start][i] = true;

                q.add(i);

            }
        }

        for (int i = 1; i <= N; i++) {
            result += dp[start][i];
        }

        System.out.println(start + " " + result);

        if (min >= result) {
            min = result;
            min_p = start;


        }

    }


}