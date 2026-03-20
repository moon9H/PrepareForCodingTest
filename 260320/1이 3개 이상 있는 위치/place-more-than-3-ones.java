import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	private static final int[] dr = {-1, 1, 0, 0};
	private static final int[] dc = {0, 0, -1, 1};
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int N = Integer.parseInt(br.readLine());
		int[][] map = new int[N][N];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		int result = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				int sum = 0;
				for (int d = 0; d < 4; d++) {
					int nr = i + dr[d]; 
					int nc = j + dc[d];
					
					if (nr < 0 || nr >= N || nc < 0 || nc >= N) continue;
					
					if (map[nr][nc] == 1) ++sum;
				}
				if (sum >= 3) ++result;
			}
		}
		System.out.println(result);
	}
}