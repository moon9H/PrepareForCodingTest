import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
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
		
		int[][][] dp = new int[N][N][3];	// 0 : 가로 형태, 1 : 세로 형태, 2 : 대각선
		dp[0][1][0] = 1;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if ((i == 0 && j == 1) || (i == 0 && j == 0)) continue;
				
				// 벽이 있는 경우 삭제
				if (map[i][j] == 1) {
					dp[i][j][0] = dp[i][j][1] = dp[i][j][2] = 0;
					continue;
				}
				
				// (i, j) 좌표에서 가로 형태로 있을 수 있는 경우의 수
				if (j - 1 >= 0)
					dp[i][j][0] = dp[i][j - 1][0] + dp[i][j - 1][2];
				
				// (i, j) 좌표에서 세로 형태로 있을 수 있는 경우의 수
				if (i - 1 >= 0)
					dp[i][j][1] = dp[i - 1][j][1] + dp[i - 1][j][2];
				
				// (i, j) 좌표에서 대각선 형태로 있을 수 있는 경우의 수
				if (i - 1 >= 0 && j - 1 >= 0
						&& map[i - 1][j] != 1
						&& map[i][j - 1] != 1)
					dp[i][j][2] = dp[i - 1][j - 1][0] + dp[i - 1][j - 1][1] + dp[i - 1][j - 1][2];
				
			}
		}
		
		int result = 0;
		for (Integer i : dp[N - 1][N - 1]) {
			result += i;
		}
		System.out.println(result);
	}
}