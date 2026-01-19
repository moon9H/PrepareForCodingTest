class Solution {
    public int solution(int a, int b) {
        int answer = 0;
        String strAnswer1 = Integer.toString(a) + Integer.toString(b);
        String strAnswer2 = Integer.toString(b) + Integer.toString(a);
        answer = (Integer.parseInt(strAnswer1) > Integer.parseInt(strAnswer2)) ? 
            Integer.parseInt(strAnswer1) : Integer.parseInt(strAnswer2);
        return answer;
    }
}