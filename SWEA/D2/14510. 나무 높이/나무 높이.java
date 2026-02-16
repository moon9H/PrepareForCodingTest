import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T ; tc++) {
            int N = Integer.parseInt(br.readLine());
            int[] trees = new int[N];
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                trees[i] = Integer.parseInt(st.nextToken());
            }

            Arrays.sort(trees);

            int minDays = Integer.MAX_VALUE;

            for (int target = trees[N - 1]; target <= trees[N - 1] + 1; target++) {
                int even = 0, odd = 0;
                int ans = 0;

                for (int i = 0; i < N - 1; i++) {
                    int residual = target - trees[i];
                    if (residual > 0){
                        even += residual / 2;
                        odd += residual % 2;
                    }
                }

                if (even > odd){
                    while (Math.abs(even - odd) > 1){
                        even--;
                        odd += 2;
                    }
                }

                if (odd > even){
                    ans = 2 * odd - 1;
                } else if (even > odd){
                    ans = 2 * even;
                } else {
                    ans = even + odd;
                }

                minDays = Math.min(minDays, ans);
            }

            sb.append('#').append(tc).append(' ').append(minDays).append('\n');
        }
        System.out.println(sb);
    }
}