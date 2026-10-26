class Solution {
    private static int cnt = 0;
    public int solution(int[] numbers, int target) {
        dfs(numbers, target, 0, 0);
        return cnt;
    }
    
    private void dfs(int[] nums, int target, int sum, int idx) {
        if (idx == nums.length) {
            if (sum == target) ++cnt;
            return;
        }
        
        dfs(nums, target, sum + nums[idx], idx + 1);
        dfs(nums, target, sum - nums[idx], idx + 1);
    }
}