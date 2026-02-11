import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    private static int N, B, minSum;
    private static int[] heights;
    private static boolean[] isSelected;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            B = Integer.parseInt(st.nextToken());
            heights = new int[N];
            minSum = Integer.MAX_VALUE;
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                heights[i] = Integer.parseInt(st.nextToken());
            }

            dfs(0, 0);

            sb.append('#').append(tc).append(' ').append(minSum - B).append('\n');
        }
        System.out.println(sb);
    }

    static void dfs(int index, int sum){
        if (sum > minSum) return;

        if (index == N){
            if (sum >= B) minSum = sum;
            return;
        }

        dfs(index + 1, sum + heights[index]);
        dfs(index + 1, sum);
    }
}