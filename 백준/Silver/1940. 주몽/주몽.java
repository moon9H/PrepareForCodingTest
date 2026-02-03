import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		int[] numList = new int[N];
		
		for (int i = 0; i < N; i++) {
			numList[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(numList);
		int s = 0, l = N - 1;
		int pairCnt = 0;
		int sum = 0;
		while (l > s) {
			sum = numList[s] + numList[l];
			if (sum > M) {
				l--;
			} else if (sum < M) {
				s++;
			} else {
				s++;
				l--;
				pairCnt++;
			}
		}
		System.out.println(pairCnt);
	}
}