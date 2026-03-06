import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[] inDegrees = new int[N + 1];
		ArrayList<Integer>[] graph = new ArrayList[N + 1];
		for (int i = 0; i <= N; i++) {
			graph[i] = new ArrayList<>();
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			graph[from].add(to);
			++inDegrees[to];
		}
		
		Queue<Integer> queue = new ArrayDeque<>();
		for (int i = 1; i <= N; i++) {
			if (inDegrees[i] == 0) queue.add(i);
		}
		
		StringBuilder sb = new StringBuilder();
		while(!queue.isEmpty()) {
			int curNode = queue.poll();
			sb.append(curNode).append(' ');
			for (Integer node : graph[curNode]) {
				if (--inDegrees[node] == 0) queue.add(node);
			}
		}
		System.out.println(sb);
	}
}