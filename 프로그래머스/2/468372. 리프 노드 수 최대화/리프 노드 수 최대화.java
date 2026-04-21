class Solution {
    long D;
    long[] pow2 = new long[35];
    long[] pow3 = new long[25];
    int len2, len3;

    public int solution(int dist_limit, int split_limit) {
        D = dist_limit;
        long S = split_limit;

        buildPowers(S);

        long answer = 1; // 분배 노드를 하나도 안 쓰면 리프는 1개

        for (int a = 0; a < len2; a++) {
            if (pow2[a] > S) break;

            for (int b = 0; b < len3; b++) {
                if (pow2[a] > S / pow3[b]) break;
                answer = Math.max(answer, bestFor(a, b));
            }
        }

        return (int) answer;
    }

    // 2^a, 3^b 미리 구해두기
    private void buildPowers(long S) {
        pow2[0] = 1;
        len2 = 1;
        while (pow2[len2 - 1] <= S / 2) {
            pow2[len2] = pow2[len2 - 1] * 2;
            len2++;
        }

        pow3[0] = 1;
        len3 = 1;
        while (pow3[len3 - 1] <= S / 3) {
            pow3[len3] = pow3[len3 - 1] * 3;
            len3++;
        }
    }

    // 앞에 2가 a번, 뒤에 3이 b번 오는 구조에서 최대 리프 수
    private long bestFor(int a, int b) {
        // 아무 것도 안 하는 경우
        if (a == 0 && b == 0) return 1;

        // 3만 있는 경우
        if (a == 0) {
            long maxThreeNodes = (pow3[b] - 1) / 2; // 1 + 3 + ... + 3^(b-1)
            return 1 + 2 * Math.min(D, maxThreeNodes);
        }

        long maxTwoNodes = pow2[a] - 1; // 1 + 2 + ... + 2^(a-1)

        // 2만 있는 경우
        if (b == 0) {
            return 1 + Math.min(D, maxTwoNodes);
        }

        long upperD2 = Math.min(D, maxTwoNodes);
        long k = pow3[b] - 1; // t 하나당 만들 수 있는 최대 3-분기 노드 수

        // d2를 많이 써도 아직 3-구간 용량이 부족하다면, 그냥 2-구간을 최대로 채우는 게 최선
        if (!isSaturated(a, k, upperD2)) {
            long d2 = upperD2;
            long t = maxT(a, d2);
            long d3 = Math.min(D - d2, k * t);
            return 1 + d2 + 2 * d3;
        }

        // 경계점: d2 + k * t(d2) >= D 를 처음 만족하는 최소 d2
        long lo = 0;
        long hi = upperD2;
        while (lo < hi) {
            long mid = (lo + hi) / 2;
            if (isSaturated(a, k, mid)) hi = mid;
            else lo = mid + 1;
        }

        long d2 = lo;
        long d3 = D - d2; // 이 지점에선 남은 예산 전부를 3-구간에 쓸 수 있음
        return 1 + d2 + 2 * d3;
    }

    // d2개를 2-구간에 썼을 때, 남은 예산을 모두 3-구간에 태울 수 있는지
    private boolean isSaturated(int a, long k, long d2) {
        long t = maxT(a, d2);
        return d2 + k * t >= D;
    }

    // 2-구간에 d2개를 썼을 때, 마지막 2-깊이의 분배 노드 최대 개수 t
    private long maxT(int a, long d2) {
        long lo = 0;
        long hi = pow2[a - 1]; // 마지막 2-깊이 최대 노드 수

        while (lo < hi) {
            long mid = (lo + hi + 1) / 2;
            if (needTwo(a, mid) <= d2) lo = mid;
            else hi = mid - 1;
        }

        return lo;
    }

    // 마지막 2-깊이에 t개를 만들기 위한 2-구간 최소 분배 노드 수
    private long needTwo(int a, long t) {
        long sum = 0;
        long cur = t;

        for (int i = 0; i < a; i++) {
            sum += cur;
            cur = (cur + 1) / 2; // ceil(cur / 2)
        }

        return sum;
    }
}