import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	private static int N, M, result;
	private static int[] input;
	private static boolean[] isSelected;
	private static int[][] notMatched;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int tc = 1; tc <= T; tc++) {
			// 입력 처리
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());							// 재료의 종류
			M = Integer.parseInt(st.nextToken());							// 궁합이 맞지 않는 재료 쌍
			input = new int[N];
			result = 0;
			isSelected = new boolean[N];
			for (int i = 1; i <= N; i++) input[i - 1] = i;
			notMatched = new int[M][2];
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				notMatched[i][0] = Integer.parseInt(st.nextToken());
				notMatched[i][1] = Integer.parseInt(st.nextToken());
			}
			
			dfs(0);
			
			sb.append("#").append(tc).append(" ").append(result).append("\n");
		}
		System.out.println(sb);
	}
	
	private static void dfs(int cnt) {
		for (int i = 0; i < M; i++) {
			if (isSelected[notMatched[i][0] - 1] && isSelected[notMatched[i][1] - 1])
				return;
		}
		
		if (cnt == N) {
			++result;
			return;
		}
		
		isSelected[cnt] = true;
		dfs(cnt + 1);
		
		isSelected[cnt] = false;
		dfs(cnt + 1);
	}
}