import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	private static final int[] dy = {1, 0, -1, 0};
	private static final int[] dx = {0, 1, 0, -1};
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String command = br.readLine();
		int d = 0, cX = 0, cY = 0;
		for (int i = 0; i < command.length(); i++) {
			switch (command.charAt(i)) {
			case 'F':
				cX += dx[d];
				cY += dy[d];
				if (cX == 0 && cY == 0) {
					System.out.println(i + 1);
					return;
				}
				break;
			case 'R':
				d = (d + 1) % 4;
				break;
			case 'L':
				d = (d - 1 + 4) % 4;
				break;
			}
		}
		System.out.println(-1);
	}
}