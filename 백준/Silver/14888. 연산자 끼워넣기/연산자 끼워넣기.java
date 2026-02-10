import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    private static int N, min, max;
    private static int[] arr, result, operations = new int [4];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        result = new int[N - 1];
        min = Integer.MAX_VALUE;
        max = Integer.MIN_VALUE;
        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 4; i++) {
            operations[i] = Integer.parseInt(st.nextToken());
        }

        dfs(0);
        System.out.println(max);
        System.out.println(min);
    }

    static void dfs(int cnt){
        if (cnt == N - 1){
            int r = oper(arr[0], arr[1], result[0]);
            for (int i = 2; i < N; i++){
                r = oper(r, arr[i], result[i - 1]);
            }
            min = Math.min(r, min);
            max = Math.max(r, max);
            return;
        }

        for (int i = 0; i < 4; i++){
            if (operations[i] == 0) continue;

            operations[i]--;
            result[cnt] = i;
            dfs(cnt + 1);
            operations[i]++;
        }
    }

    static int oper(int a, int b, int oper) {
        int r = 0;
        switch (oper) {
            case 0:
                r = a + b;
                break;
            case 1:
                r = a - b;
                break;
            case 2:
                r = a * b;
                break;
            case 3:
                r = a / b;
                break;
        }
        return r;
    }
}
