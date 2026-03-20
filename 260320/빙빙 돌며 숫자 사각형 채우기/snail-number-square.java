import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	private static final int[] dr = {0, 1, 0, -1};
	private static final int[] dc = {1, 0, -1, 0};
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[][] dist = new int[N][M];
		
		int d = 0, cRow = 0, cCol = 0;
		for (int i = 0; i < N * M; i++) {
			dist[cRow][cCol] = i + 1;
			int nr = cRow + dr[d];
			int nc = cCol + dc[d];
			
			if (nr < 0 || nr >= N || nc < 0 || nc >= M || dist[nr][nc] != 0) {
				d = (d + 1) % 4;
				nr = cRow + dr[d];
				nc = cCol + dc[d];
			}
			cRow = nr;
			cCol = nc;
		}
		
		StringBuilder sb = new StringBuilder();
		for (int[] is : dist) {
			for (int i : is) {
				sb.append(i).append(' ');
			}
			sb.append('\n');
		}
		System.out.println(sb);
	}
}