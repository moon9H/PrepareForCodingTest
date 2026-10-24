class Solution {
    public int solution(int[] elements) {
        int answer = 1, max = 0;
        
        for (int i : elements) max += i;
        
        boolean visited[] = new boolean[max + 1];
        visited[max] = true;
        
        for (int size = 1; size < elements.length; size++) {
            for (int i = 0; i < elements.length; i++) {
                int sum = elements[i];
                for (int j = 1; j < size; j++) {
                    sum += elements[(i + j) % elements.length];
                }

                if (!visited[sum]) {
                    answer++;
                    visited[sum] = true;
                }
            }    
        }
        return answer;
    }
}