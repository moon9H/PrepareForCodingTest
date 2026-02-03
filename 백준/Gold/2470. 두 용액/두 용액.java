import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		int[] liquidList = new int[N];
		for (int i = 0; i < N; i++) {
			liquidList[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(liquidList);
		int s = 0, l = N - 1;
		int A = liquidList[s], B = liquidList[l];
		long closest = Long.MAX_VALUE;
		while (l > s) {
			int result = liquidList[l] + liquidList[s];
			if (Math.abs(result) < closest) {
				closest = Math.abs(result);
				A = liquidList[s];
				B = liquidList[l];
			}
			if (result > 0) {
				l--;
			} else if (result < 0) {
				s++;
			} else {
				break;
			}
		}
		
		System.out.println(A + " " + B);
	}
}
