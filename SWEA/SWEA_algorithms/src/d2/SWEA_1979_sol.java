package d2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_1979_sol {
	static int[] dx = {0,1,0,-1};
	static int[] dy = {1,0,-1,0};

	static int[][] map;
	static boolean[][] visited;
	static int N, K;
	static int ans = 0;

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int T =  Integer.parseInt(st.nextToken());

		StringBuffer sf = new StringBuffer();
		for (int k=0; k < T; k++) {
			sf.append("#" + (k+1) + " ");
			st = new StringTokenizer(br.readLine());

			N =  Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken()); //단어의 길이 

			map = new int[N][N];

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken()); //1은 흰색 / 0은 검은색 
				}
			}
			
			ans = 0;
			/**
			 * 가로 세로 길이가 K이면 단어가 들어갈 수 있다. 
			 */
	
			//가로 길이 세어보기 
			
			for (int i = 0; i < N; i++) {
				int j = 0;
				int count = 0;
				
				while(true) {
					if (map[i][j] != 1) { //0을 만났을 때 길이가 3으로 떨어지면 이전까지 칸이 길이가 3이라는뜻 
						if (count == K) ans++;
						count = 0;
						j++;
					}
					else { //1일 경우 
						count++;
						j++;
					}
					if (j == N) { //끝에 다다랐을 때 -> while문을 나가야 함 
						if (count == K) ans++;
						break;
					}
				}
			}

			//세로 길이 세어보기 
			
			for (int i = 0; i < N; i++) {
				int j = 0;
				int count = 0;
				
				while(true) {
					if (map[j][i] != 1) { //0을 만났을 때 길이가 3으로 떨어지면 이전까지 칸이 길이가 3이라는뜻 
						if (count == K) ans++;
						count = 0;
						j++;
					}
					else { //1일 경우 
						count++;
						j++;
					}
					if (j == N) { //끝에 다다랐을 때 -> while문을 나가야 함 
						if (count == K) ans++;
						break;
					}
				}
			}
			
			sf.append(ans + "\n");
			
			
		}
		System.out.println(sf.toString());
	}
	
}
