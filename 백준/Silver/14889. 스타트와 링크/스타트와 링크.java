import java.io.*;
import java.util.*;

public class Main {
    private static int N, diff;
    private static int[] result;
    private static int[][] board;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        diff = Integer.MAX_VALUE;

        board = new int[N][N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        result = new int[N / 2];
        result[0] = 1;          // 1번을 스타트팀에 고정 (중복 제거 핵심)
        dfs(1, 2);              // 이미 1명 뽑았으니 cnt=1, 다음 후보는 2부터

        System.out.println(diff);
    }

    static void dfs(int cnt, int start) {
        if (cnt == N / 2) {
            int startTeam = 0, linkTeam = 0;

            boolean[] isSelected = new boolean[N + 1];
            for (int i = 0; i < N / 2; i++) isSelected[result[i]] = true;

            int[] linkResult = new int[N / 2];
            int idx = 0;
            for (int i = 1; i <= N; i++) {
                if (!isSelected[i]) linkResult[idx++] = i;
            }

            for (int i = 0; i < N / 2 - 1; i++) {
                for (int j = i + 1; j < N / 2; j++) {
                    int a = result[i] - 1, b = result[j] - 1;
                    startTeam += board[a][b] + board[b][a];

                    int c = linkResult[i] - 1, d = linkResult[j] - 1;
                    linkTeam += board[c][d] + board[d][c];
                }
            }

            diff = Math.min(diff, Math.abs(startTeam - linkTeam));
            return;
        }

        for (int i = start; i <= N; i++) {   // ✅ 여기 N/2가 아니라 N
            result[cnt] = i;
            dfs(cnt + 1, i + 1);
        }
    }
}