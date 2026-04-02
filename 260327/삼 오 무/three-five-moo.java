import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		long N = Long.parseLong(br.readLine());

		long left = 1;
		long right = N * 2; // 충분히 크게
		long ans = 0;

		while (left <= right) {
			long mid = (left + right) / 2;
			long count = mid - mid / 3 - mid / 5 + mid / 15;

			if (count >= N) {
				ans = mid;
				right = mid - 1;
			} else {
				left = mid + 1;
			}
		}

		System.out.println(ans);
	}
}