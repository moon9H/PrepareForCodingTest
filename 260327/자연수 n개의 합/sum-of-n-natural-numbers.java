import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		long S = Long.parseLong(br.readLine());
		
		long left = 1, right = S;
		long maxNim = 0;
		
		while (left <= right) {
			long mid = (left + right) / 2;
			
			if ((mid * (mid + 1)) / 2 <= S) {
				maxNim = Math.max(maxNim, mid);
				left = mid + 1;
			} else {
				right = mid - 1;
			}
		}
		
		System.out.println(maxNim);
	}
}