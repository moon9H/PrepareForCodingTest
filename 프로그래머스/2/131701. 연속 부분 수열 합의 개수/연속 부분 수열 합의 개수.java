class Solution {
    public int solution(int[] elements) {
        int n = elements.length, total = 0;
        for (int e : elements) total += e;

        boolean[] visited = new boolean[total + 1];
        int answer = 0;

        for (int i = 0; i < n; i++) {
            int sum = 0;
            for (int len = 0; len < n; len++) {
                sum += elements[(i + len) % n];
                if (!visited[sum]) {
                    visited[sum] = true;
                    answer++;
                }
            }
        }
        return answer;
    }
}