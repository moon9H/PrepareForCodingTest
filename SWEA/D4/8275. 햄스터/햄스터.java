import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Solution {
    private static int N, X, M, maxSum;
    private static int[] input, result;
    private static int[][] records;
    private static Stack<int[]> list;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++){
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            X = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            records = new int[M][3];
            input = new int[X + 1];
            list = new Stack<>();
            maxSum = -1;
            for (int i = 0; i <= X; i++) input[i] = i;
            for (int i = 0; i < M; i++){
                st = new StringTokenizer(br.readLine());
                records[i][0] = Integer.parseInt(st.nextToken());
                records[i][1] = Integer.parseInt(st.nextToken());
                records[i][2] = Integer.parseInt(st.nextToken());
            }
            result = new int[N];
            combination(0);
            String ans = "";
            if (list.isEmpty()){
                ans = "-1";
            }
            else {
                StringBuilder sb2 = new StringBuilder();
                for (int a : list.pop()) sb2.append(a).append(" ");
                ans = sb2.toString();
            }
            sb.append("#").append(tc).append(" ").append(ans).append("\n");
        }
        System.out.println(sb);
    }

    public static void combination(int cnt){
        if (cnt == N){
            boolean isSatisfied = true;
            for (int i = 0; i < M; i++){
                int left = records[i][0];
                int right = records[i][1];
                int hamCnt = records[i][2];

                int sum = 0;

                for (int j = left - 1; j < right; j++) {
                    sum += result[j];
                }
                if (sum != hamCnt){
                    isSatisfied = false;
                    break;
                }
            }
            if (isSatisfied) {
                int rSum = 0;
                for (int i = 0; i < result.length; i++) rSum += result[i];

                if (rSum > maxSum){
                    maxSum = rSum;
                    list.push(result.clone());
                }
            }
            return;
        }

        for (int i = 0; i <= X; i++){
            result[cnt] = input[i];
            combination(cnt + 1);
        }
    }
}