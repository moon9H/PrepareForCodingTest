import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[] mem = new int[N + 1];
		int[] cost = new int[N + 1];
		
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			mem[i] = Integer.parseInt(st.nextToken());
		}
		
		int maxCost = 0;
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			cost[i] = Integer.parseInt(st.nextToken());
			maxCost += cost[i];
		}
		
		int[][] dp = new int[N + 1][maxCost + 1];
		
		for (int i = 1; i <= N; i++) {
			for (int c = 0; c <= maxCost; c++) {
				dp[i][c] = dp[i - 1][c];
				
				if (c >= cost[i]) {
					dp[i][c] = Math.max(dp[i][c], dp[i - 1][c - cost[i]] + mem[i]);
				}
			}
		}

		for (int c = 0; c <= maxCost; c++) {
			if (dp[N][c] >= M) {
				System.out.println(c);
				break;
			}
		}
	}
}