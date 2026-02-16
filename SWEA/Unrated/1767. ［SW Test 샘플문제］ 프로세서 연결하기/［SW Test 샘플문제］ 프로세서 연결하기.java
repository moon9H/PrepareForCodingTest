import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Solution {
    private static int N, needToConnectCores, maxCores, minLineSum;
    private static int[][] board;
    private static final int[] dr = {-1, 1, 0, 0};
    private static final int[] dc = {0, 0, -1, 1};
    private static ArrayList<int[]> corePos;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T ; tc++) {
            N = Integer.parseInt(br.readLine());
            board = new int[N][N];needToConnectCores = maxCores = 0;
            minLineSum = Integer.MAX_VALUE;
            corePos = new ArrayList<>();
            for (int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    board[i][j] = Integer.parseInt(st.nextToken());
                    if (board[i][j] == 1) {
                        if (i > 0 && i < N - 1 && j > 0 && j < N - 1) {
                            needToConnectCores++;
                            corePos.add(new int[]{i, j});
                        }
                    }
                }
            }

            dfs(0, 0, 0);

            sb.append('#').append(tc).append(' ').append(minLineSum).append('\n');
        }
        System.out.println(sb);
    }

    static void dfs(int count, int lineSum, int coreSum){
        if (count == needToConnectCores){
            if (coreSum > maxCores){
                maxCores = coreSum;
                minLineSum = lineSum;
            } else if (coreSum == maxCores) minLineSum = Math.min(minLineSum, lineSum);
            
            return;
        }

        int[] curCore = corePos.get(count);

        for (int i = 0; i < 4; i++) {
            if (checkLine(curCore[0], curCore[1], i)){
                int lineLength = makeLine(curCore[0], curCore[1], i, 1);
                dfs(count + 1, lineSum + lineLength, coreSum + 1);
                makeLine(curCore[0], curCore[1], i, 0);
            }
        }

        dfs(count + 1, lineSum, coreSum);
    }

    static boolean checkLine(int row, int col, int dir){
        int nr = row + dr[dir];
        int nc = col + dc[dir];

        while(nr >= 0 && nr < N && nc >= 0 && nc < N){
            if (board[nr][nc] == 1) return false;
            nr += dr[dir];
            nc += dc[dir];
        }

        return true;
    }
    static int makeLine(int row, int col, int dir, int mode){
        int nr = row + dr[dir];
        int nc = col + dc[dir];
        int length = 0;
        while(nr >= 0 && nr < N && nc >= 0 && nc < N){
            board[nr][nc] = mode;
            ++length;
            nr += dr[dir];
            nc += dc[dir];
        }
        return length;
    }
}