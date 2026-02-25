import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        for (int tc = 1; tc <= 10; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int V = Integer.parseInt(st.nextToken());
            int E = Integer.parseInt(st.nextToken());
            ArrayList<Integer>[] graph = new ArrayList[V + 1];
            int[] inDegree = new int[V + 1];
            for (int i = 0; i <= V; i++) {
                graph[i] = new ArrayList<>();
            }
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < E; i++) {
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());
                graph[from].add(to);
                ++inDegree[to];
            }

            Queue<Integer> queue = new ArrayDeque<>();
            for (int i = 1; i <= V; i++) {
                if (inDegree[i] == 0) queue.add(i);
            }


            sb.append('#').append(tc).append(' ');
            while (!queue.isEmpty()){
                int curVertex = queue.poll();
                sb.append(curVertex).append(' ');
                for (Integer v: graph[curVertex]) {
                    if (--inDegree[v] == 0) queue.add(v);
                }
            }
            sb.append('\n');
        }
        System.out.println(sb);
    }
}