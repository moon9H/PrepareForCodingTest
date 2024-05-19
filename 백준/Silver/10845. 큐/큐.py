from collections import deque
import sys
input = sys.stdin.readline

dq = deque()

numInstr = int(input())

answer_list = []
for _ in range(numInstr) :
    oper = list(input().rstrip().split(' '))
    
    if oper[0] == 'push' :
        dq.append(oper[1])
    
    elif oper[0] == 'front' :
        if len(dq) == 0 :
            answer_list.append(-1)
        else :
            answer_list.append(dq[0])

    elif oper[0] == 'back' :
        if len(dq) == 0 :
            answer_list.append(-1)
        else :
            answer_list.append(dq[-1])
    
    elif oper[0] == 'size' :
        answer_list.append(len(dq))
    
    elif oper[0] == 'empty' :
        if len(dq) == 0 :
            answer_list.append(1)
        else :
            answer_list.append(0)

    elif oper[0] == 'pop' :
        if len(dq) == 0 :
            answer_list.append(-1)
        else :
            answer_list.append(dq.popleft())

    else :
        print('wrong input!!!')

for answer in answer_list :
    print(answer)