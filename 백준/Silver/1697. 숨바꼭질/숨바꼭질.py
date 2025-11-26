import sys
input = sys.stdin.readline

from collections import deque
N, K = map(int, input().split())

if N >= K :
    print(N - K)
else :
    MAX = 100000
    dist = [-1] * (MAX + 1)
    q = deque([N])
    dist[N] = 0

    while q :
        x = q.popleft()
        if x == K :
            print(dist[K])
            break
        else :
            for nx in (x - 1, x + 1, 2*x) :
                if 0 <= nx <= MAX and dist[nx] == -1 :
                    q.append(nx)
                    dist[nx] = dist[x] + 1