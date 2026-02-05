import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    private static int maxScore;
    private static int[][] ingredients;
    private static int limitCalorie, N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++){
            // 입력 처리 파트
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            limitCalorie = Integer.parseInt(st.nextToken());
            ingredients = new int[N][2];
            maxScore = 0;
            for (int i = 0; i < N; i++){
                st = new StringTokenizer(br.readLine());
                ingredients[i][0] = Integer.parseInt(st.nextToken());
                ingredients[i][1] = Integer.parseInt(st.nextToken());
            }

            combination(0, 0, 0);

            sb.append("#").append(tc).append(" ").append(maxScore).append("\n");
        }
        System.out.println(sb);
    }

    public static void combination(int cnt, int calorieSum, int scoreSum){
        if (calorieSum > limitCalorie) return;
        maxScore = Math.max(maxScore, scoreSum);
        if (cnt == N) return;

        combination(cnt + 1,
                calorieSum + ingredients[cnt][1],
                scoreSum + ingredients[cnt][0]);

        combination(cnt + 1, calorieSum, scoreSum);
    }
}