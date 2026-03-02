import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    private static final int[] dr = {0, -1, 1, 0};
    private static final int[] dc = {-1, 0, 0, 1};
    private static int N;
    private static int[][] map;
    static class BabyShark {
        int size;
        int needToEat;
        public BabyShark() {
            this.size = this.needToEat = 2;
        }

        public void eat() {
            if (--needToEat == 0) {
                ++size;
                needToEat = size;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        int sr = -1, sc = -1;
        BabyShark bs = new BabyShark();

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 9) {
                    sr = i;
                    sc = j;
                    map[i][j] = 0;
                }
            }
        }

        int momTime = 0;
        while (true) {
            boolean[][] visited = new boolean[N][N];
            Queue<int[]> queue = new ArrayDeque<>();
            queue.add(new int[] {sr, sc, 0});
            visited[sr][sc] = true;
            int minDist = Integer.MAX_VALUE;
            PriorityQueue<int[]> pq  = new PriorityQueue<>((o1, o2) -> {
                if (o1[2] != o2[2]) return Integer.compare(o1[2], o2[2]);
                if (o1[0] != o2[0]) return Integer.compare(o1[0], o2[0]);
                return Integer.compare(o1[1], o2[1]);
            });

            while (!queue.isEmpty()) {
                int[] curShark = queue.poll();
                for (int d = 0; d < 4; d++) {
                    int nr = curShark[0] + dr[d];
                    int nc = curShark[1] + dc[d];

                    if (nr < 0 || nr >= N || nc < 0 || nc >= N ||
                        visited[nr][nc] || map[nr][nc] > bs.size) continue;

                    queue.add(new int[] {nr, nc, curShark[2] + 1});
                    visited[nr][nc] = true;

                    if (map[nr][nc] != 0 && map[nr][nc] < bs.size
                            && curShark[2] + 1 <= minDist) {
                        minDist = Math.min(curShark[2] + 1, minDist);
                        pq.add(new int[] {nr, nc, minDist});
                    }
                }
            }

            if (pq.isEmpty()) break;

            momTime += minDist;
            sr = pq.peek()[0];
            sc = pq.peek()[1];
            map[sr][sc] = 0;
            bs.eat();
        }

        System.out.println(momTime);
    }
}