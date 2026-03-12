import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	private static final int[] dr = {-1, 1, 0, 0};
	private static final int[] dc = {0, 0, -1, 1};
	private static final int[] horseDr = {-1, -2, -2, -1, 1, 2, 2, 1};
	private static final int[] horseDc = {-2, -1, 1, 2, 2, 1, -1, -2};
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int K = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		int W = Integer.parseInt(st.nextToken());
		int H = Integer.parseInt(st.nextToken());
		int[][] board = new int[H][W];
		boolean[][][] visited = new boolean[H][W][K + 1];
		
		for (int i = 0; i < H; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < W; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int minDist = Integer.MAX_VALUE;
		Queue<int[]> queue = new ArrayDeque<>();
		visited[0][0][0] = true;
		queue.add(new int[] {0, 0, 0, 0});
		
		while(!queue.isEmpty()) {
			int[] curPos = queue.poll();
			
			if (curPos[0] == H - 1 && curPos[1] == W - 1) {
				minDist = Math.min(minDist, curPos[2]);
                System.out.println(minDist);
				return;
			}
			
			if (curPos[3] < K) {
				for (int d = 0; d < 8; d++) {
					int nr = curPos[0] + horseDr[d];
					int nc = curPos[1] + horseDc[d];
					
					if (nr < 0 || nr >= H || nc < 0 || nc >= W || 
						visited[nr][nc][curPos[3] + 1] || board[nr][nc] == 1) continue;
					
					visited[nr][nc][curPos[3] + 1] = true;
					queue.add(new int[] {nr, nc, curPos[2] + 1, curPos[3] + 1});
				}
			}
			
			for (int d = 0; d < 4; d++) {
				int nr = curPos[0] + dr[d];
				int nc = curPos[1] + dc[d];
				
				if (nr < 0 || nr >= H || nc < 0 || nc >= W || 
					visited[nr][nc][curPos[3]] || board[nr][nc] == 1) continue;

				visited[nr][nc][curPos[3]] = true;
				queue.add(new int[] {nr, nc, curPos[2] + 1, curPos[3]});
			}
			
		}
		
		System.out.println(minDist == Integer.MAX_VALUE ? -1 : minDist);
	}
}