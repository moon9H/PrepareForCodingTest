class Solution {
    public int solution(int[][] cost, int[][] hint) {
        int n = cost.length;
        int bundleCount = n - 1;
        long answer = Long.MAX_VALUE;

        for (int mask = 0; mask < (1 << bundleCount); mask++) {
            int[] cnt = new int[n];
            long total = 0;

            // 어떤 번들을 샀는지 반영
            for (int i = 0; i < bundleCount; i++) {
                if ((mask & (1 << i)) == 0) continue;

                total += hint[i][0]; // 번들 가격

                for (int j = 1; j < hint[i].length; j++) {
                    int stageNum = hint[i][j] - 1; // 1-based -> 0-based
                    cnt[stageNum]++;
                }
            }

            // 각 스테이지 해결 비용 더하기
            for (int i = 0; i < n; i++) {
                int use = Math.min(cnt[i], n - 1);
                total += cost[i][use];
            }

            answer = Math.min(answer, total);
        }

        return (int) answer;
    }
}