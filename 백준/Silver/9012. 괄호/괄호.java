import java.util.Scanner;
import java.util.Stack;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		Stack<Character> stack = new Stack<>();
		first : for (int i = 0; i < T; i++){
			String parenthesis = sc.next();
			for (int j = 0; j < parenthesis.length(); j++){
				if (parenthesis.charAt(j) == '('){
					stack.push(parenthesis.charAt(j));
				} else {
					if (stack.isEmpty()){
						System.out.println("NO");
						continue first;
					} else {
						stack.pop();
					}
				}
			}
			if (stack.isEmpty())
				System.out.println("YES");
			else
				System.out.println("NO");
			stack.clear();
		}
	}
}
