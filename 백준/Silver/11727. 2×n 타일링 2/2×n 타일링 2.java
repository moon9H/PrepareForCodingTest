import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	private static final int DEVIDE = 10_007;
	private static int[] memo;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		memo = new int[N + 1];
		Arrays.fill(memo, -1);
		memo[0] = 1;
		memo[1] = 1;
		
		System.out.println(tiling(N));
	}
	
	static int tiling(int n) {
		
		if (memo[n] != -1) return memo[n];
		
		memo[n] = (tiling(n - 1) + 2 * tiling(n - 2)) % DEVIDE;
		
		return memo[n];
	}
}