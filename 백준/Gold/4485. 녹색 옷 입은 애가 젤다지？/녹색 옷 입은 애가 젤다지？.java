import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    private static final int[] dr = {-1, 1, 0, 0};
    private static final int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int idx = 1;
        while (true) {
            int N = Integer.parseInt(br.readLine());
            if (N == 0) break;
            int[][] map = new int[N][N];
            int[][] dist = new int[N][N];

            for (int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
                Arrays.fill(dist[i], Integer.MAX_VALUE);
            }

            PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) ->
                                                            Integer.compare(o1[0], o2[0]));
            dist[0][0] = map[0][0];
            pq.add(new int[] {dist[0][0], 0, 0});

            while (!pq.isEmpty()) {
                int[] curPos = pq.poll();

                if (curPos[0] != dist[curPos[1]][curPos[2]]) continue;

                for (int d = 0; d < 4; d++) {
                    int nr = curPos[1] + dr[d];
                    int nc = curPos[2] + dc[d];

                    if (nr < 0 || nr >= N || nc < 0 || nc >= N) continue;

                    int newCost = map[nr][nc] + curPos[0];

                    if (newCost < dist[nr][nc]) {
                        dist[nr][nc] = newCost;
                        pq.add(new int[] {newCost, nr, nc});
                    }
                }
            }


            sb.append("Problem ").append(idx).append(": ").append(dist[N-1][N-1]).append('\n');
            ++idx;
        }
        System.out.println(sb);
    }
}