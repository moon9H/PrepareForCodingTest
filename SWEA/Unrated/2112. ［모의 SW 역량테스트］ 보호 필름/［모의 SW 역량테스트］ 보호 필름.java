import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
    private static int D, W, K, minInjection;
    private static boolean[] selected;
    private static int[][] board;

    // 조합 크기(몇 개 행을 주입할지)
    private static int targetCnt;

    // selected 된 행 인덱스들
    private static int[] selectedRows;

    // selectedRows 각 행의 원본 백업
    private static int[][] backup;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            D = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());

            board = new int[D][W];
            selected = new boolean[D];
            minInjection = Integer.MAX_VALUE;

            for (int i = 0; i < D; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < W; j++) {
                    board[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            // K==1이면 무조건 통과
            if (K == 1) {
                minInjection = 0;
            } else {
                // 주입 0회로 통과하면 0
                if (check()) {
                    minInjection = 0;
                } else {
                    // 1 ~ D회 주입 시도 (최소 찾는 방식)
                    for (int cnt = 1; cnt <= D; cnt++) {
                        if (cnt >= minInjection) break;

                        targetCnt = cnt;
                        Arrays.fill(selected, false);

                        selectedRows = new int[targetCnt];
                        backup = new int[targetCnt][W];

                        dfs(0, 0);

                        // cnt로 답을 찾았으면 더 볼 필요 없음
                        if (minInjection == cnt) break;
                    }
                }
            }

            sb.append('#').append(tc).append(' ').append(minInjection).append('\n');
        }
        System.out.println(sb);
    }

    // 조합: D개 행 중 targetCnt개를 선택
    static void dfs(int count, int start) {
        if (count >= minInjection) return; // 이미 더 나쁨
        if (count == targetCnt) {
            // selectedRows 채우고, 백업
            int idx = 0;
            for (int i = 0; i < D; i++) {
                if (selected[i]) {
                    selectedRows[idx] = i;
                    backup[idx] = Arrays.copyOf(board[i], W);
                    idx++;
                }
            }
            // 선택된 행들에 대해 A/B 주입 결정
            injectDfs(0);
            return;
        }

        // 남은 행 수로 targetCnt 못 채우면 컷
        if (D - start < targetCnt - count) return;

        for (int i = start; i < D; i++) {
            selected[i] = true;
            dfs(count + 1, i + 1);
            selected[i] = false;

            if (minInjection == targetCnt) return; // 현재 cnt에서 답 찾으면 조기 종료
        }
    }

    // 선택된 행들(selectedRows)에 대해 A(0)/B(1) 넣는 DFS
    static void injectDfs(int depth) {
        if (targetCnt >= minInjection) return;

        if (depth == targetCnt) {
            if (check()) {
                minInjection = Math.min(minInjection, targetCnt);
            }
            return;
        }

        int r = selectedRows[depth];

        // A 주입(0)
        Arrays.fill(board[r], 0);
        injectDfs(depth + 1);
        if (minInjection == targetCnt) return;

        // B 주입(1)
        Arrays.fill(board[r], 1);
        injectDfs(depth + 1);
        if (minInjection == targetCnt) return;

        // 원복 (현재 depth 행만 원복하면 됨)
        board[r] = Arrays.copyOf(backup[depth], W);
    }

    // 성능 검사: 모든 열이 K개 이상 연속 동일이면 true
    static boolean check() {
        for (int c = 0; c < W; c++) {
            int run = 1;
            int best = 1;

            for (int r = 1; r < D; r++) {
                if (board[r][c] == board[r - 1][c]) run++;
                else run = 1;

                if (run > best) best = run;
                if (best >= K) break; // 이 열은 통과 확정
            }

            if (best < K) return false;
        }
        return true;
    }
}