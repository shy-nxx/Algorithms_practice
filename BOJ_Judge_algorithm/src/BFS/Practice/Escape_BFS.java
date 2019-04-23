package BFS.Practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Escape_BFS {
    /**
     * 물과 고슴도치가 동시에 움직인다 -> queue가 두개 생성해서 각각 범위를 넓혀나간다.
     */

    static int R, C;
    static char[][] map;

    static int[] dx = {0,0,-1,1};
    static int[] dy = {-1,1,0,0};

    static Queue<Point> animalQ = new LinkedList<>();
    static Queue<Point> waterQ = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new char[R][C];

        for (int i = 0; i < R; i++) {
            String s = br.readLine();
            for (int j = 0; j < C; j++) {
                map[i][j] = s.charAt(j);

                if (map[i][j] == '*') {
                    waterQ.add(new Point(i, j));
                }

                if (map[i][j] == 'S') {
                    animalQ.add(new Point(i, j));
                }
            }
        }

        int result = 0;

        while(true) {
            ++result;

            if (animalQ.size() == 0) {
                System.out.println("KAKTUS");
                return;
            }

            extendWaterQ();

            if (extendAnimalQ()) {
                System.out.println(result);
                return;
            }
        }
    }

    static void extendWaterQ() {

        int n = waterQ.size();

        for (int i = 0; i < n; i++) {
            Point p = waterQ.poll();

            for (int j = 0; j <4; j++ ) {
                int nx = dx[j] + p.x;
                int ny = dy[j] + p.y;

                if (nx < 0 || nx >= R || ny < 0 || ny >= C) continue;

                if (map[nx][ny] == '.') {
                    map[nx][ny] = '*';
                    waterQ.add(new Point(nx, ny));
                }
            }
        }
    }

    static boolean extendAnimalQ() {
        int n = animalQ.size();

        for (int i = 0; i < n; i++) {
            Point p = animalQ.poll();

            for (int j = 0; j <4; j++ ) {
                int nx = dx[j] + p.x;
                int ny = dy[j] + p.y;

                if (nx < 0 || nx >= R || ny < 0 || ny >= C) continue;

                if (map[nx][ny] == 'D') {
                    animalQ.add(new Point(nx, ny));
                    return true;
                }
                if (map[nx][ny] == '.') {
                    map[nx][ny] = 'S';
                    animalQ.add(new Point(nx, ny));
                }
            }
        }
        return false;
    }

    static class Point {
        int x, y;

        public Point (int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}