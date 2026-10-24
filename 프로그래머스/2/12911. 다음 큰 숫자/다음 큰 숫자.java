class Solution {
    public int solution(int n) {
        int answer = 0;
        
        int i = 1;
        
        int n_one = makeToBinary(n);
        
        while (n_one != makeToBinary(n + i)){
            i++;
        }
        
        answer = n + i;
        
        return answer;
    }
    
    private int makeToBinary(int num) {
        int one_cnt = 0;
        while(num / 2 > 0) {
            if (num % 2 == 1) ++one_cnt;
            num /= 2;
        }
        
        if (num == 1) ++one_cnt;
        
        return one_cnt;
    }
}