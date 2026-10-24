class Solution {
    public int[] solution(int brown, int yellow) {
        int[] answer = new int[2];
        
        int height = 1;
        
        while(yellow % height != 0 ||
              (yellow / height) * 2 + (height + 2) * 2 != brown) height++;
        
        answer[0] = (brown + yellow) / (height + 2);
        answer[1] = height + 2;
        
        return answer;
    }
}