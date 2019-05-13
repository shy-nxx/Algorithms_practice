package d2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_1946 {
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int T = Integer.parseInt(st.nextToken());

		StringBuffer sf = new StringBuffer();

		for (int k = 0; k < T; k++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			
			String[] ch = new String[N];
			int[] K = new int[N];
			
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				ch[i] = st.nextToken();
				K[i] = Integer.parseInt(st.nextToken());
			}
			
			String s = "";
			
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < K[i]; j++) {
					s += ch[i];
				}
			}
			
			int index = 0;
			System.out.println("#" + (k+1) + " ");
			while(index != s.length()) {
				if ((s.length() - index) / 10 >= 1) {
					System.out.print(s.substring(index, index+10));
					index += 10;
				}
					
				else {
					System.out.print(s.substring(index, s.length()));
					index = s.length();
				}
				System.out.println();
			}
			
		}
	}
}
