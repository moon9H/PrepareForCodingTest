import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
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
		while(w != 0 && h != 0) {
			int[][] map = new int[h][w];
			boolean[][] isVisited = new boolean[h][w];
			for (int i = 0; i < h; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < w; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			int islandCnt = 0;
			
			for (int i = 0; i < h; i++) {
				for (int j = 0; j < w; j++) {
					if (map[i][j] == 1 && !isVisited[i][j]) {
						ArrayDeque<int[]> queue = new ArrayDeque<>();
						queue.add(new int[] {i, j});
						isVisited[i][j] = true;
						while(!queue.isEmpty()) {
							int[] curPos = queue.poll();
							for (int d = 0; d < 8; d++) {
								int nr = curPos[0] + dr[d];
								int nc = curPos[1] + dc[d];
								if (nr >= 0 && nr < h && nc >= 0 && nc < w
										&& map[nr][nc] != 0 && !isVisited[nr][nc]) {
									isVisited[nr][nc] = true;
									queue.add(new int[] {nr, nc});
								}
							}
						}
						islandCnt++;
					}
				}
			}
			
			sb.append(islandCnt).append('\n');
			
			st = new StringTokenizer(br.readLine());
			w = Integer.parseInt(st.nextToken());
			h = Integer.parseInt(st.nextToken());
		}
		System.out.println(sb);
	}
}