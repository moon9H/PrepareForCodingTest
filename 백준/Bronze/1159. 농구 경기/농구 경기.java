import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		int[] count = new int[26];
		
		for (int i = 0; i < N; i++) {
			String name = br.readLine();
			count[name.charAt(0) - 'a']++;
		}
		
		StringBuilder sb = new StringBuilder();
		
		for (int i = 0; i < 26; i++) {
			if (count[i] >= 5) {
				sb.append((char)(i + 'a'));
			}
		}
		
		if (sb.length() == 0) {
			System.out.println("PREDAJA");
		} else {
			System.out.println(sb);
		}
	}
}