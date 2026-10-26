import java.util.*;

class Solution {
    // 상 하 좌 우
    private static int[] dr = {-1, 1, 0, 0};
    private static int[] dc = {0, 0, -1, 1};
    private static int[][] dist;
    private static boolean[][] visited;
    
    public int solution(int[][] maps) {
        int n = maps.length;
        int m = maps[0].length;
        
        dist = new int[n][m];
        for (int[] line : dist) Arrays.fill(line, -1);
        
        Deque<int[]> dq = new ArrayDeque<>();
        
        dist[0][0] = 1;
        dq.add(new int[] {0, 0});
        
        while(!dq.isEmpty()) {
            int[] pos = dq.pop();
            
            for (int d = 0; d < 4; d++) {
                int nRow = pos[0] + dr[d];
                int nCol = pos[1] + dc[d];
                
                if (nRow >= n || nRow < 0 || nCol < 0 || nCol >= m 
                    || maps[nRow][nCol] == 0 || dist[nRow][nCol] != -1) continue;
                
                dist[nRow][nCol] = dist[pos[0]][pos[1]] + 1;
                dq.add(new int[] {nRow, nCol});
            }
        }
        
        return dist[n - 1][m - 1];
    }
}