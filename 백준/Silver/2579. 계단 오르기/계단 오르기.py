import sys
input = sys.stdin.readline

numStair = int(input())
stairs = [0]
for _ in range(numStair) : stairs.append(int(input()))

dp = [0] * (numStair + 1)
if numStair >= 1 :
    dp[1] = stairs[1]
if numStair >= 2 :
    dp[2] = stairs[1] + stairs[2]
if numStair >= 3 :
    dp[3] = max(stairs[1] + stairs[3], stairs[2] + stairs[3])

for i in range(4, numStair + 1) :
    dp[i] = max(dp[i - 2] + stairs[i], dp[i - 3] + stairs[i - 1] + stairs[i])

print(dp[numStair])