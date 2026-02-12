import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	private static int[] trees;
	private static int N, M, result;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		trees = new int[N];
		st = new StringTokenizer(br.readLine());
		
		int maxHeight = Integer.MIN_VALUE;
		
		for (int i = 0; i < N; i++) {
			trees[i] = Integer.parseInt(st.nextToken());
			maxHeight = Math.max(maxHeight, trees[i]);
		}
		
		dfs(0, maxHeight);
		
		System.out.println(result);
	}
	
	static void dfs(int left, int right) {
		if (left > right) {
			return;
		}
		
		int mid = (left + right) / 2;
		long cutted = checkCut(mid);
		
		if (cutted >= M) {
			result = mid;
			dfs(mid + 1, right);
		} else{
			dfs(left, mid - 1);
		}
	}
	
	static long checkCut(int height) {
		long sum = 0;
		for (int i = 0; i < N; i++) {
			int diff = trees[i] - height;
			if (diff > 0) sum += diff;
		}
		return sum;
	}
}