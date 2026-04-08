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
		
		// dp[c] : 비용이 c일 때 확보 가능한 최대 메모리
		int[] dp = new int[maxCost + 1];
		
		for (int i = 1; i <= N; i++) {
			for (int c = maxCost; c >= cost[i]; c--) {
				dp[c] = Math.max(dp[c], dp[c - cost[i]] + mem[i]);
			}
		}
		
		for (int c = 0; c <= maxCost; c++) {
			if (dp[c] >= M) {
				System.out.println(c);
				return;
			}
		}
	}
}