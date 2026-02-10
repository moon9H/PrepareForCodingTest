import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	private static int N, maxBreakEggs;
	private static int[][] eggs;

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		maxBreakEggs = 0;
		eggs = new int[N][2];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			eggs[i][0] = Integer.parseInt(st.nextToken());							// 내구도
			eggs[i][1] = Integer.parseInt(st.nextToken());							// 무게
		
		}
		breakEggs(0, 0);
		
		System.out.println(maxBreakEggs);
	}
	
	
	static void breakEggs(int index, int sum) {
		if (2 * (N - index + sum) < maxBreakEggs) return;
		
		if (index == N) {
			maxBreakEggs = Math.max(maxBreakEggs, sum);
			return;
		}
		
		if (eggs[index][0] <= 0 || sum == N - 1) {
			breakEggs(index + 1, sum);
			return;
		}
		
		for (int i = 0; i < N; i++) {
			if (i == index || eggs[i][0] <= 0) continue;
			
			
			eggs[index][0] -= eggs[i][1];
			eggs[i][0] -= eggs[index][1];
			
			int broken = 0;
			if (eggs[index][0] <= 0) ++broken;
			if (eggs[i][0] <= 0) ++broken;
			
			breakEggs(index + 1, sum + broken);
			
			eggs[index][0] += eggs[i][1];
			eggs[i][0] += eggs[index][1];
		}	
	}
}