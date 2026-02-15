import java.io.*;
import java.util.*;

public class Main {
    static class State {
        int r, c, dir; // 끝점 (r,c), dir: 0가로 1세로 2대각
        State(int r, int c, int dir) { this.r = r; this.c = c; this.dir = dir; }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[][] board = new int[N][N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) board[i][j] = Integer.parseInt(st.nextToken());
        }

        long count = 0;
        ArrayDeque<State> q = new ArrayDeque<>();
        q.add(new State(0, 1, 0)); // 시작: 끝점(0,1), 가로

        while (!q.isEmpty()) {
            State cur = q.poll();

            int r = cur.r, c = cur.c, dir = cur.dir;

            // 도착 체크: 끝점이 (N-1,N-1)
            if (r == N - 1 && c == N - 1) {
                count++;
                continue;
            }

            // 1) 오른쪽 이동 (가로로)
            // 가능 dir: 가로(0), 대각(2)
            if ((dir == 0 || dir == 2) && c + 1 < N && board[r][c + 1] == 0) {
                q.add(new State(r, c + 1, 0));
            }

            // 2) 아래 이동 (세로로)
            // 가능 dir: 세로(1), 대각(2)
            if ((dir == 1 || dir == 2) && r + 1 < N && board[r + 1][c] == 0) {
                q.add(new State(r + 1, c, 1));
            }

            // 3) 대각 이동
            // 가능 dir: 전부(0,1,2)에서 가능하지만, 3칸 비어야 함
            if (r + 1 < N && c + 1 < N
                    && board[r][c + 1] == 0
                    && board[r + 1][c] == 0
                    && board[r + 1][c + 1] == 0) {
                q.add(new State(r + 1, c + 1, 2));
            }
        }

        System.out.println(count);
    }
}