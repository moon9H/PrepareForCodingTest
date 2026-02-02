import java.io.*;
import java.util.*;

public class Solution {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int testCase = 1; testCase <= T; testCase++) {
			int N = Integer.parseInt(br.readLine());
			StringTokenizer st = new StringTokenizer(br.readLine());
			String[] deck = new String[N];
			for (int i = 0; i < N; i++)
				deck[i] = st.nextToken();
			String [] A;
			String [] B = new String[N / 2];
			if (N % 2 != 0) {
				A = new String[(N / 2) + 1];
				for (int i = 0; i < N / 2; i++) {
					A[i] = deck[i];
					B[i] = deck[(N / 2 + 1) + i];
				}
				A[N / 2] = deck[N / 2];
			} else {
				A = new String[N / 2];
				for (int i = 0; i < N / 2; i++) {
					A[i] = deck[i];
					B[i] = deck[(N / 2) + i];
				}
			}
			sb.append("#" + testCase + " ");
			for (int i = 0; i < N/2 ; i++) {
				sb.append(A[i] + " ");
				sb.append(B[i] + " ");
			}
			if (N % 2 != 0) {
				sb.append(A[N / 2]);
			}
			sb.append("\n");
		}
		System.out.println(sb.toString());
	}
}