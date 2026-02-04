import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	
	public static int[] dfs(int[][] arr, boolean[][] visited, int[] current) {
		if (current[0] == 0) {
			return current;
		}
		int[][] dir = {{0, 1}, {0, -1}, {-1, 0}};
		
		int[] leftPos = new int[2];
		leftPos[0] = current[0] + dir[0][0];
		leftPos[1] = current[1] + dir[0][1];
		
		int[] rightPos = new int[2];
		rightPos[0] = current[0] + dir[1][0];
		rightPos[1] = current[1] + dir[1][1];
		
		int[] fowardPos = new int[2];
		fowardPos[0] = current[0] + dir[2][0];
		fowardPos[1] = current[1] + dir[2][1];
		
		if (leftPos[0] >= 0 && leftPos[0] < 100 && leftPos[1] >= 0 && leftPos[1] < 100
				&& arr[leftPos[0]][leftPos[1]] != 0 && !visited[leftPos[0]][leftPos[1]]) {
			visited[leftPos[0]][leftPos[1]] = true;
			int result[] = dfs(arr, visited, leftPos);
			if (result != null) {
				return result;
			}
		} else if (rightPos[0] >= 0 && rightPos[0] < 100 && rightPos[1] >= 0 && rightPos[1] < 100
				&& arr[rightPos[0]][rightPos[1]] != 0 && !visited[rightPos[0]][rightPos[1]]) {
			visited[rightPos[0]][rightPos[1]] = true;
			int result[] = dfs(arr, visited, rightPos);
			if (result != null) {
				return result;
			}
		} else if (fowardPos[0] >= 0 && fowardPos[0] < 100 && fowardPos[1] >= 0 && fowardPos[1] < 100
				&& arr[fowardPos[0]][fowardPos[1]] != 0 && !visited[fowardPos[0]][fowardPos[1]]) {
			visited[fowardPos[0]][fowardPos[1]] = true;
			int result[] = dfs(arr, visited, fowardPos);
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