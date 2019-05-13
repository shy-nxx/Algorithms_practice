import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class BOJ_SS_14891 {

	static int magnatic = 4;
	static int edge = 8;
	static int N = 0;
	static int S = 1;
	
	static LinkedList<Integer>[] list = (LinkedList<Integer>[])new LinkedList[5];
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		for (int i = 1; i <= magnatic; i++) {
			list[i] = new LinkedList<>();
			String s = br.readLine();
			for (String temp : s.split("")) {
				int n = Integer.parseInt(temp);
				list[i].add(n);
			}
		}
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int K = Integer.parseInt(st.nextToken());
		
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int m = Integer.parseInt(st.nextToken());
			int dir = Integer.parseInt(st.nextToken()); //해당 마그네틱의 회전 방향 1-> 시계 -1-> 반시 
			
			left(m-1, -dir); //반대방향으로 가야하니까 -1을 곱해준다. (1->-1 / -1->1)
			right(m+1, -dir);
			rotate(m, dir);
		}
		
		int ans = 0;
		for (int i = 1; i <= magnatic; i++) {
			if (list[i].get(0) == 1) {
				ans += Math.pow(2, i-1);
			}
		}
		
		System.out.println(ans);
	}
	static void left(int m, int d) {
		if (!(1<= m && m <= 4)) {
			return;
		}
		
		if (check(m, m+1)) {
			left(m-1, -d);
			rotate(m, d);
		}
	}
	
	static void right(int m, int d) {
		if (!(1 <= m && m <= 4)) 
			return;
		if (check(m, m-1)) {
			right(m+1, -d);
			rotate(m, d);
		}
	}
	static boolean check(int a, int b) {
		if (a > b) { //마그네틱 비교 (2,6)
			if (list[a].get(6) == list[b].get(2)) {
				return false;
			}
			else 
				return true;
		}
		else {
			if (list[a].get(2) == list[b].get(6) ) 
				return false;
			else 
				return true;
		}
	}
	
	static void rotate(int m, int d) {
		if (d == -1) {
			list[m].addLast(list[m].pollFirst());
		}
		else {
			list[m].addFirst(list[m].pollLast());
		}
	}
}
