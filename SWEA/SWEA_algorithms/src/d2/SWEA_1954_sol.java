package d2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_1954_sol {
	static int[] dx = {0,1,0,-1};
	static int[] dy = {1,0,-1,0};

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int T = Integer.parseInt(st.nextToken());

		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken()); //배열의 크기 

			int[][] map = new int[N][N];

			int dir = 0; //0-> 우 / 1 -> 하 / 2-> 좌 / 3-> 상 
			int x = 0;
			int y = 0;
			
			for (int i = 0; i < N*N; i++) {
				map[x][y] = i+1;
				x += dx[dir];
				y += dy[dir];

				if (x < 0 || x >= N || y < 0 || y >= N || map[x][y] != 0) { //막다른 길로 가면 다른 방향으로 꺾음 
					x -= dx[dir];
					y -= dy[dir];
					//원래 자리로 되돌려 준다. 
					dir =(dir+1) % 4;
					
					x += dx[dir];
					y += dy[dir];
				}

			}
			
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					System.out.print(map[i][j] + " ");
				}
				System.out.println();
			}

		}
	}
}
