import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	private static int N, minCost;
	private static boolean[] isSelected;
	private static int[][] W;
	private static ArrayList<Integer> answer = new ArrayList<>();
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		isSelected = new boolean[N];
		W = new int[N][N];
		minCost = Integer.MAX_VALUE;
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				W[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		dfs(0);
		
		System.out.println(minCost);
	}
	
	static void dfs(int count) {
		if (count == N) {
			int sum = 0;
			for (int i = 0; i < N - 1; i++) {
				if (W[answer.get(i)][answer.get(i + 1)] == 0) {
					return;
				}
				sum += W[answer.get(i)][answer.get(i + 1)];
			}
			
			if (W[answer.get(answer.size() - 1)][answer.get(0)] == 0) return;
			sum += W[answer.get(answer.size() - 1)][answer.get(0)];
			
			minCost = Math.min(sum, minCost);
			return;
		}
		
		for (int i = 0; i < N; i++) {
			if (isSelected[i]) continue;
			isSelected[i] = true;
			answer.add(i);
			dfs(count + 1);
			answer.remove(answer.size() - 1);
			isSelected[i] = false;
		}
	}
}