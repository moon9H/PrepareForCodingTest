def comb_dp(n, r) :
    dp = [[0]*(r + 1) for _ in range(n + 1)]
    for i in range(n + 1) :
        for j in range(min(i, r) + 1) :
            if i == 0 or j == 0 :
                dp[i][j] = 1
            else :
                dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j]
    
    return dp[n][r]
    
T = int(input())
for test_case in range(1, T + 1):
    n, a, b = map(int, input().split())
    small = a if a < b else b
    print(f"#{test_case} {comb_dp(n, small)}")