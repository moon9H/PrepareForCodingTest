from collections import deque
import sys

input = sys.stdin.readline
dq = deque()

N = int(input())
answers = []
for i in range(N) :
    oper = list(input().rstrip().split())
    
    if oper[0] == 'push_front' :
        dq.appendleft(oper[1])
    elif oper[0] == 'push_back' :
        dq.append(oper[1])
    elif oper[0] == 'pop_front' :
        if len(dq) == 0 :
            answers.append(-1)
        else :
            answers.append(dq.popleft())
    elif oper[0] == 'pop_back' :
        if len(dq) == 0 :
            answers.append(-1)
        else :
            answers.append(dq.pop())
    elif oper[0] == 'size' :
        answers.append(len(dq))
    elif oper[0] == 'empty' :
        if len(dq) == 0 :
            answers.append(1)
        else :
            answers.append(0)
    elif oper[0] == 'front' :
        if len(dq) == 0 :
            answers.append(-1)
        else :
            answers.append(dq[0])
    elif oper[0] == 'back' :
        if len(dq) == 0 :
            answers.append(-1)
        else :
            answers.append(dq[-1])

for ans in answers :
    print(ans)