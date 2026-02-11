import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int cnt = 0;
		while (N % 5 != 0) {
			N -= 3;
			cnt++;
		}
		
		if (N < 0) {
			System.out.println(-1);
		} else if (N == 0){
			System.out.println(cnt);
		} else {
			if (N % 5 == 0) {
				System.out.println(cnt + N / 5);
			}else if (N % 3 != 0) {
				System.out.println(-1);
			}
		}
	}
}