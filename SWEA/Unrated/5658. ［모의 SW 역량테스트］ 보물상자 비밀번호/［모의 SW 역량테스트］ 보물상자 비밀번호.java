import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
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
			int K = Integer.parseInt(st.nextToken());
			int rotateCnt = N / 4;
			String nums = br.readLine();
						
			Set<String> set = new HashSet<>();				// 중복 관리할 자료 구조
			for (int i = 0; i < rotateCnt; i++) {
				int len = N / 4;
				set.add(nums.substring(0, len));
				set.add(nums.substring(len, 2 * len));
				set.add(nums.substring(2 * len, 3 * len));
				set.add(nums.substring(3 * len));
				
				nums = nums.charAt(N - 1) + nums.substring(0, N - 1);
			}
			
			ArrayList<String> ans = new ArrayList<>();
			for (String string : set) {
				ans.add(string);
			}
			
			Integer[] converAns = new Integer[set.size()];
			
			for (int i = 0; i < set.size(); i++) {
				converAns[i] = Integer.valueOf(ans.get(i), 16);
			}
			
			Arrays.sort(converAns, (o1, o2) -> o2 - o1);
			
			sb.append('#').append(tc).append(' ').append(converAns[K - 1]).append('\n');
		}
		System.out.println(sb);
	}
}