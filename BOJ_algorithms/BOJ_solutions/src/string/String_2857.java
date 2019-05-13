package string;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class String_2857 {
	public static void main(String[] args) throws IOException {
		/**
		 * 폭탄문자열 ->폭탄 부분 문자열이 아닌온전한 문자열을 말한다. 
		 */
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringBuilder sb = new StringBuilder();
		
		String find = "FBI";
		
		for (int k = 0; k < 5; k++) {
			String s = br.readLine();
			
			char[] ch = new char[10];
			int index = 0;
			
			for (int i = 0; i < s.length(); i++) {
				ch[index++] = s.charAt(i);
				
				if (ch[index-1] == find.charAt(find.length()-1)) {
					if (index - find.length() < 0) continue;
					
					boolean flag= true;
					for (int j = 0; j < find.length(); j++) {
						if (ch[index - j - 1] != find.charAt(find.length() -j -1)) {
							flag = false;
							break;
						}
					}
					
					if (flag) {
						sb.append((k+1) + " ");
					}
				}
			}
		}
		if (sb.length() == 0) {
			System.out.println("HE GOT AWAY!");
		} else {
			System.out.println(sb.toString().trim());
		}
		
	}
}
