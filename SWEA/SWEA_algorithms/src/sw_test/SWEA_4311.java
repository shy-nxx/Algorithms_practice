package sw_test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_4311 {
	//-> 오래된 스마트폰 (swea 연숩문제 )
	
	static int N, O, M;
	
	static int MIN = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int T = Integer.parseInt(st.nextToken());

		StringBuffer sf = new StringBuffer();

		for (int k = 0; k < T; k++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken()); //터치 가능한 숫자의 개수 
			O = Integer.parseInt(st.nextToken()); //터치 가능한 연산의 개수 
			M = Integer.parseInt(st.nextToken()); //최대 터치회수 
			
			int[] nums = new int[N];
			
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				nums[i] = Integer.parseInt(st.nextToken());
				
			}
			
			int[] ops = new int[O];
			
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < O; i++) {
				ops[i] = Integer.parseInt(st.nextToken());
			}
			
			st = new StringTokenizer(br.readLine());
			int target = Integer.parseInt(st.nextToken());
			
			//터치 가능한 숫자들과 연산을 사용해서 target을 만들수 있는 최소 연산횟수 
			
			sf.append("#" + (k+1)  + " ");
			if (target == 0) {
				sf.append(-1 + "\n");
			}
			
			/*주어진 숫자들의 터치를 이용해서 만들 수 있는 숫자라면 그 연산횟수도 포함되어야 한다. 
			-> dp? 어떤 걸 사용해야하지?
			M의 제한을 두고 할 수 있는 건 dfs? 근데 탐색하는것도 아니고 dfs로 하엔,....
			일단 dfs로 해서 만들 수 있는 숫자가 터치 가능한 숫자에 포함되어있으면 그 연산 횟수를 min 넣고 만들 수 없으면 연산과 숫자의조합을 사용한다? 
			target의 자리수 != M이면 터치를 이용해서 숫자를 만들 수 없다. -> M-1 개의 숫자와 연산을 이용해서 만들어야 한다. . 
			그럼 dfs든 뭐든 반복문의 개수는 M-1로 제한이 되므로 
			
			*/
			int count= 0; 
			int[] temp = new int[1000];
			
			while(target > 0) {
				target /= 10;
				temp[count] = target % 10;
				System.out.println(temp[count]);
				count++;
				
			}
			
			int ans = 0;
			
			if (count <= M) {
				boolean flag = true;
				//숫자들의 터치만으로 숫자를 만들 가능성도 고려 
				for (int i = 0; i < N; i++) {
					for (int j = 0; j < temp.length; j++) {
						if (nums[i] == temp[j]) ans++;
//						System.out.println(nums[i] + " " + temp[j]);
					}
					
				}
				if (ans == count) {
					//M-1번의 연산을 진행 
					MIN = Math.min(ans, MIN);
				}
				
			}
			else {
				//M-1 개의 숫자와 연산을 이요해서 숫자를 만든다. 
			}
			
			sf.append(MIN + "\n");
		}
		System.out.println(sf.toString());
	}
	
	static void dfs(int target, int[] nums, int[] ops, int count) {
		
	}
}
