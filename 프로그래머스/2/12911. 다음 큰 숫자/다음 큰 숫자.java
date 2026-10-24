class Solution {
    public int solution(int n) {
        int target = Integer.bitCount(n);
        int next = n + 1;
        while (Integer.bitCount(next) != target) next++;
        return next;
    }
}