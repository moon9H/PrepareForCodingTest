import sys
input = sys.stdin.readline

def sol(N, K, arr) :
    psum = arr + [0]
    for i in range(N) :
        psum[i] += psum[i - 1]
    maxs = float('-inf')
    for i in range(K - 1, N) :
        curs = psum[i] - psum[i - K]
        maxs = max(maxs, curs)
    return maxs

N, K = map(int, input().split())
arr = list(map(int, input().split()))
print(sol(N, K, arr))