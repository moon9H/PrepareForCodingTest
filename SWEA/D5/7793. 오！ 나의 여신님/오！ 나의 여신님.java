import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Solution {
    private static final int[] dr = {-1, 1, 0, 0};
    private static final int[] dc = {0, 0, -1, 1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            char[][] map = new char[N][M];
            int[][] demonMap = new int[N][M];
            for (int[] ints : demonMap) {
                Arrays.fill(ints, -1);
            }
            int suyeonSr = -1, suyeonSc = -1;
            int goddessRow = -1, goddessCol = -1;
            ArrayList<int[]> demonPos = new ArrayList<>();

            for (int i = 0; i < N; i++) {
                String line = br.readLine();
                for (int j = 0; j < M; j++) {
                    map[i][j] = line.charAt(j);
                    switch (map[i][j]) {
                        case 'D' :
                            goddessRow = i;
                            goddessCol = j;
                            break;
                        case 'S' :
                            suyeonSr = i;
                            suyeonSc = j;
                            break;
                        case '*' :
                            demonPos.add(new int[] {i, j});
                            break;
                    }
                }
            }

            Queue<int[]> queue = new ArrayDeque<>();
            boolean[][] visited = new boolean[N][M];

            for (int[] demonPo : demonPos) {
                queue.add(new int[] {demonPo[0], demonPo[1], 0});
                visited[demonPo[0]][demonPo[1]] = true;
            }

            while (!queue.isEmpty()) {
                int[] curPos = queue.poll();
                demonMap[curPos[0]][curPos[1]] = curPos[2];
                for (int d = 0; d < 4; d++) {
                    int nr = curPos[0] + dr[d];
                    int nc = curPos[1] + dc[d];

                    if (nr < 0 || nr >= N || nc < 0 || nc >= M ||
                        visited[nr][nc] || map[nr][nc] == 'X' ||
                        map[nr][nc] == 'D') continue;

                    queue.add(new int[] {nr, nc, curPos[2] + 1});
                    visited[nr][nc] = true;
                }
            }

            for (boolean[] booleans : visited) {
                Arrays.fill(booleans, false);
            }

            int minTime = Integer.MAX_VALUE;
            queue.add(new int[] {suyeonSr, suyeonSc, 0});
            visited[suyeonSr][suyeonSc] = true;

            while (!queue.isEmpty()) {
                int[] curPos = queue.poll();

                if (map[curPos[0]][curPos[1]] == 'D') {
                    minTime = Math.min(minTime, curPos[2]);
                }
                for (int d = 0; d < 4; d++) {
                    int nr = curPos[0] + dr[d];
                    int nc = curPos[1] + dc[d];

                    if (nr < 0 || nr >= N || nc < 0 || nc >= M ||
                        visited[nr][nc]) continue;

                    if (map[nr][nc] != 'X' && (demonMap[nr][nc] == -1 ||
                            demonMap[nr][nc] > curPos[2] + 1)) {
                        queue.add(new int[] {nr, nc, curPos[2] + 1});
                        visited[nr][nc] = true;
                    }
                }
            }

            sb.append('#').append(tc).append(' ');
            if (minTime == Integer.MAX_VALUE) sb.append("GAME OVER");
            else sb.append(minTime);
            sb.append('\n');
        }
        System.out.println(sb);
    }
}