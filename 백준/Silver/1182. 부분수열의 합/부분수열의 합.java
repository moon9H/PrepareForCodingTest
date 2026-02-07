import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static int[] arr;
    private static int N, S;
    private static int cnt;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        cnt = 0;
        arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        dfs(0, 0, 0);

        System.out.println(cnt);
    }

    public static void dfs(int count, int sum, int picked){
        if (count == N) {
            if (picked > 0 && sum == S) cnt++;
            return;
        }

        dfs(count + 1, sum + arr[count], picked + 1);
        dfs(count + 1, sum, picked);
    }
}