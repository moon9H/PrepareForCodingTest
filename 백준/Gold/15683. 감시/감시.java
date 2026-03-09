import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	private static int N, M, minArea, builtIn;
	private static int[][] map;
	private static ArrayList<int[]> cctvPos;
	private static final int[] dr = {-1, 0, 1, 0};
	private static final int[] dc = {0, 1, 0, -1};
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		builtIn = 0;
		cctvPos = new ArrayList<>();
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] > 0 && map[i][j] < 6) {
					cctvPos.add(new int[] {i, j, map[i][j]});
				}
				if (map[i][j] != 0) builtIn++;
			}
		}
		
		minArea = N * M;
		dfs(0, builtIn);
		
		System.out.println(minArea);
	}
	
	static void dfs(int count, int checkedZone) {
		if (count == cctvPos.size()) {
			minArea = Math.min(minArea, N * M - checkedZone);
			return;
		}
		
		int curRow = cctvPos.get(count)[0];
		int curCol = cctvPos.get(count)[1];
		int cctvType = cctvPos.get(count)[2];
		
		if (cctvType == 1) {
			for (int d = 0; d < 4; d++) {
				int zone = drawLine(curRow, curCol, d, -1);
				dfs(count + 1, checkedZone + zone);
				drawLine(curRow, curCol, d, 0);
			}
		} else if (cctvType == 2) {
			for (int d = 0; d < 2; d++) {
				int zone = drawLine(curRow, curCol, d, -1) + drawLine(curRow, curCol, d + 2, -1);
				dfs(count + 1, checkedZone + zone);
				drawLine(curRow, curCol, d, 0);
				drawLine(curRow, curCol, d + 2, 0);
			}
		} else if (cctvType == 3) {
			for (int d = 0; d < 4; d++) {
				int zone = drawLine(curRow, curCol, d, -1) + drawLine(curRow, curCol, (d + 1) % 4, -1);
				dfs(count + 1, checkedZone + zone);
				drawLine(curRow, curCol, d, 0);
				drawLine(curRow, curCol, (d + 1) % 4, 0);
			}
		} else if (cctvType == 4) {
			for (int d = 0; d < 4; d++) {
				int zone = drawLine(curRow, curCol, d, -1)
						+ drawLine(curRow, curCol, (d + 1) % 4, -1)
						+ drawLine(curRow, curCol, (d + 2) % 4, -1);
				dfs(count + 1, checkedZone + zone);
				drawLine(curRow, curCol, d, 0);
				drawLine(curRow, curCol, (d + 1) % 4, 0);
				drawLine(curRow, curCol, (d + 2) % 4, 0);
			}
		} else if (cctvType == 5) {
			int zone = 0;
			for (int d = 0; d < 4; d++) {
				zone += drawLine(curRow, curCol, d, -1);
			}
			dfs(count + 1, checkedZone + zone);
			for (int d = 0; d < 4; d++) {
				drawLine(curRow, curCol, d, 0);
			}
		}
	}
	
	static int drawLine(int row, int col, int d, int mode) {
		int check = 0;
		int nr = row + dr[d];
		int nc = col + dc[d];
		
		while (nr >= 0 && nr < N && nc >= 0 && nc < M) {
			if (map[nr][nc] == 6) break;
			
			if (mode == -1) {
				if (map[nr][nc] == 0) ++check;
				if (map[nr][nc] <= 0) {
					--map[nr][nc];
				}
			} else {
				if (map[nr][nc] < 0) {
					++map[nr][nc];
				}
			}
			
			nr += dr[d];
			nc += dc[d];
		}
		
		return check;
	}
}