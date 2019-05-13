package string;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class String_3986 {
	/**
	 * AABB 와 ABBA의 경우를 보자.
	 * 겹치는 부분이 존재하지 않으므로 좋은 단어인 것을 알 수 있다.
	 * 그렇다는건, 결국 A와 B가 인접해있으면 좋은 단어라는 것이다.
	 * ABBA의 경우는 BB를 뺏을 때,  AA가 됨으로 인접해진다.
	 * 위의 규칙을 스택을 활용하여 접근할 수 있다.
	 * 1. 스택에 알파벳을 넣는다. (push)
	 * 2. 스택의 맨 위에 있는 알파벳과 넣을 알파벳이 같다면 뺀다. (pop)
	 * 위의 처리가 올바르게 된다면 좋은 단어이다.
	 * 그렇다면, 처리가 되지 않는다면 좋은 단어가 아니라는 것을 알 수 있다.
	 * 말하자면, 스택의 top을 비교하면서 push, pop을 구별하면 된다
	 * */
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		
		int count = 0;
		
		for (int i = 0; i < N; i++) {
			String s = br.readLine();
			Stack<Character> stack = new Stack<>();
			
			stack.add(s.charAt(0));
			
			for (int j = 1; j < s.length(); j++) {
				if(!stack.isEmpty() ) { //스택에 무언가 있으
					if (stack.peek() == s.charAt(j)) {
						stack.pop();
					} else {
						stack.push(s.charAt(j));
					}
				} else {
					stack.push(s.charAt(j));
				}
			}
			
			if (stack.size() == 0) {
				count++;
			}
		}
		System.out.println(count);
	}

}
