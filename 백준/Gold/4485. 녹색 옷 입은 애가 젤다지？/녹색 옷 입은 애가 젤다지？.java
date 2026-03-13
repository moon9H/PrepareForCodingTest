import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	private static final int[] dr = {-1, 1, 0, 0};
	private static final int[] dc = {0, 0, -1, 1};
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		int idx = 1;
		while(true) {
			int N = Integer.parseInt(br.readLine());
			if (N == 0) break;
			int[][] map = new int[N][N];
			int[][] dist = new int[N][N];
			boolean[][] visited = new boolean[N][N];
			for (int i = 0; i < N; i++) {
				Arrays.fill(dist[i], Integer.MAX_VALUE);
			}
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1[2], o2[2]));
			pq.add(new int[] {0, 0, map[0][0]});
			dist[0][0] = map[0][0];
			
			while(!pq.isEmpty()) {
				int[] curPos = pq.poll();
				
				if (visited[curPos[0]][curPos[1]]) continue;
				
				visited[curPos[0]][curPos[1]] = true;
				
				if (curPos[0] == N - 1 && curPos[1] == N - 1) break;
				
				for (int d = 0; d < 4; d++) {
					int nr = curPos[0] + dr[d];
					int nc = curPos[1] + dc[d];
					
					if (nr < 0 || nr >= N || nc < 0 || nc >= N || visited[nr][nc]) continue;
					
					int newCost = curPos[2] + map[nr][nc];
					
					if (dist[nr][nc] > newCost) {
						dist[nr][nc] = newCost;
						pq.add(new int[] {nr, nc, newCost});
					}
				}
			}
			
			sb.append("Problem ").append(idx).append(": ").append(dist[N - 1][N - 1]).append('\n');
			++idx;
		}
		System.out.println(sb);
	}
}