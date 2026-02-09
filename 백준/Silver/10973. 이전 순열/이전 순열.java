import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	private static int N;
	private static int[] input;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		input = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			input[i] = Integer.parseInt(st.nextToken());
		}
		
		if (pp())
			for (int a : input) System.out.print(a + " ");
		else
			System.out.println(-1);
	}
	
	private static boolean pp() {
		int i = N - 1;
		
		while (i > 0 && input[i - 1] <= input[i]) --i;
		
		if (i == 0) return false;
		
		int j = N - 1;
		while (input[i - 1] <= input[j]) --j;
		
		swap(i - 1, j);
		 
		int k = N - 1;
		while (i < k) swap(i++, k--);
		
		return true;
	}
	
	private static void swap(int a, int b) {
		int temp = input[a];
		input[a] = input[b];
		input[b] = temp;
	}
}