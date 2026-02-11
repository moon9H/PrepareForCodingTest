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
			int cRow = N / 2;
			int cCol = N / 2;
			int sum = 0;
			for (int i = 0; i < N; i++) {
				String line = br.readLine();
				for (int j = 0; j < N; j++) {
					farm[i][j] = line.charAt(j) - '0';
					if (Math.abs(cRow - i) + Math.abs(cCol - j) <= N/2) {
						sum += farm[i][j];
					}
				}
			}
			sb.append("#" + tc + " ").append(sum).append("\n");
		}
		System.out.println(sb);
	}
}