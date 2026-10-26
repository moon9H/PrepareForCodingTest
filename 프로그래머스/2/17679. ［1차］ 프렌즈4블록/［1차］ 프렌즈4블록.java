import java.util.*;

class Solution {
    private static boolean[][] remove;
    public int solution(int m, int n, String[] board) {
        int answer = 0;
        
        remove = new boolean[m][n];
        
        while(true) {
            // 지워지는 블럭 마킹 및 개수 카운트
            int count = isThereRemove(board);
            if (count == 0) break;
            else {
                // 지워지는 블럭 개수 카운트
                answer += count;
                
                for (int i = 0; i < board.length; i++) {
                    char[] line = board[i].toCharArray();
                    
                    for (int j = 0; j < board[i].length(); j++) {
                        if (remove[i][j]) line[j] = 'X';
                    }
                    board[i] = String.valueOf(line);
                }
                
                // 열마다: 아래에서부터 남은 블록만 새 배열에 차곡차곡
                char[][] next = new char[m][n];
                for (char[] row : next) Arrays.fill(row, 'X');

                for (int j = 0; j < n; j++) {
                    int write = m - 1;
                    for (int i = m - 1; i >= 0; i--) {
                        if (board[i].charAt(j) != 'X') next[write--][j] = board[i].charAt(j);
                    }
                }
                
                for (int i = 0; i < m; i++) board[i] = String.valueOf(next[i]);
                
                remove = new boolean[m][n];
            }
        }
        
        
        return answer;
    }
    
    private int isThereRemove(String[] board) {
        int needToRemove = 0;
        
        for (int i = 0; i < board.length - 1; i++) {
            for (int j = 0; j < board[i].toCharArray().length - 1; j++) {
                if (board[i].charAt(j) == 'X') continue;
                
                if (board[i].charAt(j) == board[i + 1].charAt(j) &&
                    board[i].charAt(j) == board[i].charAt(j + 1) &&
                    board[i].charAt(j) == board[i + 1].charAt(j + 1)) {
                    
                    if (!remove[i][j]) {
                        remove[i][j] = true;
                        ++needToRemove;
                    }
                    
                    if (!remove[i + 1][j]) {
                        remove[i + 1][j] = true;
                        ++needToRemove;
                    }
                    
                    if (!remove[i][j + 1]) {
                        remove[i][j + 1] = true;
                        ++needToRemove;
                    }
                    
                    if (!remove[i + 1][j + 1]) {
                        remove[i + 1][j + 1] = true;
                        ++needToRemove;
                    }
                }
            }
        }
        
        return needToRemove;
    }
}