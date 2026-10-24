class Solution {
    public int solution(int n) {
        int answer = 0;
        
        for (int i = 1; i <= n / 2; i++) {
            if (dfs(i, 0, n)) ++answer;
        }
        
        return ++answer;
    }
    
    private boolean dfs(int n, int sum, int target) {
        if (sum ==  target) return true;
        
        if (sum > target) return false;
        
        return dfs(n + 1, sum + n, target);
    }
}