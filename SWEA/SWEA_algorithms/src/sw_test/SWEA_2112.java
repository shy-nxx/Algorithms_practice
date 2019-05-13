package sw_test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class SWEA_2112 {
	static int D,W,K;

	static int[][] map = new int[14][21];

	static int min = Integer.MAX_VALUE;

	static boolean flag;
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int T = Integer.parseInt(st.nextToken());

		StringBuffer sf = new StringBuffer();

		for (int z = 0; z < T; z++) {
			sf.append("#" + (z+1) + " ");
			st = new StringTokenizer(br.readLine());

			D = Integer.parseInt(st.nextToken()); //(3≤D≤13) 두께  
			W = Integer.parseInt(st.nextToken()); //(1≤W≤20) 너비 
			K = Integer.parseInt(st.nextToken()); //(1≤K≤D) 합격기준 

			map = new int[14][21];

			for (int i = 0; i < D; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < W; j++) {
					map[i][j] = Integer.parseInt(st.nextToken()); //0->A / 1 -> B 

				}
			}
			//세로로 탐색해야 한다.같은 특성을 가진 특성이 K개 이상있으면 통과 W를 모두 돌았을 때 모두 통과하면 완료 

			min = Integer.MAX_VALUE;

			if (K == 1 || isThereK(map)) {
				sf.append(0 + "\n");
				continue;
			}

			dfs(0,0, map); //주입할 행?, 주입 횟수 

			sf.append(min + "\n");

		}
		System.out.println(sf.toString());
	}

	static void dfs(int cur, int cnt, int[][] arr) {
		printMap(arr, cnt);

		if (isThereK(arr)) {
			min = Math.min(cnt, min);
			return;
		}

		if (cnt >= min) return;
		if (cur == D) return; //마지막 행까지 모두 주입 완료 


		int[][] arr2 = new int[14][21];

		for (int i = 0 ; i < D; i++) {
			for (int j = 0; j < W; j++) {
				arr2[i][j] = arr[i][j];
			}
		}

		dfs(cur+1, cnt, arr);


		for (int j = 0; j < W; j++) {
			arr2[cur][j] = 0;
		}

		dfs(cur+1, cnt+1, arr2);

		for (int j = 0; j < W; j++) {
			arr2[cur][j] = 1;
		}

		dfs(cur+1, cnt+1, arr2); //방금 살펴본 행이 만족하지 않은 경우 다음 행도 살펴봄 



	}
	static void printMap(int[][] arr, int cnt) {
		for (int i = 0; i < D; i++) {
			for (int j = 0; j < W; j++) {
				if (arr[i][j] == 0) {
					System.out.print("A ");
				} else {
					System.out.print("B ");
				}

			}
			System.out.println();
		}
		System.out.println(cnt);
	}

	//	static boolean isThereK(int c) {
	//		for (int j = 0; j < D-K; j++) {
	//			boolean flag = true;
	//			for (int t = 0; t < K; t++) {
	//				if (map[j][c] != map[t][c]) { //다르면 다른 열로 넘어간다. 
	//					flag = false;
	//					break;
	//				}
	//			}
	//			if (flag) {
	//				return true;
	//			}
	//		}
	//
	//		return false;
	//	}

	static boolean isThereK(int[][] arr) {
		boolean flag = false;
		for (int i = 0; i < W; i++) {

			for (int j = 0; j < D-K+1; j++) {

				for (int t = 1; t < K; t++) {
					if (arr[j][i] != arr[j+t][i]) { //다르면 다른 열로 넘어간다. 
						flag = false;
						break;
					}else {
						flag = true;
					}
				}
				if (flag) {
					break;
				}	
			}
			if (!flag) break;			


		}
		if (flag) return true;
		else return false;
	}
}
