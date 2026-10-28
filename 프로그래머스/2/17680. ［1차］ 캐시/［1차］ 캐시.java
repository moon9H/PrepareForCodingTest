import java.util.*;

class Solution {
    public int solution(int cacheSize, String[] cities) {
        int answer = 0;
        Deque<String> dq = new ArrayDeque<>();
        
        for (String s : cities) {
            if (dq.contains(s.toLowerCase())) {
                dq.remove(s.toLowerCase());
                dq.offer(s.toLowerCase());
                ++answer;
            } else {
                dq.offer(s.toLowerCase());
                if (dq.size() > cacheSize) dq.removeFirst();
                answer += 5;
            }
        }
        return answer;
    }
}