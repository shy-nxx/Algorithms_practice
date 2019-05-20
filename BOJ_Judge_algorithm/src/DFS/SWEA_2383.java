package DFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.*;

public class SWEA_2383 {
    static int N;
    static int[][] map = new int[11][11];
    static boolean[] visited = new boolean[11];
    static ArrayList<Person> list = new ArrayList<>();
    static Stair[] stairs = new Stair[2];

//    static PriorityQueue<OnStair> pq = new PriorityQueue<>();

    static int min = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        // TODO Auto-generated method stub
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int T = Integer.parseInt(st.nextToken());

        StringBuffer sf = new StringBuffer();

        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());

            map = new int[11][11];
            visited = new boolean[11];

            list.clear();
//            pq.clear();

            stairs = new Stair[2];

            min = Integer.MAX_VALUE;

            int flag = 0;
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());

                    if (map[i][j] == 1) {
                        list.add(new Person(i, j));
                    }

                    if (map[i][j] > 1) {
                        stairs[flag] = new Stair(i, j, map[i][j]);
                        // 한 계단에 있을 수 있는 사람의 수는 3 / 계단의 수는 2로고정
                        flag = 1;
                    }
                }
            }

            // 완전 탐색 -> 1번 계단을 선택해서 내려가는 경우의 시간과 2번 계단을 선택해서 내려가는 경우의 시간의최소값을 구해야 한다.
//            if (list.size()!= 1) {

//            }
//            else {
//                Arrays.fill(visited, true);
//                divide();
//            }

            divPerson(0, 0);
            divide();
            sf.append("#" + tc + " " + min + "\n");
        }
        System.out.println(sf.toString());
    }

    //사람들을 2팀으로 나눔 (스타트와 링크 참조)
    static void divPerson(int cur, int cnt) {
//        if (cnt == (list.size()/2 == 0 ? list.size() : list.size()/2)) {
//            divide();
//            return;
//        }

        if (cur == list.size()) {
            divide();
            return;
        }

        visited[cur] = true;
        divPerson(cur+1, cnt+1);
        visited[cur] = false;
        divPerson(cur+1, cnt+1);
//        for (int i = 0; i < list.size(); i++) {
//            if (!visited[i]) {
//                visited[i] = true;
//                divPerson(i, cnt+1);
//
//            }
//        }
//
//        visited[cur] = false;
    }

    static void divide() {
        PriorityQueue<OnStair> pq = new PriorityQueue<>(new Comparator<OnStair>() {
            @Override
            public int compare(OnStair o1, OnStair o2) {
                if (o1.time > o2.time) return 1;
                else if (o1.time == o2.time) {
                    if (o1.dist > o2.dist) return 1;
                    else if (o1.dist == o2.dist) return 0;
                    else return -1;
                }
                else return -1;

            }
        });

        for (int i = 0; i < list.size(); i++) {
            int distance = 0;


            if (visited[i]) {
//                System.out.println("stair0 = " + i);
                distance = range(list.get(i), stairs[0]);
                pq.add(new OnStair(0, 0, -1, 0, distance));

            }
            else {
//                System.out.println("stair1 = " + i);
                distance = range(list.get(i), stairs[1]);
                pq.add(new OnStair(0, 1, -1, 0, distance));

            }
        }


        //계단을 내려오는 경우의 수를 모두 구한다.
        calcTime(pq);

    }

    static void calcTime(PriorityQueue<OnStair> pq) {
        int time = 0;

        int[] downPersoncnt = new int[2]; //내려가는 사람 카운트

        while(!pq.isEmpty()) {
            OnStair now = pq.poll();

            time = now.time;

            // 해당 계단에 아직 도달 안했다면 다시 넣어주고 다음꺼.(계단에 도달후 한 번 대기까지)
            if (now.dist >= time) {
                pq.add(new OnStair(now.time+1, now.stair, now.status, now.wait, now.dist));
                continue;
            }

            // 계단 내려가는 중
            if (now.wait != 0) {
                //다 내려옴
                if (now.wait == stairs[now.stair].height) {
                    downPersoncnt[now.stair]--;
                    continue;
                }
                pq.add(new OnStair(now.time+1, now.stair, 1,now.wait+1, now.dist));
                continue;
            }

            // 해당 계단이 꽉차있다면 대기-> 다시 큐에 넣음
            if (downPersoncnt[now.stair] == 3) {
                pq.add(new OnStair(now.time+1, now.stair, 0, now.wait, now.dist));
            } else {
                downPersoncnt[now.stair]++;
                pq.add(new OnStair(now.time+1, now.stair, 1, now.wait+1, now.dist));
            }
        }
        min = Math.min(min, time);
    }


    static int range(Person p, Stair s) {
        return Math.abs(p.x - s.x) + Math.abs(p.y - s.y);
    }

    static class Person {
        int x, y;

        public Person(int x, int y) {
            super();
            this.x = x;
            this.y = y;
        }

    }

    static class Stair {
        int x, y;
        int height; // 모두 내려가는 데 K분 k == height

        public Stair(int x, int y, int height) {
            super();
            this.x = x;
            this.y = y;
            this.height = height;
        }

    }
    static class OnStair {
        int time;
        int stair;
        int status;
        int wait;
        int dist;

        public OnStair(int time, int stair, int status, int wait, int dist) {
            this.time = time;
            this.stair = stair;
            this.status = status;
            this.wait = wait;
            this.dist = dist;
        }

    }
}

