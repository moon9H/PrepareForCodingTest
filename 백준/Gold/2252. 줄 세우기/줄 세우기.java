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
		ArrayList<Integer>[] adjList = new ArrayList[N + 1];
		for (int i = 1; i <= N; i++) {
			adjList[i] = new ArrayList<>();
		}
		int[] inDegree = new int[N + 1];
		int M = Integer.parseInt(st.nextToken());
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			adjList[A].add(B);
			++inDegree[B];
		}
		
		Queue<Integer> queue = new ArrayDeque<>();
		for (int i = 1; i < N + 1; i++) {
			if (inDegree[i] == 0) queue.add(i);
		}
		
		StringBuilder sb = new StringBuilder();
		while(!queue.isEmpty()) {
			int curPos = queue.poll();
			sb.append(curPos).append(' ');
			for (int next : adjList[curPos]) {
				if (--inDegree[next] == 0) queue.add(next);
			}
		}
		sb.append('\n');
		System.out.println(sb);
	}
}