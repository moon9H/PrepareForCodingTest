import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/* 색종이 만들기 */
public class Main {
	private static int N, B = 0, W = 0; // B: 파란색, W: 하얀색 개수
	private static int[][] arr;

	// 0: 하얀색, 1: 파란색
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		arr = new int[N][N];

		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		solve(0, 0, N);
		System.out.println(W);
		System.out.println(B);
	}
	
	public static void solve(int r, int c, int size) {
		// isSame이 true이면 바로 리턴 
		if (isSame(r, c, size)) {
			return;
		}
		
		// isSame이 false면 구간 분할하여 반복 
		int newSize = size / 2;
	    solve(r, c, newSize);                         // 왼쪽 위
	    solve(r, c + newSize, newSize);               // 오른쪽 위
	    solve(r + newSize, c, newSize);               // 왼쪽 아래
	    solve(r + newSize, c + newSize, newSize);     // 오른쪽 아래
	}

	public static boolean isSame(int r, int c, int size) {
		int firstValue = arr[r][c];

		for (int i = r; i < r + size; i++) {
			for (int j = c; j < c + size; j++) {
				if (arr[i][j] != firstValue) {
					return false;
				}
			}
		}
		
		// isSame이 true를 반환하기 전 색상 개수 업데이트 
		if (firstValue == 0) {
			W++;
		} else {
			B++;
		}
		return true;

	}
}
