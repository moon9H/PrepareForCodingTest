import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	private static int[] dr = {-1, 0, 1, 0};
	private static int[] dc = {0, 1, 0, -1};
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String command = br.readLine();
		int curDir = 0;
		int curX = 0;
		int curY = 0;
		for (int i = 0; i < command.length(); i++) {
			switch (command.charAt(i)) {
			case 'L':
				curDir = (curDir - 1 + 4) % 4;
				break;

			case 'R':
				curDir = (curDir + 1) % 4;
				break;
				
			case 'F':
				curX += dc[curDir];
				curY += dr[curDir];
				break;
			}			
		}
		System.out.println(curX + " " + curY);
	}
}