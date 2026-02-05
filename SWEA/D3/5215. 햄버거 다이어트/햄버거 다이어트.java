import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    private static int maxScore;
    private static int[][] ingredients;
    private static int[] result;
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

            for (int i = 1; i <= N; i++){
                result = new int[i];
                combination(0, 0, i);
            }

            sb.append("#").append(tc).append(" ").append(maxScore).append("\n");
        }
        System.out.println(sb);
    }

    public static void combination(int cnt, int start, int r){
        if (cnt ==  r){
            int scoreSum = 0;
            int calorieSum = 0;
            for (int i : result){
                scoreSum += ingredients[i][0];
                calorieSum += ingredients[i][1];
            }

            if (calorieSum < limitCalorie) maxScore = Math.max(scoreSum, maxScore);

            return;
        }

        for (int i = start; i < N; i++){
            result[cnt] = i;
            combination(cnt + 1, i + 1, r);
        }
    }
}