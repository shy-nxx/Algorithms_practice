package d5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SWEA_7229_sol {
	/**
	 * 주어진 케이크의 수만큼 다 자르면 다시 처음 케이크로 돌아가서 자르기를 시작한다. 
	 * 케이크는 사이즈가 큰 순서대로 정해서 지금 나오는 케이크가 다음 케이크 보다 크거나 같으면 다시 처음부터 자르게 한다. 
	 * -> 매번 min, max 값을 구해서 비교 한다. 
	 * 각 케이크 별 자른 횟수를 따로 저장한다. 
	 * 
	 */
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


			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				cake_size[i] = Integer.parseInt(st.nextToken());
			}

			Arrays.sort(cake_size);

			Double[] cakes = new Double[N];

			//역순으로 정렬 
			for (int i = 0; i < N; i++) {
				cakes[i] = (double) cake_size[N-i-1];
			}

			int[] cake_cutting = new int[N];

			Arrays.fill(cake_cutting, 1); //기본이 1 -> 2부터 2씩 나눠짐 (한 번 자르면 2개의 케이크가 나옴)

			st = new StringTokenizer(br.readLine());
			int M = Integer.parseInt(st.nextToken()); //케이크 자를 최대 횟수 

			double min = cakes[N-1];
			double max = cakes[0];

			Double MIN = max - min;

			int index = 0;
			for (int i = 1; i <= M; i++) {
				cake_cutting[index]++;

				min = Double.MAX_VALUE;
				max = 0;

				for (int j = 0; j < N; j++) {
					min = Math.min(min, cakes[j] / cake_cutting[j]);
					max = Math.max(max, cakes[j] / cake_cutting[j]);
				}

				MIN = Math.min(MIN, max - min);

				if (MIN == 0) { //반드시 먼저! 
					break;
				}

				if (index == N-1) {
					index = 0; //모든 케이크를 잘랐으면 다시 처음 케이크로 돌아가서 자름 
					continue;
				}

				if (cakes[index] / cake_cutting[index] >= cakes[index+1] / cake_cutting[index+1]) {
					index = 0;
				} else {
					index++;
				}
			}
			sf.append("#" + (k+1) + " ");
			if (MIN == 0) {
				sf.append(0 + "\n");
			}
			else {
				String s = new Double(MIN).toString();
				sf.append(s.substring(0, s.length() > 10 ? 10 : s.length()) + "\n");
				
			}

		}
		System.out.println(sf.toString());
	}
}
