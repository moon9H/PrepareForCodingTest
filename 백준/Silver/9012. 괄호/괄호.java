import java.io.*;
import java.util.Stack;

public class Main {
	static boolean isVPS(String paren){
		Stack<Character> stack = new Stack<>();
		for (char c : paren.toCharArray()){
			if (c == '('){
				stack.push(c);
			} else {
				if (stack.isEmpty()) return false;
				stack.pop();
			}
		}
		return stack.isEmpty();
    }
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int i = 0; i < T; i++){
			String parenthesis = br.readLine();
			sb.append(isVPS(parenthesis) ? "YES" : "NO").append("\n");
		}
		System.out.println(sb.toString());
	}
}