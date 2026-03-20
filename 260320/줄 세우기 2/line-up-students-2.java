import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(br.readLine());
		int[][] stdList = new int[N][3];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int h = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			stdList[i] = new int[] {h, w, i + 1};
		}
		Arrays.sort(stdList, (o1, o2) -> {
			if (o1[0] != o2[0]) return o1[0] - o2[0];
			return o2[1] - o1[1];
		});
		for (int[] is : stdList) {
			for (int i : is) {
				sb.append(i + " ");
			}
			sb.append('\n');
		}
		System.out.println(sb);
	}
}