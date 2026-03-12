import java.util.*;
import java.io.*;

public class Solution {
    static int[] visited;
    static int N;
    static pos[] customers;
    static pos home;
    static int min;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int tc = Integer.parseInt(br.readLine());

        for(int t = 1;t<=tc;t++) {
            N = Integer.parseInt(br.readLine());    // 고객의 수 N
            StringTokenizer st = new StringTokenizer(br.readLine());
            int homeR = Integer.parseInt(st.nextToken());
            int homeC = Integer.parseInt(st.nextToken());
            home = new pos(homeR, homeC);   // 집의 좌표
            int coR = Integer.parseInt(st.nextToken());
            int coC = Integer.parseInt(st.nextToken());
            pos co = new pos(coR, coC);     // 회사의 좌표
            customers = new pos[N+1];   // 고객 좌표 배열
            customers[N] = co;
            for(int i = 0;i<N;i++) {
                int cusR = Integer.parseInt(st.nextToken());
                int cusC = Integer.parseInt(st.nextToken());
                customers[i] = new pos(cusR, cusC);
            }
            visited = new int[N+1];
            min = Integer.MAX_VALUE;

            dfs(0, N, 0);
            System.out.printf("#%d %d%n", t, min);
        }
    }

    static void dfs (int depth, int last, int totalDist) {
        if(depth == N) {
            // 모든 고객을 방문한경우 last에서 home까지의 거리를 더한뒤 최소값 계산
            // 계산 종료후 return
            int dist = Math.abs(customers[last].r - home.r) + Math.abs(customers[last].c - home.c);
            min = Math.min(min, totalDist + dist);
        }

        // visited 배열을 통해 모든 고객 방문하기
        for(int i = 0;i<N;i++) {
            if(visited[i] == 1) continue;
            int dist = Math.abs(customers[last].r - customers[i].r) + Math.abs(customers[last].c - customers[i].c);
            visited[i] = 1;
            dfs(depth + 1, i, totalDist + dist);
            visited[i] = 0;
        }
    }

    static class pos {
        int r;
        int c;
        pos(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
}
