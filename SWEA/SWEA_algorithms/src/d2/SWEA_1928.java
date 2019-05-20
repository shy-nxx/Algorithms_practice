package d2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_1928 {
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int T = Integer.parseInt(st.nextToken());

		StringBuffer sf = new StringBuffer();
		for (int tc = 1; tc <= T; tc++) {
			String s = br.readLine();

			String result = "";
			char temp ;
			for (int i = 0; i < s.length()/4; i++) {
				temp = (char) (decoding(s.charAt(i*4))*4 + decoding(s.charAt(i*4+1))/16); //T G
				result += temp;
				temp = (char) ((decoding(s.charAt(i*4+1))%16)*16 + decoding(s.charAt(i*4+2))/4); //G l
				result += temp;
				temp = (char) ((decoding(s.charAt(i*4+2))%4)*64 + decoding(s.charAt(i*4+3))); //l m
				result += temp;

			}
			
			sf.append("#" + tc + " " + result + "\n" );
		}
		System.out.println(sf.toString());

	}
	static char decoding(char encode) {
		if (encode >= 'A' && encode <= 'Z') {
			encode = (char) (encode - 'A');
		} else if (encode >= 'a' && encode <= 'z') {
			encode = (char) (encode -'a' + 26);
		} else if (encode >= '0' && encode <= '9') {
			encode = (char) (encode - '0' + 52);
		} else if (encode == '+') {
			encode = 62;
		} else {
			encode = 63;
		}
		
		return encode;
	}
}
