import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for (int i = 1; i <= T; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            char[] A = st.nextToken().toCharArray();
            char[] B = st.nextToken().toCharArray();

            Stack<Character> aStack = new Stack<>();
            Stack<Character> bStack = new Stack<>();
            Stack<Character> result = new Stack<>();

            for (char c : A) {
                aStack.push(c);
            }

            for (char c : B) {
                bStack.push(c);
            }

            int carry = 0;

            while (!aStack.isEmpty() || !bStack.isEmpty() || carry > 0) {
                int a = 0;
                int b = 0;

                if (!aStack.isEmpty()) {
                    a = aStack.pop() - '0';
                }

                if (!bStack.isEmpty()) {
                    b = bStack.pop() - '0';
                }

                int sum = a + b + carry;

                result.push((char) (sum % 10 + '0'));
                carry = sum / 10;
            }

            sb.append('#').append(i).append(' ');

            while (!result.isEmpty()) {
                sb.append(result.pop());
            }

            sb.append('\n');
        }

        System.out.println(sb);
    }
}