import java.io.*;
import java.util.*;

public class Main {
    static int res = 0;
    static List<Integer>[] edges;
    static int[] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());   // 사람의 수
        int M = Integer.parseInt(st.nextToken());   // 친구 관계의 수

        edges = new ArrayList[N];
        for(int i = 0;i<N;i++) edges[i] = new ArrayList<>();
        visited = new int[N];

        for(int i = 0;i<M;i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            edges[a].add(b);
            edges[b].add(a);
        }

        for(int i = 0;i<N && res == 0;i++) {
            dfs(i, 0);
        }

        System.out.println(res);
    }

    static void dfs (int start, int depth) {
        if(depth >= 5) {
            res = 1;
            return;
        }

        for(int edge : edges[start]) {
            if(visited[edge] == 0 && res == 0) {
                visited[edge] = 1;
                dfs(edge, depth+1);
                visited[edge] = 0;
            }
        }
    }
}