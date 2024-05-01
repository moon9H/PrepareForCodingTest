import sys
input = sys.stdin.readline

def isEmpty(st) :
    if len(st) == 0:
        return True
    else :
        return False

N = int(input())

sequence = []
answer = []

for i in range(N):
    sequence.append(int(input()))

stack = []
num = 0
index = 0

while (num <= N and index != N) :
    if isEmpty(stack) :
        answer.append('+')
        stack.append(num + 1)
        num = num + 1
        continue

    if stack[-1] == sequence[index] :
        answer.append('-')
        stack.pop()
        index += 1
    else :
        answer.append('+')
        stack.append(num + 1)
        num = num + 1

if N != num :
    print('NO')
else :
    for i in answer:
        print(i)