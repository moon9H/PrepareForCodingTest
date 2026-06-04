import java.io.*;
import java.util.*;

public class Main {

    static int N;
    static TreeMap<Integer, Integer> lamps = new TreeMap<>();
    static PriorityQueue<Gap> pq = new PriorityQueue<>();
    static int[] idToPos;
    static int nextId;

    static class Gap implements Comparable<Gap> {
        int left;
        int right;
        int dist;

        Gap(int left, int right) {
            this.left = left;
            this.right = right;
            this.dist = right - left;
        }

        @Override
        public int compareTo(Gap o) {
            // 간격이 큰 것 우선
            if (this.dist != o.dist) {
                return Integer.compare(o.dist, this.dist);
            }

            // 간격이 같으면 왼쪽 좌표가 작은 것 우선
            return Integer.compare(this.left, o.left);
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int Q = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());

        int cmd = Integer.parseInt(st.nextToken()); // 100
        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        idToPos = new int[M + Q + 5];

        for (int i = 1; i <= M; i++) {
            int pos = Integer.parseInt(st.nextToken());

            lamps.put(pos, i);
            idToPos[i] = pos;
        }

        nextId = M + 1;

        // 초기 간격 넣기
        Integer prev = null;

        for (int pos : lamps.keySet()) {
            if (prev != null) {
                pq.add(new Gap(prev, pos));
            }

            prev = pos;
        }

        for (int i = 1; i < Q; i++) {
            st = new StringTokenizer(br.readLine());

            cmd = Integer.parseInt(st.nextToken());

            if (cmd == 200) {
                addLamp();
            } else if (cmd == 300) {
                int id = Integer.parseInt(st.nextToken());
                deleteLamp(id);
            } else if (cmd == 400) {
                sb.append(getAnswer()).append('\n');
            }
        }

        System.out.print(sb);
    }

    static void addLamp() {
        cleanPQ();

        Gap gap = pq.poll();

        int left = gap.left;
        int right = gap.right;

        // 가운데 좌표
        // 거리 홀수면 올림
        int mid = left + (right - left + 1) / 2;

        int id = nextId++;

        lamps.put(mid, id);
        idToPos[id] = mid;

        // 기존 간격을 두 개로 쪼갬
        pq.add(new Gap(left, mid));
        pq.add(new Gap(mid, right));
    }

    static void deleteLamp(int id) {
        int pos = idToPos[id];

        Integer left = lamps.lowerKey(pos);
        Integer right = lamps.higherKey(pos);

        lamps.remove(pos);
        idToPos[id] = 0;

        // 삭제한 가로등 양쪽에 가로등이 있으면 새 간격 추가
        if (left != null && right != null) {
            pq.add(new Gap(left, right));
        }
    }

    static int getAnswer() {
        cleanPQ();

        int first = lamps.firstKey();
        int last = lamps.lastKey();

        int leftNeed = 2 * (first - 1);
        int rightNeed = 2 * (N - last);

        int middleNeed = 0;

        if (!pq.isEmpty()) {
            middleNeed = pq.peek().dist;
        }

        return Math.max(middleNeed, Math.max(leftNeed, rightNeed));
    }

    static void cleanPQ() {
        while (!pq.isEmpty()) {
            Gap gap = pq.peek();

            // 둘 중 하나라도 삭제된 가로등이면 버림
            if (!lamps.containsKey(gap.left) || !lamps.containsKey(gap.right)) {
                pq.poll();
                continue;
            }

            // gap.left 바로 오른쪽 가로등이 gap.right가 아니면
            // 중간에 다른 가로등이 생긴 것이므로 예전 간격임
            Integer next = lamps.higherKey(gap.left);

            if (next == null || next != gap.right) {
                pq.poll();
                continue;
            }

            break;
        }
    }
}