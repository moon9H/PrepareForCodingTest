import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    private static int N, M, D, maxKill, enemy;
    private static int[][] map;
    private static ArrayList<Integer> pos = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());
        maxKill = Integer.MIN_VALUE;
        map = new int[N][M];
        enemy = 0;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 1) ++enemy;
            }
        }

        dfs(0, 0);

        System.out.println(maxKill);
    }

    static int[] pickTarget(int[][] playBoard, int archerCol) {
        // 좌, 상, 우 (이 순서가 "왼쪽 우선"을 보장)
        int[] rr = {0, -1, 0};
        int[] cc = {-1, 0, 1};

        boolean[][] vis = new boolean[N][M];
        ArrayDeque<int[]> q = new ArrayDeque<>();

        // 궁수는 (N, archerCol)에 있다고 보고, 첫 탐색 칸은 (N-1, archerCol) (거리 1)
        int sr = N - 1, sc = archerCol;
        q.add(new int[]{sr, sc, 1});
        vis[sr][sc] = true;

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int r = cur[0], c = cur[1], dist = cur[2];

            // 가장 먼저 발견되는 적이 곧 "가장 가까움 + 왼쪽 우선" 타겟
            if (playBoard[r][c] == 1) return new int[]{r, c};

            if (dist == D) continue; // 사거리 끝이면 더 확장 X

            for (int k = 0; k < 3; k++) {
                int nr = r + rr[k];
                int nc = c + cc[k];

                if (nr < 0 || nr >= N || nc < 0 || nc >= M) continue;
                if (vis[nr][nc]) continue;

                vis[nr][nc] = true;
                q.add(new int[]{nr, nc, dist + 1});
            }
        }
        return null; // 사거리 내에 적 없음
    }

    static void playCastleDefence() {
        int[][] playBoard = new int[N][M];
        int curEnemy = enemy;
        for (int i = 0; i < N; i++) {
            playBoard[i] = Arrays.copyOf(map[i], M);
        }
        int kill = 0;
        while (curEnemy > 0) {
            // 1. 궁수 위치 기반으로 타겟 선정
            ArrayList<int[]> targets = new ArrayList<>();
            for (Integer archerPos : pos) {
                int[] target = pickTarget(playBoard, archerPos);
                if (target != null) targets.add(target);
            }

            // 2. 타겟 삭제 및 킬 수 업데이트
            for (int[] target : targets) {
                if (playBoard[target[0]][target[1]] == 1) {
                    playBoard[target[0]][target[1]] = 0;
                    kill++;
                    curEnemy--;
                }
            }
            for (int i = 0; i < M; i++) {
                curEnemy -= playBoard[N - 1][i];
            }
            // 3. 남은 적군 한 칸 진격
            for (int r = N - 2; r >= 0; r--) {
                for (int c = 0; c < M; c++) {
                    playBoard[r + 1][c] = playBoard[r][c];
                }
            }
            Arrays.fill(playBoard[0], 0); // 맨 윗줄은 비워짐
        }

        maxKill = Math.max(maxKill, kill);
    }

    static void dfs(int count, int start) {
        if (count ==  3) {
            playCastleDefence();
            return;
        }

        for (int i = start; i < M; i++) {
            pos.add(i);
            dfs(count + 1, i + 1);
            pos.remove(pos.size() - 1);
        }
    }
}