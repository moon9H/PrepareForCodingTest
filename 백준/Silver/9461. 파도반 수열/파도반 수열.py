import sys
input = sys.stdin.readline

P = [0] * 101
P[0 : 7] = [1, 1, 1, 2, 2, 3, 4, 5]
for i in range(8, 102) :
    P[i] = P[i - 5] + P[i - 1]
T = int(input())

for _ in range(T) :
    N = int(input())
    print(P[N - 1])