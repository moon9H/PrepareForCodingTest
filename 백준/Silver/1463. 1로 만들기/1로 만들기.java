import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		int[] dp = new int [N + 1];
		
		Arrays.fill(dp, -1);
		
		dp[N] = 0;
		
		for (int i = N - 1; i >= 0; i--) {
			dp[i] = dp[i + 1] + 1;
			if (i * 2 <= N && dp[i * 2] != -1) dp[i] = Math.min(dp[i], dp[i * 2] + 1);
			if (i * 3 <= N && dp[i * 3] != -1) dp[i] = Math.min(dp[i], dp[i * 3] + 1);
		}
		
		System.out.println(dp[1]);
	}
}