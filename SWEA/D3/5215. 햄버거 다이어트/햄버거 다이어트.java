import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	private static int N, limit, highestScore;
	private static int[][] foodList;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			limit = Integer.parseInt(st.nextToken());
			highestScore = Integer.MIN_VALUE;
			foodList = new int[N][2];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				foodList[i][0] = Integer.parseInt(st.nextToken());
				foodList[i][1] = Integer.parseInt(st.nextToken());
			}
			
			dfs(0, 0, 0);
			
			sb.append('#').append(tc).append(' ').append(highestScore).append('\n');
		}
		System.out.println(sb);
	}
	
	static void dfs(int count, int sSum, int cSum) {
		if (cSum > limit) return;
		
		if (count == N) {
			highestScore = Math.max(highestScore, sSum);
			return;
		}
		
		dfs(count + 1, sSum + foodList[count][0], cSum + foodList[count][1]);
		
		dfs(count + 1, sSum, cSum);
	}
}