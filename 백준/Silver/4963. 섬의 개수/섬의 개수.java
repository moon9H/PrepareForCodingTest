import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	private static final int[] dr = {-1, -1, -1, 0, 1, 1, 1, 0};
	private static final int[] dc = {-1, 0, 1, 1, 1, 0, -1, -1};
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		int w = Integer.parseInt(st.nextToken());
		int h = Integer.parseInt(st.nextToken());
		
		while (w + h != 0) {
			int[][] map = new int[h][w];
			boolean[][] visited = new boolean[h][w];
			for (int i = 0; i < h; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < w; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if (map[i][j] == 0) visited[i][j] = true;
				}
			}
			
			Queue<int[]> queue = new ArrayDeque<>();
			int island = 0;
			for (int i = 0; i < h; i++) {
				for (int j = 0; j < w; j++) {
					if (map[i][j] == 1 && !visited[i][j]) {
						++island;
						queue.offer(new int[] {i, j});
						visited[i][j] = true;
						while(!queue.isEmpty()) {
							int[] curPos = queue.poll();
							for (int k = 0; k < dr.length; k++) {
								int nr = curPos[0] + dr[k];
								int nc = curPos[1] + dc[k];
								if (nr >= 0 && nr < h && nc >= 0 && nc < w && !visited[nr][nc]) {
									queue.offer(new int[] {nr, nc});
									visited[nr][nc] = true;
								}
							}
						}
					}
				}
			}
			
			sb.append(island).append('\n');
			st = new StringTokenizer(br.readLine());
			w = Integer.parseInt(st.nextToken());
			h = Integer.parseInt(st.nextToken());
		}
		
		System.out.println(sb);
	}
}