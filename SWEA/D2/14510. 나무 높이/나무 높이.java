import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {
	// 홀수날에 높이 1, 짝수날에 2 => 모든 나무가 가장 큰 나무과 높이가 같아지는 최소 일수
	// 나무의 개수 N은 2 이상 100 이하
	// 짝수날의 개수가 홀수날 개수보다 하나 많을 때 최적의 선택
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine()); 

		for (int test_case = 1; test_case <= T; test_case++) {
			int N = Integer.parseInt(br.readLine());

			List<Integer> arr = new ArrayList<>();

			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int max = 0;
			for (int i = 0; i < N; i++) {
				int h = Integer.parseInt(st.nextToken());
				arr.add(h);
				if (h > max)
					max = h; 
			}

			int odd = 0;
			int even = 0;

			for (int i = 0; i < N; i++) {
				int diff = max - arr.get(i);
				if (diff == 0)
					continue;

				odd += diff % 2;
				even += diff / 2;
			}

			// 짝수날보다 홀수날이 같거나 더 많아지는 순간까지 홀수날에 + 2 
			while (even > odd + 1) {
				even--;
				odd += 2;
			}

			int res = 0;
			if (odd > even) {
				// 홀수날이 더 많으면: 1, 2, 1, 2, 1... 순서이므로
				res = odd * 2 - 1;
			} else if (even > odd) {
				// 짝수날이 더 많으면: (짝수날이 1개 더 많은 경우만 남음))
				res = even * 2;
			} else {
				res = odd + even;
			}

			System.out.println("#" + test_case + " " + res);
		}
	}
}