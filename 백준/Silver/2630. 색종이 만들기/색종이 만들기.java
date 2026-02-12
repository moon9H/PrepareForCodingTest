import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	private static int[][] board;
	private static int white, blue;
	public static void main(String[] args) throws IOException{
		// 입력 처리 파트
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		board = new int[N][N];
		white = blue = 0;
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 로직 시작
		dfs(N, 0 ,0);
		System.out.println(white);
		System.out.println(blue);
	}
	
	static void dfs(int size, int row, int col) {
		int b = 0;
		int w = 0;
		for (int i = row; i < row + size; i++) {
			for (int j = col; j < col + size; j++) {
				if (board[i][j] == 0) w++;
				else b++;
			}
		}
		
		if (b == size * size) {
			blue++;
			return;
		}
		
		if (w ==  size * size) {
			white++;
			return;
		}
		
		dfs(size / 2, row, col);
		dfs(size / 2, row, col + size / 2);
		dfs(size / 2, row + size / 2, col);
		dfs(size / 2, row + size / 2, col + size / 2);
	}
}