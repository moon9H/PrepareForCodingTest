import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	private static int[] A;
	private static int N;
	public static void main(String[] args) throws IOException{
		// 입력 처리
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		A = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			A[i] = Integer.parseInt(st.nextToken());
		}
				
		Arrays.sort(A);
		
		int M = Integer.parseInt(br.readLine());
		int[] B = new int[M];											// A 안에 존재하는 지 알아내야 하는 정수들
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < M; i++) {
			B[i] = Integer.parseInt(st.nextToken());
		}
		
		for (int i = 0; i < M; i++) {
			System.out.println(bSearch(B[i]));
		}
	}
	
	static int bSearch(int target) {
		int left = 0;
		int right = N - 1;
		
		while (left <= right) {
			int mid = (left + right) / 2;
			if (A[mid] == target) return 1;
			else if (A[mid] > target) right = mid - 1;
			else left = mid + 1;
		}
		
		return 0;
	}
}
