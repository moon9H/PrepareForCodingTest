import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= T; tc++) {
			int N = Integer.parseInt(br.readLine());
			int[] treeHeights = new int[N];
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				treeHeights[i] = Integer.parseInt(st.nextToken());
			}
			Arrays.sort(treeHeights);
			
			int minDay = Integer.MAX_VALUE;
			for (int target = treeHeights[N - 1]; target <= treeHeights[N - 1] + 1; target++) {
				int evenDiff = 0, oddDiff = 0;
				int ans = 0;
				
				for (int i = 0; i < N - 1; i++) {
					int residual = target - treeHeights[i];
					if (residual > 0) {
						evenDiff += residual / 2;
						oddDiff += residual % 2;
					}
				}
				
				if (evenDiff > oddDiff) {
					while(Math.abs(evenDiff - oddDiff) > 1) {
						evenDiff--;
						oddDiff += 2;
					}
				}
				
				if (oddDiff > evenDiff) {
					ans = 2 * oddDiff - 1;
				} else if (evenDiff > oddDiff) {
					ans = 2 * evenDiff;
				} else {
					ans  = oddDiff + evenDiff;
				}
				
				minDay = Math.min(minDay, ans);
			}
			sb.append('#').append(tc).append(' ').append(minDay).append('\n');
		}
		
		System.out.println(sb);
	}
}