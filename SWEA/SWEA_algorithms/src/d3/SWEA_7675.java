package d3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_7675 {
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int T = Integer.parseInt(st.nextToken());

		StringBuffer sf = new StringBuffer();

		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			String s = br.readLine();

			sf.append("#" + tc + " ");
			
			int index = 0;
			int result = 0;
			for (int k = 0; k < s.length(); k++) {
				
				int temp = index;
				if (s.charAt(k) == '!' || s.charAt(k) == '.' || s.charAt(k) == '?') {
					index = k+1;
					result = 0;
				}
				if (index > temp) {
					String sub_s = s.substring(temp, index).trim();
					
					String[] temp_s = sub_s.split(" ");

					for (int t = 0; t < temp_s.length; t++) {
							
						if (Character.isUpperCase(temp_s[t].charAt(0))) { //첫 글자가 대문자 
							//나머지는 소문자 
							String others = temp_s[t].substring(1, temp_s[t].length());

							if (others.equals("?") || others.equals("!") || others.equals(".")) {
								result++;
								break;
							}
							
							if (isLowerCase(others.toCharArray())) {
								result++;
							}
						}
					}
					sf.append(result + " ");
				}
				
			}

			sf.append("\n");
		}
		System.out.println(sf.toString());
	}
	static boolean isLowerCase(char[] arrays) {
		boolean flag = false;
		for (int i = 0; i < arrays.length; i++) {
			if (arrays[i] == '.' || arrays[i] == '!' || arrays[i] == '?') continue;
			if (arrays[i] >= 'a' && arrays[i] <= 'z') {
				flag = true;
			}
			else {
				return false;
			}
		}
		return true;
	}
}
