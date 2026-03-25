import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
	private static final int[] dr = {-1, 1, 0, 0};
	private static final int[] dc = {0, 0, -1, 1};
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			char[][] map = new char[N][M];
			int[][] fireMap = new int[N][M];
			for (int i = 0; i < N; i++) {
				Arrays.fill(fireMap[i], -1);
			}
			boolean[][] visited = new boolean[N][M];
			ArrayList<int[]> demonPos = new ArrayList<>();
			int sRow = -1, sCol = -1;
			int fRow = -1, fCol = -1;
			
			for (int i = 0; i < N; i++) {
				String line = br.readLine();
				for (int j = 0; j < M; j++) {
					map[i][j] = line.charAt(j);
					
					if (map[i][j] == '*') {
						demonPos.add(new int[] {i, j});
					}
					
					if (map[i][j] == 'S') {
						sRow = i;
						sCol = j;
					}
					
					if (map[i][j] == 'D') {
						fRow = i;
						fCol = j;
					}
				}
			}
			
			Queue<int[]> queue = new ArrayDeque<>();
			for (int[] is : demonPos) {
				fireMap[is[0]][is[1]] = 0;
				visited[is[0]][is[1]] = true;
				queue.add(new int[] {is[0], is[1], 0});
			}
			
			while(!queue.isEmpty()) {
				int[] curPos = queue.poll();
				
				for (int d = 0; d < 4; d++) {
					int nr = curPos[0] + dr[d];
					int nc = curPos[1] + dc[d];
					
					if (nr < 0 || nr >= N || nc < 0 || nc >= M 
						|| visited[nr][nc] || map[nr][nc] == 'X' || map[nr][nc] == 'D') continue;
					
					fireMap[nr][nc] = curPos[2] + 1;
					visited[nr][nc] = true;
					queue.add(new int[] {nr, nc, curPos[2] + 1});
				}
			}
			
			for (boolean[] bs : visited) {
				Arrays.fill(bs, false);
			}
			
			visited[sRow][sCol] = true;
			queue.add(new int[] {sRow, sCol, 0});
			int minDist = -1;
			while(!queue.isEmpty()) {
				int[] curPos = queue.poll();
				
				if (map[curPos[0]][curPos[1]] == 'D') {
					minDist = curPos[2];
					break;
				}
				
				for (int d = 0; d < 4; d++) {
					int nr = curPos[0] + dr[d];
					int nc = curPos[1] + dc[d];
					
					if (nr < 0 || nr >= N || nc < 0 || nc >= M ||
						visited[nr][nc] || map[nr][nc] == 'X' || map[nr][nc] == '*') continue;
					
					if (fireMap[nr][nc] != -1 && fireMap[nr][nc] <= curPos[2] + 1) continue;
					
					visited[nr][nc] = true;
					queue.add(new int[] {nr, nc, curPos[2] + 1});
				}
			}
			
			String answer = minDist == -1 ? "GAME OVER" : String.valueOf(minDist);
			sb.append('#').append(tc).append(' ').append(answer).append('\n');
		}
		System.out.println(sb);
	}
}