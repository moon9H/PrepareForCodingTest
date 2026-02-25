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
		int[][] route = new int[N][M];
		boolean[][] visited = new boolean[N][M];
		for (int i = 0; i < N; i++) {
			String line = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = line.charAt(j) - '0';
			}
		}
		Queue<int[]> queue = new ArrayDeque<>();
		queue.add(new int[] {0, 0});
		visited[0][0] = true;
		route[0][0] = 1;
		while(!queue.isEmpty()) {
			int[] curPos = queue.poll();
			for (int d = 0; d < 4; d++) {
				int nr = curPos[0] + dr[d];
				int nc = curPos[1] + dc[d];
				if (nr >= 0 && nr < N && nc >= 0 && nc < M &&
					!visited[nr][nc] && map[nr][nc] == 1) {
					queue.add(new int[] {nr, nc});
					visited[nr][nc] = true;
					route[nr][nc] += route[curPos[0]][curPos[1]] + 1;
				}
			}
		}
		
		System.out.println(route[N - 1][M - 1]);
	}
}
