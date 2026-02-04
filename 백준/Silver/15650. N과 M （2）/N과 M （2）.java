import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void comb(int cnt,int start, int N, int M, int[] numbers, StringBuilder sb){
        if (cnt == M){
            for (int i = 0; i < M; i++) sb.append(numbers[i]).append(" ");
            sb.append("\n");
            return;
        }

        for (int i = start; i <= N; i++){
            numbers[cnt] = i;

            comb(cnt + 1, i + 1, N, M, numbers, sb);
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] numbers = new int[M];

        comb(0, 1, N, M, numbers, sb);

        System.out.println(sb);
    }
}
