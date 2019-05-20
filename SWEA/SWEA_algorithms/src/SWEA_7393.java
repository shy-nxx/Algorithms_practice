import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_7393 {

	//시간복잡도(N*10*2^10)
	static int N, visited = 1 << 10;//1024
	static int MOD = 1000000000;
	static long[][][] dp = new long[101][10][visited];
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int T = Integer.parseInt(st.nextToken());
		
		StringBuffer sf = new StringBuffer();
		
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			
			dp = new long[101][10][visited];
			
			System.out.println(solve());
			
		}
	}
	static long solve() {
		long sum = 0;
		
		int status;
		
		for (int i = 1; i < 10; i++ ) dp[1][i][1<<i] = 1; //1<<i -> i만큼 왼쪽으로 민다. 
		
		for (int i = 2; i <= N; i++) {
			for (int j = 0; j <= 9; j++) {
				for (int k = 0; k < visited; k++) {
					status = k | (1<<j); //-> 둘이 같은 값이면 0이 되고 아니면 1이된다. 즉, 1100 / 1001 == 1010 // 즉 j번째 숫자로 끝나는 값이 쓰였으면 해당 값에 1을 넣는다. -> 이미 쓰였음을 표시 
					
					if (j > 0) {
						dp[i][j][status] += dp[i-1][j-1][k] % MOD; //i-1번째 자리수에서 j-1로 끝나는 숫자 중 k상태인 수가 있다면 더해줌 
					}
					if (j < 9) {
						dp[i][j][status] += dp[i-1][j+1][k] % MOD; // i-1번째 자리수에서 j+1로 끝나는 숫자 중 k상태인 수가 있다면 더해줌 
					}
				}
			}
		}
		
		for (int i = 0; i < 10; i++) {
			sum = (sum + dp[N][i][visited-1]) % MOD;
		}
		return sum;
	}
	
	
}
