package d3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_7584 {
	static StringBuffer sf = new StringBuffer();
	static int K;
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int T = Integer.parseInt(st.nextToken());

		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			K = Integer.parseInt(st.nextToken());
			
			dfs(0, "0", "0");
			
		}
		System.out.println(sf.toString());
	}
	static void dfs(int cnt, String str, String prev) {
		if (cnt == K) {
			sf.append(str.charAt(cnt-1) + "\n");
			return;
		}
		
//		System.out.println("Str = " + str + " " + prev);
		String temp = str.substring(prev.length()-1, str.length());
		String s = f_func(temp.toCharArray());
//		System.out.println("s = " + s);
		dfs(cnt+1, str + "0" + s, str);
	}
	static String f_func(char[] arrays) {
		String temp = "";
		char[] g_arrays = g_func(arrays);
		for (int i = 0; i < g_arrays.length; i++) {
			if (g_arrays[i] == '1') g_arrays[i] = '0';
			else {
				g_arrays[i] = '1';
			}
			temp += g_arrays[i];
		}
		return temp;
		
	}
	static char[] g_func(char[] arrays) {
		char[] temp = new char[arrays.length];
		
		int index = 0;
		for (int i = arrays.length-1; i >= 0; i--) {
			temp[index++] = arrays[i];
		}
		return temp;
	}
}
