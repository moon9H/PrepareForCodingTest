import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static int N, totalCnt; // N: 체스판 행렬 크기, totalCnt: 경우의 수
	static boolean[] col, slash, bSlash;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		totalCnt = 0;
		
		// 사선 방향 개수는 2*N
		col = new boolean[N+1];
		slash = new boolean[2*N+1];
		bSlash = new boolean[2*N]; 
		
		// 재귀 함수 
		setQueen(1);
		
		// 출력 
		System.out.println(totalCnt);
	}
	
	static void setQueen(int row) {
		if (row > N) {
			++totalCnt; 
			return; 
		}
		
		for (int c = 1; c <= N; c++) {
			// 우상향 사선은 현재 위치 좌표의 합과 동일하고, 좌상향 사선은 차와 동일함 
			if (col[c] || slash[row+c] || bSlash[(row-c) + N]) continue;
			col[c] = slash[row+c] = bSlash[(row-c)+N] = true; 
			setQueen(row+1);
			col[c] = slash[row+c] = bSlash[(row-c)+N] = false;
		}
	}
}
