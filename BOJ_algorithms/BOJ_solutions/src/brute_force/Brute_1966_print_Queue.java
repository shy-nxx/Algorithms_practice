package brute_force;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Brute_1966_print_Queue {
	
	static Queue<Paper> q = new LinkedList<>();
	static LinkedList<Integer> list = new LinkedList<>();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int T = Integer.parseInt(st.nextToken());
		
		StringBuffer sf = new StringBuffer();
		
		for (int k = 0; k < T; k++) {
			q.clear();
			list.clear();
			
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken()); //문서의 개수 
			int M = Integer.parseInt(st.nextToken()); //찾으려는 문서의 번호 
			
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				int priority = Integer.parseInt(st.nextToken());
				q.add(new Paper(i, priority));
				
				list.add(getIndex(priority), priority ); //우선순위 순서대로 정렬해줌 -> 9 1 1 1 1 1
			}
			
			int ans = 1; //바로 가장 우선순위가 나오면 결과는 1이므로 
			
			while(!q.isEmpty()) {
				int max = getMax(); //리스트에 있는 것 중 가장 중요도가 높은 아이를 데려옴 
				
				Paper p = q.poll();
				
				if (p.importance == max) {
					if (p.order == M) {
						sf.append(ans + "\n");
						break;
					}
					ans++;
					list.remove(list.indexOf(max)); //중요! 지워줘야지 다음 중요도가 높은 걸 찾을 수 있다. 
					continue;
				}
				q.add(p); //가장 중요하지 않는 아이는 큐의 마지막으로 다시 넣어줌 
			}
			
		}
		System.out.println(sf.toString());
	}
	
	static int getMax() {
		int max = 0;
		
		for (int i = 0; i < list.size(); i++) {
			max = Math.max(max, list.get(i));
		}
		return max;
	}
	
	static int getIndex(int priority) {
		int index = 0;
		
		for (int i = 0; i < list.size(); i++) {
			++index;
			if (priority < list.get(i)) {
				break; //자신보다 큰 녀석을 만남 
			}
		}
		
		return index == list.size() ? 0 : index;
	}
	
	static class Paper{
		int order;
		int importance;
		public Paper(int order, int importance) {
			super();
			this.order = order;
			this.importance = importance;
		}

	}
}
