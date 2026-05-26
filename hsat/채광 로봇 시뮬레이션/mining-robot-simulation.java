import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    
    private static int N, T;
    private static int[][] board;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());
        
        board = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        
        int[][] fromStart = new int[N][N];
        int[][] toEnd = new int[N][N];

        // 1. 시작점에서 각 칸까지 올 때 얻을 수 있는 최대 이득
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                fromStart[r][c] = Integer.MIN_VALUE;

                if (r == 0 && c == 0) {
                    fromStart[r][c] = board[r][c];
                    continue;
                }

                if (r > 0) {
                    fromStart[r][c] = Math.max(fromStart[r][c], fromStart[r - 1][c] + board[r][c]);
                }

                if (c > 0) {
                    fromStart[r][c] = Math.max(fromStart[r][c], fromStart[r][c - 1] + board[r][c]);
                }
            }
        }

        // 2. 각 칸에서 도착점까지 갈 때 얻을 수 있는 최대 이득
        for (int r = N - 1; r >= 0; r--) {
            for (int c = N - 1; c >= 0; c--) {
                toEnd[r][c] = Integer.MIN_VALUE;

                if (r == N - 1 && c == N - 1) {
                    toEnd[r][c] = board[r][c];
                    continue;
                }

                if (r + 1 < N) {
                    toEnd[r][c] = Math.max(toEnd[r][c], toEnd[r + 1][c] + board[r][c]);
                }

                if (c + 1 < N) {
                    toEnd[r][c] = Math.max(toEnd[r][c], toEnd[r][c + 1] + board[r][c]);
                }
            }
        }

        /*
         * rewindDp[r][c][t]
         *
         * 의미:
         * (r, c)에서 출발해서 정확히 t초 동안 이동했을 때,
         * 추가로 얻을 수 있는 최대 이득
         *
         * 주의:
         * 시작 칸 (r, c)의 값은 포함하지 않는다.
         * 왜냐하면 (r, c)는 이미 fromStart에서 한 번 먹고,
         * 시간 역행 후 toEnd에서 다시 한 번 먹기 때문이다.
         */
        int[][][] rewindDp = new int[N][N][T + 1];

        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                for (int t = 0; t <= T; t++) {
                    rewindDp[r][c][t] = Integer.MIN_VALUE;
                }
            }
        }

        // 0초 동안 이동하면 추가로 얻는 이득은 0
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                rewindDp[r][c][0] = 0;
            }
        }

        // 정확히 t초 동안 이동하는 경우 계산
        for (int t = 1; t <= T; t++) {
            for (int r = N - 1; r >= 0; r--) {
                for (int c = N - 1; c >= 0; c--) {

                    // 아래로 이동
                    if (r + 1 < N && rewindDp[r + 1][c][t - 1] != Integer.MIN_VALUE) {
                        rewindDp[r][c][t] = Math.max(
                            rewindDp[r][c][t],
                            board[r + 1][c] + rewindDp[r + 1][c][t - 1]
                        );
                    }

                    // 오른쪽으로 이동
                    if (c + 1 < N && rewindDp[r][c + 1][t - 1] != Integer.MIN_VALUE) {
                        rewindDp[r][c][t] = Math.max(
                            rewindDp[r][c][t],
                            board[r][c + 1] + rewindDp[r][c + 1][t - 1]
                        );
                    }
                }
            }
        }

        // 시간 역행을 아예 사용하지 않는 경우
        int answer = fromStart[N - 1][N - 1];

        /*
         * 시간 역행을 사용하는 경우
         *
         * 어떤 칸 (r, c)에서 시작해서 T초 동안 이동한다.
         * 그 후 시간 역행으로 다시 (r, c)로 돌아온다.
         *
         * 총 이득:
         * 시작점 -> (r, c)까지의 최대 이득
         * + (r, c)에서 T초 동안 이동하며 얻는 이득
         * + 시간 역행 후 (r, c) -> 도착점까지의 최대 이득
         */
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                if (rewindDp[r][c][T] == Integer.MIN_VALUE) continue;

                int total = fromStart[r][c] + rewindDp[r][c][T] + toEnd[r][c];
                answer = Math.max(answer, total);
            }
        }

        System.out.println(answer);
    }
}