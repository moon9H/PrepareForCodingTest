import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static int R, C, maxPath;
    private static char[][] board;
    private static final int[] dr = {-1, 1, 0, 0};
    private static final int[] dc = {0, 0, -1, 1};

    // 알파벳 사용 여부
    private static boolean[] used = new boolean[26];

    static class Node {
        int row;
        int col;
        int len; // 지금까지 경로 길이 (기존 path.length() 역할)

        public Node(int row, int col, int len) {
            this.row = row;
            this.col = col;
            this.len = len;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        board = new char[R][C];

        for (int i = 0; i < R; i++) {
            String line = br.readLine();
            for (int j = 0; j < C; j++) {
                board[i][j] = line.charAt(j);
            }
        }

        maxPath = 1;

        // 시작 알파벳 방문 처리
        used[board[0][0] - 'A'] = true;
        dfs(new Node(0, 0, 1));
        used[board[0][0] - 'A'] = false; // (사실 main 끝이라 없어도 되지만 구조상 깔끔하게)

        System.out.println(maxPath);
    }

    static void dfs(Node curNode) {
        // 기존에는 루프 안에서 계속 갱신했는데, 여기서 한 번만 하는 게 의미적으로 정확
        maxPath = Math.max(maxPath, curNode.len);

        for (int d = 0; d < 4; d++) {
            int nr = curNode.row + dr[d];
            int nc = curNode.col + dc[d];

            if (nr < 0 || nr >= R || nc < 0 || nc >= C) continue;

            int idx = board[nr][nc] - 'A';
            if (used[idx]) continue;

            used[idx] = true;
            dfs(new Node(nr, nc, curNode.len + 1));
            used[idx] = false; // 백트래킹
        }
    }
}