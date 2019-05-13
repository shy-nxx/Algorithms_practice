package BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class SWEA_5653_sol {
    static int N, M, K;

    static int[][] cells;

    static int answer = 0;

    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};

    static Queue<Cell> q = new LinkedList<>();

    static int time = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int T = Integer.parseInt(st.nextToken());

        StringBuffer sf= new StringBuffer();

        for (int z = 0; z < T; z++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());

            cells = new int[350][350];
            q = new LinkedList<>();

            time = 0;

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < M; j++) {
                    int life = Integer.parseInt(st.nextToken());
                    if (life > 0) {
                        cells[i+150][j+150] = 1; //세포가 존재함을 표시한다.
                        Cell c = new Cell(i+150, j+150, life, 0, 0, 0);
                        q.add(c);
                    }
                }
            }

            bfs();

            answer = 0;
            int temp = 0;

            for (Cell cell : q) {
                if (cell.age >= 0)
                 answer++;
                else {
                    temp++;
                }
            }
            System.out.println(temp);
            sf.append("#" + (z+1) + " " + answer + "\n" );

        }
        System.out.println(sf.toString());

    }
    static void bfs() {
        while(time < K) {
            while(true) {
                if (q.peek().time == time) { //비활성화 된 것들은 활성화 하고 이미 활성화인 것들은 증식한다.
                    Cell now = q.poll();
                    if (now.status == 0) { //아직 비활성화인 경우 -> 활성화 할 시간
                        now.time++;
                        now.age++;
                        if (now.age == now.life) {
                            now.status = 1; //활성화한다.
                        }
                        q.add(now);
                    } else if (now.status == 1) { //이미 활성화된 상태면 time 이 2배가 되면 죽는다.
                        now.time++;
                        now.age++;
                        if (now.life * 2 != now.age) { // 사망 진단
                            q.add(now);
                        }
                    }
                }
                else break;
            }


            LinkedList<Cell> list = new LinkedList<>(); //번식한 세포들을 저장한다.-> 1시간 한정이기 때문에 매번 새로 만들어줘도 된다.

            for (Cell c : q) {
                if (c.status == 1) { //활성화 된 세포의 번식을 한다. -> 1시간 동안!
                    int x = c.x;
                    int y = c.y;

                    for (int i = 0; i < 4; i++) {
                        int nx = x + dx[i];
                        int ny = y + dy[i];

                        if (nx < 0 || nx >= cells.length || ny < 0 || ny >= cells.length) continue;

                        if (cells[nx][ny] == 0) { //세포가 없는 상태 -> 세포를 그냥 번식시킨다.
                            list.add(new Cell(nx, ny, c.life, 0, c.time, -1));
                            cells[nx][ny] = 2; //번식된 세포임을 표시헌다.
                        }

                        else if (cells[nx][ny] == 2) { //번식된 또 다른 세포를 만났을 때-> 같은 자리에 번식됐을 때
                            for (Cell p : list){
                                if (nx == p.x && ny == p.y && p.life < c.life) {
                                    list.add(new Cell(nx, ny, c.life, 0, c.time, -1));
                                    list.remove(p);
                                    break; // 더 이상 확인할 필요 없다.
                                }
                            }
                        }
                    }
                }
            }
            for (Cell temp : list) {
                cells[temp.x][temp.y] = 1; //세포가 존재함
                q.add(temp);

            }

            time++;
        }
    }
    static class Cell {
        int x,y;
        int life; //입력 받은 생명력 시간
        int status; //활성화 비활성화 비교
        int time; //지나간 시간을 저장 (새로 증식된 세포와 구별할 수 있다.)
        int age;

        public Cell(int x, int y, int life, int status, int time, int age) {
            this.x = x;
            this.y = y;
            this.life = life;
            this.status = status;
            this.time = time;
            this.age = age;
        }

        public Cell(int x, int y, int life, int status, int time) {
            this.x = x;
            this.y = y;
            this.life = life;
            this.status = status;
            this.time = time;
        }
    }
}
