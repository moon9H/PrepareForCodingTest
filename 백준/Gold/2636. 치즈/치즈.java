import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
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
		int cheeseCnt = 0;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 1) ++cheeseCnt;
			}
		}
		int time = 0;
		int leftCheese = 0; 
		while(cheeseCnt > 0) {
			leftCheese = cheeseCnt;
			Queue<int[]> queue = new ArrayDeque<int[]>();
			boolean[][] visited = new boolean[N][M];
			ArrayList<int[]> meltCheesePos = new ArrayList<>();
			visited[0][0] = true;
			queue.add(new int[] {0, 0});
			while(!queue.isEmpty()) {
				int[] curPos = queue.poll();
				
				for (int d = 0; d < 4; d++) {
					int nr = curPos[0] + dr[d];
					int nc = curPos[1] + dc[d];
					
					if (nr < 0 || nr >= N || nc < 0 || nc >= M || visited[nr][nc]) continue;
					
					visited[nr][nc] = true;
					if (map[nr][nc] == 0) queue.add(new int[] {nr, nc});
					else meltCheesePos.add(new int[] {nr, nc});
				}
			}
			
			for (int[] pos : meltCheesePos) {
				map[pos[0]][pos[1]] = 0;
			}
			++time;
			cheeseCnt -= meltCheesePos.size();
		}
		
		System.out.println(time);
		System.out.println(leftCheese);
	}
}