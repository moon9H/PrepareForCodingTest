import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	private static int[] prices = new int[4];					// 1일, 1달, 3달, 1년
	private static int[] monthPlan = new int[12];
	private static int minCost;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= T; tc++) {
			// 입력 처리
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i = 0; i < 4; i++) {
				prices[i] = Integer.parseInt(st.nextToken());
			}
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < 12; i++) {
				monthPlan[i] = Integer.parseInt(st.nextToken());
			}
			minCost = prices[3];								// 최소값을 1년 이용권으로 초기화
			// 주요 로직 시작
			
			dfs(0, 0);
			
			sb.append(String.format("#%d %d\n", tc, minCost));
		}
		System.out.println(sb);
	}
	
	static void dfs(int month, int sum) {
		if (month >= 12) {
			minCost = Math.min(minCost, sum);
			return;
		}
		
		if (sum >= minCost) return;
		
		dfs(month + 1, sum + (monthPlan[month] * prices[0]));
		
		dfs(month + 1, sum + prices[1]);
		
		dfs(month + 3, sum + prices[2]);
	}
}