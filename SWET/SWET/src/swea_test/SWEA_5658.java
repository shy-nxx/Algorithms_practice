package swea_test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class SWEA_5658 {
	static int N, K;
	
	static String s;
	
	static ArrayList<Integer> list = new ArrayList<>();
	 
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int T = Integer.parseInt(st.nextToken());
		
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			
			N = Integer.parseInt(st.nextToken());// (8 ≤ N ≤ 28) N은 4의 배수이고, 8이상 28이하의 정수이다. 
			K = Integer.parseInt(st.nextToken());// k번째 큰 수가 10진수로 만든 수가 비밀번호 
			
			s = br.readLine();
			
			for (int i = 0; i < N/4; i++) {
				calcHexString();
				rotate();
			}

			Collections.sort(list);
			int intValue = Integer.parseInt(String.valueOf(list.get(list.size()-K)), 10);
			System.out.println(intValue);
		}
	}
	static void calcHexString() {
		//계산해서 side에 저장 
		int n = N/4;
		int[] side = new int[4];
		
		int index= 0;
		for (int i = 0; i < N; i+= n) {
			String sub = s.substring(i, i+n);
			side[index++] = Integer.parseInt(sub, 16);
			
		}
		for (int i = 0; i < 4; i++) {
			if (!list.contains(side[i])) list.add(side[i]);
		}
	}
	static void rotate() {
		String last = s.substring(s.length()-1);
		s = last + s.substring(0, s.length()-1);
		System.out.println(s);
	}
}
