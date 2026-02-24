import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class Main {
    private static final int[] dr = {-1, 1, 0, 0};
    private static final int[] dc = {0, 0, -1, 1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[][] grid = new int[N][N];
        boolean[][] normalVisited = new boolean[N][N];
        int[][] colorBlindGrid = new int[N][N];
        boolean[][] colorBlindVisited = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < N; j++) {
                switch (line.charAt(j)) {
                    case 'R' :
                        grid[i][j] = 0;
                        colorBlindGrid[i][j] = 0;
                        break;
                    case 'G' :
                        grid[i][j] = 1;
                        colorBlindGrid[i][j] = 0;
                        break;
                    case 'B' :
                        grid[i][j] = 2;
                        colorBlindGrid[i][j] = 2;
                        break;
                }
            }
        }

        int normalArea = 0;
        Queue<int[]> normalQueue = new ArrayDeque<>();

        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                if (!normalVisited[r][c]) {
                    ++normalArea;
                    normalQueue.add(new int[]{r, c, grid[r][c]});
                    normalVisited[r][c] = true;
                    while (!normalQueue.isEmpty()) {
                        int[] curPos = normalQueue.poll();
                        for (int d = 0; d < 4; d++) {
                            int nr = curPos[0] + dr[d];
                            int nc = curPos[1] + dc[d];
                            if (nr >= 0 && nr < N && nc >= 0 && nc < N &&
                                    !normalVisited[nr][nc] && grid[nr][nc] == curPos[2]) {
                                normalQueue.add(new int[] {nr, nc, grid[nr][nc]});
                                normalVisited[nr][nc] = true;
                            }
                        }
                    }
                }
            }
        }

        int colorBlindArea = 0;
        Queue<int[]> colorBlindQueue = new ArrayDeque<>();

        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                if (!colorBlindVisited[r][c]) {
                    ++colorBlindArea;
                    colorBlindQueue.add(new int[]{r, c, colorBlindGrid[r][c]});
                    colorBlindVisited[r][c] = true;
                    while (!colorBlindQueue.isEmpty()) {
                        int[] curPos = colorBlindQueue.poll();
                        for (int d = 0; d < 4; d++) {
                            int nr = curPos[0] + dr[d];
                            int nc = curPos[1] + dc[d];
                            if (nr >= 0 && nr < N && nc >= 0 && nc < N &&
                                    !colorBlindVisited[nr][nc] && colorBlindGrid[nr][nc] == curPos[2]) {
                                colorBlindQueue.add(new int[] {nr, nc, colorBlindGrid[nr][nc]});
                                colorBlindVisited[nr][nc] = true;
                            }
                        }
                    }
                }
            }
        }

        System.out.println(normalArea + " " + colorBlindArea);
    }
}