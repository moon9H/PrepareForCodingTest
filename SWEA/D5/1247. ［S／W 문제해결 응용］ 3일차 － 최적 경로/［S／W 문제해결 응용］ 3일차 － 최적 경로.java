import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Solution {
	private static int N, homeRow, homeCol, companyRow, companyCol, minDist;
	private static boolean[] selected;
	private static ArrayList<Integer> ans;
	private static int[][] customerPos;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			customerPos = new int[N][2];
			selected = new boolean[N];
			minDist = Integer.MAX_VALUE;
			ans = new ArrayList<>();
			StringTokenizer st = new StringTokenizer(br.readLine());
			companyRow = Integer.parseInt(st.nextToken());
			companyCol = Integer.parseInt(st.nextToken());
			homeRow = Integer.parseInt(st.nextToken());
			homeCol = Integer.parseInt(st.nextToken());
			for (int i = 0; i < N; i++) {
				customerPos[i][0] = Integer.parseInt(st.nextToken());
				customerPos[i][1] = Integer.parseInt(st.nextToken());
			}
			
			dfs(0);
			sb.append('#').append(tc).append(' ').append(minDist).append('\n');
		}
		System.out.println(sb);
	}
	
	static void dfs(int count) {
		if (count == N) {
			int dist = Math.abs(customerPos[ans.get(0)][0] - homeRow) 
					+ Math.abs(customerPos[ans.get(0)][1] - homeCol)
					+ Math.abs(customerPos[ans.get(N - 1)][0] - companyRow)
					+ Math.abs(customerPos[ans.get(N - 1)][1] - companyCol);
			for (int i = 0; i < N - 1; i++) {
				dist += Math.abs(customerPos[ans.get(i)][0] - customerPos[ans.get(i + 1)][0])
						+ Math.abs(customerPos[ans.get(i)][1] - customerPos[ans.get(i + 1)][1]);
			}
			
			minDist = Math.min(dist, minDist);
			return;
		}
		
		for (int i = 0; i < N; i++) {
			if (selected[i]) continue;
			selected[i] = true;
			ans.add(i);
			dfs(count + 1);
			selected[i] = false;
			ans.remove(ans.size() - 1);
		}
	}
}