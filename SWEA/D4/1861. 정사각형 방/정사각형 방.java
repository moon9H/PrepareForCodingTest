import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	static int[][] map;
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	static int N;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			// 입력 처리 부분
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			int maxMoves = 0, pos = 0;
			// 주요 로직 시작 (전략 : 모든 방에서 dfs 돌려서 완탐)
			for (int r = 0; r < N; r++) {
				for (int c = 0; c < N; c++) {
					int canMove = dfs(r, c);
					if (canMove > maxMoves) {
						maxMoves = canMove;
						pos = map[r][c];
					} else if (canMove == maxMoves) {
						pos = Math.min(pos, map[r][c]);
					}
				}
			}
			
			sb.append('#').append(tc).append(' ').append(pos).append(' ').append(maxMoves).append('\n');
		}
		System.out.println(sb);
	}
	
	static int dfs(int row, int col) {
		for (int i = 0; i < 4; i++) {
			int nr = row + dr[i];
			int nc = col + dc[i];
			if (nr < 0 || nr >= N || nc < 0 || nc >= N) continue;
			if (map[nr][nc] == map[row][col] + 1) {
				return dfs(nr, nc) + 1;
			}
		}
		return 1;
	}
}