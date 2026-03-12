import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
	private static int N, H, W, minLeftBricks, originBricks;
	private static int[][] map;
	private static int[] order;
	private static final int[] dr = {-1, 1, 0, 0};
	private static final int[] dc = {0, 0, -1, 1};

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			H = Integer.parseInt(st.nextToken());
			originBricks = 0;
			
			map = new int[H][W];
			order = new int[N];
			minLeftBricks = Integer.MAX_VALUE;

			for (int i = 0; i < H; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < W; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if (map[i][j] != 0) ++originBricks;
				}
			}

			dfs(0);

			sb.append('#').append(tc).append(' ').append(minLeftBricks).append('\n');
		}

		System.out.println(sb);
	}

	static int dropBomb() {
		int[][] copyMap = new int[H][W];
		int remain = originBricks;
		for (int i = 0; i < H; i++) {
			copyMap[i] = Arrays.copyOf(map[i], W);
		}

		for (int i = 0; i < N; i++) {
			int col = order[i];
			int row = 0;

			while (row < H && copyMap[row][col] == 0) {
				row++;
			}

			if (row == H) continue;								// 해당 열에 벽돌이 없으므로 skip

			Queue<int[]> queue = new ArrayDeque<>();
			queue.add(new int[] {row, col, copyMap[row][col]});
			copyMap[row][col] = 0;
			--remain;
			while (!queue.isEmpty()) {
				int[] curBrick = queue.poll();
				int r = curBrick[0];
				int c = curBrick[1];
				int power = curBrick[2];

				for (int d = 0; d < 4; d++) {
					for (int j = 1; j < power; j++) {
						int nr = r + dr[d] * j;
						int nc = c + dc[d] * j;

						if (nr < 0 || nr >= H || nc < 0 || nc >= W) continue;
						if (copyMap[nr][nc] == 0) continue;

						queue.add(new int[] {nr, nc, copyMap[nr][nc]});
						copyMap[nr][nc] = 0;						// 블록 삭제
						--remain;
					}
				}
			}
			
			// 중력을 적용해서 아래로 내리기
			for (int j = 0; j < W; j++) {
				int[] temp = new int[H];
				int idx = H - 1;

				for (int k = H - 1; k >= 0; k--) {
					if (copyMap[k][j] != 0) {
						temp[idx--] = copyMap[k][j];
					}
				}

				for (int k = 0; k < H; k++) {
					copyMap[k][j] = temp[k];
				}
			}
		}

		return remain;
	}

	static void dfs(int count) {
		if (count == N) {
			minLeftBricks = Math.min(minLeftBricks, dropBomb());
			return;
		}

		for (int i = 0; i < W; i++) {
			order[count] = i;
			dfs(count + 1);
		}
	}
}