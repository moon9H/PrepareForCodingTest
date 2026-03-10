import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	private static int N, M, minChickenDist;
	private static int[][] map;
	private static ArrayList<int[]> chickenPos;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		minChickenDist = Integer.MAX_VALUE;
		map = new int[N][N];
		chickenPos = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 2) chickenPos.add(new int[] {i, j});
			}
		}
		dfs(0, 0);
		System.out.println(minChickenDist);
	}
	
	static void dfs(int count, int select) {
		if (select == chickenPos.size() - M) {
			minChickenDist = Math.min(minChickenDist, calcChickenDist());
			return;
		}
		
		if (count == chickenPos.size()) return;
		
		map[chickenPos.get(count)[0]][chickenPos.get(count)[1]] = 0;
		dfs(count + 1, select + 1);
		
		map[chickenPos.get(count)[0]][chickenPos.get(count)[1]] = 2;
		dfs(count + 1, select);
	}
	
	static int calcChickenDist() {
		int total = 0;
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] != 1) continue;
				
				int minDist = Integer.MAX_VALUE;
				for (int[] chicken : chickenPos) {
					if (map[chicken[0]][chicken[1]] != 2) continue;
					minDist = Math.min(Math.abs(i - chicken[0]) + Math.abs(j - chicken[1]), minDist);
				}
				
				total += minDist;
			}
		}
		return total;
	}
}