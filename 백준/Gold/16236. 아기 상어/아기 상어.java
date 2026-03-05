import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    private static final int[] dr = {-1, 1, 0, 0};
    private static final int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[][] map = new int[N][N];

        int sharkSize = 2, sharkNeedToEat = 2;
        int sr = -1, sc = -1;

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 9) {
                    sr = i;
                    sc = j;
                    map[i][j] = 0; // 시작 위치는 빈 칸으로 처리
                }
            }
        }

        int time = 0;

        while (true) {
            boolean[][] visited = new boolean[N][N];
            Queue<int[]> queue = new ArrayDeque<>();
            queue.add(new int[]{sr, sc, 0});
            visited[sr][sc] = true;

            int minDist = Integer.MAX_VALUE;
            int bestR = -1, bestC = -1;

            while (!queue.isEmpty()) {
                int[] curPos = queue.poll();
                int curR = curPos[0];
                int curC = curPos[1];
                int curD = curPos[2];

                // 이미 최소거리 먹잇감을 찾았으면 그 이상 거리 확장은 필요 없음
                if (curD >= minDist) continue;

                for (int d = 0; d < 4; d++) {
                    int nr = curR + dr[d];
                    int nc = curC + dc[d];

                    if (nr < 0 || nr >= N || nc < 0 || nc >= N) continue;
                    if (visited[nr][nc]) continue;
                    if (map[nr][nc] > sharkSize) continue; // 지나갈 수 없음

                    int nd = curD + 1;
                    visited[nr][nc] = true;
                    queue.add(new int[]{nr, nc, nd});

                    // 먹을 수 있는 물고기 후보
                    if (map[nr][nc] != 0 && map[nr][nc] < sharkSize) {
                        if (nd < minDist) {
                            minDist = nd;
                            bestR = nr;
                            bestC = nc;
                        } else if (nd == minDist) {
                            if (nr < bestR || (nr == bestR && nc < bestC)) {
                                bestR = nr;
                                bestC = nc;
                            }
                        }
                    }
                }
            }

            if (bestR == -1) break; // 먹을 수 있는 물고기 없음

            // 이동 + 먹기
            time += minDist;
            sr = bestR;
            sc = bestC;
            map[sr][sc] = 0;

            if (--sharkNeedToEat == 0) {
                sharkSize++;
                sharkNeedToEat = sharkSize;
            }
        }

        System.out.println(time);
    }
}