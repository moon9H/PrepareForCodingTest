import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Solution {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for (int testCase = 1; testCase <= T; testCase++) {
			String pipeline = br.readLine();
			Stack<Character> stack = new Stack<>();
			int result = 0;
			for (int i = 0; i < pipeline.length(); i++) {
				if (pipeline.charAt(i) == '(') {
					stack.push('(');
				} else {
					if (pipeline.charAt(i - 1) == '(') {
						stack.pop();
						if (!stack.isEmpty()) {
							result += stack.size();
						}
					} else {
						stack.pop();
						result++;
					}
					
				}
			}
			sb.append("#" + testCase + " ").append(result).append("\n");
		}
		System.out.println(sb.toString());
	}
}