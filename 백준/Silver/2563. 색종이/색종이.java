import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/* 색종이 */
public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        boolean[][] paper = new boolean[101][101];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            // 색종이 붙은 곳은 true로 변경 
            for (int r = x; r < x + 10; r++) {
                for (int c = y; c < y + 10; c++) {
                    paper[r][c] = true;
                }
            }
        }

        // 전체 배열 순회하며 area에 true 누적
        int area = 0;
        for (int i = 0; i < 101; i++) {
            for (int j = 0; j < 101; j++) {
                if (paper[i][j]) area++;
            }
        }

        System.out.println(area);
    }
}
