class Solution {
    public int solution(int[] citations) {
        int answer = 0;
        for (int h = citations.length; h >= 0; h--) {
            int moreThanH = 0;
            int lessThanH = 0;
            for (int i = 0; i < citations.length; i++) {
                if (citations[i] >= h) ++moreThanH;
                if (citations[i] <= h) ++lessThanH;
            }
            if (moreThanH >= h && lessThanH <= h) {
                answer = h;
                break;
            }
        }
        return answer;
    }
}