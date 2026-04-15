import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int N = Integer.parseInt(br.readLine());
		int[][] houses = new int[N + 1][3];				// 0, 1, 2 : R, G, B
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			int rCost = Integer.parseInt(st.nextToken());
			int gCost = Integer.parseInt(st.nextToken());
			int bCost = Integer.parseInt(st.nextToken());
			houses[i][0] = rCost;
			houses[i][1] = gCost;
			houses[i][2] = bCost;
		}
		
		int[][] dp = new int[N + 1][3];
		dp[1][0] = houses[1][0]; 
		dp[1][1] = houses[1][1]; 
		dp[1][2] = houses[1][2]; 
		for (int i = 2; i <= N; i++) {
			dp[i][0] = houses[i][0] + Math.min(dp[i - 1][1], dp[i - 1][2]);
			dp[i][1] = houses[i][1] + Math.min(dp[i - 1][0], dp[i - 1][2]);
			dp[i][2] = houses[i][2] + Math.min(dp[i - 1][0], dp[i - 1][1]);
		}
		
		int result = Integer.MAX_VALUE;
		
		for (int i = 0; i < 3; i++) {
			result = Math.min(result, dp[N][i]);
		}
		
		System.out.println(result);
	}
}