package string;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class String_9935 {
	/**
	 * 문자열이 폭발 문자열을 포함하고 있는 경우에, 모든 폭발 문자열이 폭발하게 된다. 
	 * 남은 문자열을 순서대로 이어 붙여 새로운 문자열을 만든다.
	 * 새로 생긴 문자열에 폭발 문자열이 포함되어 있을 수도 있다.
	 * 폭발은 폭발 문자열이 문자열에 없을 때까지 계속된다.
	 */
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String s = br.readLine();
		String bomb = br.readLine();

		char[] ch = s.toCharArray();

		for (int i = 0; i < ch.length; i++) {
			for (int j = 0; j < bomb.length(); j++) {
				if (ch[i] == bomb.charAt(j)) {
					ch[i] = '\0';
				}
			}
		}
		
		StringBuffer sb = new StringBuffer();
		
		for (int i = 0; i < ch.length; i++) {
			if (ch[i] != '\0') {
				sb.append(ch[i]);
			}
		}
		
		if (sb.length() == 0) {
			System.out.println("FRULA");
		}
		else  
			System.out.println(sb.toString());
	}
}
