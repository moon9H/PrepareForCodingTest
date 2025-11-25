import sys
input = sys.stdin.readline
import heapq

N = int(input())
h = []

for _ in range(N):
    x = int(input())
    if x == 0:
        if h:
            print(heapq.heappop(h))
        else:
            print(0)
    else:
        heapq.heappush(h, x)