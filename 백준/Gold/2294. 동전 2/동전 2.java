import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());   // 동전의 개수
        int k = Integer.parseInt(st.nextToken());   // 목표값

        int[] coins = new int[n];
        for(int i = 0;i<n;i++) {
            coins[i] = Integer.parseInt(br.readLine());
        }

        int[] dp = new int[k+1];
        Arrays.fill(dp, 100001);
        dp[0] = 0;

        for(int coin : coins) {
            for(int i = coin;i<=k;i++) {
                dp[i] = Math.min(dp[i], dp[i-coin]+1);
            }
        }

        System.out.println(dp[k] == 100001 ? -1 : dp[k]);
    }
}