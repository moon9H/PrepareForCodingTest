import java.util.ArrayList;
import java.util.Arrays;

class Solution {
    public static final int[] discout = new int[] {10, 20, 30, 40};
    public static int emoticonLength, maxPrice, plusMan;
    public static int[] emti;
    public static int[][] user;
    public static ArrayList<Integer> disc;
        
    public int[] solution(int[][] users, int[] emoticons) {
        disc = new ArrayList<>();
        emti = emoticons;
        emoticonLength = emoticons.length;
        maxPrice = Integer.MIN_VALUE;
        plusMan = Integer.MIN_VALUE;
        user = users;

        dfs(0);

        return new int[] {plusMan, maxPrice};
    }
    
    static void dfs(int count){
        if (count == emoticonLength) {
            int[] userSum = new int[user.length];
            for (int i = 0; i < emoticonLength; i++) {
                int salePercent = disc.get(i);
                int price = (emti[i] * (100 - salePercent)) / 100;
                for (int j = 0; j < user.length; j++) {
                    if (salePercent >= user[j][0]){
                        userSum[j] += price;
                    }
                }
            }
            int totalSum = 0;
            int emtiPlus = 0;
            for (int i = 0; i < user.length; i++) {
                if (userSum[i] >= user[i][1]) ++emtiPlus;
                else totalSum += userSum[i];
            }
            
            if (emtiPlus > plusMan){
                plusMan = emtiPlus;
                maxPrice = totalSum;
            }
            
            if (emtiPlus == plusMan) {
                maxPrice = Math.max(maxPrice, totalSum);    
            }
            
            return;
        }

        for (int i = 0; i < 4; i++){
            disc.add(discout[i]);
            dfs(count + 1);
            disc.remove(disc.size() - 1);
        }
    }
}