import sys
input = sys.stdin.readline

N, K = map(int, input().split())
coins = []
for _ in range(N) :
    coin = int(input())
    coins.append(coin)
need_coin = 0

for i in range(N - 1, -1, -1) :
    cnt = K // coins[i]
    K %= coins[i]
    need_coin += cnt

print(need_coin)