import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	
	public static int[] dfs(int[][] arr, boolean[][] visited, int[] current) {
		if (current[0] == 0) {
			return current;
		}

		// 사다리 타기이므로 왼쪽이나 오른쪽에 길이 있다면 해당 방향으로 탐색을 진행해야 함.
		if (current[1] - 1 >= 0 && arr[current[0]][current[1] - 1] != 0 && !visited[current[0]][current[1] - 1]) {
			visited[current[0]][current[1] - 1] = true;
			current[1] -= 1;
			int result[] = dfs(arr, visited, current);
			if (result != null) {
				return result;
			}
		} else if (current[1] + 1 < 100 && arr[current[0]][current[1] + 1] != 0 && !visited[current[0]][current[1] + 1]) {
			visited[current[0]][current[1] + 1] = true;
			current[1] += 1;
			int result[] = dfs(arr, visited, current);
			if (result != null) {
				return result;
			}
		} else if (current[0] - 1 >= 0 && arr[current[0] - 1][current[1]] != 0 && !visited[current[0] - 1][current[1]]) {
			visited[current[0] - 1][current[1]] = true;
			current[0] -= 1;
			int result[] = dfs(arr, visited, current);
			if (result != null) {
				return result;
			}
		}
		return null;
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = 10;
		for (int testCase = 1; testCase <= T; testCase++) {
			// 입력 처리
			br.readLine();
			int[][] data = new int [100][100];
			boolean[][] visited = new boolean [100][100];			
			for (int i = 0; i < 100; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < 100; j++) {
					data[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			// 도착점 확인
			int [] finalPos = new int[2];
			for (int i = 0; i < 100; i++) {
				if (data[99][i] == 2) {
					finalPos[0] = 99;
					finalPos[1] = i;
				}
			}
			sb.append("#").append(testCase).append(" ").append(dfs(data, visited, finalPos)[1]).append("\n");
		}
		System.out.println(sb.toString());
	}

}