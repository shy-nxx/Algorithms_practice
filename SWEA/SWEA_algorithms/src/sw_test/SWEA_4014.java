package sw_test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_4014 {
	static int N, X;
	static int[][] map;
	static boolean[][] visited;
	
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	
	static int MAX = 0;
	
	static int[] height;
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int T = Integer.parseInt(st.nextToken());

		StringBuffer sf = new StringBuffer();

		for (int k = 0; k < T; k++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			X = Integer.parseInt(st.nextToken());
			
			map = new int[N*2][N];
			visited = new boolean[N][N];
			
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			for (int i = N; i < N*2; i++) {
				for (int j = 0; j < N; j++) {
					map[i][j] = map[j][i-N]; //가로 맵  + 세로맵 
				}
			}
			
			
			int count = N*2; //일단 모든 경로에 활주로가 가능하다고 가정함 
			
			for (int i = 0; i< N*2; i++) {

				int target = map[i][0]; //가로부터 시작 
				height = new int[7]; //지형의 높이는 1 이상 6 이하의 정수이다.
				height[target] = 1;
				
				int j = 1;
				while(j < N) {
					int next = map[i][j];
					
					if (!validHeight(target, next)) { //높이의 차이가 1 이상인 경우에는 활주로 건설 안됨 (경사로 건설도 안됨 -> count 제거)
						--count;
						break;
					}
					
					//높은 칸을 만났을 경우 -> 지금까지 걸어온 길을 돌아보면서 경사로 배치 가능한지 판단 
					//낮은 칸을 만났을 경우 -> 낮은 칸부터 지나가면서 경사로 배치 가능한지 확인 
					
					if (target != next) { //평지가 아닌데 높이의 차가 1도 아닌 경우 -> 경사로 배치 해야함 
						if (target < next ) {
							//높은 칸을 만났을 경우 
							if (!high(target, next)) {
								--count;
								break;
							}
						}
						else {
							if (!low(i, j, target, next)) {
								--count;
								break;
							}
							j+= X+1; //낮은 칸이라면, L만큼 지나가면서 경사로를 배치할 수 있는 지 연속 유무를 확인한다.
						}
						target = next;
					}
					else {
						 // 같은 높이의 칸을 뜻하기 때문에, L의 길이를 갖는 경사로 배치 판단을 위한 카운터 증가
						height[target]++;
					}
					j++;
				}
				
				
			}
			sf.append("#"+(k+1) + " " + count);
		}

		System.out.println(sf.toString());
	}
	static boolean validHeight(int target, int next) {
		if (Math.abs(target - next) > 1) {
			return false;
		}
		return true;
	}
	static boolean high(int target, int next) {
		if (height[target] < X) { 
			return false;
		}
		height[target] = 0;
		height[next] = 1;
		
		return true;
	}
	
	static boolean low(int x, int y, int target, int next) {
		for (int i = 0; i < X; i++) {
			if (y + i == N) break;
			if (map[x][y+i] == next) height[next]++;
		}
		
		if (height[next] < X) return false;
		height[next] -= X;
		return true;
	}
}
