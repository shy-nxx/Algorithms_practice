package d2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_1945 {
	//간단한 소인수분해 
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int T = Integer.parseInt(st.nextToken());

		//N=2a x 3b x 5c x 7d x 11e
		int[] mods = {2,3,5,7,11};

		StringBuffer sf = new StringBuffer();

		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());

			int[] temp = new int[mods.length];

			for (int i = 0; i < mods.length; i++) {
				while (N % mods[i] == 0) {
					temp[i]++;
					N = N/mods[i] + N%mods[i];
				}
			}

			sf.append("#" + tc + " ");
			for (int i = 0; i < temp.length; i++) {
				sf.append(temp[i] + " ");
			}
			sf.append("\n");
		}
		System.out.println(sf.toString());
	}

}
