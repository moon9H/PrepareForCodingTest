import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		int[][] arr = new int[N][2];
		int[] origin = new int[N];
		boolean[] checked = new boolean[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = new int[] {Integer.parseInt(st.nextToken()), i + 1};
			origin[i] = arr[i][0];
		}
		Arrays.sort(arr, (o1, o2) -> o1[0] - o2[0]);	
		
		for (int i : origin) {
			for (int j = 0; j < N; j++) {
				if (arr[j][0] == i && !checked[j]) {
					sb.append((j + 1) + " ");
					checked[j] = true;
					break;
				}
			}
		}
		sb.append('\n');
		System.out.println(sb);
	}
}