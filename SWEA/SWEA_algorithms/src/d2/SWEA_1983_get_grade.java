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

public class SWEA_1983_get_grade {

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
			
			Grade[] grades = new Grade[N+1];
			double[] results = new double[N+1];
			
			int unit = N/10;
			
			for (int i = 1; i <= N; i++) {
				st = new StringTokenizer(br.readLine());
				grades[i] = new Grade(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()));
			}
			
			HashMap<Integer, Double> hash = new HashMap<>();
			
			for (int i = 1; i <= N; i++) {
				double result = 0;
				result += grades[i].mid * 0.35;
				result += grades[i].fi_exam * 0.45;
				result += grades[i].hw * 0.20;
				results[i] = result;
				
				hash.put(i, results[i]);
			}
		
			Iterator it = sortHash(hash).iterator();
			
			int j = 0;
			while(it.hasNext()) {
				int temp = (Integer)it.next();
				
				System.out.println(temp + " " + hash.get(temp));
				 
				if (K == temp) {
					sf.append(grade[j/unit] + "\n");
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
			@SuppressWarnings("unchecked")
			@Override
			public int compare(Object o1, Object o2) {
				// TODO Auto-generated method stub
				Object v1 = map.get(o1);
                Object v2 = map.get(o2);
                return ((Comparable<Object>) v1).compareTo(v2);
			}
		});
		Collections.reverse(list); // 주석시 오름차순
		
		return list;
		
	}
	static class Grade {
		int mid;
		int fi_exam;
		int hw;
		public Grade(int mid, int fi_exam, int hw) {
			super();
			this.mid = mid;
			this.fi_exam = fi_exam;
			this.hw = hw;
		}
		
		
		
	}
}
