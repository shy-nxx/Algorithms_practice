import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_7393_sol {
	static int N, VISIT = 1 << 10;//1024
	static int MOD = 1000000000;
	static long[][][] dp = new long[101][10][VISIT];
	static boolean[] visited = new boolean[10];
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int T = Integer.parseInt(st.nextToken());
		
		StringBuffer sf = new StringBuffer();
		
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			
			dp = new long[101][10][VISIT];
			
			sf.append("#" + tc + " " + solve() + "\n");
			
		}
		System.out.println(sf.toString());
	}
	
	static long solve() {
		long sum = 0;
		
		int bit;
		
		for (int j = 1; j < 10; j++) { 
			dp[1][j][1<<j] = 1;
		}
		
		for (int i = 2; i <= N; i++) {
			for (int j =0; j <=9; j++) {
				for (int k = 0; k < VISIT; k++) { //0~1023까지 
					bit = k | 1<<j;
					
					if (j > 0) {
						dp[i][j][bit] += dp[i-1][j-1][k] % MOD;
						
					}
					if (j < 9) {
						dp[i][j][bit] += dp[i-1][j+1][k] % MOD;
					}
				}
			}
		}
		for (int i = 0; i <= 9; i++ ) {
			sum = (sum + dp[N][i][VISIT-1]) % MOD;
		}
		return sum;
	}
	
}
