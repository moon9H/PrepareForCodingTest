import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;

public class Solution {
    private static int N;
    private static int[][] map;
    private static final int[] dr = {-1, 1, 0, 0};
    private static final int[] dc = {0, 0, -1, 1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            // 입력 처리
            N = Integer.parseInt(br.readLine());
            map = new int[N][N];
            int[][] dist = new int[N][N];
            for (int i = 0; i < N; i++) {
                Arrays.fill(dist[i], Integer.MAX_VALUE);
            }
            PriorityQueue<int[]> pq = new PriorityQueue<>(
                    (o1, o2) -> o1[2] - o2[2]
            );
            for (int i = 0; i < N; i++) {
                String line = br.readLine();
                for (int j = 0; j < N; j++) {
                    map[i][j] = line.charAt(j) - '0';
                }
            }
            // 시작 지점 마킹 및 큐에 add
            dist[0][0] = map[0][0];
            pq.add(new int[]{0, 0, map[0][0]});
            int minRoute = map[0][0];
            while (!pq.isEmpty()){
                int[] curPos = pq.poll();
                int r = curPos[0];
                int c = curPos[1];
                int cost = curPos[2];

                if (cost > dist[r][c]) continue;

                if (r == N - 1 && c == N - 1) minRoute = cost;

                for (int i = 0; i < 4; i++) {
                    int nr = curPos[0] + dr[i];
                    int nc = curPos[1] + dc[i];
                    if (nr >= 0 && nr < N && nc >= 0 && nc < N){
                        int newCost = cost + map[nr][nc];
                        if (newCost < dist[nr][nc]){
                            dist[nr][nc] = newCost;
                            pq.add(new int[] {nr, nc, newCost});
                        }
                    }
                }
            }

            sb.append('#').append(tc).append(' ').append(minRoute).append('\n');
        }
        System.out.println(sb);
    }
}