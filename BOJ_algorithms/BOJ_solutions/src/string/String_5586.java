package string;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class String_5586 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		String s = br.readLine();
		
		String joi = "JOI";
		String ioi = "IOI";
		
		char[] ch = new char[10001];
		int index = 0;
		
		int[] result = new int[2];
		
		for (int i = 0; i < s.length(); i++) {
			ch[index++] = s.charAt(i);
			
			int temp = index;
			
			if (ch[temp -1] == joi.charAt(joi.length()-1)) {
				if (temp - joi.length() < 0) continue;
				
				boolean flag = true;
				for (int j =0; j < joi.length(); j++) {
					if (ch[temp - j - 1] != joi.charAt(joi.length() - j - 1)) {
						flag = false;
						break;
					}
				}
				if (flag) {
					result[0]++;
				}
			}
			
			temp = index;
			
			if (ch[temp -1] == ioi.charAt(ioi.length()-1) ) {
				if (temp - ioi.length() < 0) continue;
				
				boolean flag = true;
				for (int j =0; j < ioi.length(); j++) {
					if (ch[temp - j - 1] != ioi.charAt(ioi.length() - j - 1)) {
						flag = false;
						break;
					}
				}
				if (flag) {
					result[1]++;
				}
			}
		}
		System.out.println(result[0] + "\n" + result[1]);
		
		
	}
}
