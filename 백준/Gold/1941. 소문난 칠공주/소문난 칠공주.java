import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Queue;

public class Main {
    private static int answer;
    private static final int[] dr = {-1, 1, 0, 0};
    private static final int[] dc = {0, 0, -1, 1};
    private static char[][] board;
    private static final ArrayList<Integer> sevenPrincess = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        board = new char[5][5];
        answer = 0;
        for (int i = 0; i < 5; i++) {
            String line = br.readLine();
            for (int j = 0; j < 5; j++) {
                board[i][j] = line.charAt(j);
            }
        }
        dfs(0, 0, 0, 0);

        System.out.println(answer);
    }

    static boolean isConnected() {
        boolean[][] canGo = new boolean[5][5];
        boolean[][] visited = new boolean[5][5];
        for (Integer i : sevenPrincess) {
            canGo[i / 5][i % 5] = true;
        }

        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(new int[]{sevenPrincess.get(0) / 5, sevenPrincess.get(0) % 5});
        visited[sevenPrincess.get(0) / 5][sevenPrincess.get(0) % 5] = true;

        while (!queue.isEmpty()) {
            int[] curPos = queue.poll();

            for (int d = 0; d < 4; d++) {
                int nr = curPos[0] + dr[d];
                int nc = curPos[1] + dc[d];

                if (nr < 0 || nr >= 5 || nc < 0 || nc >= 5 ||
                    visited[nr][nc] || !canGo[nr][nc])
                    continue;

                queue.add(new int[]{nr, nc});
                visited[nr][nc] = true;
            }
        }

        for (Integer i : sevenPrincess) {
            if (!visited[i / 5][i % 5]) return false;
        }
        return true;
    }

    static void dfs(int count, int start, int yeon, int som) {
        if (yeon >= 4) return;

        if (count == 7) {
            if (isConnected()) ++answer;
            return;
        }

        for (int i = start; i < 25; i++) {
            sevenPrincess.add(i);
            if (board[i / 5][i % 5] == 'Y') {
                dfs(count + 1, i + 1, yeon + 1, som);
            } else {
                dfs(count + 1, i + 1, yeon, som + 1);
            }
            sevenPrincess.remove(sevenPrincess.size() - 1);
        }
    }
}