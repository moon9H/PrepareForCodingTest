import java.util.*;

class Solution {
    private Set<Integer> set = new HashSet<>();
    private boolean[] used;

    public int solution(String numbers) {
        used = new boolean[numbers.length()];
        dfs(numbers, "");

        int count = 0;
        for (int n : set) if (isPrime(n)) count++;
        return count;
    }

    private void dfs(String numbers, String cur) {
        if (!cur.isEmpty()) set.add(Integer.parseInt(cur));

        for (int i = 0; i < numbers.length(); i++) {
            if (used[i]) continue;
            used[i] = true;
            dfs(numbers, cur + numbers.charAt(i));
            used[i] = false;
        }
    }

    private boolean isPrime(int a) {
        if (a < 2) return false;
        for (int i = 2; (long) i * i <= a; i++)
            if (a % i == 0) return false;
        return true;
    }
}