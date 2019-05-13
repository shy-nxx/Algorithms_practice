package sw_test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class SWEA_5658_sol {
	//보물상자 비밀번호 
	static int N, K;
	static ArrayList<Integer> list= new ArrayList<>();
	static String s;
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int T =  Integer.parseInt(st.nextToken());

		StringBuffer sf = new StringBuffer();
		for (int k=0; k < T; k++) {
			sf.append("#" + (k+1) + " ");
			st = new StringTokenizer(br.readLine());
			
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			
			s = br.readLine();
			
			for (int i = 0; i < N/4; i++) {
				findPW();
				rotate();
			}
			
			Collections.sort(list); //16진수들을 오름차순으로 정렬
			int intValue = Integer.parseInt(String.valueOf(list.get(list.size()-K)), 10);
			sf.append(intValue + "\n");
			
		}
		System.out.println(sf.toString());
	}
	static void findPW() {
		int k = N/4; //3개씩 존재 
		int[] side = new int[4];
		
		int index = 0;
		
		for (int i = 0; i < N; i+= k) {
			String temp = s.substring(i, i+k);
			side[index++] = Integer.parseInt(temp, 16); //16진수로 저장 
		}
		
		for (int i = 0; i < 4; i++) {
			//리스트 안에 이미 사이드에 저장된 값이 있는 지 확인 
			if (!list.contains(side[i])) list.add(side[i]);
		}
		
	}
	static void rotate() {
		String last = s.substring(N-1);
		s = last + s.substring(0,  N-1);
	}
}
