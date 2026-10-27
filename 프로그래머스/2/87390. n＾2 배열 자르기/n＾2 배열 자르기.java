class Solution {
    public int[] solution(int n, long left, long right) {
        int[] answer = new int[(int) (right - left + 1)];

        for (long k = left; k <= right; k++) {
            long row = k / n;
            long col = k % n;
            answer[(int) (k - left)] = (int) Math.max(row, col) + 1;
        }

        return answer;
    }
}