import java.io.*;
import java.util.*;

public class Main {

    static int N, K;
    static int[][] map;

    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    static class Node {
        int r;
        int c;
        int h;

        Node(int r, int c, int h) {
            this.r = r;
            this.c = c;
            this.h = h;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new int[N][N];
        List<Node> nodes = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                nodes.add(new Node(i, j, map[i][j]));
            }
        }

        // 낮은 높이부터 처리
        nodes.sort((a, b) -> a.h - b.h);

        int INF = Integer.MAX_VALUE;

        // dp[r][c][len]
        int[][][] dp = new int[N][N][K + 1];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                Arrays.fill(dp[i][j], INF);
                dp[i][j][1] = 0;
            }
        }

        for (Node node : nodes) {
            int r = node.r;
            int c = node.c;

            for (int len = 1; len < K; len++) {
                if (dp[r][c][len] == INF) {
                    continue;
                }

                for (int d = 0; d < 4; d++) {
                    int nr = r + dr[d];
                    int nc = c + dc[d];

                    if (nr < 0 || nr >= N || nc < 0 || nc >= N) {
                        continue;
                    }

                    // 더 높은 칸으로만 이동 가능
                    if (map[nr][nc] <= map[r][c]) {
                        continue;
                    }

                    int diff = map[nr][nc] - map[r][c];

                    // 지금까지의 최대 높이 차와 이번 이동 높이 차 중 큰 값
                    int nextCost = Math.max(dp[r][c][len], diff);

                    dp[nr][nc][len + 1] = Math.min(dp[nr][nc][len + 1], nextCost);
                }
            }
        }

        int answer = INF;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                answer = Math.min(answer, dp[i][j][K]);
            }
        }

        if (answer == INF) {
            System.out.println(-1);
        } else {
            System.out.println(answer);
        }
    }
}