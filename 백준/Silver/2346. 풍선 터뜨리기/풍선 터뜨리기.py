import sys
from collections import deque

input = sys.stdin.readline

N = int(input())
dq = deque(map(int,input().split()))
dq2 = deque([])
for i in range(N) :
    intToList = [dq.popleft()]
    dq2.append(intToList)

index = 1
for i in dq2 :
    i.append(index)
    index += 1

for i in range(N) :
    ballon = dq2.popleft()
    K = ballon[0]
    print(ballon[1], end = " ")
    if K > 0 :
        dq2.rotate(1-K)
    else :
        dq2.rotate(-(K))
print()