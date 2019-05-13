package bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BFS_1966 {

	static int N, M;

	static LinkedList<Integer> list = new LinkedList<>();

	static StringBuffer sf = new StringBuffer();

	static Queue<Paper> q = new LinkedList<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int T = Integer.parseInt(st.nextToken());

		for (int k = 0; k < T; k++) {
			q.clear();
			list.clear();
			
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken()); //문서의 
			M = Integer.parseInt(st.nextToken()); //원하는 문서의 위치 

			st = new StringTokenizer(br.readLine());

			for (int i = 0; i < N; i++) {
				int importance = Integer.parseInt(st.nextToken());
				q.offer(new Paper(i, importance));
				list.add(findIndex(importance), importance);
			}
//
//			for (int i = 0; i < list.size(); i++) {
//				System.out.println(list.get(i));
//			}
			
			int result = 1;
			
			while(!q.isEmpty()) {
				int max = checkMax();
				Paper p = q.poll();
				
				if (p.importance == max) {
					if (p.order == M) {
						sf.append(result + "\n");
						break;
					}
					result++;
					list.remove(list.indexOf(max));
					continue;
				}
				q.offer(p);
			
			}
		}
		System.out.println(sf.toString());
	}

	static int findIndex(int importance) {
		int index = 0;
		for (int i = 0; i < list.size(); i++) {
			++index;
			if (list.get(i) > importance) {
				break;
			}
		}
		//넘어온 값이 가장 중요함이 높은 경우-> 0을 리턴 (제일 먼저 처리할값)
		return (index == list.size()) ? 0 : index;
	}
	static int checkMax() {
		int max = 0;
		for (int i = 0; i < list.size(); i++) {
			max = Math.max(max, list.get(i));
		}
		return max; //나보다 중요성 큰 게 없음 

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
