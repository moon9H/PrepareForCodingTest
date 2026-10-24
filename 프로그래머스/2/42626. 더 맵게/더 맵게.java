import java.util.*;

class Solution {
    public int solution(int[] scoville, int K) {
        int answer = 0;
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        
        for (int i : scoville) minHeap.add(i);
        
        while(minHeap.peek() < K && minHeap.size() > 1) {
            int one = minHeap.poll();
            int two = minHeap.poll();
            minHeap.add(one + two * 2);
            ++answer;
        }
        if (minHeap.size() == 1 && minHeap.peek() < K) return -1;
        return answer;
    }
}