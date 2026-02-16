import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
    private static int D, W, K, minInjection;
    private static int[][] film;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            // 입력 처리
            StringTokenizer st = new StringTokenizer(br.readLine());
            D = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            minInjection = Integer.MAX_VALUE;
            film = new int[D][W];                       // 0 : A, 1 : B
            for (int i = 0; i < D; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < W; j++) {
                    film[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            
            dfs(0, 0);

            sb.append('#').append(tc).append(' ').append(minInjection).append('\n');
        }
        System.out.println(sb);
    }

    static boolean isCellOk(){
        for (int i = 0; i < W; i++) {
            boolean isOk = false;
            for (int j = 0; j < D - (K - 1); j++) {
                int sum = 0;
                for (int k = 0; k < K; k++) sum += film[j + k][i];

                if (sum == 0 || sum == K){
                    isOk = true;
                    break;
                }
            }
            if (!isOk) return false;
        }
        return true;
    }

    static void dfs(int floor, int injectionCnt){
        if (injectionCnt >= minInjection) return;
        if (floor == D){
            if (isCellOk()){
                minInjection = Math.min(minInjection, injectionCnt);
            }
            return;
        }

        int[] temp = Arrays.copyOf(film[floor], W);

        for (int i = 0; i < W; i++) {           // A 주사
            film[floor][i] = 0;
        }

        dfs(floor + 1, injectionCnt + 1);

        film[floor] = Arrays.copyOf(temp, W);

        for (int i = 0; i < W; i++) {           // B 주사
            film[floor][i] = 1;
        }

        dfs(floor + 1, injectionCnt + 1);

        film[floor] = Arrays.copyOf(temp, W);

        dfs(floor + 1, injectionCnt);       // 주사 X
    }
}