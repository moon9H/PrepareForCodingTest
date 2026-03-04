import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;

public class Solution {
	private static final int[] dr = {-1, 1, 0, 0};
	private static final int[] dc = {0, 0, -1, 1};
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			int N = Integer.parseInt(br.readLine());
			int[][] map = new int[N][N];
			int[][] dist = new int[N][N];
			for (int i = 0; i < N; i++) {
				String line = br.readLine();
				for (int j = 0; j < N; j++) {
					map[i][j] = line.charAt(j) - '0';
				}
				Arrays.fill(dist[i], Integer.MAX_VALUE);
			}
			
			PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1[0], o2[0]));
			
			dist[0][0] = map[0][0];
			pq.add(new int[] {map[0][0], 0, 0});
			
			while(!pq.isEmpty()) {
				int[] curPos = pq.poll();
				
				for (int d = 0; d < 4; d++) {
					int nr = curPos[1] + dr[d];
					int nc = curPos[2] + dc[d];
					
					if (nr < 0 || nr >= N || nc < 0 || nc >= N) continue;
					
					int newCost = map[nr][nc] + curPos[0];
					
					if (dist[nr][nc] > newCost) {
						dist[nr][nc] = newCost;
						pq.add(new int[] {newCost, nr, nc});
					}
				}
			}
			
			sb.append('#').append(tc).append(' ').append(dist[N - 1][N - 1]).append('\n');
		}
		System.out.println(sb);
	}
}