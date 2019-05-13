package practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class String_3986_greate_word {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());

		int count = 0;
		
		for (int i = 0; i < N; i++) {
			String s = br.readLine();

			Stack<Character> q = new Stack<>();
			q.add(s.charAt(0));

			for (int j = 1; j < s.length(); j++) {
				if (!q.isEmpty()) {
					if (q.peek() == s.charAt(j)) {
						q.pop();
					}
					else {
						q.push(s.charAt(j));
					}
				}
				else {
					q.push(s.charAt(j));
				}
			}
			if (q.size() == 0)  {
				count++;
			}
		}
		System.out.println(count);
		
	}
}
