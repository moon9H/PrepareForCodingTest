import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    private static final int[] dr = {-1, 1, 0, 0};
    private static final int[] dc = {0, 0, -1, 1};
    static class RipedTomatoes{
        int row;
        int col;
        int day;

        public RipedTomatoes(int row, int col, int day) {
            this.row = row;
            this.col = col;
            this.day = day;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int w = Integer.parseInt(st.nextToken());
        int h = Integer.parseInt(st.nextToken());
        int[][] grid = new int[h][w];
        Queue<RipedTomatoes> queue = new ArrayDeque<>();
        for (int i = 0; i < h; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < w; j++) {
                grid[i][j] = Integer.parseInt(st.nextToken());
                if (grid[i][j] == 1) queue.add(new RipedTomatoes(i, j, 1));
            }
        }

        while (!queue.isEmpty()) {
            RipedTomatoes curTomato = queue.poll();
            for (int d = 0; d < 4; d++) {
                int nr = curTomato.row + dr[d];
                int nc = curTomato.col + dc[d];

                if (nr >= 0 && nr < h && nc >= 0 && nc < w &&
                    grid[nr][nc] == 0) {
                    grid[nr][nc] = curTomato.day + 1;
                    queue.add(new RipedTomatoes(nr, nc, grid[nr][nc]));
                }
            }
        }
        System.out.println(isAllRiped(grid));
    }

    static int isAllRiped(int[][] board){
        int maxDay = Integer.MIN_VALUE;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == 0) return -1;
                maxDay = Math.max(maxDay, board[i][j]);
            }
        }
        return maxDay - 1;
    }
}