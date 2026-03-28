import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		long K = Long.parseLong(br.readLine());
		
		long left = 1;
		long right = (long) N * N;
		long answer = 0;
		
		while (left <= right) {
			long mid = (left + right) / 2;
			long count = 0;
			
			for (int i = 1; i <= N; i++) {
				count += Math.min((long) N, mid / i);
			}
			
			if (count >= K) {
				answer = mid;
				right = mid - 1;
			} else {
				left = mid + 1;
			}
		}
		
		System.out.println(answer);
	}
}