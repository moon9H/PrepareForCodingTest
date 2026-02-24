import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int[] pos = new int[100_001];
		boolean[] visited = new boolean[100_001];
		Queue<int[]> queue = new ArrayDeque<>();
		queue.add(new int[] {N, 0});
		visited[N] = true;
		while(!queue.isEmpty()) {
			int[] cur = queue.poll();
			if (cur[0] == K) {
				System.out.println(cur[1]);
				break;
			}
			
			if (cur[0] - 1 >= 0 && !visited[cur[0] - 1]) {
				queue.add(new int[] {cur[0] - 1, cur[1] + 1});
				visited[cur[0] - 1] = true;
			}
			
			if (cur[0] + 1 <= 100_000 && !visited[cur[0] + 1]) {
				queue.add(new int[] {cur[0] + 1, cur[1] + 1});
				visited[cur[0] + 1] = true;
			}
			
			if (2 * cur[0] <= 100_000 && !visited[2 * cur[0]]) {
				queue.add(new int[] {2 * cur[0], cur[1] + 1});
				visited[2 * cur[0]] = true;
			}
		}
	}
}