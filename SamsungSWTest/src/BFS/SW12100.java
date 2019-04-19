package BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class SW12100 {
    /**
     * 이 게임에는 뱀이 나와서 기어다니는데, 사과를 먹으면 뱀 길이가 늘어난다. 뱀이 이리저리 기어다니다가 벽 또는 자기자신의 몸과 부딪히면 게임이 끝난다.
     * 게임은 NxN 정사각 보드위에서 진행되고, 몇몇 칸에는 사과가 놓여져 있다. 보드의 상하좌우 끝에 벽이 있다.
     * 게임이 시작할때 뱀은 맨위 맨좌측에 위치하고 뱀의 길이는 1 이다. 뱀은 처음에 오른쪽을 향한다.
     */

    /**
     * 고려해야 할 점 -> 뱀의 길이가 늘어나면 올길 때 시간이 필요 (그럼 회전할 때 몸이 부딪힐 가능성이 높다.)
     * 뱀의 꼬리가 이전 칸에 남아있어야 함 (사과가 있으면)
     */

    /**
     * 뱀이 사과를 먹지 않으면 꼬리부터 없어지기 때문에 FIFO방식의 큐를 이용해 뱀의 몸을 저장한다.
     * 뱀은 머리를 움직일 때 마다 머리가 있는 위치를 큐에 넣어주고, 뱀의 머리가 있는 장소에 사과가 없다면 큐에서 꼬리를 꺼내준다.
     * 또한 뱀의 몸 어느 곳이라도 머리가 부딪히면 게임이 끝나도록 하기 위해 큐에 넣을 때에는 해당 공간을 벽과 동일한 2로 변경해준다.
     * 꼬리를 poll() 할 때 역시 빈칸이 되도록 0으로 변경해준다.
     */
    static int[][] matrix;
    static int[] dx = {-1,0,1,0};
    static int[] dy = {0,1,0,-1};
    static int total_sec = 0;
    static char[] info;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        matrix = new int[N+2][N+2]; //벽까지 계산하면 N+2

        //벽세팅
        for(int i=0; i<N+2; i++)
            for(int j=0; j<N+2; j++)
                if(i==0 || j==0 || i==N+1 || j==N+1)
                    matrix[i][j] = 2; // 보드 (0-빈칸, 1-사과, 2-벽)

        st = new StringTokenizer(br.readLine());
        int K = Integer.parseInt(st.nextToken()); //사과의 개수

        //사과위치 세팅
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            matrix[x][y] = 1;
        }

        st = new StringTokenizer(br.readLine());
        int L = Integer.parseInt(st.nextToken()); //뱀위 방향 전환 회수

        info = new char[1000001];

        //명령어 저
        for (int i = 0; i < L; i++) {
            st = new StringTokenizer(br.readLine());

            int seconds = Integer.parseInt(st.nextToken());
            char direction = st.nextToken().charAt(0);
//            System.out.println(seconds + " " + direction);

            info[seconds] = direction;
        }

        BFS(1,1);
    }

    static void BFS(int x, int y) {
        Queue<Position> q = new LinkedList<>();

        int dir = 1; //이동방향 북(↑) 동(→) 남(↓) 서(←)를 0 1 2 3

        Position cP = new Position(x, y);

        q.add(cP);

        matrix[cP.x][cP.y] = 2; //뱀의 몸 어느 곳이라도 머리가 부딪히면 게임이 끝나도록 하기 위해 큐에 넣을 때에는 해당 공간을 벽과 동일한 2로 변경해준다. 꼬리를 poll() 할 때 역시 빈칸이 되도록 0으로 변경해준다.

        while(true) {
            ++total_sec;

            cP.x += dx[dir];
            cP.y += dy[dir]; //동쪽 방향 (1)

            // 벽이나 뱀의 몸을 만났을 경우 게임 종료
            // 보드 (0-빈칸, 1-사과, 2-벽)
            if(matrix[cP.x][cP.y] == 2 ){
                break;
            }

            // 빈칸인 경우 마지막 칸 꼬리를 비워줌
            // 보드 (0-빈칸, 1-사과, 2-벽)
            if(matrix[cP.x][cP.y] == 0){
                Position retail = q.poll();
                matrix[retail.x][retail.y] = 0;
            }

            // 머리를 큐에 넣고 맵의 변수 변경
            q.add(cP);
            matrix[cP.x][cP.y] = 2;

            // 방향전환
            if(info[total_sec] == 'D' || info[total_sec] == 'L')
                dir = changeDir(dir, info[total_sec]);
        }

        System.out.println(total_sec);
    }

    //방향 바꾸기 북(↑) 동(→) 남(↓) 서(←)를 0 1 2 3
    //오른쪽으로 90도 꺾어져 이동할 때에는 1씩 증가하고, 왼쪽으로 꺾어질 때에는 1씩 감소하는 규칙을 이용한다.
    private static int changeDir (int cur_dir, char instruct) {
        int next_dir;
        if (instruct == 'D')
            next_dir = (cur_dir == 3) ? 0 : ++cur_dir;
        else
            next_dir = (cur_dir == 0) ? 3 : --cur_dir;

        return next_dir;
    }


    private static class Position {
        int x;
        int y;

        public Position(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    static class Direction {
        int seconds;
        char direction;

        public Direction(int seconds, char direction) {
            this.seconds = seconds;
            this.direction = direction;

        }
    }
}
