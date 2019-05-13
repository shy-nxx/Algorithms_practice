package string;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class String_5218 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		StringBuffer sb = new StringBuffer();
		
		int N = Integer.parseInt(st.nextToken());
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			String s1 = st.nextToken();
			String s2 = st.nextToken();
			
			sb.append("Distances: ");
			
			for (int j = 0; j < s1.length(); j++) {
//				System.out.println((s1.charAt(j) - 'A') + " " + (s2.charAt(j) - 'A'));
				if ((s1.charAt(j) - 'A') > (s2.charAt(j) - 'A')) {
					int result = (s2.charAt(j) - 'A') + 26 - (s1.charAt(j) - 'A');
					sb.append(result + " ");
				}
				else {
					int result = (s2.charAt(j) - 'A') - (s1.charAt(j) - 'A');	
					sb.append(result + " ");
				}
			}
			sb.append("\n");
		}
		
		System.out.println(sb.toString());
	}
}
