import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {

	public static void main(String[] args) throws IOException{
		// 입력 처리
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int testCase = 1; testCase <= T; testCase++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			int[] snackWeights = new int[N];
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				snackWeights[i] = Integer.parseInt(st.nextToken());
			}
			
			// 메인 로직 시작
			Arrays.sort(snackWeights);
			int s = 0, l = N - 1;
			int sum = 0;
			int max = -1;
			while (l > s) {
				sum = snackWeights[s] + snackWeights[l];
				if (sum > M) {
					l--;
				} else if (sum < M) {
					s++;
					if (sum > max) {
						max = sum;
					}
				} else {
					max = sum;
					break;
				}
			}
			sb.append("#").append(testCase).append(" ").append(max).append("\n");
		}
		System.out.println(sb.toString());
	}
}