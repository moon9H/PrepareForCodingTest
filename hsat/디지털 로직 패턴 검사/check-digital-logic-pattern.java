import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String S = br.readLine();
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int K = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		boolean isNoised = false;
		
		HashMap<Long, Integer> map = new HashMap<>();
		
		long value = 0;

		long mask = (1L << K) - 1;
		
		for (int i = 0; i < S.length(); i++) {
			int bit = S.charAt(i) - '0';
			

			value = ((value << 1) | bit) & mask;

			if (i < K - 1) {
				continue;
			}
			
			int count = 0;
			
			if (map.get(value) != null) {
				count = map.get(value);
			}
			
			count++;
			map.put(value, count);
			
			if (count >= M) {
				isNoised = true;
				break;
			}
		}
		
		if (isNoised) System.out.println(1);
		else System.out.println(0);
	}
}