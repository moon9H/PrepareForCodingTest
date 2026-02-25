import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        ArrayList<Integer>[] graph = new ArrayList[N + 1];
        int[] inDegree = new int[N + 1];
        for (int i = 0; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }
        int M = Integer.parseInt(st.nextToken());
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int numSinger = Integer.parseInt(st.nextToken());
            int[] singers = new int[numSinger];
            for (int j = 0; j < numSinger; j++) {
                singers[j] = Integer.parseInt(st.nextToken());
            }

            for (int j = 0; j < numSinger - 1; j++) {
                graph[singers[j]].add(singers[j + 1]);
                inDegree[singers[j + 1]]++;
            }
        }

        Queue<Integer> queue = new ArrayDeque<>();
        for (int i = 1; i <= N; i++) {
            if (inDegree[i] == 0) {
                queue.add(i);
            }
        }
        ArrayList<Integer> answer = new ArrayList<>();
        while (!queue.isEmpty()) {
            int curSinger = queue.poll();
            answer.add(curSinger);
            for (int i : graph[curSinger]) {
                if (--inDegree[i] == 0) queue.add(i);
            }
        }

        if (answer.size() != N) {
            System.out.println(0);
        } else {
            for (Integer i : answer) {
                System.out.println(i);
            }
        }
    }
}