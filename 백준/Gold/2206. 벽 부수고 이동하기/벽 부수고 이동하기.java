import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	private static final int[] dr = {-1, 1, 0, 0};
	private static final int[] dc = {0, 0, -1, 1};
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[][] map = new int[N][M];
		boolean[][][] visited = new boolean[N][M][2];
		for (int i = 0; i < N; i++) {
			String line = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = line.charAt(j) - '0';
			}
		}
		
		int minDist = Integer.MAX_VALUE;
		Queue<int[]> queue = new ArrayDeque<int[]>();
		visited[0][0][0] = true;
		queue.add(new int[] {0, 0, 1, 0});
		
		while(!queue.isEmpty()) {
			int[] curPos = queue.poll();
			
			if (curPos[0] == N - 1 && curPos[1] == M - 1) {
				minDist = Math.min(minDist, curPos[2]);
				queue.clear();
			}
			
			for (int d = 0; d < 4; d++) {
				int nr = curPos[0] + dr[d];
				int nc = curPos[1] + dc[d];
				
				if (nr < 0 || nr >= N || nc < 0 || nc >= M || 
					visited[nr][nc][curPos[3]]) continue;
				
				if (map[nr][nc] == 0) {
					visited[nr][nc][curPos[3]] = true;
					queue.add(new int[] {nr, nc, curPos[2] + 1, curPos[3]});
				} else {
					if (curPos[3] == 0) {
						if (!visited[nr][nc][1]) {
							visited[nr][nc][1] = true;
							queue.add(new int[] {nr, nc, curPos[2] + 1, 1});														
						}
					}
				}
			}
		}
		
		System.out.println(minDist == Integer.MAX_VALUE ? -1 : minDist);
	}
}