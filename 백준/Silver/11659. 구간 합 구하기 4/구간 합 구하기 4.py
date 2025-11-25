import sys
input = sys.stdin.readline

N, tc = map(int, input().split())
numList = list(map(int, input().split()))
dp = [0] * N
dp[0] = numList[0]
for i in range(1, N) :
    dp[i] += (dp[i - 1] + numList[i])

for _ in range(tc) :
    start_idx, end_idx = map(int, input().split())
    if start_idx == end_idx :
        print(numList[start_idx - 1])
    else :
        if start_idx == 1 :
            print(dp[end_idx - 1])
        else :
            print(dp[end_idx - 1] - dp[start_idx - 2])