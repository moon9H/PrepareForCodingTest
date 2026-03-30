import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());

		for (int tc = 0; tc < T; tc++) {
			String[] input = br.readLine().split(" ");
			long x = Long.parseLong(input[0]);
			long y = Long.parseLong(input[1]);

			long dist = y - x;
			long n = (long) Math.sqrt(dist);

			if (n * n == dist) {
				sb.append(2 * n - 1).append("\n");
			} else if (dist <= n * n + n) {
				sb.append(2 * n).append("\n");
			} else {
				sb.append(2 * n + 1).append("\n");
			}
		}

		System.out.print(sb);
	}
}