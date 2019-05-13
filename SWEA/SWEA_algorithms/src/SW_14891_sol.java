import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class SW_14891_sol {
	static final int MAG = 4;
	static LinkedList<Integer>[] list =(LinkedList<Integer>[]) new LinkedList[5];
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		for (int i = 0; i < MAG; i++) {
			list[i] = new LinkedList<>();
			String s = br.readLine();
			for (int j = 0; j < s.length(); j++) {
				list[i].add(s.charAt(j) - 48);
			}
		}
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int K = Integer.parseInt(st.nextToken());
		
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken()); //회전시킬 자석의 번호 
			int d = Integer.parseInt(st.nextToken()); //회전 방향 
			
			/**
			  자석들은 연쇄 회전을 한다. 그리고 처음 제시된 자석은 반드시 회전을 한다. 다른자석들은 맞물린자석의 극을 확인 후 회전 한다. 
			  현재 자석을 기준으로 왼쪽 자석과 오른쪽 자석을 나누어서 회전을 한다. 
			  
			 */
			
			left(n-1, -d); //반대 방향 회전
			right(n+1, -d);
			rotate(n, d);
		}
		
		int ans = 0;
		for (int i = 0; i < MAG; i++) {
			if (list[i].get(0) == 1) { //s극이면 
				ans += Math.pow(2, i);
			}
		}
	}
	static void left(int n, int d) {
		if (!(1<= n && n <= MAG)) return;
		
		if (check(n, n+1)) {
			left(n-1, -d);
			rotate(n, d);
		}
	}
	
	static void right(int n, int d) {
		if (!(1<= n && n <= MAG)) return;
		
		if (check(n, n-1)) {
			right(n+1, d);
			rotate(n,d);
		}
	}
	
	static void rotate(int n, int d) {
		if (d == 1) { //시계방향 -> 마지막 노드가 첫번째로 들어옴 
			list[n].addFirst(list[n].getLast());
		}
		else {
			list[n].addLast(list[n].getFirst());
		}
	}
	static boolean check(int a, int b) {
		if (a > b) { //1과 2를 예를 들어보면 1과 2의 맞물린 자석은 2와 6이다. 
			if (list[a].get(6) == list[b].get(2)) return false; //같은 극이면 움직이지 않음 
			else return true;
		}
		else {
			if (list[a].get(2) == list[b].get(6)) return false;
			else return true;
		}
	}
}
