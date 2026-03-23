import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = null;
        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int X = Integer.parseInt(st.nextToken());
            int[][] map = new int[N][N];
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            int possible = 0;
            for (int[] line : map) {
                if (canBuild(line, X)) ++possible;
            }

            for (int i = 0; i < N; i++) {
                int[] vertLine = new int[N];
                for (int j = 0; j < N; j++) {
                    vertLine[j] = map[j][i];
                }
                if (canBuild(vertLine, X)) ++possible;
            }

            sb.append('#').append(tc).append(' ').append(possible).append('\n');
        }
        System.out.println(sb);
    }
    static boolean canBuild(int[] line, int X) {
        boolean[] installed = new boolean[line.length]; // 경사로 설치 여부

        for (int i = 0; i < line.length - 1; i++) {

            // 1. 높이가 같으면 그냥 진행
            if (line[i] == line[i + 1]) continue;

            // 2. 높이 차이가 2 이상이면 불가능
            if (Math.abs(line[i] - line[i + 1]) > 1) return false;

            // 3. 오르막인 경우
            // ex) 2 2 2 3
            if (line[i] + 1 == line[i + 1]) {
                for (int j = 0; j < X; j++) {
                    int idx = i - j;

                    // 범위 밖이면 불가능
                    if (idx < 0) return false;

                    // 높이가 다르거나 이미 경사로 설치된 곳이면 불가능
                    if (line[idx] != line[i] || installed[idx]) return false;

                    installed[idx] = true;
                }
            }

            // 4. 내리막인 경우
            // ex) 3 2 2 2
            else if (line[i] - 1 == line[i + 1]) {
                for (int j = 1; j <= X; j++) {
                    int idx = i + j;

                    // 범위 밖이면 불가능
                    if (idx >= line.length) return false;

                    // 높이가 다르거나 이미 경사로 설치된 곳이면 불가능
                    if (line[idx] != line[i + 1] || installed[idx]) return false;

                    installed[idx] = true;
                }
            }
        }

        return true;
    }
}