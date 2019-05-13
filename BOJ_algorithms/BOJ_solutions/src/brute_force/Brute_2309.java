package brute_force;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Brute_2309 {
	static int COUNT = 9;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int[] height = new int[COUNT];
		int result = 0;
		
		for (int i = 0; i< COUNT; i++) {
			String s = br.readLine();
			height[i] = Integer.parseInt(s);
			result += height[i];
		}
		
		boolean flag = false;
		for (int i = 0; i < COUNT; i++) {
			for (int j = i+1; j < COUNT; j++) {
				if ((result - height[i] - height[j]) == 100) {
					height[i] = -1;
					height[j] = -1;
					flag = true;
					break;
				}
			}
			if (flag) 
				break;
			
		}
		
		Arrays.sort(height);
		
		for (int i = 0; i < COUNT; i++) {
			if (height[i] != -1) 
				System.out.println(height[i]);
		}
		
	}
}
