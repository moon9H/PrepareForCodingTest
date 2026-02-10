import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
 
public class Solution {
    private static int N, maxCore, minSum;
    private static int[][] board;
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
            corePos = new ArrayList<>();
            maxCore = 0;
            minSum = Integer.MAX_VALUE;
 
            for (int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    board[i][j] = Integer.parseInt(st.nextToken());
                    if (board[i][j] == 1) {
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
 
    static void setLine(int coreIdx, int connectedCore, int lineSum) {
    	if (connectedCore + corePos.size() - coreIdx < maxCore) return;
        
    	if (coreIdx == corePos.size()) {
            if (connectedCore > maxCore) {
                maxCore = connectedCore;
                minSum = lineSum;
            } else if (connectedCore == maxCore) {
                minSum = Math.min(minSum, lineSum);
            }
            return;
        }
 
        int[] cur = corePos.get(coreIdx);
        int r = cur[0];
        int c = cur[1];
 
        for (int i = 0; i < 4; i++) {
            if (linePossible(r, c, i)) {
                int dist = connect(r, c, i, 1);							// 해당 방향으로 전선 연결 가능
                setLine(coreIdx + 1, connectedCore + 1, lineSum + dist);
                connect(r, c, i, 0); 									// 전선 연결 해제
            }
        }
 
        setLine(coreIdx + 1, connectedCore, lineSum);						// 전선을 연결할 수 없는 경우
    }
 
    static boolean linePossible(int curRow, int curCol, int dir) {
        int nr = curRow + dirRow[dir];
        int nc = curCol + dirCol[dir];
 
        while (nr >= 0 && nr < N && nc >= 0 && nc < N) {
            if (board[nr][nc] == 1) return false;
            nr += dirRow[dir];
            nc += dirCol[dir];
        }
        return true;
    }
 
    static int connect(int curRow, int curCol, int dir, int state) {
        int nr = curRow + dirRow[dir];
        int nc = curCol + dirCol[dir];
        int lineLength = 0;
 
        while (nr >= 0 && nr < N && nc >= 0 && nc < N) {
            board[nr][nc] = state;
            lineLength++;
            nr += dirRow[dir];
            nc += dirCol[dir];
        }
        return lineLength;
    }
}