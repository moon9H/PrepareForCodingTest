import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int L = Integer.parseInt(st.nextToken());
		
		for (int len = L; len <= 100; len++) {
			int remain = N - len * (len - 1) / 2;
			
			if (remain < 0) break;
			
			if (remain % len == 0) {
				int start = remain / len;
				
				StringBuilder sb = new StringBuilder();
				for (int i = 0; i < len; i++) {
					sb.append(start + i).append(' ');
				}
				System.out.println(sb);
				return;
			}
		}
		
		System.out.println(-1);
	}
}