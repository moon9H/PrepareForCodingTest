import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		Stack<Integer[]> stack = new Stack<>();
		int score = 0;
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			if (st.nextToken().equals("1")) {
				int point = Integer.parseInt(st.nextToken());
				int time = Integer.parseInt(st.nextToken());
				if (time - 1 == 0) {
					score += point;
				} else {
					stack.add(new Integer[] {point, time - 1});
				}
			} else {
				if (!stack.isEmpty()) {
					Integer[] top = stack.peek();
					top[1] -= 1;
					if (top[1] == 0) {
						score += top[0];
						stack.pop();
					}
				}
			}
		}
		System.out.println(score);
	}
}