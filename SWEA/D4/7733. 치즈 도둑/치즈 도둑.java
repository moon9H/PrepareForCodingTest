import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
	private static final int[] dr = {-1, 1, 0, 0};
	private static final int[] dc = {0, 0, -1, 1};
	private static int[][] map;
	private static int N;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			int maxDays = Integer.MIN_VALUE;
			int maxCheeseLump = Integer.MIN_VALUE;
			 N = Integer.parseInt(br.readLine());
			 map = new int[N][N];
			 for (int i = 0; i < N; i++) {
				 StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					maxDays = Math.max(maxDays, map[i][j]);
				}
			}
			 
			 for (int i = 0; i <= maxDays; i++) {
				maxCheeseLump = Math.max(maxCheeseLump, countCheese(i));
			}
			
			sb.append('#').append(tc).append(' ').append(maxCheeseLump).append('\n');
		}
		System.out.println(sb);
	}
	
	static int countCheese(int day) {
		int lumpCnt = 0;
		boolean[][] visited = new boolean[N][N];
		Queue<int[]> queue = new ArrayDeque<>();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] > day && !visited[i][j]) {
					lumpCnt++;
					queue.add(new int[] {i, j});
					visited[i][j] = true;
					while(!queue.isEmpty()) {
						int[] curPos = queue.poll();
						for (int d = 0; d < 4; d++) {
							int nr = curPos[0] + dr[d];
							int nc = curPos[1] + dc[d];
							
							if (nr >= 0  && nr < N && nc >= 0 && nc < N &&
									map[nr][nc] > day && !visited[nr][nc]) {
								queue.add(new int[] {nr, nc});
								visited[nr][nc] = true;
							}
						}
					}
				}
			}
		}
		return lumpCnt;
	}
}