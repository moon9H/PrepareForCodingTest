import java.util.*;

class Solution
{
    public int solution(String s)
    {
        if (s.length() % 2 != 0) return 0;
        
        Deque<Character> stack = new ArrayDeque<>();
        
        for (int i = 0; i < s.length(); i++) {
            if (stack.isEmpty()) {
                stack.push(s.charAt(i));
                continue;
            }
            
            if (stack.peek() == s.charAt(i)) stack.pop();
            
            else stack.push(s.charAt(i));
        }
        
        if (stack.isEmpty()) return 1;

        return 0;
    }
}