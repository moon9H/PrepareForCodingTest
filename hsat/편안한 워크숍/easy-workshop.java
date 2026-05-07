import java.io.*;
import java.util.*;

public class Main {

    static int N, K;
    static int[][] map;
    static int[][] dp;

    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    static List<Node> nodes = new ArrayList<>();

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

        int minH = Integer.MAX_VALUE;
        int maxH = Integer.MIN_VALUE;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());

                minH = Math.min(minH, map[i][j]);
                maxH = Math.max(maxH, map[i][j]);

                nodes.add(new Node(i, j, map[i][j]));
            }
        }

        // 높이가 높은 칸부터 처리하기 위해 정렬
        nodes.sort((a, b) -> b.h - a.h);

        int left = 0;
        int right = maxH - minH;
        int answer = -1;

        while (left <= right) {
            int mid = (left + right) / 2;

            if (canMakeRoad(mid)) {
                answer = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        System.out.println(answer);
    }

    static boolean canMakeRoad(int limit) {
        dp = new int[N][N];

        int maxLength = 1;

        for (Node node : nodes) {
            int r = node.r;
            int c = node.c;

            dp[r][c] = 1;

            for (int d = 0; d < 4; d++) {
                int nr = r + dr[d];
                int nc = c + dc[d];

                if (nr < 0 || nr >= N || nc < 0 || nc >= N) {
                    continue;
                }

                // 반드시 높이가 높아져야 함
                if (map[nr][nc] <= map[r][c]) {
                    continue;
                }

                // 인접한 높이 차가 limit 이하여야 함
                if (map[nr][nc] - map[r][c] > limit) {
                    continue;
                }

                dp[r][c] = Math.max(dp[r][c], dp[nr][nc] + 1);
            }

            maxLength = Math.max(maxLength, dp[r][c]);

            if (maxLength >= K) {
                return true;
            }
        }

        return false;
    }
}