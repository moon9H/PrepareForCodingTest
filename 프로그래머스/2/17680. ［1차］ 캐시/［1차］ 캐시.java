import java.util.*;

class Solution {
    public int solution(int cacheSize, String[] cities) {
        int answer = 0;
        Deque<String> dq = new ArrayDeque<>();
        
        for (String s : cities) {
            String city = s.toLowerCase();
            if (dq.remove(city)) {   // remove는 지웠으면 true를 돌려줘요
                dq.offer(city);
                answer += 1;
            } else {
                dq.offer(city);
                if (dq.size() > cacheSize) dq.pollFirst();
                answer += 5;
            }
        }
        return answer;
    }
}