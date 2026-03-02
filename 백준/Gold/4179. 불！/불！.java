import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static final int[] dr = {-1, 1, 0, 0};
    private static final int[] dc = {0, 0, -1, 1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        char[][] map = new char[r][c];
        int[][] fireMap = new int[r][c];

        for (int[] ints : fireMap) {
            Arrays.fill(ints, -1);
        }

        int minTime = Integer.MAX_VALUE;
        int sr = -1, sc = -1;
        ArrayList<int[]> firePlace = new ArrayList<>();
        for (int i = 0; i < r; i++) {
            String line = br.readLine();
            for (int j = 0; j < c; j++) {
                map[i][j] = line.charAt(j);
                if (map[i][j] == 'J') {
                    sr = i;
                    sc = j;
                }

                if (map[i][j] == 'F') {
                    firePlace.add(new int[] {i, j, 0});
                }
            }
        }

        boolean[][] visited = new boolean[r][c];
        Queue<int[]> queue = new ArrayDeque<>();

        for (int[] ints : firePlace) {
            queue.add(ints);
            visited[ints[0]][ints[1]] = true;
        }

        while (!queue.isEmpty()) {
            int[] curPos = queue.poll();
            fireMap[curPos[0]][curPos[1]] = curPos[2];
            for (int d = 0; d < 4; d++) {
                int nr = curPos[0] + dr[d];
                int nc = curPos[1] + dc[d];

                if (nr < 0 || nr >= r || nc < 0 || nc >= c
                        || visited[nr][nc] || map[nr][nc] == '#') continue;

                queue.add(new int[] {nr, nc, curPos[2] + 1});
                visited[nr][nc] = true;
            }
        }

        for (boolean[] booleans : visited) {
            Arrays.fill(booleans, false);
        }

        queue.add(new int[] {sr, sc, 0});
        visited[sr][sc] = true;

        while (!queue.isEmpty()) {
            int[] curPos = queue.poll();
            for (int d = 0; d < 4; d++) {
                int nr = curPos[0] + dr[d];
                int nc = curPos[1] + dc[d];

                if (nr < 0 || nr >= r || nc < 0 || nc >= c) {
                    minTime = Math.min(minTime, curPos[2] + 1);
                    break;
                }

                if (!visited[nr][nc] && map[nr][nc] == '.' &&
                    (curPos[2] + 1 < fireMap[nr][nc] || fireMap[nr][nc] == -1)) {
                    queue.add(new int[] {nr, nc, curPos[2] + 1});
                    visited[nr][nc] = true;
                }
            }
        }

        if (minTime == Integer.MAX_VALUE) System.out.println("IMPOSSIBLE");
        else System.out.println(minTime);
    }
}