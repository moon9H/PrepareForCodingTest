import sys
input = sys.stdin.readline

N, M = map(int, input().split())
treeHeights = list(map(int, input().split()))

start, end = 0, max(treeHeights)
answer = 0

while(start <= end) :
    mid = (start + end) // 2
    cutted = 0

    for i in treeHeights :
        if i > mid :
            cutted += (i - mid)
        if cutted >= M :
            break
    
    if cutted >= M :
        answer = mid
        start = mid + 1
    else :
        end = mid - 1

print(answer)