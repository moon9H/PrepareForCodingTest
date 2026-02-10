import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static int N, totalCnt;
	
	static boolean[] col, slash, bSlash;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		totalCnt = 0;
		
		col = new boolean[N + 1];
		slash = new boolean[2 * N + 1];
		bSlash = new boolean[2 * N + 1];
		
		setQueen(1);							// 1행부터 놓기 시작
		
		System.out.println(totalCnt);
	}
	
	static void setQueen(int row) {
		// 기저 파트
		if (row > N) {
			++totalCnt;
			return;
		}
		
		// 유도 파트
		for (int c = 1; c <= N; c++) {
			// 유망성 체크 : row행 c열에 두는 것이 가능한지.
			if (col[c] || slash[row + c] || bSlash[row - c + N]) continue;			// 가지치기 (Pruning)
			
			col[c] = slash[row + c] = bSlash[row - c + N] = true;
			setQueen(row + 1);
			col[c] = slash[row + c] = bSlash[row - c + N] = false;					// 다른 선택지 시도를 위해 상태 원상복구
		}
	}
}
