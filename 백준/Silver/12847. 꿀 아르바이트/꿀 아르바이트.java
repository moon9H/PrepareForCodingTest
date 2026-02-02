import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		
		int[] dailyPay = new int[N];
		for (int i = 0; i < N; i++) {
			dailyPay[i] = Integer.parseInt(st.nextToken());
		}
		long rangeSum = 0;
		for (int i = 0; i < M; i++) {
			rangeSum += dailyPay[i];
		}
		long maxSum = rangeSum;
		for (int i = M; i < N; i++) {
			rangeSum = rangeSum - (dailyPay[i - M]) + dailyPay[i];
			if (rangeSum > maxSum) maxSum = rangeSum;
		}
		System.out.println(maxSum);
	}
}