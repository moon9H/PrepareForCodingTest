import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	private static int N, M;
	private static int[] nums;
	private static boolean[] selected;
	private static int[] ans;
	private static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		nums = new int[N];
		selected = new boolean[N];
		ans = new int[M];
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(nums);
		
		dfs(0);
		System.out.print(sb);
	}
	
	static void dfs(int count) {
		if (count == M) {
			for (int i = 0; i < M; i++) {
				sb.append(ans[i]).append(" ");
			}
			sb.append("\n");
			return;
		}
		
		int prev = -1;
		for (int i = 0; i < N; i++) {
			if (selected[i]) continue;
			if (prev == nums[i]) continue;
			
			selected[i] = true;
			ans[count] = nums[i];
			prev = nums[i];
			
			dfs(count + 1);
			
			selected[i] = false;
		}
	}
}