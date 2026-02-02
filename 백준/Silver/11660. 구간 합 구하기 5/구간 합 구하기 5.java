import java.util.Arrays;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		// 표의 크기
		int N = sc.nextInt();
		// 합을 구해야 하는 줄 수
		int M = sc.nextInt();

		int[][] arr = new int[N + 1][N + 1];

		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				arr[i][j] = arr[i - 1][j] + arr[i][j - 1] - arr[i - 1][j - 1] + sc.nextInt();
			}
		}

		int sum = 0;

		for (int i = 1; i <= M; i++) {
			int A = sc.nextInt();
			int B = sc.nextInt();
			int C = sc.nextInt();
			int D = sc.nextInt();

			sum = arr[C][D] - arr[A-1][D] - arr[C][B-1] + arr[A-1][B-1];

			System.out.println(sum);
			
		}

		sc.close();
	}

}
