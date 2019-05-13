package brute_force;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Brute_1065_sol {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		StringBuffer sf = new StringBuffer();
		
		int N = Integer.parseInt(st.nextToken());
		
		int cnt = 0;
		
		//100이상부터면 판단 가능 
		if (N < 100 ) {
			System.out.println(N);
			return;
		}
		else  {
			cnt = 99;
			
			for (int i = 100; i <= N; i++) {
				int num1 = i / 100;
				int num2 = (i / 10) % 10;
				int num3 = i % 10;
				
				if ((num1 - num2) == (num2 - num3)) {
					cnt++;
				}
			}
			System.out.println(cnt);
		}
	}
}
