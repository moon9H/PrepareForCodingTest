import java.io.*;
import java.util.*;

public class Main {

    static class Module {
        boolean[] used;
        int score;

        Module(boolean[] used, int score) {
            this.used = used;
            this.score = score;
        }
    }

    static int N, M;
    static int total;
    static int[] energy;

    static boolean[] selected;

    static ArrayList<Module> modules = new ArrayList<>();
    static HashSet<String> made = new HashSet<>();

    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        total = N * M;
        energy = new int[total];
        selected = new boolean[total];

        for (int r = 0; r < N; r++) {
            st = new StringTokenizer(br.readLine());

            for (int c = 0; c < M; c++) {
                int idx = r * M + c;
                energy[idx] = Integer.parseInt(st.nextToken());
            }
        }

        // 모든 칸을 시작점으로 DFS
        for (int i = 0; i < total; i++) {
            selected[i] = true;
            dfs(1, energy[i]);
            selected[i] = false;
        }

        int answer = Integer.MIN_VALUE;

        // 모듈 2개 선택
        for (int i = 0; i < modules.size(); i++) {
            for (int j = i + 1; j < modules.size(); j++) {

                Module a = modules.get(i);
                Module b = modules.get(j);

                int overlap = countOverlap(a.used, b.used);

                if (overlap == 2) {
                    int score = a.score + b.score;
                    answer = Math.max(answer, score);
                }
            }
        }

        System.out.println(answer);
    }

    static void dfs(int count, int sum) {

        if (count == 5) {
            String key = makeKey();

            // 같은 5칸 조합이 여러 순서로 만들어질 수 있으므로 중복 제거
            if (!made.contains(key)) {
                made.add(key);

                boolean[] copy = selected.clone();
                modules.add(new Module(copy, sum));
            }

            return;
        }

        /*
         * 현재 선택된 칸들 중 하나에서 상하좌우로 확장한다.
         * 그래서 항상 연결된 5칸짜리 모듈만 만들어진다.
         */
        boolean[] tried = new boolean[total];

        for (int cur = 0; cur < total; cur++) {

            if (!selected[cur]) {
                continue;
            }

            int r = cur / M;
            int c = cur % M;

            for (int d = 0; d < 4; d++) {
                int nr = r + dr[d];
                int nc = c + dc[d];

                if (nr < 0 || nr >= N || nc < 0 || nc >= M) {
                    continue;
                }

                int next = nr * M + nc;

                if (selected[next]) {
                    continue;
                }

                // 같은 단계에서 같은 칸을 중복으로 시도하지 않기 위한 처리
                if (tried[next]) {
                    continue;
                }

                tried[next] = true;

                selected[next] = true;
                dfs(count + 1, sum + energy[next]);
                selected[next] = false;
            }
        }
    }

    static String makeKey() {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < total; i++) {
            if (selected[i]) {
                sb.append(i).append(",");
            }
        }

        return sb.toString();
    }

    static int countOverlap(boolean[] a, boolean[] b) {
        int count = 0;

        for (int i = 0; i < total; i++) {
            if (a[i] && b[i]) {
                count++;
            }
        }

        return count;
    }
}