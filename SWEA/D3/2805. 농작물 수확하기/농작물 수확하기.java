import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			int N = Integer.parseInt(br.readLine());
			int[][] farm = new int[N][N];
			
			for (int i = 0; i < N; i++) {
				String line = br.readLine();
				for (int j = 0; j < N; j++)
					farm[i][j] = line.charAt(j) - '0';
			}
			
			int cRow = N / 2;
			int cCol = N / 2;
			int sum = 0;
			for (int row = cRow - N / 2; row <= cRow + N / 2; row++) {
				for (int col = cCol - N / 2; col <= cCol + N / 2; col++) {
					if (Math.abs(cRow - row) + Math.abs(cCol - col) <= N/2) {
						sum += farm[row][col];
					}
				}
			}
			
			sb.append("#" + tc + " ").append(sum).append("\n");
		}
		System.out.println(sb);
	}
}