import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException{
		// 입력 처리
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		// 상근이가 가지고 있는 숫자 카드 입력
		int[] cards = new int[20_000_001];
		Set<Integer> set = new HashSet<>();
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			int n = Integer.parseInt(st.nextToken()) + 10_000_000;
			++cards[n];
			set.add(n);
		}
		
		
		int M = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < M; i++) {
			int m = Integer.parseInt(st.nextToken()) + 10_000_000;
			sb.append(set.contains(m) ? cards[m] + " " : 0 + " ");
		}
		System.out.println(sb);
	}
}