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
		long sum = -1;
		long rangeSum = 0;
		for (int i = 0; i < N - M + 1; i++) {
			for (int j = 0; j < M; j++) {
				rangeSum += dailyPay[i + j];
			}
			sum = (rangeSum > sum) ? rangeSum : sum;
			rangeSum = 0;
		}
		System.out.println(sum);
	}
}