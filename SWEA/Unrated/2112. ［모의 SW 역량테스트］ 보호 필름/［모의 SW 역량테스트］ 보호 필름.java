import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
	private static int D, W, K, minInjection;
	private static int[][] board;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			D = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			minInjection = Integer.MAX_VALUE;
			board = new int[D][W];
			for (int i = 0; i < D; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < W; j++) {
					board[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			dfs(0, 0);
			
			sb.append('#').append(tc).append(' ').append(minInjection).append('\n');
		}
		System.out.println(sb);
	}
	
	static void dfs(int floor, int count) {
		if (count >= minInjection) return;
		if (floor == D) {
			if (isCellOk()) {
				minInjection = Math.min(minInjection, count);
			}
			return;
		}
		
		dfs(floor + 1, count);
		
		int[] temp = Arrays.copyOf(board[floor], W);
		Arrays.fill(board[floor], 0);
		
		dfs(floor + 1, count + 1);
		
		board[floor] = Arrays.copyOf(temp, W);
		
		
		Arrays.fill(board[floor], 1);
		
		dfs(floor + 1, count + 1);
		board[floor] = Arrays.copyOf(temp, W);
	}
	
	static boolean isCellOk() {
		for (int i = 0; i < W; i++) {
			boolean pass = false;
			for (int j = 0; j <= D - K; j++) {
				int start = board[j][i];
				boolean ok = true;
				for (int k = 1; k < K; k++) {
					if (board[j + k][i] != start) {
						ok = false;
						break;
					}
				}
				
				if(ok) {
					pass = true;
					break;
				}
			}
			
			if (!pass) return false;
		}
		return true;
	}
}