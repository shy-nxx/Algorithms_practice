package d2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_1959 {
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int T = Integer.parseInt(st.nextToken());

		StringBuffer sf = new StringBuffer();

		for (int k = 0; k < T; k++) {

			st = new StringTokenizer(br.readLine());

			int N = Integer.parseInt(st.nextToken());

			int M = Integer.parseInt(st.nextToken());

			int[] A = new int[N];
			int[] B = new int[M];

			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				A[i] = Integer.parseInt(st.nextToken());
			}

			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < M; i++) {
				B[i] = Integer.parseInt(st.nextToken());
			}

			int max = 0;

			if (N < M) {
				for (int j = 0; j <= M-N; j++) {
					int result = 0;
					for (int i = 0; i< N; i++) {
						result += A[i] * B[i + j];
					}
					max = Math.max(result, max);
				}
			}
			else if (N > M) {
				
				for (int j = 0; j < N-M; j++) {
					int result = 0;
					for (int i = 0; i< M; i++) {
						result += A[i+j] * B[i];
					}
					max = Math.max(result, max);
				}
			}
			else {
				int result = 0;

				for (int i = 0; i< M; i++) {
					result += A[i] * B[i];
				}
				
				max = Math.max(result, max);

			}
			
			sf.append("#" + (k+1) + " " + max + "\n");
		}
		System.out.println(sf.toString());
		
	}
}
