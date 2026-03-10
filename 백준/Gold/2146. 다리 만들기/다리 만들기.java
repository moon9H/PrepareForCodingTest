import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	private static final int[] dr = {-1, 1, 0, 0};
	private static final int[] dc = {0, 0, -1, 1};
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[][] map = new int[N][N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		boolean[][] visited = new boolean[N][N];
		int islandCnt = 0;
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				
				if (map[i][j] != 0 && !visited[i][j]) {
					++islandCnt;
					Queue<int[]> queue = new ArrayDeque<int[]>();
					visited[i][j] = true;
					queue.add(new int[] {i, j});
					while(!queue.isEmpty()) {
						int[] curPos = queue.poll();
						map[curPos[0]][curPos[1]] = islandCnt;
						for (int d = 0; d < 4; d++) {
							int nr = curPos[0] + dr[d];
							int nc = curPos[1] + dc[d];
							
							if (nr < 0 || nr >= N || nc < 0 || nc >= N || 
								visited[nr][nc] || map[nr][nc] == 0) continue;
							queue.add(new int[] {nr, nc});
							visited[nr][nc] = true;
						}
					}
				}
			}
		}
		
		int minDist = Integer.MAX_VALUE;
		
		for (int island = 1; island <= islandCnt; island++) {
			Queue<int[]> queue = new ArrayDeque<>();
			int[][] dist = new int[N][N];
			for (int[] is : dist) {
				Arrays.fill(is, -1);
			}
			
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (map[i][j] == island) {
						queue.add(new int[] {i, j});
						dist[i][j] = 0;
					}
				}
			}
			
			while(!queue.isEmpty()) {
				int[] curPos = queue.poll();
				
				for (int d = 0; d < 4; d++) {
					int nr = curPos[0] + dr[d];
					int nc = curPos[1] + dc[d];
					
					if (nr < 0 || nr >= N || nc < 0 || nc >= N || map[nr][nc] == island) continue;
					
					if (map[nr][nc] != 0) {
						minDist = Math.min(minDist, dist[curPos[0]][curPos[1]]);
						queue.clear();
						break;
					}
					
					if (dist[nr][nc] == -1) {
						dist[nr][nc] = dist[curPos[0]][curPos[1]] + 1;
						queue.add(new int[] {nr, nc});
					}
				}
			}
		}
		
		System.out.println(minDist);
	}
}