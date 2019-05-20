package d2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_1940 {
	//가랏!RC카 
	static final int stop = 0;
	static final int speed_up = 1;
	static final int speed_down = 2;
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
	
		int T = Integer.parseInt(st.nextToken());
		
		StringBuffer sf = new StringBuffer();
		
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			
			int distance = 0;
			int speed = 0;
			
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				if (x == speed_up) {
					speed += Integer.parseInt(st.nextToken());
				}
				else if (x == speed_down) {
					if (speed > 0)
						speed -= Integer.parseInt(st.nextToken());
				}
				
				distance += speed;
			}
			sf.append("#" + tc + " " + distance + "\n");
		}
		System.out.println(sf.toString());
	}
}
