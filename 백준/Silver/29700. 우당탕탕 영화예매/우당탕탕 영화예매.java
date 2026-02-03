import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException{
		// 입력 처리
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int[][] seats = new int[N][M];
		for (int i = 0; i < N; i++) {
			String line = br.readLine();
			for (int j = 0; j < M; j++) {
				seats[i][j] = line.charAt(j) - '0';
			}
		}
		
		// 메인 로직 시작
		int result = 0;
		for (int i = 0; i < N; i++) {
			if (K > M) break;
			int sum = 0;
			for (int j = 0; j < K; j++) {
				sum += seats[i][j];
			}
			if (sum == 0) result ++;
			for (int j = 1; j < M - K + 1; j++) {
				sum -= seats[i][j - 1];
				sum += seats[i][j + K - 1];
				if (sum == 0) result++;
			}
		}
		System.out.println(result);
	}
}