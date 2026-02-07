import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[][] ingredients;
    static int N, diff;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        diff = 1_000_000_001;
        ingredients = new int[N][2];            // 0 : S(신맛 - 곱), 1 : B(쓴맛 - 합)
        for (int i = 0; i < N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            ingredients[i][0] = Integer.parseInt(st.nextToken());
            ingredients[i][1] = Integer.parseInt(st.nextToken());
        }

        dfs(0, 1, 0, 0);
        System.out.println(diff);
    }

    static void dfs(int cnt, int sour, int bitter, int select){
        if (cnt == N){
            if (select != 0)
                diff = Math.min(Math.abs(sour - bitter), diff);
            return;
        }

        dfs(cnt + 1, sour * ingredients[cnt][0], bitter + ingredients[cnt][1], select + 1);
        dfs(cnt + 1, sour, bitter, select);

    }
}