import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		int[] dist = new int[100_001];
		int[] cnt = new int[100_001];
		Arrays.fill(dist, -1);
		
		Queue<Integer>queue = new ArrayDeque<>();
		queue.add(N);
		dist[N] = 0;
		cnt[N] = 1;
		while(!queue.isEmpty()) {
			int cur = queue.poll();
			int nextDistance = dist[cur] + 1;
			
			int[] nexts = {cur - 1, cur + 1, cur * 2};
			for (int nx : nexts) {
				if (nx < 0 || nx > 100_000) continue;
				
				if (dist[nx] == -1) {
					dist[nx] = nextDistance;
					cnt[nx] = cnt[cur];
					queue.add(nx);
				} else if (dist[nx] == nextDistance) {
					cnt[nx] += cnt[cur];
				}
			}
		}
		System.out.println(dist[K]);
		System.out.println(cnt[K]);
	}
}