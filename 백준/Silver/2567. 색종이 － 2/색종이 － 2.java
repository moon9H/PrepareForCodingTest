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
			int r = p[0] - minRow + 1;
			int c = p[1] - minCol + 1;
			for (int i = r; i < r + 10; i++) {
				for (int j = c; j < c + 10; j++) {
					board[i][j] = 1;
				}
			}
		}
		
		int ans = 0;
		
		int[] dr = {-1, 1, 0, 0};
		int[] dc = {0, 0, -1, 1};
		
		for (int i = 0; i < mappingSize; i++) {
		    for (int j = 0; j < mappingSize; j++) {
		        if (board[i][j] == 1) { // 색종이가 있는 칸이라면
		            for (int k = 0; k < 4; k++) {
		                int nr = i + dr[k];
		                int nc = j + dc[k];
		                // 주변이 0(빈 공간)이면 그 면은 둘레다!
		                if (nr < 0 || nr >= mappingSize + 2 || nc < 0 || nc >= mappingSize + 2 || board[nr][nc] == 0) {
		                    ans++;
		                }
		            }
		        }
		    }
		}
		System.out.println(ans);
	}
}