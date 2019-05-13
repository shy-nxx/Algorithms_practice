package string;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class String_9935_stack {
	public static void main(String[] args) throws IOException {
		/**
		 * 폭탄문자열 ->폭탄 부분 문자열이 아닌온전한 문자열을 말한다. 
		 */
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String s = br.readLine();
		String bomb = br.readLine();

		char[] ch = new char[1000001];
		
		int index = 0;
		
		for (int i = 0; i < s.length(); i++) {
			ch[index++] = s.charAt(i);
			
			if (ch[index - 1] == bomb.charAt(bomb.length()-1)) { //폭탄 추정 문자열 발견
				if (index - bomb.length() < 0) {
					continue;
				}
				
				boolean flag = true;
				
				for (int j = 0; j < bomb.length(); j++) {
					if (ch[index - j - 1] != bomb.charAt(bomb.length()-j-1)) {
						flag = false;
						break;
					}
				}
				
				if (flag) {
					index -= bomb.length();
				}
			}
		}
		
		if (index == 0) {
			System.out.println("FRULA");
		}
		else {
			for (int i = 0; i < index; i++) {
				System.out.print(ch[i]);
			}
		}
	}
}
