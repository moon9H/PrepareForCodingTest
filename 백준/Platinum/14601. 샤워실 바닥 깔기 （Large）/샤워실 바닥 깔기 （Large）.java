import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[][] map;
    static int N;
    static int tileId = 1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int K = Integer.parseInt(br.readLine().trim());
        N = 1 << K;
        map = new int[N][N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        int x = Integer.parseInt(st.nextToken()); // 1 ~ N, (왼쪽->오른쪽)
        int y = Integer.parseInt(st.nextToken()); // 1 ~ N, (아래->위)

        // BOJ 14600 좌표계: (1,1)이 왼쪽 아래
        // 배열 좌표계: (0,0)이 왼쪽 위
        int r = N - y;
        int c = x - 1;

        map[r][c] = -1; // 배수구

        dfs(0, 0, N);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                sb.append(map[i][j]).append(' ');
            }
            sb.append('\n');
        }
        System.out.print(sb);
    }

    static void dfs(int row, int col, int size) {
        if (size == 1) return;

        int half = size / 2;
        int holePos = findHole(row, col, size); // 1~4 (사분면)

        // 가운데 2x2의 좌상단
        int cr = row + half - 1;
        int cc = col + half - 1;

        // holePos에 해당하는 한 칸만 빼고, 나머지 3칸을 같은 tileId로 채운다
        // 1: 좌상, 2: 우상, 3: 좌하, 4: 우하
        if (holePos != 1 && map[cr][cc] == 0) map[cr][cc] = tileId;
        if (holePos != 2 && map[cr][cc + 1] == 0) map[cr][cc + 1] = tileId;
        if (holePos != 3 && map[cr + 1][cc] == 0) map[cr + 1][cc] = tileId;
        if (holePos != 4 && map[cr + 1][cc + 1] == 0) map[cr + 1][cc + 1] = tileId;

        tileId++;

        // 4분할 재귀 (로직 그대로)
        dfs(row, col, half);
        dfs(row, col + half, half);
        dfs(row + half, col, half);
        dfs(row + half, col + half, half);
    }

    static int findHole(int row, int col, int size) {
        int half = size / 2;

        // 1) 좌상
        for (int r = row; r < row + half; r++) {
            for (int c = col; c < col + half; c++) {
                if (map[r][c] != 0) return 1;
            }
        }

        // 2) 우상
        for (int r = row; r < row + half; r++) {
            for (int c = col + half; c < col + size; c++) {
                if (map[r][c] != 0) return 2;
            }
        }

        // 3) 좌하
        for (int r = row + half; r < row + size; r++) {
            for (int c = col; c < col + half; c++) {
                if (map[r][c] != 0) return 3;
            }
        }

        // 4) 우하
        return 4;
    }
}