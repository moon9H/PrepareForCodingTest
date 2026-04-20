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
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int[][] adjMatrix = new int[N][N];
			int[][] D = new int[N][N];
			
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					adjMatrix[i][j] = Integer.parseInt(st.nextToken());
					if (i == j) continue;
					if (adjMatrix[i][j] == 0) D[i][j] = 10000;
					else D[i][j] = 1;
				}
			}
			
			for (int k = 0; k < N; k++) {
				for (int i = 0; i < N; i++) {
					if (i == k) continue;
					for (int j = 0; j < N; j++) {
						if (j == i || j == k) continue;			
						D[i][j] = Math.min(D[i][k] + D[k][j], D[i][j]);
					}
				}
			}
			
			int answer = Integer.MAX_VALUE;
			for (int i = 0; i < N; i++) {
				int sum = 0;
				for (int j = 0; j < N; j++) {
					sum += D[i][j];
				}
				answer = Math.min(answer, sum);
			}
			
			sb.append('#').append(tc).append(' ').append(answer).append('\n');
		}
		System.out.println(sb);
	}
}