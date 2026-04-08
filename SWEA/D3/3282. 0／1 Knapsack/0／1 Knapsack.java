import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int W = Integer.parseInt(st.nextToken());
			
			int[] w = new int[N + 1];
			int[] v = new int[N + 1];
			
			for (int i = 1; i <= N; i++) {
				st = new StringTokenizer(br.readLine());
				w[i] = Integer.parseInt(st.nextToken());
				v[i] = Integer.parseInt(st.nextToken());
			}
			
			int[][] K = new int[N + 1][W + 1];
			
			for (int i = 0; i <= N; i++) K[i][0] = 0;
			for (int i = 0; i <= W; i++) K[0][i] = 0;
			
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= W; j++) {
					if (w[i] > j) K[i][j] = K[i - 1][j];
					
					else {
						K[i][j] = Math.max(
								K[i - 1][j],
								K[i - 1][j - w[i]] + v[i]
								);
					}
				}
			}
			sb.append('#').append(tc).append(' ').append(K[N][W]).append('\n');
		}
		System.out.println(sb);
	}
}