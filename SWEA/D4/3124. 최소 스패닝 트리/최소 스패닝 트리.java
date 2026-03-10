import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;
 
public class Solution {
    private static int V;
    private static int[] parents;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            V = Integer.parseInt(st.nextToken());
            makeSets();
            int E = Integer.parseInt(st.nextToken());
            ArrayList<int[]> edgeGraph = new ArrayList<>();
            for (int i = 0; i < E; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());
                 
                edgeGraph.add(new int[] {a, b, c});
            }
             
            edgeGraph.sort((o1, o2) -> {
                return Integer.compare(o1[2], o2[2]);
            });
             
            int cnt = 0;
            long result = 0;
            for (int[] edge : edgeGraph) {
                if (union(edge[0], edge[1])) {
                    ++cnt;
                    result += edge[2];
                    if (cnt == V - 1) break;
                }
            }
             
            sb.append('#').append(tc).append(' ').append(result).append('\n');
        }
        System.out.println(sb);
    }
     
    static void makeSets() {
        parents = new int[V + 1];
        for (int i = 0; i <= V; i++) {
            parents[i] = i;
        }
    }
     
    static int findSet(int a) {
        if (a == parents[a]) return a;
        return parents[a] = findSet(parents[a]); 
    }
     
    static boolean union(int a, int b) {
        int aRoot = findSet(a);
        int bRoot = findSet(b);
         
        if (aRoot == bRoot) return false;
         
        parents[bRoot] = aRoot;
        return true;
    }
}