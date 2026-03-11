import java.util.*;
import java.io.*;

public class Main {
    static int[] dr = {0,1,0,-1};
    static int[] dc = {1,0,-1,0};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int size = Integer.parseInt(br.readLine());
        
        int[][] board = new int[size][size];
        int[][] dist = new int[size][size];
        for(int i = 0;i<size;i++) {
            Arrays.fill(dist[i], Integer.MAX_VALUE);
        }

        for(int i = 0;i<size;i++){
            int[] row = Arrays.stream(br.readLine().split("")).mapToInt(Integer::valueOf).toArray();
            for(int j = 0;j<size;j++){
                board[i][j] = row[j];
            }
        }

        PriorityQueue<Node> pq = new PriorityQueue<>((a, b) -> a.weight - b.weight);

        pq.add(new Node(0, 0, 0));
        dist[0][0] = 0;

        while(!pq.isEmpty()) {
            Node now = pq.poll();

            if(now.weight != dist[now.r][now.c]) continue;
            
            for(int i = 0;i<4;i++) {
                int nextR = now.r + dr[i];
                int nextC = now.c + dc[i];
                if(nextR >= 0 && nextC >= 0 && nextR < size && nextC < size) {
                    // 진행방향의 기존 weight보다 현재 연산하는 weight가 더 작을경우 queue에 추가한다.
                    int nextCost = now.weight;
                    if(board[nextR][nextC] == 0) {
                        nextCost += 1;    
                    }
                    if(dist[nextR][nextC] > nextCost) {
                        dist[nextR][nextC] = nextCost;
                        pq.add(new Node(nextR, nextC, nextCost));
                    }
                }
            }
        }

        System.out.println(dist[size-1][size-1]);

    }

    static class Node {
        int r;
        int c;
        int weight;
        public Node(int r, int c, int weight) {
            this.r = r;
            this.c = c;
            this.weight = weight;
        }
    }
}
