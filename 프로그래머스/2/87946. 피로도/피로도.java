class Solution {
    public int solution(int k, int[][] dungeons) {
        return dfs(dungeons, new boolean[dungeons.length], k);
    }

    // 현재 피로도로 앞으로 더 갈 수 있는 최대 던전 수
    private int dfs(int[][] dungeons, boolean[] visited, int stamina) {
        int best = 0;
        for (int i = 0; i < dungeons.length; i++) {
            if (visited[i] || stamina < dungeons[i][0]) continue;
            visited[i] = true;
            best = Math.max(best, 1 + dfs(dungeons, visited, stamina - dungeons[i][1]));
            visited[i] = false;   // 되돌리기 (백트래킹)
        }
        return best;
    }
}