import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int [][] board = new int[N][N];
		int [][] pos = new int[M][4];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 4; j++) {
				pos[i][j] = Integer.parseInt(st.nextToken()) - 1;
			}
		}
		
		int [][] partialSum = new int [N][N];
		for (int i = 0; i < N; i++) {
			partialSum[i][0] = board[i][0];
			for (int j = 1; j < N; j++) {
				partialSum[i][j] = partialSum[i][j - 1] + board[i][j];
			}
		}
		
		for (int i = 0; i < M; i++) {
			int sum = 0;
			int row1 = pos[i][0], row2 = pos[i][2];
			int col1 = pos[i][1], col2 = pos[i][3];
			for (int j = row1; j < row2 + 1; j++) {
				if (col1 == 0) {
					sum += partialSum[j][col2];
				} else {
					sum += partialSum[j][col2] - partialSum[j][col1 - 1];
				}
			}
			sb.append(sum).append("\n");
		}
		System.out.println(sb.toString());
	}
}
