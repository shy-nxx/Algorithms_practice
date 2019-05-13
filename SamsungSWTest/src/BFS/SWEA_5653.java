package BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class SWEA_5653 {
    /**
     * 입력으로 주어지는 K의 최대값이 300이므로
     *
     * 세포의 생명력이 모두 1이라고 가정했을 때 상하좌우 최대 150개씩 번식해 나갈 수 있다.
     *
     * 증식된 세포의 비활성화/ 활성화 시간과 기존의 세포의 활성화 및 비활성화 시간이 다르다.  -> 죽는 시간도 다르다.
     * 같은 좌표에 세포가 생길 경우 더 생명력이 높은 세포를 우선시 한다. -> 후순위가 된 세포는 없앤다.
     *
     */
    static int N, M, K;

    static int[][] cells;

    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};

    static int answer = 0;

    static Queue<Cell> q = new LinkedList<>();

    static int time = 0;

    public static void main(String[] args) throws IOException {
        // TODO Auto-generated method stub
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int T = Integer.parseInt(st.nextToken());

        for (int k = 0; k < T; k++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken()); //배양 시간 -> K시간 후 살아있는 줄기 세포(비활성 상태 + 활성 상태)의 총 개수를 구하는 프로그램

            cells = new int[350][350];

            q = new LinkedList<>();

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < M; j++) {
                    int life = Integer.parseInt(st.nextToken());
                    if (life > 0) {
                        cells[i+150][j+150] = 1; //세포 존재함
                        q.add(new Cell(i+150, j+150, life, 0,0,0 ));
                    }
                }
            }

            bfs();

            answer = 0;
            for (Cell c : q) {
                if (c.age >= 0) {
                    answer++;
                }
            }
            System.out.println(answer);
        }
    }
    static void bfs() {
        while(time < K) {

            while (true) {
                if (q.peek().time == time) { //증식 및 비활성화 될 시간
                    Cell now = q.poll();
                    if (now.status == 0) { //아직 활성화 안됨 -> 활성화 하도록
                        now.time++;
                        now.age++;
                        if (now.life == now.age) {//활성화시키기
                            now.status = 1;
                        }
                        q.add(now);
                    }
                    else if (now.status == 1) { //이미 활성화-> 죽이기
                        now.time++;
                        now.age++;
                        //사망 구분하기
                        if (now.life * 2 != now.age) {
                            q.add(now); // 2배가 될 때까지는 사망 아님
                        }
                    }
                } else break;

            }
            //세포 증식
            LinkedList<Cell> prolifer_cell = new LinkedList<>();

            for (Cell now : q) {
                if (now.status == 1) {
                    int x = now.x;
                    int y = now.y;
                    int status = now.status;

                    for (int j = 0; j < 4; j++) {
                        int nx = x + dx[j];
                        int ny = y + dy[j];

                        if (nx < 0 || nx >= 350 || ny < 0 || ny >= 350) continue;

                        if (cells[nx][ny] == 0)  {
                            prolifer_cell.add(new Cell(nx, ny, cells[x][y], 0, now.time,-1));
                            cells[nx][ny] = 2; //증식된 세포임을 표시
                        }

                        else if (cells[nx][ny] == 2) {
                            //같은 좌표에 있는 증식된 세포들
                            for (Cell c : prolifer_cell) {
                                if (c.x == nx && c.y == ny && c.life < now.life) { //생명력이 더 높은 아이를 먼저 함
                                    prolifer_cell.add(new Cell(nx, ny, now.life, 0, now.time, -1));
                                    prolifer_cell.remove(c);
                                    break;
                                }
                            }
                        }
                    }
                }

            }
            for (Cell c : prolifer_cell) {
                q.add(c);
                cells[c.x][c.y] = 1;
            }
            time++;

        }
    }
    static class Cell {
        int x, y;
        int life;
        int status;
        int time;
        int age;

        public Cell(int x, int y, int life, int status, int time, int age) {
            this.x = x;
            this.y = y;
            this.life = life;
            this.status = status;
            this.time = time;
            this.age = age;
        }
    }
}
