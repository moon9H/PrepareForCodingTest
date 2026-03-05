import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Solution {
	private static int N, maxCore, minLength;
	private static int[][] board;
	private static final int[] dr = {-1, 1, 0, 0};
	private static final int[] dc = {0, 0, -1, 1};
	private static ArrayList<int[]> sideCore;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			maxCore = Integer.MIN_VALUE;
			minLength = Integer.MAX_VALUE;
			board = new int[N][N];
			sideCore = new ArrayList<>();
			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					board[i][j] = Integer.parseInt(st.nextToken());
					if (board[i][j] == 1 && (i != 0 && j != 0 && i != N - 1 && j != N - 1)) sideCore.add(new int[] {i, j});
				}
			}
			
			dfs(0, 0, 0);
			sb.append('#').append(tc).append(' ').append(minLength).append('\n');
		}
		System.out.println(sb);
	}
	
	static void dfs(int count, int numCore, int lineSum) {
		if (count == sideCore.size()) {
			if (numCore > maxCore) {
				minLength = lineSum;
				maxCore = numCore;
			} else if (numCore == maxCore) {
				minLength = Math.min(minLength, lineSum);
			}
			return;
		}
		
		for (int d = 0; d < 4; d++) {
			if (okToConnect(sideCore.get(count), d)) {
				dfs(count + 1, numCore + 1, lineSum + connectLine(sideCore.get(count), d, 1));
				connectLine(sideCore.get(count), d, 0);
			}
		}
		dfs(count + 1, numCore, lineSum);
	}
	
	static int connectLine(int[] curPos, int dir, int mode) {
		int length = 0;
		int nr = curPos[0] + dr[dir], nc = curPos[1] + dc[dir];
		while(nr >= 0 && nr < N && nc >= 0 && nc < N) {
			board[nr][nc] = mode;
			length += mode;
			nr += dr[dir];
			nc += dc[dir];
		}
		return length;
	}
	
	static boolean okToConnect(int[] curPos, int dir) {
		int nr = curPos[0] + dr[dir], nc = curPos[1] + dc[dir];
		while(nr >= 0 && nr < N && nc >= 0 && nc < N) {
			if (board[nr][nc] == 1) return false;
			nr += dr[dir];
			nc += dc[dir];
		}
		return true;
	}
}