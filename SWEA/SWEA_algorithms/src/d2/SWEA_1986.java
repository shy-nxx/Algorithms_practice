package d2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_1986 {
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		StringBuffer sf = new StringBuffer();
		
		int T = Integer.parseInt(st.nextToken());
		
		for (int k = 0; k < T; k++) {
			sf.append("#" + (k+1) + " ");
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			
			int result= 0;
			for (int i = 1; i <= N; i++) {
				if (i % 2 == 0) {
					result -= i;
				}
				else {
					result += i;
				}
			}
			sf.append(result + "\n");
		}

		System.out.println(sf.toString());
	}
}
