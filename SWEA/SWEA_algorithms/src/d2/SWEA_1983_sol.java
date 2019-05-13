package d2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

public class SWEA_1983_sol {
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		StringBuffer sf = new StringBuffer();
		
		int T = Integer.parseInt(st.nextToken());
		
		
		String[] grade = { "A+", "A0", "A-", "B+", "B0", "B-", "C+", "C0", "C-", "D0"};
		
		for (int k = 0; k < T; k++) {
			sf.append("#" + (k+1) + " ");
			st = new StringTokenizer(br.readLine());
			
			int N = Integer.parseInt(st.nextToken()); //학생 수 
			int K = Integer.parseInt(st.nextToken()); // 학점을 알고 싶은 학생의 번호 
			
			
			int unit = N /10;
			
			HashMap<Integer, Double> hash = new HashMap<>();
			
			for (int i = 1; i <= N; i++) {
				st = new StringTokenizer(br.readLine());
				
				int mid = Integer.parseInt(st.nextToken());
				int fi_ex = Integer.parseInt(st.nextToken());
				int hw = Integer.parseInt(st.nextToken());
				
				Double result = 0.0;
				
				result += mid * 0.35;
				result += fi_ex * 0.45;
				result += hw * 0.20;
				
				hash.put(i, result);
				
			}
			
			Iterator it = sortHash(hash).iterator();
			
			int j = 0;
			while(it.hasNext()) {
				int temp = (int) it.next();
				
				if (K == temp) {
					sf.append(grade[j / unit ] + "\n");
					break;
				}
				j++;
			}
		}
		System.out.println(sf.toString());
	}
	static List<Integer> sortHash(final HashMap<Integer, Double> map) {
		List<Integer> list = new ArrayList<>();
		
		list.addAll(map.keySet());
		
		Collections.sort(list, new Comparator<Object>() {

			@Override
			public int compare(Object o1, Object o2) {
				// TODO Auto-generated method stub
				Object v1 = map.get(o1);
				Object v2 = map.get(o2);
				
				return ((Comparable<Object>) v1).compareTo(v2);
			}
			
		});
		
		Collections.reverse(list);
		
		return list;
	}
}
