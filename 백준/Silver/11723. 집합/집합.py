import sys
input = sys.stdin.readline

M = int(input())
S = set()
S = [0] * 20

for _ in range(M) :
    cmds = list(input().split())
    command = cmds[0]
    if len(cmds) == 1 :
        if command == 'all' :
            for i in range(20) :
                S[i] = 1
        elif command == 'empty' :
            for i in range(20) :
                S[i] = 0
    elif len(cmds) == 2 :
        param = int(cmds[1])
        if command == 'add' :
            S[param - 1] = 1
        elif command == 'remove' :
            S[param - 1] = 0
        elif command == 'check' :
            if S[param - 1] : print(1)
            else : print(0)
        elif command == 'toggle' :
            if S[param - 1] : S[param - 1] = 0
            else : S[param - 1] = 1