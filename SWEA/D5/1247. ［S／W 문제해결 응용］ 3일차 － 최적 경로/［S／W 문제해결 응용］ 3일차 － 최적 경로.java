import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
    private static boolean[] isVisited;
    private static int[] result, homePos, companyPos;
    private static int[][] customerPos;
    private static int customerCnt, shortestPath;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        for (int tc =  1; tc <= T; tc++){
            // 입력 처리
            customerCnt = Integer.parseInt(br.readLine());
            companyPos = new int[2];                          // 회사 위치
            homePos = new int[2];                             // 집 위치
            customerPos = new int[customerCnt][2];          // 들려야하는 고객들 위치
            isVisited = new boolean[customerCnt];
            result = new int[customerCnt];
            shortestPath = Integer.MAX_VALUE;
            StringTokenizer st = new StringTokenizer(br.readLine());
            companyPos[0] = Integer.parseInt(st.nextToken());
            companyPos[1] = Integer.parseInt(st.nextToken());
            homePos[0] = Integer.parseInt(st.nextToken());
            homePos[1] = Integer.parseInt(st.nextToken());

            for (int customer = 0; customer < customerCnt; customer++){
                customerPos[customer][0] = Integer.parseInt(st.nextToken());
                customerPos[customer][1] = Integer.parseInt(st.nextToken());
            }
            dfs(0);
            sb.append('#').append(tc).append(' ').append(shortestPath).append('\n');
        }
        System.out.println(sb);
    }

    public static void dfs(int cnt){
        if (cnt == customerCnt){

            int pathLength = Math.abs(customerPos[result[0]][0] - companyPos[0]) +
                             Math.abs(customerPos[result[0]][1] - companyPos[1]);
            for (int i = 1; i < customerCnt; i++){
                pathLength += Math.abs(customerPos[result[i]][0] - customerPos[result[i - 1]][0]) +
                        Math.abs(customerPos[result[i]][1] - customerPos[result[i - 1]][1]);
            }
            pathLength += Math.abs(customerPos[result[customerCnt - 1]][0] - homePos[0]) +
                    Math.abs(customerPos[result[customerCnt - 1]][1] - homePos[1]);

            shortestPath = Math.min(shortestPath, pathLength);
            return;
        }

        for (int i = 0; i < customerCnt; i++){
            if (isVisited[i]) continue;

            isVisited[i] = true;
            result[cnt] = i;
            dfs(cnt + 1);
            isVisited[i] = false;
        }
    }
}