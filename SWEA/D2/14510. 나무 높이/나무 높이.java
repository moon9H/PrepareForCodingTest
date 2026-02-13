import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            int N = Integer.parseInt(br.readLine());
            int[] trees = new int[N];
            StringTokenizer st = new StringTokenizer(br.readLine());
            int tallest = 0;
            for (int i = 0; i < N; i++) {
                trees[i] = Integer.parseInt(st.nextToken());
                tallest = Math.max(tallest, trees[i]);
            }

            int minDays = Integer.MAX_VALUE;

            for (int target = tallest; target <= tallest + 1; target++) {
                int oddTrees = 0;  // 1만큼 더 자라야 하는 횟수 (홀수날용)
                int evenTrees = 0; // 2만큼 더 자라야 하는 횟수 (짝수날용)

                for (int i = 0; i < N; i++) {
                    int diff = target - trees[i];
                    if (diff == 0) continue;

                    evenTrees += diff / 2;
                    oddTrees += diff % 2;
                }

                // 2를 1+1로 쪼개서 균형 맞추기 (짝수날이 너무 많이 남는 경우 방지)
                if (evenTrees > oddTrees) {
                    while (Math.abs(evenTrees - oddTrees) > 1) {
                        evenTrees--;
                        oddTrees += 2;
                    }
                }

                int ans = 0;
                if (oddTrees > evenTrees) {
                    // 홀수날이 더 많으면 홀-짝-홀-짝-홀... 순서로 끝남
                    ans = oddTrees * 2 - 1;
                } else if (evenTrees > oddTrees) {
                    // 짝수날이 더 많으면 (위의 조절로 최대 1개 차이) 짝수날에 끝남
                    ans = evenTrees * 2;
                } else {
                    // 개수가 같으면 모든 날을 다 씀
                    ans = oddTrees + evenTrees;
                }
                
                minDays = Math.min(minDays, ans);
            }

            sb.append('#').append(tc).append(' ').append(minDays).append('\n');
        }
        System.out.println(sb);
    }
}