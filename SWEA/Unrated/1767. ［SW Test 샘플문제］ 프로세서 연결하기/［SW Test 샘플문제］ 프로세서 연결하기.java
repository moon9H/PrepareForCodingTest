import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {
    private static int N, maxCore, minSum;
    private static int[][] board;
    private static boolean[][] cantConnect;
    private static List<int[]> corePos;
    private final static int[] dirRow = {-1, 0, 1, 0};
    private final static int[] dirCol = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        for (int t = 1; t <= T; t++) {
            N = Integer.parseInt(br.readLine());
            board = new int[N][N];
            cantConnect = new boolean[N][N];
            corePos = new ArrayList<>();
            maxCore = 0;
            minSum = Integer.MAX_VALUE;

            for (int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    board[i][j] = Integer.parseInt(st.nextToken());
                    if (board[i][j] == 1) {
                        cantConnect[i][j] = true;
                        if (!(i == 0 || i == N - 1 || j == 0 || j == N - 1)) {
                            corePos.add(new int[]{i, j});
                        }
                    }
                }
            }

            setLine(0, 0, 0);
            
            sb.append("#").append(t).append(" ").append(minSum).append("\n");
        }
        System.out.println(sb);
    }

    static void setLine(int index, int connectedCount, int lineSum) {
        if (index == corePos.size()) {
            if (connectedCount > maxCore) {
                maxCore = connectedCount;
                minSum = lineSum;
            } else if (connectedCount == maxCore) {
                minSum = Math.min(minSum, lineSum);
            }
            return;
        }

        int[] cur = corePos.get(index);
        int r = cur[0];
        int c = cur[1];

        for (int i = 0; i < 4; i++) {
            if (linePossible(r, c, i)) {
                int dist = connect(r, c, i, true); 
                setLine(index + 1, connectedCount + 1, lineSum + dist);
                connect(r, c, i, false); 
            }
        }

        setLine(index + 1, connectedCount, lineSum);
    }

    static boolean linePossible(int curRow, int curCol, int dir) {
        int nr = curRow + dirRow[dir];
        int nc = curCol + dirCol[dir];

        while (nr >= 0 && nr < N && nc >= 0 && nc < N) {
            if (cantConnect[nr][nc]) return false;
            nr += dirRow[dir];
            nc += dirCol[dir];
        }
        return true;
    }

    
    static int connect(int curRow, int curCol, int dir, boolean state) {
        int nr = curRow + dirRow[dir];
        int nc = curCol + dirCol[dir];
        int count = 0;

        while (nr >= 0 && nr < N && nc >= 0 && nc < N) {
            cantConnect[nr][nc] = state;
            count++;
            nr += dirRow[dir];
            nc += dirCol[dir];
        }
        return count;
    }
}