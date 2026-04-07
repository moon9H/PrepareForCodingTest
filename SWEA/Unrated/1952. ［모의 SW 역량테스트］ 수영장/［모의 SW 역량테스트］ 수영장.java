import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		int T = Integer.parseInt(br.readLine());
		int[] usePlan = new int[13];
		for (int tc = 1; tc <= T; tc++) {
			// 이용권 가격 : 1일 이용권, 1달 이용권, 3달 이용권, 1년 이용권
			st = new StringTokenizer(br.readLine());
			int dailyCost = Integer.parseInt(st.nextToken());
			int monthCost = Integer.parseInt(st.nextToken());
			int threeMonthCost = Integer.parseInt(st.nextToken());
			int yearCost = Integer.parseInt(st.nextToken());
			
			// 1 ~ 12월 이용 계획
			st = new StringTokenizer(br.readLine());
			for (int i = 1; i <= 12; i++) {
				usePlan[i] = Integer.parseInt(st.nextToken());
			}
			
			int[] monthMin = new int[13];
			
			for (int i = 1; i <= 12; i++) {
				monthMin[i] = Math.min(monthCost, dailyCost * usePlan[i]);
			}
			
			int[] dp = new int[13];
			dp[1] = monthMin[1];
			dp[2] = dp[1] + monthMin[2];
			
			for (int i = 3; i <= 12; i++) {
				dp[i] = Math.min(dp[i - 1] + monthMin[i], dp[i - 3] + threeMonthCost);
			}

			sb.append('#').append(tc).append(' ').append(Math.min(dp[12], yearCost)).append('\n');
		}
		System.out.println(sb);
	}
}