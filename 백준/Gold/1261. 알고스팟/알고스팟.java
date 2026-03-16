import java.io.*;
import java.util.*;

public class Main {
    static int[] dr = {0,1,0,-1};
    static int[] dc = {1,0,-1,0};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int sizeC = Integer.parseInt(st.nextToken());
        int sizeR = Integer.parseInt(st.nextToken());
        int[][] board = new int[sizeR][sizeC];
        
        for(int i = 0;i<sizeR;i++) {
            board[i] = Arrays.stream(br.readLine().split("")).mapToInt(Integer::valueOf).toArray();
        }

        int[][] minDist = new int[sizeR][sizeC];
        for(int i = 0;i<sizeR;i++) {
            Arrays.fill(minDist[i], Integer.MAX_VALUE);
        }
        minDist[0][0] = 0;

		Deque<pos> dq = new ArrayDeque<>();
        dq.addFirst(new pos(0, 0));
        while(!dq.isEmpty()) {
            pos now = dq.pollFirst();

            for(int i = 0;i<4;i++) {
                int nextR = now.r + dr[i];
                int nextC = now.c + dc[i];
                if(nextR < 0 || nextC < 0 || nextR >= sizeR || nextC >= sizeC) continue;
                int nextW = minDist[now.r][now.c] + board[nextR][nextC];
                if(nextW >= minDist[nextR][nextC]) continue;

				// 보드의 다음값이 0이면 deque의 앞에 추가
				// 보드의 다음값이 1이면 deque의 뒤에 추가
                minDist[nextR][nextC] = nextW;
				if(board[nextR][nextC] == 0) {
					dq.addFirst(new pos(nextR, nextC));
				} else {
					dq.addLast(new pos(nextR, nextC));
				}
            }
        }

        System.out.println(minDist[sizeR - 1][sizeC - 1]);
    }

    static class pos {
        int r;
        int c;
        pos(int r, int c){
            this.r = r;
            this.c = c;
        }
    }
}