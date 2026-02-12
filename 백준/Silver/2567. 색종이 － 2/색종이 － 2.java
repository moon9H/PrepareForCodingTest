import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/* 색종이 - 2 */
public class Main {
	// 길이는 true인 면에서 상/하/좌/우 true 아닌 면으로 전환되는 곳의 합..
	static boolean[][] paper = new boolean[102][102]; 
	
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		// 색종이 붙이기
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());

			for (int r = x; r < x + 10; r++) {
				for (int c = y; c < y + 10; c++) {
					paper[r][c] = true;
				}
			}
		}

		int length = 0;

		for (int r = 1; r <= 100; r++) {
			for (int c = 1; c <= 100; c++) {
				if (!paper[r][c])
					continue;

				// 4방 탐색
				for (int d = 0; d < 4; d++) {
					int nr = r + dr[d];
					int nc = c + dc[d];
					
					// true인 면에서 true가 아닌 면으로 전환되는 지점의 합 = 길이 
					if (!paper[nr][nc])
						length++;
				}
			}
		}

		System.out.println(length);
	}
}