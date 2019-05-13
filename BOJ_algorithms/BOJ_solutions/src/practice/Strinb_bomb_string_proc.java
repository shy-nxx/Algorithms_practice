package practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Strinb_bomb_string_proc {
	public static void main(String[] args) throws IOException {
		/**
		 * 폭탄문자열 ->폭탄 부분 문자열이 아닌온전한 문자열을 말한다. (이중 반복문을 돌면서 처리하면 안된다.-> 온전한 폭탄을 만났을때만 제거)
		 */
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String s = br.readLine();
		String bomb = br.readLine();
		
		char ch[] = new char[1000001];
		int index = 0;
		
		for (int i = 0; i < s.length(); i++) {
			ch[index++] = s.charAt(i);
			
			if (ch[index-1] == bomb.charAt(bomb.length()-1)) {
				if (index - bomb.length() <0) {
					continue;
				}
				
				boolean flag = true; //일단 끝 문자열이 같으니까 폭탄으로 간
				for (int j = 0; j < bomb.length(); j++) {
					if (ch[index - j - 1] != bomb.charAt(bomb.length() - j - 1)) {
						flag = false;
						break;
					}
				}
				
				if (flag) { //폭탄이 마
					index -= bomb.length();
				}
			}
		}
		if (index == 0) {
			System.out.println("FRULA");
		}
		else {
			for (int j = 0; j < index; j++) {
				System.out.print(ch[j]);
			}
		}
	}
}
