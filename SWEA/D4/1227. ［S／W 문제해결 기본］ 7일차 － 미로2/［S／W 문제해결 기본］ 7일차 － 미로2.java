import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class Solution {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = 10;
		for (int testCase = 1; testCase <= T; testCase++) {
			// 입력 처리
			br.readLine();
			int[][] maze = new int[100][100];
			boolean[][] visited = new boolean[100][100];
			for (int i = 0; i < 100; i++) {
				String line = br.readLine();
				for (int j = 0; j < 100; j++) {
					maze[i][j] = line.charAt(j) - '0';
				}
			}
			int[] startPos = {1, 1};
			int[][] dir = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
			int result = 0;
			Queue<int[]> queue = new ArrayDeque<int[]>();
			queue.offer(startPos);
			visited[1][1] = true;
			while(!queue.isEmpty()) {
				int[] curPos = queue.poll();
				for (int i = 0; i < 4; i++) {
					int[] nPos = new int[2];
					nPos[0] = curPos[0] + dir[i][0];
					nPos[1] = curPos[1] + dir[i][1];
					if (maze[nPos[0]][nPos[1]] == 0 && !visited[nPos[0]][nPos[1]]) {
						visited[nPos[0]][nPos[1]] = true;
						queue.offer(nPos);
					} else if (maze[nPos[0]][nPos[1]] == 3 && !visited[nPos[0]][nPos[1]]) {
						result = 1;
						break;
					}
				}
				if (result == 1) break;
			}
			
			sb.append("#").append(testCase).append(" ").append(result).append("\n");
		}
		System.out.println(sb.toString());
	}
}