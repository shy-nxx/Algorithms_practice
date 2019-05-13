package d5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class SWEA_4411 {
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int T = Integer.parseInt(st.nextToken());

		StringBuffer sf = new StringBuffer();
		
		for (int k = 0; k < T; k++) {
			st = new StringTokenizer(br.readLine());
			long N = Long.parseLong(st.nextToken());
			long K = Long.parseLong(st.nextToken());
			
			long result = 0;
			
			for (int i =1; i <= N; i++ ) {
				result = (result + K) % i + 1;
			}
			sf.append("#" + (k+1) + " " + result + "\n") ;
			
		}
		System.out.println(sf.toString());
	}
}
