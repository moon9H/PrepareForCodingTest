import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class Solution {
	private static int N;
	private static int[][] map;
	private static final int[] dr = {-1, -1, -1, 0, 1, 1, 1, 0};
	private static final int[] dc = {-1, 0, 1, 1, 1, 0, -1, -1};
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			for (int i = 0; i < N; i++) {
				String line = br.readLine();
				for (int j = 0; j < N; j++) {
					if (line.charAt(j) == '*') map[i][j] = -1;
				}
			}
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (map[i][j] == 0) {
						int mineCnt = 0;
						for (int d = 0; d < 8; d++) {
							int nr = i + dr[d];
							int nc = j + dc[d];
							
							if (nr < 0 || nr >= N || nc < 0 || nc >= N) continue;
							
							if (map[nr][nc] == -1) ++mineCnt;
						}
						map[i][j] = mineCnt;
					}
				}
			}
			int ans = 0;
			boolean[][] visited = new boolean[N][N];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (map[i][j] == 0 && !visited[i][j]) {
						++ans;
						Queue<int[]> queue = new ArrayDeque<>();
						queue.add(new int[] {i, j});
						visited[i][j] = true;
						
						while(!queue.isEmpty()) {
							int[] curPos = queue.poll();
							
							for (int d = 0; d < 8; d++) {
								int nr = curPos[0] + dr[d];
								int nc = curPos[1] + dc[d];
								
								if (nr < 0 || nr >= N || nc < 0 || nc >= N 
									|| visited[nr][nc] || map[nr][nc] == -1) continue;
								
								visited[nr][nc] = true;
								if (map[nr][nc] == 0)
									queue.add(new int[] {nr, nc});
							}
						}
					}
				}
			}
			
			for (int i = 0; i < N; i++) {
			    for (int j = 0; j < N; j++) {
			        if (map[i][j] != -1 && !visited[i][j]) {
			            ++ans;
			        }
			    }
			}
			
			sb.append('#').append(tc).append(' ').append(ans).append('\n');
		}
		System.out.println(sb);
	}
}