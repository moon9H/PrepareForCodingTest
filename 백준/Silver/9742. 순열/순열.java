import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	private static int cnt;
	private static char[] input;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		String line;
		StringTokenizer st;
		while((line = br.readLine()) != null) {
			if (line.isEmpty()) break;
			
			st = new StringTokenizer(line);
			String originInput = st.nextToken();
			cnt = Integer.parseInt(st.nextToken());
			input = originInput.toCharArray();
			boolean isNext = true;
			
			int leftover = cnt - 1;
			while (leftover > 0) {
				isNext = np();
				if (!isNext) break;
				leftover--;
			}
			
			String ans = (isNext) ? new String(input) : "No permutation";
			
			sb.append(originInput).append(" ").append(cnt).append(" = ").append(ans).append("\n");
		}
		System.out.println(sb);
	}
	
	public static boolean np() {
		int i = input.length - 1;
		
		while (i > 0 && input[i - 1] >= input[i]) --i;
		
		if (i == 0) return false;
		
		int j = input.length - 1;
		
		while (input[i - 1] >= input[j]) --j;
		
		swap(i - 1, j);
		
		int k = input.length - 1;
		
		while (i < k) swap(i++, k--);
		
		return true;
	}
	
	private static void swap(int a, int b) {
		char temp = input[a];
		input[a] = input[b];
		input[b] = temp;
	}
}