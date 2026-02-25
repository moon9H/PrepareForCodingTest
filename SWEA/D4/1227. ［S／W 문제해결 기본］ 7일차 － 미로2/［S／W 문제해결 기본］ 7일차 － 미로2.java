import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class Solution {
	private static final int[] dr = {-1, 1, 0, 0};
	private static final int[] dc = {0, 0, -1, 1};
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		for (int tc = 1; tc <= 10; tc++) {
			br.readLine();
			int[][] map = new int[100][100];
			boolean[][] visited = new boolean[100][100];
			for (int i = 0; i < 100; i++) {
				String line = br.readLine();
				for (int j = 0; j < 100; j++) {
					map[i][j] = line.charAt(j) - '0';
				}
			}
			int isPossible = 0;
			
			Queue<int[]> queue = new ArrayDeque<>();
			queue.add(new int[] {1, 1});
			visited[1][1] = true;
			
			while(!queue.isEmpty()) {
				int[] curPos = queue.poll();
				for (int i = 0; i < 4; i++) {
					int nr = curPos[0] + dr[i];
					int nc = curPos[1] + dc[i];
					if (nr > 0 && nr < 99 && nc > 0 && nc < 99 &&
						!visited[nr][nc] && map[nr][nc] != 1) {
						if (map[nr][nc] == 3) isPossible = 1;
						queue.add(new int[] {nr, nc});
						visited[nr][nc] = true;
					}
				}
			}
			
			sb.append('#').append(tc).append(' ').append(isPossible).append('\n');
		}
		System.out.println(sb);
	}
}