import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		int [] arr = new int[N];
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		int [] partialSum = new int[N];
		partialSum[0] = arr[0];
		for (int i = 1; i < N; i++) {
			partialSum[i] += partialSum[i - 1] + arr[i];
		}
		
		for (int cnt = 0; cnt < M; cnt++) {
			st = new StringTokenizer(br.readLine());
			int i = Integer.parseInt(st.nextToken());
			int j = Integer.parseInt(st.nextToken());
			if (i == j) {
				sb.append(arr[i - 1]).append("\n");
			} else {
				if (i == 1) {
					sb.append(partialSum[j - 1]).append("\n");
				} else {
					sb.append(partialSum[j - 1] - partialSum[i - 2]).append("\n");	
				}
			}
		}
		System.out.println(sb.toString());
	}
}