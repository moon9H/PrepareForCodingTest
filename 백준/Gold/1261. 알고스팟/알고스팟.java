import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
    private static final int[] dr = {-1, 1, 0, 0};
    private static final int[] dc = {0, 0, -1, 1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int M = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());
        int[][] map = new int[N][M];
        int[][] dist = new int[N][M];
        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = line.charAt(j) - '0';
            }
        }
        for (int[] ints : dist) {
            Arrays.fill(ints, Integer.MAX_VALUE);
        }
        Deque<int[]> dq = new ArrayDeque<>();
        dist[0][0] = 0;
        dq.add(new int[]{0, 0, 0});

        while (!dq.isEmpty()){
            int[] curPos = dq.poll();

            for (int d = 0; d < 4; d++) {
                int nr = curPos[0] + dr[d];
                int nc = curPos[1] + dc[d];

                if (nr < 0 || nr >= N || nc < 0 || nc >= M) continue;

                if (dist[nr][nc] > curPos[2] + map[nr][nc]){
                    dist[nr][nc] = curPos[2] + map[nr][nc];
                    if (map[nr][nc] == 0){
                        dq.addFirst(new int[] {nr, nc, dist[nr][nc]});
                    } else {
                        dq.add(new int[] {nr, nc, dist[nr][nc]});
                    }
                }
            }
        }
        System.out.println(dist[N - 1][M - 1]);
    }
}