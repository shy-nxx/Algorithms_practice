package d2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_2007_length_of_pattern_words {

	/**
	 * 10:42~12:08
	 * 
	 * 패턴에서 반복되는 부분을 마디라고 부른다. 문자열을 입력 받아 마디의 길이를 출력하는 프로그램을 작성하라.
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		StringBuffer sf = new StringBuffer();
		
		int T = Integer.parseInt(st.nextToken());

		for (int k = 0; k < T; k++) {
			String s = br.readLine();
			
			sf.append("#" + (k+1) + " ");
		
			int j = 0;
			int i = 1;
			for (i = 1; i < s.length() ; i++) {
				if (s.charAt(i) == s.charAt(j)) {
					j++;
				}
				else {
					j = 0;
				}
			}
			sf.append(i-j + "\n");
			
		}
		System.out.println(sf.toString());
	}
	
}
