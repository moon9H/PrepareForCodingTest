import sys
input = sys.stdin.readline
MOD = 10007

N, K = map(int, input().split())
cache = [[0] * (i + 1) for i in range(N + 1)]

for i in range(N + 1):
    cache[i][0] = 1  # nC0 = 1
    cache[i][i] = 1  # nCn = 1

for n in range(2, N + 1):
    for k in range(1, n):
        cache[n][k] = (cache[n - 1][k - 1] + cache[n - 1][k]) % MOD

print(cache[N][K])