import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		long S = Long.parseLong(br.readLine());

		long left = 1;
		long right = 2000000000L;
		long ans = 0;

		while (left <= right) {
			long mid = (left + right) / 2;
			long sum = mid * (mid + 1) / 2;

			if (sum <= S) {
				ans = mid;
				left = mid + 1;
			} else {
				right = mid - 1;
			}
		}

		System.out.println(ans);
	}
}