class Solution {
    int n;
    int[][] cost;
    int[][] hint;
    int answer = Integer.MAX_VALUE;

    public int solution(int[][] cost, int[][] hint) {
        this.n = cost.length;
        this.cost = cost;
        this.hint = hint;

        int[] hints = new int[n]; // 각 스테이지에서 사용할 수 있는 힌트 개수
        dfs(0, hints, 0);

        return answer;
    }

    void dfs(int stage, int[] hints, int sum) {
        // 현재 스테이지에서 쓸 수 있는 힌트는 무조건 다 쓰는 게 이득
        int use = Math.min(hints[stage], n - 1);
        sum += cost[stage][use];

        // 가지치기
        if (sum >= answer) return;

        // 마지막 스테이지면 종료
        if (stage == n - 1) {
            answer = Math.min(answer, sum);
            return;
        }

        // 1) 현재 스테이지에서 번들을 사지 않는 경우
        dfs(stage + 1, hints, sum);

        // 2) 현재 스테이지에서 번들을 사는 경우
        int[] nextHints = hints.clone();
        for (int i = 1; i < hint[stage].length; i++) {
            int target = hint[stage][i] - 1; // 문제 번호는 1번부터라서 -1
            nextHints[target]++;
        }

        dfs(stage + 1, nextHints, sum + hint[stage][0]);
    }
}