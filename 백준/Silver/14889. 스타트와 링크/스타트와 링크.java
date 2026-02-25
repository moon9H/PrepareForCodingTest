import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	private static int N, diff;
	private static int[] result;
	private static int[][] board;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		diff = Integer.MAX_VALUE;
		result = new int[N/2];
		board = new int[N][N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		dfs(0, 1);
		System.out.println(diff);
	}
	
	static void dfs(int cnt, int start) {
		if (cnt == N / 2) {
			int startTeam = 0;
			int linkTeam = 0;
			boolean[] isSelected = new boolean[N + 1];
			int[] linkResult = new int[N / 2];
			for (int i = 0; i < N / 2; i++) {
				isSelected[result[i]] = true;
			}
			int idx = 0;
			for (int i = 1; i <= N; i++) {
				if (!isSelected[i]) {
					linkResult[idx++] = i;
				}
			}
			
			for (int i = 0; i < N / 2 - 1; i++) {
				for (int j = i + 1; j < N / 2; j++) {
					startTeam += board[result[i] - 1][result[j] - 1] + board[result[j] - 1][result[i] - 1];
					linkTeam  += board[linkResult[i] - 1][linkResult[j] - 1] + board[linkResult[j] - 1][linkResult[i] - 1];
				}
			}
			
			diff = Math.min(diff, Math.abs(startTeam - linkTeam));
			return;
		}
		
		for (int i = start; i <= N; i++) {
			result[cnt] = i;
			dfs(cnt + 1, i + 1);
		}
	}
}