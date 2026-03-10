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
			
			dfs(0, 0);
			sb.append('#').append(tc).append(' ').append(minDist).append('\n');
		}
		System.out.println(sb);
	}
	
	static void dfs(int count, int length) {
		if (length >= minDist) return;
		
		if (count == N) {
			int last = ans.get(ans.size() - 1);
			length += Math.abs(customerPos[last][0] - homeRow)
					+ Math.abs(customerPos[last][1] - homeCol);
			minDist = Math.min(minDist, length);
			return;
		}
		
		for (int i = 0; i < N; i++) {
			if (selected[i]) continue;
			
			selected[i] = true;
			ans.add(i);
			
			if (count == 0) {
				dfs(count + 1, length + Math.abs(companyRow - customerPos[i][0])
						+ Math.abs(companyCol - customerPos[i][1]));
			} else {
				int prev = ans.get(ans.size() - 2);
				dfs(count + 1, length + Math.abs(customerPos[prev][0] - customerPos[i][0])
						+ Math.abs(customerPos[prev][1] - customerPos[i][1]));
			}
			
			ans.remove(ans.size() - 1);
			selected[i] = false;
		}
	}
}