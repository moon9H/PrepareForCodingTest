class Solution {
    public int solution(int n) {
        int answer = 0;
        
        int left = 1, right = 1, sum = 1;
        
        while (left <= n) {
            if (sum == n) ++answer;
            
            if (sum >= n) sum -=left++;
            else sum += ++right;
        }
        
        return answer;
    }
}