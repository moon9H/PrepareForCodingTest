import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 결국 mCn 구하는 문제
public class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		int T = Integer.parseInt(br.readLine());
		int[][] dp;
		for (int tc = 0; tc < T; tc++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			
			dp = new int[M + 1][M + 1];
			
			for (int i = 0; i <= M; i++) {
				for (int j = 0, end = Math.min(i, N); j <= end; j++) {
					if (j == 0 || j == i) dp[i][j] = 1;
					else dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j];
				}
			}
			sb.append(dp[M][N]).append('\n');
		}
		System.out.println(sb);
	}
}