import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	private static final int[] dr = {-1, 1, 0, 0};
	private static final int[] dc = {0, 0, -1, 1};
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		char[][] map = new char[N][M];
		boolean[][][] visited = new boolean[N][M][64];				// 키 보유 상황
		int sRow = -1, sCol = -1;
		
		for (int i = 0; i < N; i++) {
			String line = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = line.charAt(j);
				
				if (map[i][j] == '0') {
					sRow = i;
					sCol = j;
				}
			}
		}
		
		Queue<int[]> queue = new ArrayDeque<int[]>();
		visited[sRow][sCol][0] = true; 
		queue.add(new int[] {sRow, sCol, 0, 0});				// 위치 + 키 보유 상황 + 거리
		
		while(!queue.isEmpty()) {
			int[] curPos = queue.poll();

			if (map[curPos[0]][curPos[1]] == '1') {
				System.out.println(curPos[3]);
				return;
			}
			
			for (int d = 0; d < 4; d++) {
				int nr = curPos[0] + dr[d];
				int nc = curPos[1] + dc[d];
				
				if (nr < 0 || nr >= N || nc < 0 || nc >= M || map[nr][nc] == '#') continue;
				
				int doorTest = map[nr][nc] - 'A';
				if (doorTest >= 0 && doorTest < 6) {
					int mask = charToCode(map[nr][nc]);
					if ((curPos[2] & mask) == 0) continue;
				}
				
				int nextKey = curPos[2];
				int keyTest = map[nr][nc] - 'a';
				if (keyTest >= 0 && keyTest < 6) {
					int mask = charToCode(map[nr][nc]);
					nextKey |= mask;
				}
				
				if (visited[nr][nc][nextKey]) continue;
				
				visited[nr][nc][nextKey] = true;
				queue.add(new int[] {nr, nc, nextKey, curPos[3] + 1});
			}
		}
		
		System.out.println(-1);
	}
	
	static int charToCode(char c) {
		switch (c) {
		case 'a':
		case 'A': return 32;
		case 'b':
		case 'B': return 16;
		case 'c':
		case 'C': return 8;
		case 'd':
		case 'D': return 4;
		case 'e':
		case 'E': return 2;
		case 'f':
		case 'F': return 1;
		default: return -1;
		}
	}
}