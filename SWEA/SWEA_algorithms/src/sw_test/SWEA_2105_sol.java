package sw_test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_2105_sol {
	static int max = 0;
	static int N;

	static int[][] map;
	static boolean[][] visited;
	static boolean[] check;

	static int sx, sy;

	static int[] dx = { 1, 1, -1, -1 };
	static int[] dy = { 1, -1, -1, 1 };

	static StringBuffer sf = new StringBuffer();

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int T = Integer.parseInt(st.nextToken());

		for (int k = 0; k < T; k++) {
			max = 0;
			sf.append("#" + (k + 1) + " ");
			st = new StringTokenizer(br.readLine());

			N = Integer.parseInt(st.nextToken());

			map = new int[N][N];

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			visited = new boolean[N][N];
			check = new boolean[101];

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					sx = i;
					sy = j;
					visited[i][j] = true;
					check[map[i][j]] = true;
					dfs(i, j, 0, 0);
					visited[i][j] = false;
					check[map[i][j]] = false; // 백트래킹
				}
			}
			if (max == 0) {
				max = -1;
			}
			sf.append(max + "\n");
		}
		System.out.println(sf.toString());
	}

	static void dfs(int x, int y, int dir, int count) {
		if (dir == 4) { // 사각형은 4번 회전하면 사이클 형성됨
			if (x == sx && y == sy && count > 1) {
				max = Math.max(max, count);
			}

			return;
		}

		for (int i = 0; i < 2; i++) { // 나아가는 방향은 시계 방향과 반시계 방향밖에 없음
			int nx = x + dx[dir];
			int ny = y + dy[dir];

			if (nx >= 0 && nx < N && ny >= 0 && ny < N && !visited[nx][ny] && !check[map[nx][ny]]) {
				visited[nx][ny] = true;
				check[map[nx][ny]] = true;
				dfs(nx, ny, dir + i, count + 1);
				visited[nx][ny] = false;
				check[map[nx][ny]] = false; // 백트래킹
			} else if (nx == sx && ny == sy) { // 바로 다시 되돌아간 경우
				dfs(nx, ny, dir + i, count + 1);
			}
		}
	}

}
