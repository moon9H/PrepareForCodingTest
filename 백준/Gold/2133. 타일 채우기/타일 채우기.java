import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	static int[] memo;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		memo = new int[N + 1];
		Arrays.fill(memo, -1);
		
		if (N > 0) memo[0] = 1;
		
		if (N >= 2) memo[2] = 3;
		
		System.out.println(tiling(N));
	}
	
	static int tiling(int n) {
		if (n % 2 != 0) return 0;
		
		if (memo[n] != -1) return memo[n];
		
		int result = tiling(n - 2) * 3;
		
		for (int i = n - 4; i >= 0; i -= 2) {
			result += tiling(i) * 2;
		}
		
		memo[n] = result;
		
		return memo[n];
	}
}