import sys
from collections import deque
input = sys.stdin.readline

dq = deque()
N = int(input())
ds_list = list(map(int,input().split()))
in_list = list(map(int,input().split()))
M = int(input())
input_list = list(map(int,input().split()))

for i in range(N) :
    if (not ds_list[i]) * in_list[i] :
        dq.append((ds_list[i] + 1) * in_list[i])

def queuestack(dq, num) :
    dq.appendleft(num)
    num = dq.pop()
    return num

for i in range(M) :
    print(queuestack(dq,input_list[i]),end=" ")

print()