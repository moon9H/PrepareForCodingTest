import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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
						
			Set<Integer> set = new HashSet<>();				// 중복 관리할 자료 구조
			for (int i = 0; i < rotateCnt; i++) {
				for (int j = 0; j < 4; j++) {
					int start = j * rotateCnt;
					int end = start + rotateCnt;
					set.add(Integer.parseInt(nums.substring(start, end), 16));
				}
				
				nums = nums.charAt(N - 1) + nums.substring(0, N - 1);
			}
			
			Integer[] converAns = set.toArray(new Integer[0]);
			
			Arrays.sort(converAns, (o1, o2) -> o2 - o1);
			
			sb.append('#').append(tc).append(' ').append(converAns[K - 1]).append('\n');
		}
		System.out.println(sb);
	}
}