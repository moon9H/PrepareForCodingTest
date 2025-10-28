dp = [1 for _ in range(31)]
for i in range(2, 31) :
    dp[i] = dp[i - 1] + 2 *dp[i - 2]
T = int(input())
for test_case in range(1, T + 1):
    N = int(input())
    print(f"#{test_case} {dp[N // 10]}")