import sys
from collections import deque
input = sys.stdin.readline

N, K = map(int,input().split())
p_list = []
dq = deque([i for i in range(1,N+1)])
while(len(dq) != 0) :
    dq.rotate(-(K - 1))
    p_list.append(dq.popleft())

print("<",end="")

for i in range(N):
    if (i < N -1) :
        print(p_list[i], end=", ")
    else :
        print(p_list[i], end=">\n")