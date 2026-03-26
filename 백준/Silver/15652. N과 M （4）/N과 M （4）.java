import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	private static int N, M;
	private static final ArrayList<Integer> ans = new ArrayList<>();
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		dfs(0, 0);
	}
	
	static void dfs(int count, int start) {
		if (count == M) {
			for (Integer integer : ans) {
				System.out.print(integer + " ");
			}
			System.out.println();
			return;
		}
		
		for (int i = start; i < N; i++) {
			ans.add(i + 1);
			dfs(count + 1, i);
			ans.remove(ans.size() - 1);
		}
	}
}