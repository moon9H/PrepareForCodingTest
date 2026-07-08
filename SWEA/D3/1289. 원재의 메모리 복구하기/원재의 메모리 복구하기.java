import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for (int i = 1; i <= T; i++) {
            int count = 0;
            char[] state = br.readLine().toCharArray();
            boolean isFliped = false;

            for (char c : state) {
                if (c == '0' && isFliped) {
                    isFliped = false;
                    ++count;
                }

                if (c == '1' && !isFliped){
                    isFliped = true;
                    ++count;
                }
            }

            sb.append('#').append(i).append(' ').append(count).append('\n');
        }

        System.out.println(sb);
    }
}