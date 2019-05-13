package d5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SWEA_7229 {
	static Double MIN = Double.MAX_VALUE;
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int T = Integer.parseInt(st.nextToken());

		StringBuffer sf = new StringBuffer();

		for (int k = 0; k < T; k++) {

			st = new StringTokenizer(br.readLine());

			int N = Integer.parseInt(st.nextToken());

			int[] cake_size = new int[N];
			int[] slice_cnt = new int[N]; //각 케이크 별 슬라이스 카운트 저장 
			
			Arrays.fill(slice_cnt, 1);
			
			st = new StringTokenizer(br.readLine());
		
			for (int i = 0; i < N; i++) {
				cake_size[i] = Integer.parseInt(st.nextToken());
			}
			
			Arrays.sort(cake_size);
			
			st = new StringTokenizer(br.readLine());
			int M = Integer.parseInt(st.nextToken()); //칼의 내구도 
			
			Double[] cakes = new Double[N];
			
			for (int i = 0; i < N; i++) {
				cakes[i] = (double)cake_size[N-i-1];
			}
			
			double max = cakes[0];
			double min = cakes[N-1];
			
			MIN = max - min;
			
			int index = 0;
			for (int i = 1; i <= M; i++ ) {
				slice_cnt[index]++;
				
				max = 0;
				min = Double.MAX_VALUE;
				
				for (int j = 0; j < N; j++) {
					max = Math.max(max, cakes[j] / slice_cnt[j]);
					min = Math.min(min, cakes[j] / slice_cnt[j]);
				}
				MIN = Math.min(MIN, max - min);
				
//				System.out.println(MIN);
				if (MIN == 0) 
					break;
				
				if (index == N-1) {
					index = 0; //다시 처음으로 돌아간다. -> N-1번째 케이크까지 전부 자름 
					continue;
				}
 				
//				for (int j = 0; j < N; j++) {
//					System.out.println(slice_cnt[j]);
//				}
//				for (int j = 0; j < N; j++) {
//					System.out.println(cakes[j] / slice_cnt[j]);
//				}
//				System.out.println();
				if (cakes[index] / slice_cnt[index] >= cakes[index+1] / slice_cnt[index+1]) {
					index = 0;
				} else {
					index++;
				}
			}
			if (MIN == 0) {
				sf.append(0 + "\n");
			}
			else {
				sf.append(MIN + "\n");
			}
			
		}
		System.out.println(sf.toString());
	}
}
