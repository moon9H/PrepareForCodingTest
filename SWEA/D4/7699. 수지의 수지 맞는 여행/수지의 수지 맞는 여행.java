import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
	private static int R, C, maxLen;
	private static char[][] map;
	private static boolean[] visited;
	private static final int[] dr = {-1, 1, 0, 0};
	private static final int[] dc = {0, 0, -1, 1};
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			R = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			map = new char[R][C];
			visited = new boolean[26];
			maxLen = 0;
			for (int i = 0; i < R; i++) {
				String line = br.readLine();
				for (int j = 0; j < C; j++) {
					map[i][j] = line.charAt(j);
				}
			}
			
			visited[map[0][0] - 'A'] = true;
			dfs(0, 0, 1);
			
			sb.append('#').append(tc).append(' ').append(maxLen).append('\n');
		}
		System.out.println(sb);
	}
	static void dfs(int row, int col, int len) {
		if (maxLen == 26) return;
		maxLen = Math.max(maxLen, len);
		
		for (int d = 0; d < 4; d++) {
			int nr = row + dr[d];
			int nc = col + dc[d];
			
			if (nr < 0 || nr >= R || nc < 0 || nc >= C) continue;
			
			int nextAlpha = map[nr][nc] - 'A';
			
			if (visited[nextAlpha]) continue;
			
			visited[nextAlpha] = true;
			dfs(nr, nc, len + 1);
			visited[nextAlpha] = false;
		}
	}
}