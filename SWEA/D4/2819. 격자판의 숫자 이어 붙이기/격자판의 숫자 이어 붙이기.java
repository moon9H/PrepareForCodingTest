import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

public class Solution {
	private static final int N = 4;
	private static int[][] board;
	private static final int[] dr = {-1, 1, 0, 0};
	private static final int[] dc = {0, 0, -1, 1};
	static class Node{
		int row;
		int col;
		String num;
		public Node(int row, int col, String num) {
			super();
			this.row = row;
			this.col = col;
			this.num = num;
		}
	}
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			board = new int[N][N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					board[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			Set<String> numbers = new HashSet<>();
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					Queue<Node> queue = new ArrayDeque<>();
					queue.add(new Node(i, j, String.valueOf(board[i][j])));
					while(!queue.isEmpty()) {
						Node curNode = queue.poll();
						if (curNode.num.length() == 7) {
							numbers.add(curNode.num);
							continue;
						}
						
						for (int d = 0; d < 4; d++) {
							int nr = curNode.row + dr[d];
							int nc = curNode.col + dc[d];
							
							if (nr < 0 || nr >= N || nc < 0 || nc >= N) continue;
							
							queue.add(new Node(nr, nc, curNode.num.concat(String.valueOf(board[nr][nc]))));
						}
					}
				}
			}
			
			sb.append('#').append(tc).append(' ').append(numbers.size()).append('\n');
		}
		System.out.println(sb);
	}
}