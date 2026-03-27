import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		long M = Long.parseLong(st.nextToken());

		long[] arr = new long[N];
		long max = 0;

		for (int i = 0; i < N; i++) {
			arr[i] = Long.parseLong(br.readLine());
			max = Math.max(max, arr[i]);
		}

		long left = 1;
		long right = max;
		long ans = 0;

		while (left <= right) {
			long mid = (left + right) / 2;

			long count = 0;
			for (int i = 0; i < N; i++) {
				count += arr[i] / mid;
			}

			if (count >= M) {
				ans = mid;
				left = mid + 1;
			} else {
				right = mid - 1;
			}
		}

		System.out.println(ans);
	}
}