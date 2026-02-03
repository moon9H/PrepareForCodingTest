import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class Solution {

	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = 10;
		for (int testCase = 1; testCase <= T; testCase++) {
			br.readLine();
			int result = 0;
			int [][]maze = new int[16][16];
			boolean [][]visited = new boolean[16][16];
			for (int i = 0; i < 16; i++) {
				String line = br.readLine();
				for (int j = 0; j < 16; j++) {
					maze[i][j] = line.charAt(j) - '0';
				}
			}
			
			int[] dr = {0, -1,  0, 1};
			int[] dc = {1,  0, -1, 0};
			
			Deque<int[]> stack = new ArrayDeque<>();
			stack.push(new int[] {1, 1});
			visited[1][1] = true;
			
			quit : while(!stack.isEmpty()) {
				int[] curPos = stack.pop();
				for (int i = 0; i < 4; i++) {
					int nr = curPos[0] + dr[i];
					int nc = curPos[1] + dc[i];
					
					if (maze[nr][nc] == 3) {
                        result = 1;
                        break quit;
                    }
                    if (maze[nr][nc] == 0 && !visited[nr][nc]) {
                        stack.push(new int[] {nr, nc});
                        visited[nr][nc] = true;
                    }
				}
			}
			sb.append("#").append(testCase).append(" ").append(result).append("\n");
		}
		System.out.println(sb.toString());
	}

}