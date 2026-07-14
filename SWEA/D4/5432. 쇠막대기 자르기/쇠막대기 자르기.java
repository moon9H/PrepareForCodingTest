import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            char[] lazerPipe = br.readLine().toCharArray();
            Stack<Character> stack = new Stack<>();
            int pipeUnderLazerCnt = 0;

            for (int i = 0; i < lazerPipe.length; i++) {
                if (lazerPipe[i] == '(') stack.push('(');
                else {
                    if (lazerPipe[i - 1] == '('){
                        stack.pop();
                        pipeUnderLazerCnt += stack.size();
                    }
                    else {
                        stack.pop();
                        pipeUnderLazerCnt += 1;
                    }
                }
            }

            sb.append('#').append(tc).append(' ').append(pipeUnderLazerCnt).append('\n');
        }
        System.out.println(sb);
    }
}