import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static void repPerm(int cnt, int N, int M, int[] numbers, StringBuilder sb) { // cnt: 현재까지 뽑은 개수
        if (cnt == M) { // 순열 생성 완료
            for (int i = 0; i < M; i++) sb.append(numbers[i]).append(' ');
            sb.append('\n');
            return;
        }

        for (int i = 1; i <= N; i++) {
            numbers[cnt] = i;              // numbers[cnt] <- i
            repPerm(cnt + 1, N, M, numbers, sb);                 // repPerm(cnt+1)
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] numbers = new int[M];

        repPerm(0, N, M, numbers, sb);

        System.out.print(sb);
    }
}