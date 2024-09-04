import sys
input = sys.stdin.readline
INF = float('inf')

def maxsum(n, A) :
    curs, maxs = -INF, -INF
    for i in range(n) :
        curs = max(curs, 0) + A[i]
        maxs = max(maxs, curs)
    return maxs

n = int(input())
arr = list(map(int, input().split()))
print(maxsum(n, arr))