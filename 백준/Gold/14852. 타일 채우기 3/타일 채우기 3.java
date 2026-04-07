import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	private static final int DEVIDE = 1_000_000_007;
	private static long[] memo, sum;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		memo = new long[N + 1];
		sum = new long[N + 1];
		Arrays.fill(memo, -1);
		memo[0] = 1;
		memo[1] = 2;
		if (N >= 2)
			memo[2] = 7;
		
		sum[0] = 1;
		sum[1] = (sum[0] + memo[1]) % DEVIDE;
		if (N >= 2)
			sum[2] = (sum[1] + memo[2]) % DEVIDE;
        
        for (int i = 3; i <= N; i++) {
            memo[i] = (2 * memo[i - 1] % DEVIDE
                     + 3 * memo[i - 2] % DEVIDE
                     + 2 * sum[i - 3] % DEVIDE) % DEVIDE;

            sum[i] = (sum[i - 1] + memo[i]) % DEVIDE;
        }

        System.out.println(memo[N]);
	}
}