import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int M = sc.nextInt();
		int[] arr = new int[N+1];
		arr[0] = 0;
		arr[1] = sc.nextInt();
		for (int i=2; i<=N; i++) {
			arr[i] = arr[i-1] + sc.nextInt();
		}
		
		int sum = 0; 
		for (int i=1; i<=M; i++) {
			int A = sc.nextInt();
			int B = sc.nextInt();
			sum = arr[B] - arr[A-1];
			System.out.println(sum);
		}
		sc.close();
	}
}
