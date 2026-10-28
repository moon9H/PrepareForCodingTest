import java.util.*;

class Solution {
    public int[] solution(int[] prices) {
        int[] answer = new int[prices.length];
        
        // 가격 + 들어간 시점 저장하는 배열
        Deque<int[]> dq = new ArrayDeque<>();
        
        for (int i = 0; i < prices.length; i++) {
            if (dq.isEmpty()) {
                dq.add(new int[] {prices[i], i});
            } else {
                while (!dq.isEmpty() && dq.getLast()[0] > prices[i]) {
                    int[] fallPrice = dq.removeLast();
                    answer[fallPrice[1]] = i - fallPrice[1];
                }
                dq.add(new int[] {prices[i], i});
            }
            // for (int[] a : dq) System.out.print(Arrays.toString(a));
            // System.out.println();
        }
        
        while(!dq.isEmpty()) {
            int[] fallPrice = dq.removeLast();
            answer[fallPrice[1]] = prices.length - fallPrice[1] - 1;
        }
        
        return answer;
    }
}