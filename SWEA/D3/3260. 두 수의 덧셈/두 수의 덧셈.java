import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Solution {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int testCase = 1; testCase <= T; testCase++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			String A = st.nextToken();
			String B = st.nextToken();
			String longer = (A.length() >= B.length()) ? A : B;
			String shorter = (A.length() < B.length()) ? A : B;
			
			int longStart = longer.length();
			int shortStart = shorter.length();
			Stack<Character> longStack = new Stack<>();
			Stack<Character> shortStack = new Stack<>();
			Stack<Integer> result = new Stack<>();

			for (int i = 0; i < longStart; i++) {
				longStack.push(longer.charAt(i));
			}
			for (int i = 0; i < shortStart; i++) {
				shortStack.push(shorter.charAt(i));
			}
			while(!shortStack.isEmpty()) {
				int l = longStack.pop() - '0';
				int s = shortStack.pop() - '0';
				result.push(l + s);
			}
			while(!longStack.isEmpty()) {
				result.push(longStack.pop() - '0');
			}
			for (int i = 0; i < result.size() - 1; i++) {
				if (result.get(i) >= 10) {
					result.set(i, result.get(i) % 10);
					result.set(i + 1, result.get(i + 1) + 1);;
				}
			}
			sb.append("#" + testCase + " ");
			while (!result.isEmpty()) {
				sb.append(result.pop());
			}
			sb.append("\n");
		}
		System.out.println(sb.toString());
	}
}