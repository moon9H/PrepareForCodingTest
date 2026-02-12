import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int[][] board;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[][] papers = new int[N][2];
		int minRow = 101, minCol = 101;
		int maxRow = 0, maxCol = 0;
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			papers[i][0] = Integer.parseInt(st.nextToken());
			papers[i][1] = Integer.parseInt(st.nextToken());
			
			minRow = Math.min(papers[i][0], minRow);
			minCol = Math.min(papers[i][1], minCol);
			maxRow = Math.max(papers[i][0] + 10, maxRow);
			maxCol = Math.max(papers[i][1] + 10, maxCol);
		}
		
		int size = (maxRow - minRow > maxCol - minCol) ? maxRow - minRow : maxCol - minCol;
		int mappingSize = 1;
		while (mappingSize < size) {
			mappingSize *= 4;
		}
		
		board = new int[mappingSize][mappingSize];
		
		for (int[] p : papers) {
			int r = p[0] - minRow;
			int c = p[1] - minCol;
			for (int i = r; i < r + 10; i++) {
				for (int j = c; j < c + 10; j++) {
					board[i][j] = 1;
				}
			}
		}
		
		System.out.println(dfs(0, 0, mappingSize));
	}
	
	static int dfs(int row, int col, int size) {
		
		int sum = 0;
		for (int r = row; r < row + size; r++) {
			for (int c = col; c < col + size; c++) {
				sum += board[r][c];
			}
		}
		
		if (sum == size * size) {
			return size * size;
		}else if (sum == 0) {
			return 0;
		} else {
			int p1 = dfs(row, col, size / 2);
			int p2 = dfs(row, col + size / 2, size / 2);
			int p3 = dfs(row + size / 2, col, size / 2);
			int p4 = dfs(row + size / 2, col + size / 2, size / 2);
			
			return p1 + p2 + p3 + p4;
		}
	}
}