package d2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_1989_palindrome {
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		StringBuffer sf = new StringBuffer();
		
		int N = Integer.parseInt(st.nextToken());
		
		for (int k = 0; k < N; k++) {
			sf.append("#" + (k+1) + " ");
			
			String s = br.readLine();
		
			if (checkPalindrom(s) ) {
				sf.append(1);
			}
			else {
				sf.append(0);
			}
			
			sf.append("\n");
		}
		
		System.out.println(sf.toString());
	}
	static boolean checkPalindrom(String s) {
		int n = s.length();
		for (int i = 0; i < n/2; i++) {
			if (s.charAt(i) != s.charAt(n-i-1)) {
				return false;
			}
		}
		return true;
	}
}
