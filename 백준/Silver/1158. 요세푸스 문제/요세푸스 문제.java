import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		sb.append("<");
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		Queue<Integer> queue = new ArrayDeque<>();
		for (int i = 1; i<= N; i++)
			queue.add(i);

		while (queue.size() > 1){
			for (int i = 0; i < K - 1; i++){
				queue.add(queue.poll());
			}
			sb.append(queue.poll());
			sb.append(", ");
		}
		sb.append(queue.poll());
		sb.append(">");
		System.out.println(sb.toString());
	}
}