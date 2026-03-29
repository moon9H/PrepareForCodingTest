import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		long[] arr = new long[N];
		for (int i = 0; i < N; i++) {
			arr[i] = Long.parseLong(br.readLine());
		}

		Arrays.sort(arr);

		long left = 1;
		long right = arr[N - 1] - arr[0];
		long answer = 0;

		while (left <= right) {
			long mid = (left + right) / 2;

			if (canPlace(arr, M, mid)) {
				answer = mid;
				left = mid + 1;
			} else {
				right = mid - 1;
			}
		}

		System.out.println(answer);
	}

	static boolean canPlace(long[] arr, int M, long dist) {
		int count = 1;
		long last = arr[0];

		for (int i = 1; i < arr.length; i++) {
			if (arr[i] - last >= dist) {
				count++;
				last = arr[i];
			}
		}

		return count >= M;
	}
}