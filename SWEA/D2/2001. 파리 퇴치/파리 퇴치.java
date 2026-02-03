import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		for (int testCase = 1; testCase <= T; testCase++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			int[][] flyMap = new int[N][N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					flyMap[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			// flyMap 배열 -> 누적합 배열로 변경
			for (int i = 0; i < N; i++) {
				for (int j = 1; j < N; j++) {
					flyMap[i][j] += flyMap[i][j - 1];
				}
			}
			int maxResult = 0;
			int curSum = 0;
			for (int row = 0; row < N - M + 1; row++) {
				for (int col = 0; col < N - M  + 1; col++) {
					for (int i = 0; i < M; i++) {
						if (col == 0) {
							curSum += flyMap[row + i][col + M - 1];
						}else {
							curSum += flyMap[row + i][col + M - 1] - flyMap[row + i][col - 1];
						}
					}
					if (curSum > maxResult) maxResult = curSum;
					curSum = 0;
				}
			}
			sb.append("#").append(testCase).append(" ").append(maxResult).append("\n");
		}
		System.out.println(sb.toString());
	}
}