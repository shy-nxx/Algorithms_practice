package string;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class String_2857_sol {
	public static void main(String[] args) throws IOException {
		/**
		 * 폭탄문자열 ->폭탄 부분 문자열이 아닌온전한 문자열을 말한다. 
		 */
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringBuilder sb = new StringBuilder();
		
		String find = "FBI";
		
		for (int k = 0; k < 5; k++) {
			String s = br.readLine();
			
			char[] ch = s.toCharArray();
			int index = 0;
			boolean flag = false;
			for (int i = 0; i < ch.length; i++) {
				if (i + 2 < s.length() && ch[i] == 'F' && ch[i+1] == 'B' && ch[i+2] == 'I') {
					flag = true;
					break;
				}
			}
			if (flag) {
				sb.append((k+1) + " " );
			}
		}
		if (sb.length() == 0) {
			System.out.println("HE GOT AWAY!");
		} else {
			System.out.println(sb.toString().trim());
		}
	}
}
