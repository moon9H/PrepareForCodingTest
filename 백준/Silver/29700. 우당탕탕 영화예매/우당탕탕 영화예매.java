import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		int N = Integer.parseInt(st.nextToken()); // 행 개수 
		int M = Integer.parseInt(st.nextToken()); // 열 개수
		int K = Integer.parseInt(st.nextToken()); // 동아리원 수 
		int[][] arr = new int[N][M]; // 좌석 현황 배열 
		int result = 0; // 가능한 좌석 개수 
		
		// 입력 값 배열에 담기 
		for (int i = 0; i < N; i++) {
			String line = in.readLine();
			for (int j = 0; j < M; j++) {
				arr[i][j] = line.charAt(j) - '0'; // '0' 빼서 숫자로 담기!!! 
			}
		}

		for (int i = 0; i < N; i++) {
			int count = 0; // 비어있는 좌석의 개수 
			for (int j = 0; j < M; j++) {
				if (arr[i][j] == 0) {
					count++; // 0인 요소를 만나면 증가
				} else {
					// 1인 요소를 만났을 때 count에 누적된 값이 K보다 같거나 크면 result에 반영 
					if (count >= K) {
						result += (count - K + 1); 
					}
					// count 초기화 
					count = 0;
				}
			}
			// 마지막이 0으로 끝났을 때를 위한 처리 
			if (count >= K) {
				result += (count - K + 1);
			}
		}
		System.out.print(result);
	}
}
