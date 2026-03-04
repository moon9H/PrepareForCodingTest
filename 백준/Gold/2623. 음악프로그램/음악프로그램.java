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
		ArrayList<Integer>[] graph = new ArrayList[N + 1];
		int[] inDegree = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			graph[i] = new ArrayList<>();
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int K = Integer.parseInt(st.nextToken());
			int[] order = new int[K];
			for (int j = 0; j < K; j++) {
				order[j] = Integer.parseInt(st.nextToken());
			}
			for (int j = 0; j < K - 1; j++) {
				graph[order[j]].add(order[j + 1]);
				++inDegree[order[j + 1]];
			}
		}
		
		Queue<Integer> queue = new ArrayDeque<>();
		
		for (int i = 1; i <= N; i++) {
			if (inDegree[i] == 0) queue.add(i);
		}
		
		StringBuilder sb = new StringBuilder();
		while(!queue.isEmpty()) {
			int curNode = queue.poll();
			sb.append(curNode).append('\n');
			
			for (Integer to : graph[curNode]) {
				if (--inDegree[to] == 0) queue.add(to);
			}
		}
		int sum = 0;
		for (int i : inDegree) {
			sum += i;
		}
		if (sum != 0) System.out.println(0);
		else System.out.println(sb);
		
	}
}