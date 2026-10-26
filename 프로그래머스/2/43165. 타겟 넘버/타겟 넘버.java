class Solution {
    public int solution(int[] numbers, int target) {
        return dfs(numbers, target, 0, 0);
    }
    
    private int dfs(int[] nums, int target, int sum, int idx) {
        if (idx == nums.length) return sum == target ? 1 : 0;
        
        return dfs(nums, target, sum + nums[idx], idx + 1) + 
            dfs(nums, target, sum - nums[idx], idx + 1);
    }
}