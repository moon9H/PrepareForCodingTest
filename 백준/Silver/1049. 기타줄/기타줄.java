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
		
		int minPack = Integer.MAX_VALUE;
		int minOne = Integer.MAX_VALUE;
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int pack = Integer.parseInt(st.nextToken());
			int one = Integer.parseInt(st.nextToken());
			
			minPack = Math.min(minPack, pack);
			minOne = Math.min(minOne, one);
		}
		
		int ans = Integer.MAX_VALUE;
		
		// 1. 전부 낱개로 사는 경우
		ans = Math.min(ans, N * minOne);
		
		// 2. 패키지 + 낱개 섞어서 사는 경우
		ans = Math.min(ans, (N / 6) * minPack + (N % 6) * minOne);
		
		// 3. 남는 개수가 있어도 패키지 하나 더 사는 경우
		ans = Math.min(ans, ((N / 6) + 1) * minPack);
		
		System.out.println(ans);
	}
}