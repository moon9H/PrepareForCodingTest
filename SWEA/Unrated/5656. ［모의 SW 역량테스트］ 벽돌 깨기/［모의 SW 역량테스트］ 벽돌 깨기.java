import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
    static int N, W, H, ans;
    static final int[] dr = {-1, 1, 0, 0};
    static final int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine().trim());

        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
            H = Integer.parseInt(st.nextToken());

            int[][] map = new int[H][W];
            for (int i = 0; i < H; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < W; j++) map[i][j] = Integer.parseInt(st.nextToken());
            }

            ans = Integer.MAX_VALUE;
            // 초기 벽돌 개수를 세어서 넘겨줌
            dfs(0, map, countBricks(map));
            System.out.println("#" + tc + " " + (ans == Integer.MAX_VALUE ? 0 : ans));
        }
    }

    static void dfs(int depth, int[][] map, int remain) {
        // 모든 벽돌이 이미 깨졌다면 결과는 0
        if (remain == 0) {
            ans = 0;
            return;
        }

        // 구슬을 N번 다 던졌을 때
        if (depth == N) {
            ans = Math.min(ans, remain);
            return;
        }

        // 모든 열에 구슬을 던져봄
        for (int col = 0; col < W; col++) {
            int row = topBrickRow(map, col);
            
            // 해당 열에 벽돌이 없으면 다음 구슬로 넘어감
            if (row == -1) {
                dfs(depth + 1, map, remain);
                continue;
            }

            // 맵 복사 및 시뮬레이션
            int[][] nextMap = copyMap(map);
            int removed = explode(nextMap, row, col); // 폭발
            applyGravity(nextMap); // 중력
            
            dfs(depth + 1, nextMap, remain - removed);
            
            // 이미 최적해(0)를 찾았다면 더 이상의 탐색은 무의미
            if (ans == 0) return;
        }
    }

    // BFS 기반 연쇄 폭발
    static int explode(int[][] map, int sr, int sc) {
        Queue<int[]> queue = new ArrayDeque<>();
        int removed = 0;

        // 시작 벽돌 처리
        int power = map[sr][sc];
        map[sr][sc] = 0;
        queue.offer(new int[]{sr, sc, power});
        removed++;

        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int r = curr[0];
            int c = curr[1];
            int p = curr[2];

            if (p <= 1) continue;

            for (int d = 0; d < 4; d++) {
                for (int k = 1; k < p; k++) {
                    int nr = r + dr[d] * k;
                    int nc = c + dc[d] * k;

                    if (nr >= 0 && nr < H && nc >= 0 && nc < W && map[nr][nc] != 0) {
                        removed++;
                        // 큐에 넣기 직전에 0으로 만들어야 중복 삽입이 안 됨 (메모리 관리 핵심)
                        queue.offer(new int[]{nr, nc, map[nr][nc]});
                        map[nr][nc] = 0;
                    }
                }
            }
        }
        return removed;
    }

    // 중력 작용 (벽돌 내리기)
    static void applyGravity(int[][] map) {
        for (int c = 0; c < W; c++) {
            int write = H - 1;
            for (int r = H - 1; r >= 0; r--) {
                if (map[r][c] != 0) {
                    int temp = map[r][c];
                    map[r][c] = 0;
                    map[write--][c] = temp;
                }
            }
        }
    }

    static int topBrickRow(int[][] map, int col) {
        for (int r = 0; r < H; r++) if (map[r][col] != 0) return r;
        return -1;
    }

    static int countBricks(int[][] map) {
        int cnt = 0;
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < W; j++) if (map[i][j] != 0) cnt++;
        }
        return cnt;
    }

    static int[][] copyMap(int[][] map) {
        int[][] copy = new int[H][W];
        for (int i = 0; i < H; i++) System.arraycopy(map[i], 0, copy[i], 0, W);
        return copy;
    }
}