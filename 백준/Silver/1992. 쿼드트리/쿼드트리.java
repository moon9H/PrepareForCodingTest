import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	private static int[][] map;
	private static int N;
	private static StringBuilder sb;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		sb = new StringBuilder();
		for (int i = 0; i < N; i++) {
			String line = br.readLine();
			for (int j = 0; j < N; j++) {
				map[i][j] = line.charAt(j) - '0';
			}
		}
		dfs(0, 0, N);
		sb.append('\n');
		System.out.println(sb);
	}
	
	static void dfs(int row, int col, int size) {
		int sum = 0;
		
		for (int r = row; r < row + size; r++) {
			for (int c = col; c < col + size; c++) {
				sum += map[r][c];
			}
		}
		
		if (sum == size * size) {
			sb.append(1);
		} else if (sum == 0) {
			sb.append(0);
		} else {
			sb.append('(');
			dfs(row, col, size / 2);
			dfs(row, col + size / 2, size / 2);
			dfs(row + size / 2, col, size / 2);
			dfs(row + size / 2, col + size / 2, size / 2);
			sb.append(')');
		}
	}
}