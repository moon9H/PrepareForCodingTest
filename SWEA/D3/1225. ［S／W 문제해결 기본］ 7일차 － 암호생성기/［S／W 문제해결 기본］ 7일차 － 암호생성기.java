import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Solution {
	public static void main(String[] args) throws Exception{
		int T = 10;
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for (int testCase = 1; testCase <= T; testCase++) {
			br.readLine();
			ArrayDeque<Integer> queue = new ArrayDeque<Integer>();
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i = 0; i < 8; i++) {
				queue.add(Integer.parseInt(st.nextToken()));
			}
			int cycleMinus = 1;
			while (true) {
				int firstElement = queue.poll();
				if (firstElement - cycleMinus <= 0) {
					queue.add(0);
					break;
				} else {
					queue.add(firstElement - cycleMinus);
				}
				cycleMinus++;
				if (cycleMinus > 5) cycleMinus = 1;
			}
			sb.append("#" + testCase + " ");
			for (Integer i : queue) {
				sb.append(i + " ");
			}
			sb.append("\n");
		}
		System.out.println(sb.toString());
	}
}