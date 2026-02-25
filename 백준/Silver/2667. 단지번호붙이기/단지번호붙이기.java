import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;

public class Main {
	private static final int[] dr = {-1, 1, 0, 0};
	private static final int[] dc = {0, 0, -1, 1};
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[][] map = new int[N][N];
		boolean[][] visited = new boolean[N][N];
		for (int i = 0; i < N; i++) {
			String line = br.readLine();
			for (int j = 0; j < N; j++) {
				map[i][j] = line.charAt(j) - '0';
			}
		}
		
		int land = 0;
		ArrayList<Integer> list = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] == 1 && !visited[i][j]) {
					int size = 1;
					++land;
					visited[i][j] = true;
					map[i][j] = land;
					Queue<int[]> queue = new ArrayDeque<>();
					queue.add(new int[] {i, j});
					
					while(!queue.isEmpty()) {
						int[] curPos = queue.poll();
						for (int d = 0; d < 4; d++) {
							int nr = curPos[0] + dr[d];
							int nc = curPos[1] + dc[d];
							if (nr >= 0 && nr < N && nc >= 0 && nc < N &&
								!visited[nr][nc] && map[nr][nc] == 1) {
								size++;
								map[nr][nc] = land;
								visited[nr][nc] = true;
								queue.add(new int[] {nr, nc});
							}
						}
					}
					list.add(size);
				}
			}
			
		}
		System.out.println(land);
		list.sort((o1, o2) -> Integer.compare(o1, o2));
		for (Integer integer : list) {
			System.out.println(integer);
		}
	}
}