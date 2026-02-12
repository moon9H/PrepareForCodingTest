import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	private static int N; 
	private static int[][] arr; 
 	// 영상의 크기 N은 2의 제곱수: 1 <= N, N <= 64
	// 계속 분할하면서 값을 출력 
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		N = Integer.parseInt(br.readLine()); 
		arr = new int[N][N]; 
		
		for (int i = 0; i < N; i++) {
			String s = br.readLine();
			for (int j = 0; j < s.length(); j++) {
				arr[i][j] = s.charAt(j) - '0';
			}
		}
		
		solve(0, 0, N);
	}
	
	public static void solve(int r, int c, int size) {
	    // 1. 현재 영역이 모두 같은 숫자인지 체크
	    if (isSame(r, c, size)) {
	        System.out.print(arr[r][c]); // 같으면 숫자 출력 
	        return;
	    }

	    // 2. 숫자가 다르다면 4분할 시작
	    int newSize = size / 2;
	    System.out.print("("); // 분할 시작할 때 괄호 열기

	    solve(r, c, newSize);                         // 왼쪽 위
	    solve(r, c + newSize, newSize);               // 오른쪽 위
	    solve(r + newSize, c, newSize);               // 왼쪽 아래
	    solve(r + newSize, c + newSize, newSize);     // 오른쪽 아래

	    System.out.print(")"); // 4개 영역 다 돌면 괄호 닫기
	}
	
	public static boolean isSame(int r, int c, int size) {
	    int firstValue = arr[r][c]; // 기준이 되는 첫 번째 값

	    for (int i = r; i < r + size; i++) {
	        for (int j = c; j < c + size; j++) {
	            // 하나라도 다른 숫자가 발견되면 FALSE 리턴 
	            if (arr[i][j] != firstValue) {
	                return false;
	            }
	        }
	    }
	    // 모든 반복문을 무사히 통과하면 전부 같은 숫자인 것!
	    return true;
	}
}
