import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    private static int[] dr = {0, -1, 0, 1, 0};
    private static int[] dc = {0, 0, 1, 0, -1};
    private static int[][] map = new int[11][11];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int M = Integer.parseInt(st.nextToken());
            int A = Integer.parseInt(st.nextToken());
            int maxPower = 0;
            int[] aMoves = new int[M];
            int[] bMoves = new int[M];
            int[][] bcInform = new int [A][4];      // 0 : row, 1 : col, 2 : range, 3 : power
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < M; i++) {
                aMoves[i] = Integer.parseInt(st.nextToken());
            }

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < M; i++) {
                bMoves[i] = Integer.parseInt(st.nextToken());
            }

            for (int i = 0; i < A; i++) {
                st = new StringTokenizer(br.readLine());
                bcInform[i][0] = Integer.parseInt(st.nextToken());
                bcInform[i][1] = Integer.parseInt(st.nextToken());
                bcInform[i][2] = Integer.parseInt(st.nextToken());
                bcInform[i][3] = Integer.parseInt(st.nextToken());
            }

            int aCurRow = 1, aCurCol = 1;
            int bCurRow = 10, bCurCol = 10;
            int t = 0;
            do {
                int currentBest = 0;
                for (int i = -1; i < A; i++){
                    int aCanPower = 0;
                    if (i != -1){
                        int distA = Math.abs(aCurRow - bcInform[i][1])
                                + Math.abs(aCurCol - bcInform[i][0]);

                        if (distA <= bcInform[i][2]) aCanPower += bcInform[i][3];
                        else continue;
                    }
                    for (int j = -1; j < A; j++) {
                        int bCanPower = 0;
                        if (j != -1){
                            int distB = Math.abs(bCurRow - bcInform[j][1])
                                    + Math.abs(bCurCol - bcInform[j][0]);

                            if (distB <= bcInform[j][2]) bCanPower += bcInform[j][3];
                            else continue;
                        }

                        int totalPower = 0;
                        if (i != -1 && i == j){
                            totalPower = bcInform[i][3];
                        } else {
                            totalPower = aCanPower + bCanPower;
                        }

                        currentBest = Math.max(totalPower, currentBest);
                    }
                }

                maxPower += currentBest;

                if (t == M) break;
                aCurRow += dr[aMoves[t]];
                aCurCol += dc[aMoves[t]];
                bCurRow += dr[bMoves[t]];
                bCurCol += dc[bMoves[t]];

            } while(t++ < M);

            sb.append('#').append(tc).append(' ').append(maxPower).append('\n');
        }
        System.out.println(sb);
    }
}